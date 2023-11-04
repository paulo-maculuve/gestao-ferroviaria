package com.estacao.ferroviaria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class UserSecurity {

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().requestMatchers("/public/**").permitAll()
		.requestMatchers("/", "/admin/**").authenticated()
		.anyRequest().permitAll() 
		.and().formLogin().loginPage("/login")
		.defaultSuccessUrl("/")
		.permitAll()
		.and()
        .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
            .logoutSuccessUrl("/login?logout") 
            .invalidateHttpSession(true) 
            .deleteCookies("JSESSIONID")
            .permitAll();
			return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
