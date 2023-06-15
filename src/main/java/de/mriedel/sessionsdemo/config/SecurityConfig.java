package de.mriedel.sessionsdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    /*
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public PasswordEncoder fakePasswordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(rawPassword.toString());
            }
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(conf -> {
                    conf.defaultSuccessUrl("/private");
                }).logout(conf -> {
                    conf.invalidateHttpSession(true);
                    conf.deleteCookies("SESSION");
                }).sessionManagement(conf ->{
                    conf.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                }).authorizeHttpRequests(conf -> {
                    conf.requestMatchers("/private").authenticated()
                            .requestMatchers("/**").permitAll();
                });
        return http.build();
    }
}
