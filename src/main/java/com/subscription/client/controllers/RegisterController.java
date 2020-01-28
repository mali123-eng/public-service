package com.subscription.client.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.subscription.client.models.Client;
import com.subscription.client.request.dto.RegisterRequest;
import com.subscription.client.request.dto.Subscription;
import com.subscription.client.response.dto.RegisterResponse;
import com.subscription.client.services.service.ClientService;
import com.subscription.client.services.service.RegisterService;



@RestController
public class RegisterController {
	@Autowired
	RegisterService registerService;
	@Autowired
	ClientService clientService;
	@CrossOrigin(origins="*")
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public RegisterResponse addUser(@RequestBody RegisterRequest registerRequest) throws NoSuchAlgorithmException
	{
		RegisterResponse registerResponse=null;
		registerResponse=registerService.registered(registerRequest);
		if (registerResponse != null) {
			Client client=clientService.getClientByEmail(registerRequest.getEmail());
			final String uri = "http://localhost:8081/save";
			Subscription subscription = new Subscription();
			subscription.setClientId(client.getClientId());
			subscription.setEmail(client.getEmail());
			//subscription.setGender(client.getGender());
			subscription.setfName(client.getFirstName());
			//subscription.setDateOfBith(client.getDob());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Subscription> entity = new HttpEntity<>(subscription, headers);
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.postForObject(uri, entity, Subscription.class);
		}
		
		return registerResponse;
	}

}

