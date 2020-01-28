package com.subscription.client.security;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.subscription.client.domain.JwtClient;
import com.subscription.client.utils.JwtValidator;


@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{	
	@Autowired
	JwtValidator validator;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException 
	{
		System.out.println("Inside JwtAuthenticationProvider additionalAuthenticationChecks method userDetails is "+userDetails);
	}
	
	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authenticationToken)
			throws AuthenticationException 
	{
		System.out.println("Inside JwtAuthenticationProvider retreiveUser method username is "+username);
		
		JwtAuthenticationToken jwtToken=(JwtAuthenticationToken)authenticationToken;
		
		String token=jwtToken.getToken();
		
	
		
		JwtClient jwtClient= validator.validate(token);
		
		if(jwtClient==null)
		{
			throw new RuntimeException("JWT Token is incorrect");
		}
		
		//This one was giving error in searchPost
		
		List<GrantedAuthority> grantedAuthorities=Collections.emptyList();
		
		return new CustomClientDetails(jwtClient.getEmail(), jwtClient.getClientId(), token, grantedAuthorities);
		
		//this return will not work here as we need to pass password in this Constructor of
		
		
		
	}
	
	@Override
	public boolean supports(Class<?> authentication)
	{
		System.out.println("JwtAuthenticationProvider supports method");
		return JwtAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
