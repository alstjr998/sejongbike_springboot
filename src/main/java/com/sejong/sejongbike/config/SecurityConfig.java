package com.sejong.sejongbike.config;

import com.sejong.sejongbike.auth.http.CustomAccessDeniedHandler;
import com.sejong.sejongbike.auth.http.CustomAuthenticationEntryPoint;
import com.sejong.sejongbike.auth.jwt.JwtAuthenticationFilter;
import com.sejong.sejongbike.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CorsConfigurationSource corsConfigurationSource;
    private final MemberService memberService;


    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          CorsConfigurationSource corsConfigurationSource,
                          MemberService memberService) {

        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.corsConfigurationSource = corsConfigurationSource;
        this.memberService = memberService;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .headers(headerConfig -> headerConfig
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/login").permitAll()  //토큰생성(로그인)
                        .requestMatchers("/logout").permitAll()   //토큰삭제(리프레시토큰까지 제거)

                        .requestMatchers("/").permitAll()

                        .requestMatchers("/notice").permitAll() //공지 조회
                        .requestMatchers("/notice/{id}").permitAll() //공지 조회
                        .requestMatchers("/notice/request").authenticated() //공지 등록, 수정, 삭제 요청

                        .requestMatchers("/mypage").authenticated() //내 정보 조회
                        .requestMatchers("/login/home").authenticated()

                        .requestMatchers("/member").hasRole("ADMIN") //회원 정보 조회
                        .requestMatchers("/login/admin").hasRole("ADMIN")

                        .requestMatchers("/message").authenticated() //메세지함 조회

                        .requestMatchers(request -> request.getServletPath().endsWith(".html")).permitAll()
                        .anyRequest().authenticated()
                )

                .httpBasic(httpBasic -> httpBasic.disable()) //httpBasic 비활성화 (JWT로 대체)

                .formLogin(formLogin -> formLogin.disable()) //formLogin 비활성화 (JWT로 대체)

                .logout(logout -> logout.disable()) //logout 비활성화 (JWT로 대체)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(accessDeniedHandler())   // 403 Forbidden 처리 관련
                        .authenticationEntryPoint(authenticationEntryPoint())   // 401 Unauthorized, 500 Internal Server Error 처리 관련
                )
//                .oauth2Login(oauth2 -> oauth2
//                        .clientRegistrationRepository(clientRegistrationRepository)
//                        .successHandler(oAuth2AuthenticationSuccessHandler)
//                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .csrf(csrf -> csrf.disable()); // CSRF 비활성화

        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }*/
}
