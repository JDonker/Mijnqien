package com.Mijnqien.Trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Declaratie {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Id;				// unieke identifier
	
	int nummer;
	LocalDate datum;
	String omschrijving;
	int bedrag;
	int btw;
	int totaal;
	String code;
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	public String getOmschrijving() {
		return omschrijving;
	}
	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	public int getBedrag() {
		return bedrag;
	}
	public void setBedrag(int bedrag) {
		this.bedrag = bedrag;
	}
	public int getBtw() {
		return btw;
	}
	public void setBtw(int btw) {
		this.btw = btw;
	}
	public int getTotaal() {
		return totaal;
	}
	public void setTotaal(int totaal) {
		this.totaal = totaal;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}