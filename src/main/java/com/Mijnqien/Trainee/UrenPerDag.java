package com.Mijnqien.Trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrenPerDag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	long id;
	String opdracht;
	int overwerk;
	int training;
	int ziek;
	int verlof;
	String VerklaringOverig;
	int overig;
	String datum;

	public String getOpdracht() {
		return opdracht;
	}

	public void setOpdracht(String opdracht) {
		this.opdracht = opdracht;
	}

	public int getOverwerk() {
		return overwerk;
	}

	public void setOverwerk(int overwerk) {
		this.overwerk = overwerk;
	}

	public int getTraining() {
		return training;
	}

	public void setTraining(int training) {
		this.training = training;
	}

	public int getZiek() {
		return ziek;
	}

	public void setZiek(int ziek) {
		this.ziek = ziek;
	}

	public int getVerlof() {
		return verlof;
	}

	public void setVerlof(int verlof) {
		this.verlof = verlof;
	}

	public String getVerklaringOverig() {
		return VerklaringOverig;
	}

	public void setVerklaringOverig(String verklaringOverig) {
		VerklaringOverig = verklaringOverig;
	}

	public int getOverig() {
		return overig;
	}

	public void setOverig(int overig) {
		this.overig = overig;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

}
