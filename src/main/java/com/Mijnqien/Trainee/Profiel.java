package com.Mijnqien.Trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.swing.text.html.ImageView;

@Entity
public class Profiel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	//ImageView foto;
	String adres;
	LocalDate geboorteDatum;
	String IBAN;
}
