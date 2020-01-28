package com.subscription.client.services.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subscription.client.models.Client;
import com.subscription.client.repository.ClientRepository;




@Service
public class ClientRegService
{
	@Autowired
	ClientRepository clientRepository;
	
	public Client getClientRegByEmail(String email)
	{
		Client client=null;
		try
		{
			System.out.println("Inside ClientRegService getClientRegByEmail() email is "+email);
			
			client=clientRepository.findByEmail(email);
			
			System.out.println("Inside ClientRegService getClientRegByEmail() client is "+client);
			return client;
		}
		catch(Exception e)
		{
			System.out.println("Exception in ClientRegService getClientByEmail() ");
			e.printStackTrace();
			return null;
		}
	}
	
	public String changeForgotPassword(int clientId, String newEncryptedPassword)
	{
		try
		{
			int status=clientRepository.updateNewPassword(clientId, newEncryptedPassword);
			
			System.out.println("Inside ClientRegService changeForgotPassword status is "+status);
			
			if(status==1)
			{
				return "Success";
			}
			else
			{
				return "Failed";
			}
		}
		catch(Exception e)
		{
			System.err.println("Exception Inside ClientRegService changeForgotPassword()");
			e.printStackTrace();
			
			return "Failed due to Exception";
		}
	}
	
	public String getOldPasswordByUserId(int userId)
	{
		String oldPassword=clientRepository.getPassword(userId);
		return oldPassword;
	}
	public void test(String email) {
		System.out.println("????????????????????????"+email);
		Client client1=clientRepository.findByEmail(email);
		
		System.out.println("Inside ClientRegService getClientRegByEmail() client is >>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<"+client1);
	}
}

