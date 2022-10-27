package com.qa.vhsRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.vhsRental.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	

//	
//	@Query("select u from User u where u.vhs = :vhs")
//	List<User> findUserByVhs(String vhs);
//	
	

	
}
