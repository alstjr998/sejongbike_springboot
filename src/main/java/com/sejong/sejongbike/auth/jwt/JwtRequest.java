package com.sejong.sejongbike.auth.jwt;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtRequest {
    // 기본 생성자
    public JwtRequest() {
    }

    // 모든 필드를 위한 생성자
    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
    private String username;
    private String password;
}
