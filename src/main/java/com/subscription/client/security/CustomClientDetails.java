package com.subscription.client.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.subscription.client.models.Client;



public class CustomClientDetails extends Client implements UserDetails {
	private static final long serialVersionUID = 12L;
	private String email;
	private int clientId;
	private String token;
	
	private Collection<? extends GrantedAuthority> grantedAuthorities ;
		
	public CustomClientDetails(String email, int clientId, String token, List<GrantedAuthority> grantedAuthorities) 
	{
		this.email=email;
		this.clientId=clientId;
		this.token=token;
		this.grantedAuthorities=grantedAuthorities;
	}

	@Override
	public String getUsername() {
		
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return grantedAuthorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Collection<? extends GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

	public void setGrantedAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}

}
