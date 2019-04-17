package com.Mijnqien.domain.trainee;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
public class UrenForm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	String opdracht;
	String naam;
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public Stat getStat() {
		return stat;
	}
	public void setStat(Stat stat) {
		this.stat = stat;
	}

	LocalDate maand;
	Stat stat = Stat.INAFWACHTING;
	
	@OneToMany(mappedBy = "urenform" , fetch = FetchType.EAGER)
	@JsonIgnoreProperties("urenform")
	Set <UrenPerDag> urenPerDag = new LinkedHashSet<>();
	
	public Set<UrenPerDag> getUrenPerDag() {
		return urenPerDag;
	}
	public void setUrenPerDag(Set<UrenPerDag> urenPerDag) {
		this.urenPerDag = urenPerDag;
	}
	@ManyToOne
	Trainee trainee;

	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + naam.hashCode() + maand.getYear() + maand.getMonthValue();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UrenForm other = (UrenForm) obj;
		if (!naam.equals(other.getNaam()))
			return false;
		if (maand.getYear() != other.getMaand().getYear())
			return false;
		if (maand.getMonthValue() != other.getMaand().getMonthValue())
			return false;
		return true;
	}
	public boolean bewerkbaar() {
		return (this.stat==Stat.INAFWACHTING||this.stat==Stat.WIJZIGEN);	
	}


	
}
