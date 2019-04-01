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
	
	LocalDate datum;
	String omschrijving;
	int bedrag;
	int bedragminbtw;
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
	public int getBedragminbtw() {
		return bedragminbtw;
	}
	public void setBedragminbtw(int bedragminbtw) {
		this.bedragminbtw = bedragminbtw;
	}
	
	
}