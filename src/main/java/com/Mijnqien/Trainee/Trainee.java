package com.Mijnqien.Trainee;

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
	String voornaam;
	String achternaam;
	Profiel profiel;
	int personeelsnummer;
	long id;
	String emailAdres;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Profiel getProfiel() {
		return profiel;
	}

	public void setProfiel(Profiel profiel) {
		this.profiel = profiel;
	}

	public int getPersoneelsnummer() {
		return personeelsnummer;
	}

	public void setPersoneelsnummer(int personeelsnummer) {
		this.personeelsnummer = personeelsnummer;
	}

	public Set<UrenForm> getUrenFormulieren() {
		return urenFormulieren;
	}

	public void setUrenFormulieren(Set<UrenForm> urenFormulieren) {
		this.urenFormulieren = urenFormulieren;
	}

	public Set<DeclaratieForm> getDeclaratieFormulieren() {
		return declaratieFormulieren;
	}

	public void setDeclaratieFormulieren(Set<DeclaratieForm> declaratieFormulieren) {
		this.declaratieFormulieren = declaratieFormulieren;
	}

	Set<UrenForm> urenFormulieren;
	Set<DeclaratieForm> declaratieFormulieren;
}
