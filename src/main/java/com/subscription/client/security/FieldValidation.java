package com.subscription.client.security;

import com.subscription.client.request.dto.RegisterRequest;

public class FieldValidation {

	public static int validate(RegisterRequest registerRequest)
	{
	String firstName=registerRequest.getFirstName();
	String lastName=registerRequest.getLastName();
	String contact=String.valueOf(registerRequest.getPhone());
	
	
	
	if(firstName.length()<2)
	{
		return 0;
	}
	if(lastName=="")
	{
		return 0;
	}
	if(contact.length()<10)
	{
		return 0;
	}
	return 1;
	}
}
