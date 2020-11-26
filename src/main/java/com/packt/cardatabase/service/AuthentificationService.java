package com.packt.cardatabase.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.packt.cardatabase.SecurityConfig;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import static java.util.Collections.emptyList;

public class AuthentificationService {

	static final long EXPIRATIONTIME = 864_000_00; // 1 day in miliseconds
	static final String SIGNINGKEY = "SecretKey";
	static final String PREFIX = "Bearer";
	private final static Logger logger = LoggerFactory.getLogger(AuthentificationService.class);
	
	
	// Add token to Authorization header
	static public void addToken(HttpServletResponse res, String username) {
		logger.info("avant add Token");
		String JwtToken = Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
				.compact();
		
		res.addHeader("Authorization", PREFIX + " " + JwtToken);
		res.addHeader("Access-Control-Expose-Headers", "Authorization");
		logger.info("apres add Token");
	}
	
	//get token from Authorization header
	static public Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token !=null) {
			logger.info("token OK");
			String user = Jwts.parser().setSigningKey(SIGNINGKEY)
					.parseClaimsJws(token.replace(PREFIX, ""))
					.getBody()
					.getSubject();
			if (user !=null) {
				logger.info("user OK");
				return new UsernamePasswordAuthenticationToken(user, null, emptyList());
			}
		}
		logger.info("pas de token");
		return null;
	}
}
