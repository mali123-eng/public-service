package com.subscription.client.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.subscription.client.models.Client;





public class ClientDetailsImpl  extends User
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -18009758838141461L;
	Client client;
	
	//Constructor used at the time of authentication
	public ClientDetailsImpl(Client client)
	{
		super(client.getEmail(), client.getPassword(), Collections.emptyList());
		this.client=client;
	}

	//Constructor used at the time of authorization
	public ClientDetailsImpl(String email, String password, List<? extends GrantedAuthority> grantedAuthorities) 
	{
		super(email, null, grantedAuthorities);
	}
	

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
