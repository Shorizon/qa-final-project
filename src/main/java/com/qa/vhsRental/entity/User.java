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

import org.springframework.stereotype.Component;

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
	@Column(name = "user_id")
	private int id;
	

	@NotNull
	@Pattern(regexp = "^(?:(?=.*[a-z])(?:(?=.*[A-Z])(?=.*[\\d\\W])|(?=.*\\W)(?=.*\\d))|(?=.*\\W)(?=.*[A-Z])(?=.*\\d)).{8,}$", message = "must contain a capital letter,a symbol, a lower case letter and a number")
	@Size(min = 6, max = 20, message = "pass must be between 6 and 20 characters long !!")
	@Column(name = "user_pass")
	private String password;

	@NotNull
	@Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters long !!")
	@Pattern(regexp = "^[A-Za-z ]*", message = "Invalid name - must only contain alphabetical characters !!")
	@Column(name = "user_name")
	private String name;

	@NotNull
	@Size(min = 2, max = 20, message = "surname must be between 2 and 20 characters long !!")
	@Pattern(regexp = "^[A-Za-z ]*", message = "Invalid surname - must only contain alphabetical characters !!")
	@Column(name = "user_surname")
	private String surname;
	
	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9 ]*", message = "Invalid adr - must only contain alphanumerical characters !!")
	@Column(name = "user_address")
	private String address;
	
	
	@Column(name = "currently_rented")
	private String rentedVHS;

}
