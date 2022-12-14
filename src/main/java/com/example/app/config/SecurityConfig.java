package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// HttpSecurity
		http.authorizeHttpRequests(req -> {
			req.antMatchers("/", "/home", "/register", "/css/**").permitAll();
			req.antMatchers("/user/**").authenticated();
			req.anyRequest().hasRole("ADMIN");
		}).formLogin(form -> { form.loginPage("/login") .usernameParameter("loginId")
		 .passwordParameter("loginPass") .failureForwardUrl("/loginFailure")
		 .permitAll(); }) 
		 .logout(logout -> { logout.invalidateHttpSession(true)
		 .logoutSuccessUrl("/logoutDone") .permitAll(); });
		 

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	 @Bean public RequestRejectedHandler requestRejectedHandler() { return new
	 HttpStatusRequestRejectedHandler(); }

}