package com.packt.cardatabase;


import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.packt.cardatabase.service.UserDetailsServiceImpl;

/*
 * 
 * Spring security behavior 
 * @EnableWebSecurity : pour dire qu'on va utiliser cette configuration par defaut pour spring security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	// to define which endpoints in our application are secured and which are not
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().authorizeRequests()//.antMatchers(HttpMethod.POST, "/login").permitAll()
		.anyRequest().authenticated().and()
		// filter for api/login requests
		.addFilterBefore(new LoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		// filter for other request to check JWT in header
		.addFilterBefore(new AutenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		logger.info("configure(HttpSecurity http ends)");
	}

	
	/*
	 * spring security use the UserDetailsService for user authentication and authorization
	 * version in-memory
	 */
//	@Bean
//	public UserDetailsService userDetailsService() {
//				
//		UserDetails user = User.withUsername("user")
//				      .password("{bcrypt}$2a$10$diTauhkRfxcylOsDDVCxqOkYCc9dqrgcpizDKv55jZfYcybVFnPm.")
//				      .roles("USER")
//				      .build();
//		return new InMemoryUserDetailsManager(user);
//	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
				
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Autowired
	private UserDetailsServiceImpl userDetailService;


	// to enable users from database
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailService)
		.passwordEncoder(new BCryptPasswordEncoder());
		logger.info("configure (AuthenticationManagerBuilder auth ends)");
	}
	
	
}
