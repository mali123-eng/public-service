package com.subscription.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.subscription.client.domain.JwtClient;
import com.subscription.client.request.dto.ChangePasswordRequestDto;
import com.subscription.client.response.dto.ResponseSender;
import com.subscription.client.services.service.ClientRegService;
import com.subscription.client.utils.JwtGenerator;



@Controller
public class ChangePasswordController 
{
	@Autowired
	JwtClient jwtClient;
	
	@Autowired
	ClientRegService clientRegService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	JwtGenerator jwtGenerator;
	
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/changePassword", method=RequestMethod.POST)
	public @ResponseBody ResponseSender changePassword(@RequestBody ChangePasswordRequestDto changePasswordRequestDto)
	{
		ResponseSender responseSender=new ResponseSender();

		String oldPassword=changePasswordRequestDto.getOldPassword();
		String newPassword=changePasswordRequestDto.getNewPassword();
		
		int clientId=jwtClient.getClientId();
		System.out.println("Inside ChangePasswordController changePassword() userId is "+clientId);
		System.out.println("Inside ChangePasswordController changePassword() oldPassword is "+oldPassword);
		
		
		
		try
		{
			String oldPasswordFromDB=clientRegService.getOldPasswordByUserId(clientId);
			System.out.println("Inside ChangePasswordController changePassword() oldPasswordFromDB is "+oldPasswordFromDB);
			
			if(oldPasswordFromDB.length()>0)
			{
				System.out.println("Inside ChangePasswordController changePassword() if oldPasswordFromDB.length()>0");
				System.out.println("Inside ChangePasswordController changePassword() if oldPasswordFromDB.length()>0 oldPasswordFromDB is "+oldPasswordFromDB);
				
				if(bCryptPasswordEncoder.matches(oldPassword, oldPasswordFromDB))
				{
					
					String newEncryptedPassword=bCryptPasswordEncoder.encode(newPassword);
					String message=clientRegService.changeForgotPassword(clientId, newEncryptedPassword);
					System.out.println("Inside ChangePasswordController changePassword() if loop message is "+message);
					
					jwtGenerator.generate();
					responseSender.setMessage(message);
					responseSender.setFlag(true);
					
					return responseSender;
				}
				else
				{
					System.out.println("Inside ChangePasswordController changePassword() else oldPasswordFromDB.equals(encryptedOldPassword)");
					//Wrong Password
					
					jwtGenerator.generate();
					responseSender.setMessage("Entered Password is not Correct");
					responseSender.setFlag(false);
					
					return responseSender;
				}
			}
			else
			{
				System.out.println("Inside ChangePasswordController changePassword() 2nd else ");
				
				responseSender.setMessage("Failed");
				responseSender.setFlag(false);
			
				return responseSender;
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception Inside ChangePasswordController changePassword()");
			e.printStackTrace();
			
			responseSender.setMessage("Exception");
			responseSender.setFlag(false);
			
			return responseSender;
		}
	}
}
