package com.subscription.client.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.subscription.client.models.Client;
import com.subscription.client.services.service.ClientService;



//client controller for all services
@RestController
public class ClientController {
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	@Autowired
	ClientService clientService;
  
   @CrossOrigin(origins="*")
   @RequestMapping(value = "/rest/findOneClient/{clientId}", method = RequestMethod.GET)
	public Object findOneClient(@PathVariable int clientId) {
	   
		try {
			LOG.info("finding one Client.");
			// find one client by Id
			return clientService.getClientById(clientId);
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
   @CrossOrigin(origins="*")
   @RequestMapping(value = "/rest/findAllClient", method = RequestMethod.GET)
	public Object findAllClient() {
	   try {
		   LOG.info("finding all Client.");
		   //find all client
		   return clientService.getAllClient();
		   
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  
	   }
		
	}
   @CrossOrigin(origins="*")
   @RequestMapping(value = "/rest/deleteById/{clientId}", method = RequestMethod.DELETE)
   public Object deleteById(@PathVariable int clientId) {
	   try {
		   LOG.info("delete  Client. by Id");
		   //delete client by id
		   return clientService.deleteClient(clientId);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	   }
   }
   @CrossOrigin(origins="*")
   @RequestMapping(value = "/rest/updateClient", method = RequestMethod.PUT)
   public Object updateClient(@RequestBody Client client) {
	   try {
		   LOG.info("delete  Client. by Id");
		   // update client 
		   return clientService.updateClient(client);
	   }catch(Exception ex) {
		   ex.printStackTrace();
		   return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	   }
   }
 
	 
	  
  }

