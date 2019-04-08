package com.Mijnqien.Trainee;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class UrenForm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	long id;
	String opdracht;
	public Stat getStat() {
		return stat;
	}
	public void setStat(Stat stat) {
		this.stat = stat;
	}
	LocalDate maand;
	Stat stat = Stat.INAFWACHTING;
	
	@OneToMany(fetch = FetchType.EAGER)
	Set <UrenPerDag> urenPerDag = new LinkedHashSet<>();
	
	public Set<UrenPerDag> getUrenPerDag() {
		return urenPerDag;
	}
	public void setUrenPerDag(Set<UrenPerDag> urenPerDag) {
		this.urenPerDag = urenPerDag;
	}
	
	//Trainee trainee;

	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getMaand() {
		return maand;
	}
	public void setMaand(LocalDate maand) {
		this.maand = maand;
	}
	public String getOpdracht() {
		return opdracht;
	}
	public void setOpdracht(String opdracht) {
		this.opdracht = opdracht;
	}

	
}
