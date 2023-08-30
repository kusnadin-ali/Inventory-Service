package com.tujuhsembilan.InventoryService.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    .antMatchers("/**").permitAll() // Memperbolehkan akses ke semua permintaan tanpa autentikasi
            )
            .csrf(AbstractHttpConfigurer::disable); // Menonaktifkan fitur CSRF (opsional, tergantung kebutuhan Anda)
        
        return http.build();
    }
}
