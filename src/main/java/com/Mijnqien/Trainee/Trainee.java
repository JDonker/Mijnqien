package com.Mijnqien.Trainee;

import java.util.LinkedHashSet;
import java.util.Set;
import com.Mijnqien.Gebruiker;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trainee extends Gebruiker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String voornaam;
	String achternaam;
	//Profiel profiel;
	int personeelsnummer;
	String emailAdres;
	//Set<UrenForm> urenFormulieren = new LinkedHashSet<>();
	//Set<DeclaratieForm> declaratieFormulieren = new LinkedHashSet<>();

	public String getEmailAdres() {
		return emailAdres;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getAchternaam() {
		return achternaam;
	}

	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}

	//public Profiel getProfiel() {
	//	return profiel;
	//}

	//public void setProfiel(Profiel profiel) {
//		this.profiel = profiel;
	//}

	public int getPersoneelsnummer() {
		return personeelsnummer;
	}

	public void setPersoneelsnummer(int personeelsnummer) {
		this.personeelsnummer = personeelsnummer;
	}

}
