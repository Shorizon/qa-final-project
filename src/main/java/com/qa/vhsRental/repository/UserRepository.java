package com.qa.vhsRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.vhsRental.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	List<User> findByName(String name);
	
	
}
