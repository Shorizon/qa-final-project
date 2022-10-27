package com.qa.vhsRental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qa.vhsRental.entity.VHS;

public interface VHSRepository extends JpaRepository<VHS, Integer> {

	@Query("select v from VHS v where v.author = :author")
	public List<VHS> findByAuthor(String author);
	

}
