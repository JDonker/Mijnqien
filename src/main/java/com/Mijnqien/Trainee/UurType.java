package com.Mijnqien.Trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UurType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String naam;
	double SalarisPercentage;
	LocalDate startdatum;
	LocalDate einddatum;
}
