package com.qa.vhsRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.vhsRental.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	

//	
//	@Query("select u from User u where u.vhs = :vhs")
//	List<User> findUserByVhs(String vhs);
//	
	

	
}
