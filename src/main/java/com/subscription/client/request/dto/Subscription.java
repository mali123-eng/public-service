package com.subscription.client.request.dto;



public class Subscription {
	
	private Long id;
	
	private String fName;
	
	private String gender;
	
	private String email;
	
	private long dateOfBith;
	
	private String flag;
	
	private int clientId;
	
	public Subscription(Long id, String fName, String gender, String email, long dateOfBith, String flag,
			int clientId) {
		super();
		this.id = id;
		this.fName = fName;
		this.gender = gender;
		this.email = email;
		this.dateOfBith = dateOfBith;
		this.flag = flag;
		this.clientId = clientId;
	}
	public Subscription() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getDateOfBith() {
		return dateOfBith;
	}
	public void setDateOfBith(long dateOfBith) {
		this.dateOfBith = dateOfBith;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	
}
