package com.subscription.client.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subscription.client.config.JwtConfig;
import com.subscription.client.domain.ClientLogin;
import com.subscription.client.repository.ClientRepository;
import com.subscription.client.response.dto.ResponseSender;
import com.subscription.client.services.service.ClientRegService;




public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter
{	
	@Autowired
	JwtConfig jwtConfig;
	@Autowired
	ClientRepository clientRepository;
	
	private AuthenticationManager authenticationManager;
	public String  email;
	     
	public JwtUsernamePasswordAuthenticationFilter() 
	{
		System.out.println("Inside JwtClientnamePasswordAuthenticationFilter Default Constructor");
		
	}
	
	public JwtUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) 
	{
		this.authenticationManager=authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException 
	{
		JwtAuthenticationToken jwtAuthenticationToken=null;
		try
		{
			//Code of Client authentication is done here
			
			//Getting ClientLogin Object from request
			
			ObjectMapper objectMapper=new ObjectMapper();
			
			//readValue method will need byte or json therefore extracting byte from request
			
			
			
			ClientLogin clientLogin=objectMapper.readValue(request.getInputStream(), ClientLogin.class);
			
			 email=clientLogin.getEmail();	//This will be setted as principal
			String password=clientLogin.getPassword();	//This will be setted as Credentials
			String clientSecret=clientLogin.getClientSecret();
			ClientRegService n=new ClientRegService();
			//n.test(email);
			//Client client=clientRepository.findByEmail(email);
			//System.out.println("ClientSecret :"+ client);
			
			//if(client==null) {
			System.out.println("Inside JwtClientnamePasswordAuthenticationFilter attemptAuthentication email is "+email+"  Password :"+password +"  ClientSecret :"+clientSecret);
			
			
			Collection<? extends GrantedAuthority> authorities=Collections.emptyList();
			
			jwtAuthenticationToken=new JwtAuthenticationToken(email, password, authorities);
		//}else {
			ResponseSender responseSender=new ResponseSender();
			responseSender.setMessage("You are not Aathorised");
		}//}
		catch(IOException e)
		{
			System.out.println("Exception in JwtClientnamePasswordAuthenticationFilter attemptAuthentication");
			e.printStackTrace();
		}
		return authenticationManager.authenticate(jwtAuthenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException 
	{
		super.successfulAuthentication(request, response, chain, authResult);
		
		String email=authResult.getName();
		System.out.println("Inside JwtClientnamePasswordAuthenticationFilter successfulAuthentication() Email is "+email);
		chain.doFilter(request, response);
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException 
	{
		System.out.println("Inside JwtClientnamePasswordAuthenticationFilter unsuccessfulAuthentication()");
		super.unsuccessfulAuthentication(request, response, failed);
	}
}
