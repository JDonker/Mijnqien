package com.Mijnqien;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Gebruiker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	String gebruikersnaam;
	String wachtwoord;
	String rol; // trainee of admin

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}

}
