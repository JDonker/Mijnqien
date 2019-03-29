package com.Mijnqien.Trainee;

import java.util.LinkedHashSet;
import java.util.Set;
import com.Mijnqien.Gebruiker;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Trainee extends Gebruiker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String voornaam;
	private String achternaam;
	private String tussenvoegsel;
	//Profiel profiel;
	private int personeelsnummer;
	//Set<UrenForm> urenFormulieren = new LinkedHashSet<>();
	@OneToMany
	@JsonIgnore
	Set<DeclaratieForm> declaratieFormulieren = new LinkedHashSet<>();

	public Set<DeclaratieForm> getDeclaratieFormulieren() {
		return declaratieFormulieren;
	}

	public void setDeclaratieFormulieren(Set<DeclaratieForm> declaratieFormulieren) {
		this.declaratieFormulieren = declaratieFormulieren;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
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


//	public Profiel getProfiel() {
//		return profiel;
//	}
//
//	public void setProfiel(Profiel profiel) {
//		this.profiel = profiel;
//	}


	public int getPersoneelsnummer() {
		return personeelsnummer;
	}

	public void setPersoneelsnummer(int personeelsnummer) {
		this.personeelsnummer = personeelsnummer;
	}

}
