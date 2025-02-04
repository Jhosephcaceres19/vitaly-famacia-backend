package com.vitaly.farmacia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Desactiva CSRF (solo para pruebas)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/register").permitAll() // Permite acceso público a /login y /register
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            .formLogin(form -> form
                .loginPage("/") // Página personalizada de inicio de sesión
                .permitAll() // Permite acceso público al formulario de inicio de sesión
            )
            .logout(logout -> logout
                .permitAll() // Permite acceso público al cierre de sesión
            );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if ("user".equals(username)) {
                return User.withUsername("user")
                    .password("{noop}password") // Contraseña en texto plano (solo para pruebas)
                    .roles("USER") // Asigna el rol USER
                    .build();
            }
            throw new UsernameNotFoundException("User not found");
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // No codifica la contraseña (solo para pruebas)
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
            .userDetailsService(userDetailsService()) // Configura el UserDetailsService
            .passwordEncoder(passwordEncoder()) // Configura el PasswordEncoder
            .and()
            .build();
    }
}
