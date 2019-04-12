package com.Mijnqien.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mijnqien.Exceptions.DeclaratieFormNotFoundException;
import com.Mijnqien.domain.trainee.DeclaratieForm;
import com.Mijnqien.domain.trainee.Stat;
import com.Mijnqien.domain.trainee.Trainee;
import com.Mijnqien.domain.trainee.UrenForm;
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
	
	public Iterable<DeclaratieForm> findAllByStat(Stat stat){
		Iterable<DeclaratieForm> resultaat = declaratieFormRepository.findAllByStatus(stat);
		return resultaat;
	}
	
	public DeclaratieForm findById(long id) throws DeclaratieFormNotFoundException {
		return declaratieFormRepository.findById(id).orElseThrow(DeclaratieFormNotFoundException::new);

	}
	
	public DeclaratieForm save(DeclaratieForm form){
		DeclaratieForm resultaat = declaratieFormRepository.save(form);
		return resultaat;
	}
}
