package com.packt.cardatabase;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import com.packt.cardatabase.service.UserDetailsServiceImpl;

/*
 * 
 * Spring security behavior 
 * @EnableWebSecurity : pour dire qu'on va utiliser cette configuration par defaut pour spring security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
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
	
	@Autowired
	private UserDetailsServiceImpl userDetailService;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
