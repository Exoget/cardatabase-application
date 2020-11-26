package com.packt.cardatabase;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import com.packt.cardatabase.service.AuthentificationService;


// This class will handle authentication in all other endpoints exceptlogin
public class AutenticationFilter extends GenericFilterBean{

	private final static Logger logger = LoggerFactory.getLogger(AutenticationFilter.class);
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		logger.info("AutenticationFilter doFilter : " +((HttpServletRequest) request).getRequestURL());
		
	
		
		Authentication authentication = AuthentificationService.getAuthentication((HttpServletRequest) request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);
		logger.info("AutenticationFilter doFilter ends");
	}

}
