package com.subscription.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.subscription.client.models.Client;



//client Repository
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	public Client  findByEmail(String email);
	@Modifying
	@Query("update Client u set u.password=:password where u.clientId=:clientId")
	@Transactional
	public int updateNewPassword(@Param("clientId")int clientId, @Param("password")String password);
	
	@Query("select u.password from Client u where u.clientId=?1")
	public String getPassword(@Param("clientId") int clientId);
	//@Query("select u.clientSecret from Client u where u.clientSecret=?1")
	public Client findByClientSecret(String clientSecret);
	
}
