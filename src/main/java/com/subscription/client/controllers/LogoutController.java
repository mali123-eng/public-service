package com.subscription.client.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.subscription.client.response.dto.ResponseSender;
import com.subscription.client.security.JwtAuthenticationToken;




@Controller
public class LogoutController 
{  
	@Autowired
	JwtAuthenticationToken jwtAuthenticationToken;
	@CrossOrigin(origins="*")
	@RequestMapping(value="/rest/logout", method=RequestMethod.GET)
	public @ResponseBody ResponseSender logoutUser(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		session.invalidate();
		jwtAuthenticationToken.setToken(null);
		ResponseSender responseSender=new ResponseSender();
		responseSender.setMessage("You are Logout Successfully ");
		responseSender.setFlag(true);
		System.out.println("Client you are Logout Successfully");
		return responseSender;
	}
	
}
