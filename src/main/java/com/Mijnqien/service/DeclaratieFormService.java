package com.Mijnqien.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.Trainee.Trainee;
import com.Mijnqien.repository.DeclaratieFormRepository;
import com.Mijnqien.repository.DeclaratieRepository;
import com.Mijnqien.repository.ReisRepository;

@Service
public class DeclaratieFormService {
	
	@Autowired 
	DeclaratieFormRepository declaratieFormRepository;
	
	public Iterable<DeclaratieForm> findAlleDeclaratieForms(){
		Iterable<DeclaratieForm> resultaat = declaratieFormRepository.findAll();
		return resultaat;
	}
	
	public Optional<DeclaratieForm> findById(long id){
		Optional<DeclaratieForm> resultaat = declaratieFormRepository.findById(id);
		return resultaat;
	}
	
	public DeclaratieForm save(DeclaratieForm form){
		DeclaratieForm resultaat = declaratieFormRepository.save(form);
		return resultaat;
	}
	
	
	
	
	
}
