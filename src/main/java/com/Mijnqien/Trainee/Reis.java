package com.Mijnqien.Trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Reis {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id;				// unieke identifier
	LocalDate datum;
	@NotBlank
	String van;
	@NotBlank
	String naar;
	boolean OV;
	int kilometers;
	int OVKosten;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public String getVan() {
		return van;
	}
	public void setVan(String van) {
		this.van = van;
	}
	public String getNaar() {
		return naar;
	}
	public void setNaar(String naar) {
		this.naar = naar;
	}
	public boolean isOV() {
		return OV;
	}
	public void setOV(boolean oV) {
		OV = oV;
	}
	public int getKilometers() {
		return kilometers;
	}
	public void setKilometers(int kilometers) {
		this.kilometers = kilometers;
	}
	public int getOVKosten() {
		return OVKosten;
	}
	public void setOVKosten(int oVKosten) {
		OVKosten = oVKosten;
	}

}
