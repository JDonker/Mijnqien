package com.Mijnqien.Trainee;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UrenForm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	long id;
	//Set <UrenPerDag> uren;
	int maand; //of enum?
	//Trainee trainee;
	String opdracht;
	
}
