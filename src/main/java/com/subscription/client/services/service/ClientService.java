package com.subscription.client.services.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.subscription.client.models.Client;


//clent services

@Service
public interface ClientService {
	Client addClient(Client client);
	Client getClientById(int clientId);
	List<Client> getAllClient();
	public String deleteClient(int clientId);
	public Client updateClient(Client client);
	Client getClientByEmail(String email);
	
}
