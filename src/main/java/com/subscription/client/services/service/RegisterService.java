package com.subscription.client.services.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.subscription.client.config.JwtConfig;
import com.subscription.client.domain.JwtClient;
import com.subscription.client.models.Client;
import com.subscription.client.repository.ClientRepository;
import com.subscription.client.request.dto.RegisterRequest;
import com.subscription.client.response.dto.RegisterResponse;
import com.subscription.client.security.FieldValidation;
import com.subscription.client.utils.JwtGenerator;
import com.subscription.client.utils.JwtValidator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class RegisterService {
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	JwtClient jwtClient;

	@Autowired
	private JwtValidator jwtValidator;

	@Autowired
	JwtGenerator tokenGenerator;

	@Autowired
	private JwtConfig jwtConfig;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public RegisterResponse registered(RegisterRequest registerRequest) throws NoSuchAlgorithmException {
		RegisterResponse registerResponse = new RegisterResponse();

		int status = FieldValidation.validate(registerRequest); // calling validate() for validating the field data
																// which is send by the users
		if (status == 0) {
			registerResponse.setMessage("User is invalid"); // Create a response class to send the response.

			return registerResponse;
		}
		String email = registerRequest.getEmail();

		Client emailexit = clientRepository.findByEmail(email);
		if (emailexit == null) {
			Client client = new Client();
			client.setFirstName(registerRequest.getFirstName());
			client.setLastName(registerRequest.getLastName());
			client.setEmail(registerRequest.getEmail());
			client.setAddress(registerRequest.getAddress());
			client.setCity(registerRequest.getCity());
			client.setState(registerRequest.getState());
			client.setCountry(registerRequest.getCountry());
			client.setZipCode(registerRequest.getZipCode());
			client.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
			client.setPhone(registerRequest.getPhone());
			client.setPanNumber(registerRequest.getPanNumber());

			Random rand = new Random();
			int random = rand.nextInt(1000000000);
			String password = String.valueOf(random);
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hashInBytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

			// bytes to hex
			StringBuilder sb = new StringBuilder();
			for (byte b : hashInBytes) {
				sb.append(String.format("%02x", b));
			}
			System.out.println(sb.toString());
			String secret = sb.toString();
			int length=secret.length();
			       String s=secret.substring(length/2);
			client.setClientSecret(s);
			registerResponse.setMessage("You are Successfully Register");
			registerResponse.setFirstName(registerRequest.getFirstName());
			clientRepository.save(client);

			String token = tokenGenerator.generate();
			// boolean emailVerificationStatus=sendEmailForVerification(email);
		} else {
			registerResponse.setMessage("email is already in used");
		}

		return registerResponse;
	}

}
