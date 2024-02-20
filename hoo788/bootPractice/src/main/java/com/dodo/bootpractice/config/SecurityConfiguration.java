package com.dodo.bootpractice.config;

import com.dodo.bootpractice.repository.MemberRepository;
import com.dodo.bootpractice.security.CustomAccessDeniedHandler;
import com.dodo.bootpractice.security.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    // 커스텀 접근 거부 예외 핸들러 주입
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // JDBC 인증을 위한 변경
//    private final DataSource dataSource;

    // 커스텀 userDetailsService 설정
    private final MemberRepository memberRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                // 세션 관리
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                .sessionConcurrency((sessionConcurrency) ->
                                        sessionConcurrency
                                                .maximumSessions(1)
                                                .expiredUrl("/login?expired")
                                )
                )
                // LogoutConfigurer
                .logout(logoutConfigurer -> {
                    logoutConfigurer.logoutSuccessUrl("/");
                    logoutConfigurer.addLogoutHandler((request, response, authentication) -> {
                                // LogoutFilter가 내부적으로 세션 무효화를 해줌.
                                HttpSession session = request.getSession();
                                if (session != null) {
                                    session.invalidate();
                                }
                    });
                })
                .authorizeHttpRequests((authz) -> authz
                        // delete method의 /member url에 어드민 권한만 접근하도록 제한
                        .requestMatchers(HttpMethod.DELETE, "/member").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                // exceptionHandling(()-> ...) : 예외 처리 핸들링.
                // accessDeniedHandler : 인가되지 않는 사용자 예외가 발생할때 처리하는 예외 핸들러
                .exceptionHandling((eh) -> eh.accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }

    // 이그노어링 : 시큐리티6 설정.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/static/**");
    }


    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


    // UserDetailsService를 사용해서 InMemoryUserDetailsManager 등록
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .passwordEncoder(passwordEncoder()::encode)
//                .password("1234")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .passwordEncoder(passwordEncoder()::encode)
//                .password("1234")
//                .roles("ADMIN")
//                .build();
//
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//
//        userDetailsManager.createUser(user);
//        userDetailsManager.createUser(admin);
//
//        return userDetailsManager;
//
//    }


    // jdbc 인증 설정
//    @Bean
//    public UserDetailsManager userDetailsManager() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("jdbc")
//                .password(passwordEncoder().encode("1234"))
//                .roles("USER")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.createUser(user);
//        return users;
//    }


    // Custom UserDetailsService 등록
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(memberRepository);
    }


}
