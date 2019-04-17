package com.Mijnqien.domain.trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UurType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	String naam;
	double SalarisPercentage;
	LocalDate startdatum;
	LocalDate einddatum;
}
