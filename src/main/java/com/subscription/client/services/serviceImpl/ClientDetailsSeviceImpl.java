package com.subscription.client.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.subscription.client.models.Client;
import com.subscription.client.repository.ClientRepository;




@Service
public class ClientDetailsSeviceImpl implements UserDetailsService
{
	@Autowired
	ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		//here username will be email of the Client class
		Client client=clientRepository.findByEmail(username);
		
		
			
			//therefore Child Object can be catched in Parent Object i.e Upcasting 
			
			//AND THIS IS THE PRINCIPAL INSIDE THE AUTHENTICATION OBJECT in fo
			return new com.subscription.client.security.ClientDetailsImpl(client);
		
	}
}
