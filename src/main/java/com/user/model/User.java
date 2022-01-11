package com.user.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.*;//added dependency validation for this in pom.xml

import org.springframework.lang.NonNull;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "usersDB")
@Data
@Builder

public class User {
	
	@Id
	@Column(name = "user_id")//for creation of database only
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 65)
	@Column(name = "first_name")
	private String firstName;
	
	@NotNull
	@Size(max = 65)
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull
	@Size(max = 120)
	@Column(unique = true)
	private String email;
	
	@NotNull
	@Size(max = 30)
	private String gender;
	
	@NotNull
	private int age;
	
	@NotNull
	@Size(max=30)
	private String address;
	
	@NotNull
	@Size(max=30)
	private String pincode;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "doj")
	private Date doj;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "dob")
	private Date dob;
	
	
	@NotNull
	@Size(max = 15)
	private String password;
	
	@Column(columnDefinition = "int default 0")
	private int status;

	public User(Long id, @NotNull @Size(max = 65) String firstName, @NotNull @Size(max = 65) String lastName,
			@NotNull @Size(max = 120) String email, @NotNull @Size(max = 30) String gender, @NotNull int age,
			@NotNull @Size(max = 30) String address, @NotNull @Size(max = 30) String pincode, Date doj, Date dob,
			@NotNull @Size(max = 15) String password, int status) {

		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.age = age;
		this.address = address;
		this.pincode = pincode;
		this.doj = doj;
		this.dob = dob;
		this.password = password;
		this.status = status;
	}

	public User() {

	}
	

	


	

	
}
