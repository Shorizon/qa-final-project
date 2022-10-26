package com.qa.vhsRental.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "user_details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	
	@NotNull
	@Size(min = 6, max = 20, message = "pass must be between 6 and 20 characters long !!")
	@Column(name = "user_pass")
	private int password;

	@NotNull
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long !!")
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid name - must only contain alphabetical characters !!")
	@Column(name = "user_name")
	private String name;

	@NotNull
	@Size(min = 2, max = 20, message = "surname must be between 2 and 20 characters long !!")
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid surname - must only contain alphabetical characters !!")
	@Column(name = "user_surname")
	private String surname;

	@NotNull
	@Column(name = "currently_rented")
	@Autowired
	private VHS rentedVHS;

}
