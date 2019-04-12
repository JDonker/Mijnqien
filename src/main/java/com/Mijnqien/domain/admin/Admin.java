package com.Mijnqien.domain.admin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.Mijnqien.Gebruiker;
import com.Mijnqien.User;

@Entity
public class Admin implements Gebruiker{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	String voornaam;
	String achternaam;
	int personeelsnummer;
	@OneToOne
	User user;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public int getPersoneelsnummer() {
		return personeelsnummer;
	}
	public void setPersoneelsnummer(int personeelsnummer) {
		this.personeelsnummer = personeelsnummer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
