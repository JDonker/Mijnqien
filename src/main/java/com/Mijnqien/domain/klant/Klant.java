package com.Mijnqien.domain.klant;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.Mijnqien.Gebruiker;
import com.Mijnqien.User;
import com.Mijnqien.domain.trainee.DeclaratieForm;
import com.Mijnqien.domain.trainee.Trainee;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Klant implements  Gebruiker {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String tussenvoegsel;
	private String achternaam;
	private String Bedrijfsnaam;
	@ManyToMany
	Set<Trainee> Trainees = new LinkedHashSet<>();
	@JsonIgnore
	@OneToOne
	User user;
	
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getTussenvoegsel() {
		return tussenvoegsel;
	}
	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public String getBedrijfsnaam() {
		return Bedrijfsnaam;
	}
	public void setBedrijfsnaam(String bedrijfsnaam) {
		Bedrijfsnaam = bedrijfsnaam;
	}
	public Set<Trainee> getTrainees() {
		return Trainees;
	}
	public void setTrainees(Set<Trainee> trainees) {
		Trainees = trainees;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
