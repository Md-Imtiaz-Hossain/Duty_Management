package com.example.pack.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "This filed is required !!")
	@Size(min = 2, max = 50, message = "Min 2 and maximum 50 are allowed.")
	@Column
	private String companyName;

	@Column
	private String contactPerson;

	@NotBlank(message = "This filed is required !!")
	@Column
	private String contactNumber;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email !! ")
	@Column
	private String email;


}
