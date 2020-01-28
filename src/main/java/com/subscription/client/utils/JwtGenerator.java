package com.subscription.client.utils;

import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.subscription.client.config.JwtConfig;
import com.subscription.client.domain.JwtClient;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator 
{
	@Autowired 
	JwtClient jwtClient;
	
	@Autowired
	JwtConfig jwtConfig;
	
	@Autowired
	HttpServletResponse httpServletResponse;
	
	static int tokenGenerationFlag=0;
	
	//JwtClient jwtClient, JwtConfig jwtConfig
	public String generate()
	{
		System.out.println("Inside JwtGenerator generate()");
		
		long expirationTimeInMiliSecond=jwtConfig.getExpirationTimeInMiliSecond();
		String secretKey=jwtConfig.getSecretKey();
		
		System.out.println("Inside JwtGenerator expirationTimeInMiliSecond "+expirationTimeInMiliSecond);
		System.out.println("Inside JwtGenerator secretKey "+secretKey);
		
		Claims claims=Jwts.claims()
						.setSubject(jwtClient.getEmail());
			   
		claims.put("clientId", String.valueOf(jwtClient.getClientId()) );	//Converting Integer value to String as
																	//we need to pass String in value field
		claims.put("role", jwtClient.getRole());
		
		
		
		String token = Jwts.builder()
						.setClaims(claims)
						.setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMiliSecond ))	//setting 1800000 the expiration time for JWT
						.signWith(SignatureAlgorithm.HS512, secretKey)	//Signing the token with a key
						.compact();	//making the token small
		System.out.println("Inside JwtGenerator secretKey "+secretKey);
		System.out.println("Inside JwtGenerator httpServletResponse is "+httpServletResponse);
		
		System.out.println("Inside JwtGenerator generate() token is "+token);
		if(tokenGenerationFlag==0)
		{
			httpServletResponse.addHeader("Authorization", token);
			tokenGenerationFlag++;
		}
		else
		{
			httpServletResponse.setHeader("Authorization", token);
		}
		return token;
	}
}
