package com.sejong.sejongbike.apicontroller;

import com.sejong.sejongbike.auth.jwt.JwtProvider;
import com.sejong.sejongbike.auth.jwt.JwtRequest;
import com.sejong.sejongbike.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class JwtController {
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    public JwtController(JwtProvider jwtProvider, MemberService memberService) {
        this.jwtProvider = jwtProvider;
        this.memberService = memberService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest, HttpServletResponse response) {

        memberService.findByEmail(jwtRequest.getUsername());

        // JWT 토큰 생성
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
    }

    @GetMapping("/invalidate-token")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String authorizationHeader, HttpServletResponse response) {
        // 리프레시 토큰 무효화 (쿠키 삭제)
        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/refresh-token");
        refreshTokenCookie.setMaxAge(0); // 쿠키 삭제
        response.addCookie(refreshTokenCookie);

        return ResponseEntity.ok().build();
    }
}
