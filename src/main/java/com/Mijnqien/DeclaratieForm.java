package com.Mijnqien;

import java.time.LocalDate;
import java.util.ArrayList;

public class DeclaratieForm {
	int prijsPerKilometer;
	String naam;
	String masterclassEditie;
	int maand; //of enum?
	String IBAN;
	LocalDate datum;
	ArrayList <Declaratie> declaraties;
	int totaalBedrag;
	int totaalBTW;
	int totaalTotaal;
	int totaalReisKosten;
	int totaalKilometers;
}
