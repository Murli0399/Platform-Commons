package com.murli.service;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests(authorize -> {
			authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
					.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/students/**").hasRole("STUDENT")
					.anyRequest().authenticated();
		}).formLogin(withDefaults()).logout(logout -> {
			logout.logoutSuccessUrl("/") 
					.permitAll();
		});
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
