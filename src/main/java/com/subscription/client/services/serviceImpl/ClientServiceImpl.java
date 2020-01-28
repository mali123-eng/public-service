package com.subscription.client.services.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.subscription.client.models.Client;
import com.subscription.client.repository.ClientRepository;
import com.subscription.client.services.service.ClientService;



//  client ServiceImpl
@Repository
public class ClientServiceImpl implements ClientService  {
    @Autowired
    ClientRepository clientRepository;
   
	@Override
	public Client addClient(Client client) {
		
		return clientRepository.save(client);
	}

	@Override
	public Client getClientById(int clientId) {
		
		
		
		return clientRepository.findById(clientId).get();
	     
		
	}

	@Override
	public List<Client> getAllClient() {
		
		return clientRepository.findAll();
	}

	@Override
	public String deleteClient(int clientId) {
		
	clientRepository.deleteById(clientId);
	return "Data is Deleted" ;
	}

	@Override
	public Client updateClient(Client client) {
		
		return clientRepository.save(client);
	}

	@Override
	public Client getClientByEmail(String email) {
		
		return clientRepository.findByEmail(email);
	}

	
}
