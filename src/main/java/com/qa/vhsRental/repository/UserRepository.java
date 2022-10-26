package com.qa.vhsRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.vhsRental.entity.User;
import com.qa.vhsRental.entity.VHS;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	List<User> findByName(String name);
	
	@Query("select u from User p where u.VHS <= :vhs")
	List<User> findByVHS(String vhs);
	
	
}
