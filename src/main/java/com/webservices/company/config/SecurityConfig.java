package com.webservices.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String USER_AUTHORITY = "DEFAULT";
    public static final String ADMIN_AUTHORITY = "ADMIN";
    public static final String EDITOR_AUTHORITY = "EDITOR";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password("$2a$12$R1XHhUf5H/1JYX6Kznwg7Ob.iaQX42MZ74NVRWNBzKcQ5jpFF8zvG")//userPass
                .authorities(USER_AUTHORITY)
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("$2a$12$3FzjCifisl44WHm2gZakUesXoC/gIg6.4xXBeqtxXtaA9/GgYTSUu")//adminPass
                .authorities(USER_AUTHORITY, ADMIN_AUTHORITY, EDITOR_AUTHORITY)
                .build();
        UserDetails editor = User.builder()
                .username("editor")
                .password("$2a$12$MocgVNIkZBmqun9/SillRe/FWbRnAVvi47dbZvM.exL7/w7CujAiq")//editorPass
                .authorities(EDITOR_AUTHORITY)
                .build();
        return new InMemoryUserDetailsManager(user, admin, editor);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService
    ) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET).hasAnyAuthority(USER_AUTHORITY, ADMIN_AUTHORITY)
                        .requestMatchers(HttpMethod.POST).hasAnyAuthority(ADMIN_AUTHORITY, EDITOR_AUTHORITY)
                        .requestMatchers(HttpMethod.PUT).hasAnyAuthority(ADMIN_AUTHORITY, EDITOR_AUTHORITY)
                        .requestMatchers(HttpMethod.DELETE).hasAnyAuthority(ADMIN_AUTHORITY, EDITOR_AUTHORITY)
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService())

        ;
        DefaultSecurityFilterChain build = http.build();
        return build;

    }

}
