package com.Mijnqien.Trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Reis {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id;				// unieke identifier
	
	@NotBlank
	LocalDate datum;
	@NotBlank
	String van;
	@NotBlank
	String naar;
	boolean OV;
	int kilometers;
	int OVKosten;
}
