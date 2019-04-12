package com.Mijnqien.domain.trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Profiel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String foto;
	String adres;
	LocalDate geboorteDatum;
	String IBAN;
}
