package com.sejong.sejongbike.auth.jwt;

import com.sejong.sejongbike.constant.Role;
import com.sejong.sejongbike.entity.Member;
import com.sejong.sejongbike.service.MemberService;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {
    private final MemberService memberService;
    @Value("${jwt.secret:your-very-long-secret-key-that-is-at-least-64-bytes-long-0123456789abcdef0123456789abcdef}")
    private String secret;
    @Value("${jwt.expiration:600000}") //1시간:3600000, 30초:30000
    private long expiration;
    @Value("${jwt.expiration:3600000}") //1시간:3600000, 30초:30000
    private long refreshExpiration;
    private SecretKey secretKey;

    public JwtProvider(@Lazy MemberService memberService) {
        this.memberService = memberService;
    }

    @PostConstruct
    protected void init() {
        if (secret.length() < 64) {
            throw new IllegalArgumentException("The secret key length must be at least 64 bytes for HS512 algorithm");
        }
        this.secretKey = new SecretKeySpec(secret.getBytes(), Jwts.SIG.HS512.key().build().getAlgorithm());
    }

    //액세스 토큰
    public String createToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + refreshExpiration);

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token, HttpServletRequest request) {
        String usernameFromToken = getUsernameFromToken(token);
        Member member = memberService.findByEmail(usernameFromToken);
        Set<GrantedAuthority> roleSet = Set.of(new SimpleGrantedAuthority("ROLE_" + member.getRole().toString()));

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                member.getEmail(),
                member.getPassword(),
                roleSet
        );
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authentication;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token);
        } catch (JwtException jwtException) {
            // parseSignedClaims 수행 시 인증이 만료된 경우 JwtException 발생함
            return false;
        }

        return true;
    }

    public String resolveRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
