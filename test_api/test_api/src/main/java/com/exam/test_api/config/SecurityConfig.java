package com.exam.test_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.test_api.service.MyUserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserService myUserService;

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize.requestMatchers("/api/auth/token/generate").permitAll()
						.requestMatchers("/api/auth/signup").permitAll()
						.requestMatchers(null)
						
						.anyRequest().permitAll())
				
				/* Activating basic Auth */
				/* Activating basic Auth */
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	/*
	 * UserDetailsService userDetailsService() {
	 * 
	 * User user1 = (User) User.withUsername("jaswanth") .password("{noop}jaswanth")
	 * .roles("USER_DEFAULT") .build();
	 * 
	 * User user2 = (User) User.withUsername("123456789")
	 * .password("{noop}123456789") .roles("USER_DEFAULT") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user1,user2); }
	 */
	AuthenticationProvider getAuth() {
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setPasswordEncoder(passEncoder());
		dao.setUserDetailsService(myUserService);
		return dao;
	}

	@Bean
	BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}

}
