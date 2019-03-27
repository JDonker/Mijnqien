package com.Mijnqien.Trainee;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UrenPerDag {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	long id;
	String opdracht;
	int overwerk;
	int training;
	String attribute;
	int ziek;
	int verlof;
	String VerklaringOverig;
	int overig;
	LocalDate datum;
}
