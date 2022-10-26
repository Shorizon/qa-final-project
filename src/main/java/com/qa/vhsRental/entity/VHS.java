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


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "VHS_details")
public class VHS {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vhs_id")
	private int id;
	
	@NotNull
	@Column(name = "vhs_name")
	private String name;
	
	@NotNull
	@Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters long !!")
	@Pattern(regexp = "^[A-Za-z]*", message = "Invalid author name - must only contain alphabetical characters !!")
	@Column(name = "vhs_author")
	private String author;
	
	@NotNull
	@Pattern(regexp = "^true$|^false$", message = "allowed input: true or false")
	@Column(name = "VHS_status")
	private Boolean isRented;
	
}
