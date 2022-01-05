package com.neosoft.springboot.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String surname;
	
	@NotNull
	private long pincode;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date doj;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@NotNull
	@Column(unique = true)
	private String email;
	
	@NotNull
	private String password ;
	
	@NotNull
	@Column(unique = true)
	private long phoneno;
	
	@NotNull
	private boolean deleted = Boolean.FALSE;
	
	


}
