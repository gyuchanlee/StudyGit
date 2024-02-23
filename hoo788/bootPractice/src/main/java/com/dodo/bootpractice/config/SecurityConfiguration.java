package com.dodo.bootpractice.config;

import com.dodo.bootpractice.repository.MemberRepository;
import com.dodo.bootpractice.security.CustomAccessDeniedHandler;
import com.dodo.bootpractice.security.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
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
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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
                // HTTPS를 강제하도록 변경
//                .requiresChannel(channelRequestMatcherRegistry ->
//                        channelRequestMatcherRegistry.anyRequest().requiresSecure())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(form -> form
                        .loginPage("/login") // GET
                        .loginProcessingUrl("/login") // POST
                        .successForwardUrl("/login_success") // POST
//                        .failureForwardUrl("/login_failure") // POST
                        .permitAll()
                )
                // 세션 관리 기능
                .sessionManagement((sessionManagement) ->
                        sessionManagement
                                // 세션 동시성 관련 기능
                                .sessionConcurrency((sessionConcurrency) ->
                                        sessionConcurrency
                                                //  최대 허용 가능 세션 수를 정할 수 있다. 만약 -1을 넣는다면 무제한 로그인 세션을 허용한다는 의미
                                                .maximumSessions(1)
                                                // 최초 로그인 후, 로그아웃 하고 다시 로그인이 안되는 현상 원인
                                                // 기본값은 false(기존 세션 만료), true : 사용자의 인증이 실패한다(동시 로그인 차단)
                                                .maxSessionsPreventsLogin(true)
                                                .expiredUrl("/login?expired")
                                                .sessionRegistry(sessionRegistry())
                                )
                )
                // LogoutConfigurer
                .logout(logoutConfigurer -> {
                    logoutConfigurer.logoutSuccessUrl("/");
                    logoutConfigurer.invalidateHttpSession(true); // 세션 무효화 설정
                    logoutConfigurer.deleteCookies("JSESSIONID");
                })
                .authorizeHttpRequests((authz) -> authz
                        // delete method의 /member url에 어드민 권한만 접근하도록 제한
                        .requestMatchers(HttpMethod.DELETE, "/member").hasRole("ADMIN")
                        .requestMatchers("/user").hasRole("USER")
                        .requestMatchers("/user2").hasAnyRole("USER2", "ADMIN")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/resources/static/**", "/", "/member/join", "/member/paging", "/member/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/member").permitAll()
//                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                // exceptionHandling(()-> ...) : 예외 처리 핸들링.
                // accessDeniedHandler : 인가되지 않는 사용자 예외가 발생할때 처리하는 예외 핸들러
                .exceptionHandling((eh) -> eh.accessDeniedHandler(customAccessDeniedHandler));
        return http.build();
    }

    // 이그노어링 : 시큐리티6 설정.
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/resources/static/**");
//    }


    @Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
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
