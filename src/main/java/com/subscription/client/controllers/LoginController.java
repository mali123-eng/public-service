package com.subscription.client.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.subscription.client.config.JwtConfig;
import com.subscription.client.domain.JwtClient;
import com.subscription.client.models.Client;
import com.subscription.client.response.dto.ResponseSender;
import com.subscription.client.security.ClientDetailsImpl;
import com.subscription.client.security.JwtAuthenticationToken;
import com.subscription.client.services.service.ClientService;
import com.subscription.client.utils.JwtGenerator;




@RestController
public class LoginController
{
	@Autowired
	private JwtGenerator jwtGenerator;
	
	@Autowired
	private JwtClient jwtClient;
	
	@Autowired
	JwtConfig jwtConfig;
	

	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private JwtAuthenticationToken jwtAuthenticationToken;
	
	HttpServletRequest request;
	
	
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseSender loginMethod(Authentication authResult)
	{
		//The Principal is the userDetailsImpl Object
		
		ClientDetailsImpl clientDetailsImpl=((ClientDetailsImpl)authResult.getPrincipal());
		
		Client clientReg=clientDetailsImpl.getClient();
		
		ResponseSender responseSender=new ResponseSender();	
		
		Client client=clientService.getClientById(clientReg.getClientId());
		String firstName=clientReg.getFirstName();
			
		System.out.println("Inside LoginController loginMethod() firstName is :-"+firstName);
		
		//user found in the db, make token for it
		
		//Loginid is email in client table
		jwtClient.setClientId(clientReg.getClientId());
		//ProfileType id role in client table
		
		
		String token=jwtGenerator.generate();
		
		System.out.println("Inside LoginController loginMethod() token is :- "+token);
		//adding token in the response 
		
		responseSender.setMessage("You are login Successfully");
		responseSender.setFlag(true);
		responseSender.setFirstName(firstName);
		
		//Setting token to the JwtAuthenticationToken to ensure that when user will come again in future
		//he/she must be logged in
		
		jwtAuthenticationToken.setToken(token);
		
		
		return responseSender;
	}
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/loginFailed", method=RequestMethod.POST)
	public ResponseSender loginFailed()
	{
		//System.out.println("Inside LoginController loginFailed()");
		ResponseSender responseSender=new ResponseSender();
		
		responseSender.setMessage("Login Failed");
		responseSender.setFlag(false);
		
		return responseSender;
	}

	
	
}
