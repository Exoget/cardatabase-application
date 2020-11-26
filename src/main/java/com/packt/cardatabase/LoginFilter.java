package com.packt.cardatabase;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.packt.cardatabase.domain.AccountCredentials;
import com.packt.cardatabase.service.AuthentificationService;


// class handles POST requests to the /login endpoint
public class LoginFilter extends AbstractAuthenticationProcessingFilter {

	private final static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
		logger.info("LoginFilter Constrotor");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		logger.info("avant attemptAuthentication");
		AccountCredentials creds = new ObjectMapper().readValue(request.getInputStream(), AccountCredentials.class);
		
		Authentication aut =  getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), 
						creds.getPassword(), 
						Collections.emptyList()));
		logger.info("apres attemptAuthentication");
		return aut;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		AuthentificationService.addToken(response, authResult.getName());
		logger.info("successfulAuthentication ...");
	}

	
}
