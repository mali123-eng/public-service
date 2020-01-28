package com.subscription.client.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import com.subscription.client.config.JwtConfig;
import com.subscription.client.domain.JwtClient;
import com.subscription.client.models.Client;
import com.subscription.client.services.service.ClientService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
@Component
public class JwtValidator {

	@Autowired
	ClientService clientService;
	@Autowired
	JwtConfig jwtConfig;
	@Autowired
	JwtClient jwtClient;
	
	public JwtClient validate(String token) {
		token=token.trim();
		System.out.println("Inside JwtValidator validate() token is "+token);
		
		String secretKey=jwtConfig.getSecretKey();
		System.out.println("Inside JwtValidator validate() secretKey is "+secretKey);
		
		try
		{	
			Claims claims=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
			System.out.println("Inside JwtValidator validate() claims is "+claims);
			
			//checking the token is valid or not
			int clientId =Integer.parseInt((String)claims.get("clientId"));
			System.out.println("Inside JwtValidator validate() ClientId is "+clientId);
			//saving userId at ClassLevel for further use
			
			if(isValidToken(clientId))
			{
			    
				jwtClient.setEmail(claims.getSubject());
				jwtClient.setRole("Client");//((String)//claims.get("role"));
				jwtClient.setClientId( Integer.parseInt((String)claims.get("clientId")));
			}
		
		}
		catch(Exception e)
		{
			System.out.println("Exception in JwtValidator validate() "+e);
			e.printStackTrace();
			//throw new RuntimeException("Jwt Token is null");
			throw new BadCredentialsException("Jwt Token is null");
		}
		System.out.println("Inside JwtValidator validate() jwtClient ClientId is "+jwtClient.getClientId());
		return jwtClient;
	}
	public boolean isValidToken(int clientId)
	{
		Client user=clientService.getClientById(clientId);
		System.out.println("inside isValidToken JwtValidator "+user);
		if(user!=null)
		{
			
			return true;
		}
		else
		{
			
			return false;
		}
	}
	
}
