package com.sejong.sejongbike.apicontroller;

import com.sejong.sejongbike.auth.jwt.JwtProvider;
import com.sejong.sejongbike.auth.jwt.JwtRequest;
import com.sejong.sejongbike.entity.Member;
import com.sejong.sejongbike.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@Slf4j
public class JwtController {
    private final JwtProvider jwtProvider;
    private final MemberService memberService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtController(JwtProvider jwtProvider, MemberService memberService) {
        this.jwtProvider = jwtProvider;
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) {
        try {
            Member member = memberService.findByEmail(jwtRequest.getUsername());

            if (passwordEncoder.matches(jwtRequest.getPassword(), member.getPassword())) {
                // 비밀번호가 일치하면 JWT 토큰 생성
                final String token = jwtProvider.createToken(jwtRequest.getUsername());

                // JWT 토큰을 응답 헤더로 추가
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + token);

                // JWT 리프레시 토큰 생성
                final String refreshToken = jwtProvider.createRefreshToken(jwtRequest.getUsername());
                Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
                refreshTokenCookie.setHttpOnly(true);
                refreshTokenCookie.setPath("/refresh-token");
                response.addCookie(refreshTokenCookie);

                return ResponseEntity.ok().headers(headers).build();
            } else {
                // 비밀번호가 일치하지 않으면 인증 실패 로직 수행
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
            }
        } catch (UsernameNotFoundException e) {
            // 사용자명이 존재하지 않으면 인증 실패 로직 수행
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader, HttpServletResponse response) {
        // 리프레시 토큰 무효화 (쿠키 삭제)
        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/refresh-token");
        refreshTokenCookie.setMaxAge(0); // 쿠키 삭제
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String refreshToken = jwtProvider.resolveRefreshToken(request);
        if (refreshToken != null && jwtProvider.validateToken(refreshToken)) {
            String username = jwtProvider.getUsernameFromToken(refreshToken);
            String newAccessToken = jwtProvider.createToken(username);
            return ResponseEntity.ok().body(Collections.singletonMap("accessToken", newAccessToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token is invalid or expired");
        }
    }
}
