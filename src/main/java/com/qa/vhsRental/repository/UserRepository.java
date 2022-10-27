package com.qa.vhsRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.vhsRental.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	@Query("select u from User u where u.rentedVHS = :vhs")
	public List<User> findUserByVhs(String vhs);
	
	@Query("select u from User u where u.name = :name and u.surname = :surname")
	public List<User> findUserByNameAndSurname(String name,String surname);
	
	@Query("select u from User u where u.address = :address")
	public List<User> findUserByAddress(String address);

	

	
}
