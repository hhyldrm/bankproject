package com.bank.sure.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


//When the users want to access a resource that they do not have access permission
// AuthenticationEntryPoint includes the code that determine what to do


@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
	
	private static final Logger logger=LoggerFactory.getLogger(AuthEntryPointJwt.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.error("Unauthorized error {}",authException.getMessage());
		response.sendError(HttpServletResponse.SC_BAD_GATEWAY,"Error:Unauthorized");
	}
	
	
	

}