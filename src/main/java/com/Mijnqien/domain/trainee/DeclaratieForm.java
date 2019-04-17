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

@Entity
public class DeclaratieForm {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long Id;				// unieke identifier
	int prijsPerKilometer;			// moet uit een database komen
	String naam;					// fetch van tainee op moment van wijzigen/indienen
	String masterclassEditie;		// fetch van trainee op moment van wijzigen/indienen
	LocalDate maand;				// automatisch genereren op moment van van aanmaken
	String iBAN;					// fetch van trainee op moment van wijzigen/indienen
	@OneToMany(fetch = FetchType.EAGER)
	Set <Declaratie> declaraties = new LinkedHashSet<>();
	@OneToMany(fetch = FetchType.EAGER)
	Set <Reis> reizen = new LinkedHashSet<>();
	Stat status = Stat.INAFWACHTING;
	
	@ManyToOne
	Trainee trainee;
	
	public Stat getStatus() {
		return status;
	}
	public void setStatus(Stat status) {
		this.status = status;
	}
	int totaalBedrag;
	int totaalBTW;
	int totaalTotaal;
	int totaalReisKosten;
	int totaalKilometers;

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		return result;
	}
	
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public int getPrijsPerKilometer() {
		return prijsPerKilometer;
	}
	public void setPrijsPerKilometer(int prijsPerKilometer) {
		this.prijsPerKilometer = prijsPerKilometer;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getMasterclassEditie() {
		return masterclassEditie;
	}
	public void setMasterclassEditie(String masterclassEditie) {
		this.masterclassEditie = masterclassEditie;
	}
	public LocalDate getMaand() {
		return maand;
	}
	public void setMaand(LocalDate maand) {
		this.maand = maand;
	}
	public String getiBAN() {
		return iBAN;
	}
	public void setiBAN(String iBAN) {
		this.iBAN = iBAN;
	}
	public Set<Declaratie> getDeclaraties() {
		return declaraties;
	}
	public void setDeclaraties(Set<Declaratie> declaraties) {
		this.declaraties = declaraties;
	}
	public Set<Reis> getReizen() {
		return reizen;
	}
	public void setReizen(Set<Reis> reizen) {
		this.reizen = reizen;
	}
	public Trainee getTrainee() {
		return trainee;
	}
	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}
	public int getTotaalBedrag() {
		return totaalBedrag;
	}
	public void setTotaalBedrag(int totaalBedrag) {
		this.totaalBedrag = totaalBedrag;
	}
	public int getTotaalBTW() {
		return totaalBTW;
	}
	public void setTotaalBTW(int totaalBTW) {
		this.totaalBTW = totaalBTW;
	}
	public int getTotaalTotaal() {
		return totaalTotaal;
	}
	public void setTotaalTotaal(int totaalTotaal) {
		this.totaalTotaal = totaalTotaal;
	}
	public int getTotaalReisKosten() {
		return totaalReisKosten;
	}
	public void setTotaalReisKosten(int totaalReisKosten) {
		this.totaalReisKosten = totaalReisKosten;
	}
	public int getTotaalKilometers() {
		return totaalKilometers;
	}
	public void setTotaalKilometers(int totaalKilometers) {
		this.totaalKilometers = totaalKilometers;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeclaratieForm other = (DeclaratieForm) obj;
		if (!naam.equals(other.getNaam()))
			return false;
		if (maand.getYear() != other.getMaand().getYear())
			return false;
		if (maand.getMonthValue() != other.getMaand().getMonthValue())
			return false;
		
		return true;
	}
	public boolean bewerkbaar() {
		return (this.status==Stat.INAFWACHTING||this.status==Stat.WIJZIGEN);	
	}

	
}
