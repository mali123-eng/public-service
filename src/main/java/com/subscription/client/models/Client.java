package com.subscription.client.models;

import java.io.Serializable;

import javax.persistence.*;
//client model
@Entity
@Table(name="client")
public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2457052041255843854L;
	/**
	 * 
	 */

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int clientId;	
@Column
private String firstName;
@Column
private String lastName;
@Column
private String gender;
@Column
private long dob;
@Column
private String email;
@Column
private String address;
@Column
private String city;
@Column
private String state;
@Column
private String country;
@Column
private String zipCode;
@Column
private String password;
@Column
private String conformPassword;
@Column
private String phone;
@Column
private String panNumber;
@Column
private String clientSecret;

public int getClientId() {
	return clientId;
}
public void setClientId(int clientId) {
	this.clientId = clientId;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}
public String getZipCode() {
	return zipCode;
}
public void setZipCode(String zipCode) {
	this.zipCode = zipCode;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getConformPassword() {
	return conformPassword;
}
public void setConformPassword(String conformPassword) {
	this.conformPassword = conformPassword;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getPanNumber() {
	return panNumber;
}
public void setPanNumber(String panNumber) {
	this.panNumber = panNumber;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
public String getClientSecret() {
	return clientSecret;
}
public void setClientSecret(String clientSecret) {
	this.clientSecret = clientSecret;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public long getDob() {
	return dob;
}
public void setDob(long dob) {
	this.dob = dob;
}


}
