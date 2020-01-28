package com.subscription.client.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.subscription.client.config.JwtConfig;





public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler 
{
	@Autowired
	JwtConfig jwtConfig;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException 
	{
		
		
		System.out.println("Inside JwtSuccessHandler onAuthenticationSuccess()");
		
	}
}
