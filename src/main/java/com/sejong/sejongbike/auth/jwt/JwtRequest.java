package com.sejong.sejongbike.auth.jwt;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtRequest {
    private String username;
    private String password;
}
