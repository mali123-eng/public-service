package com.subscription.client.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.subscription.client.config.JwtConfig;






public class JwtAutenticationTokenFilter extends AbstractAuthenticationProcessingFilter 
{
			//ClientnamePasswordAuthenticationFilter also extends  AbstractAuthenticationProcessingFilter
	
	@Autowired
	JwtConfig jwtConfig;
	
	@Autowired
	JwtAuthenticationProvider jwtAuthenticationProvider;
	
	@Autowired
	JwtAuthenticationToken jwtAuthenticationToken;
	public JwtAutenticationTokenFilter() 
	{
		super("/rest/**");	//Check for JWT token only for those request which are having /rest/** 
		System.out.println("Inside JwtAuthenticationTokenFilter Constructor");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException 
	{
		//Inside this method we will extract token from the header
		
		
		
		String header=request.getHeader("Authorization");
		
		System.out.println("Inside JwtAuthenticationTokenFilter attemptAuthentication header is "+header);
		
		String tokenStartingWord=jwtConfig.getTokenStartingWord();
		
		System.out.println("JwtAuthenticationTokenFilter attemptAuthentication() tokenStartingWord "+tokenStartingWord);
		
		if(header==null || !header.startsWith(tokenStartingWord))
		{
			throw new RuntimeException("JWT Token is missing");
		}
		
		//Check for if token in JwtAuthenticationToken is null
		
		if(jwtAuthenticationToken.getToken()==null)
		{
			//throw new RuntimeException("Please Login first and Send token");
			throw new BadCredentialsException("Please Login first and Send token");
		}
		
		/*	substring(int beginIndex)
			Returns a new string that is a substring of this string.
		*/
		String authenticationToken=header.substring(6);	//This will return the whole token except "Token "
											//Here we are giving 6 in substring(6) as length of "Token " is 6
		
		JwtAuthenticationToken jwtToken=new JwtAuthenticationToken(authenticationToken);
		
		
		
		return getAuthenticationManager().authenticate(jwtToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException 
	{
		System.out.println("Inside JwtAuthenticationTokenFilter successfulAuthentication()");
	
		
		
		super.successfulAuthentication(request, response, chain, authResult);
		
		chain.doFilter(request, response);	//On success we are sending request to the next Filter
		

	}
	
	@Override
	public void setAuthenticationManager(AuthenticationManager authenticationManager) 
	{
		
		super.setAuthenticationManager(authenticationManager);
		System.out.println("Inside JwtAuthenticationTokenFilter setAuthenticationManager()");
	}
	
	@Override
	protected AuthenticationManager getAuthenticationManager() 
	{
		
		System.out.println("Inside JwtAuthenticationTokenFilter getAuthenticationManager()");
		return super.getAuthenticationManager();
	}
}
