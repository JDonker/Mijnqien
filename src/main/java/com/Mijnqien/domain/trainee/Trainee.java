package com.Mijnqien.domain.trainee;

import java.util.LinkedHashSet;
import java.util.Set;
import com.Mijnqien.Gebruiker;
import com.Mijnqien.User;
import com.Mijnqien.Exceptions.TraineeNotFoundException;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Trainee implements  Gebruiker {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String voornaam="";
	private String achternaam="";
	private String tussenvoegsel="";
	@NotBlank
	String emailAdres;
	@JsonIgnore
	@OneToOne
	User user;
	
	@OneToMany(mappedBy = "trainee")
	@JsonIgnoreProperties("trainee")
	Set<DeclaratieForm> declaratieFormulieren = new LinkedHashSet<>();
	
	@OneToMany(mappedBy = "trainee")
	@JsonIgnoreProperties("trainee")
	Set<UrenForm> urenFormulieren = new LinkedHashSet<>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//Profiel profiel;
	private int personeelsnummer;
	//Set<UrenForm> urenFormulieren = new LinkedHashSet<>();


	public Set<UrenForm> getUrenFormulieren() {
		return urenFormulieren;
	}

	public void setUrenFormulieren(Set<UrenForm> urenFormulieren) {
		this.urenFormulieren = urenFormulieren;
	}




	public Set<DeclaratieForm> getDeclaratieFormulieren() {
		return declaratieFormulieren;
	}

	public void setDeclaratieFormulieren(Set<DeclaratieForm> declaratieFormulieren) {
		this.declaratieFormulieren = declaratieFormulieren;
	}

	public String getEmailAdres() {
		return emailAdres;
	}

	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTussenvoegsel() {
		return tussenvoegsel;
	}

	public void setTussenvoegsel(String tussenvoegsel) {
		this.tussenvoegsel = tussenvoegsel;
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
	
	public static Trainee getSecuredTrainee() throws TraineeNotFoundException{
	//	SecurityContext securityContext = SecurityContextHolder.getContext();
	//	return trainee = traineeService.findByEmailAdres(securityContext.getAuthentication().getUsername());
		return new Trainee();
	}
	

}
