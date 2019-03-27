package com.Mijnqien.Trainee;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class DeclaratieForm {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id;				// unieke identifier
	int prijsPerKilometer;			// moet uit een database komen
	String naam;					// fetch van tainee op moment van wijzigen/indienen
	String masterclassEditie;		// fetch van trainee op moment van wijzigen/indienen
	LocalDate maand;				// automatisch genereren op moment van van aanmaken
	String IBAN;					// fetch van trainee op moment van wijzigen/indienen
	@OneToMany(fetch = FetchType.EAGER)
	Set <Declaratie> declaraties = new LinkedHashSet<>();
	int totaalBedrag;
	int totaalBTW;
	int totaalTotaal;
	int totaalReisKosten;
	int totaalKilometers;
	
}
