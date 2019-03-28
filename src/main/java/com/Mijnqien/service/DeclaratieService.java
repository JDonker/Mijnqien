package com.Mijnqien.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Transient;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mijnqien.Exceptions.DeclaratieFormNotFoundException;
import com.Mijnqien.Exceptions.DeclaratieNotFoundException;
import com.Mijnqien.Trainee.Declaratie;
import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.repository.DeclaratieFormRepository;
import com.Mijnqien.repository.DeclaratieRepository;
import com.Mijnqien.repository.ReisRepository;

@Service
public class DeclaratieService {
	
	@Autowired
	DeclaratieFormRepository declaratieFormRepository;
	
	@Autowired 
	DeclaratieRepository declaratieRepository;
	
	@Autowired 
	ReisRepository reisRepository;
	
	public Declaratie findById(Long id) throws DeclaratieNotFoundException{
		Optional<Declaratie> declaratieOPT = declaratieRepository.findById(id);
		return declaratieOPT.orElseThrow(DeclaratieNotFoundException::new);
				
	}
	
	@Transactional
	public Declaratie save(Declaratie declaratie)  {
		return declaratieRepository.save(declaratie);
	}
	
	
	@Transactional
	public Declaratie saveInForm(Declaratie declaratie,DeclaratieForm form) throws DeclaratieFormNotFoundException  {
		Optional<DeclaratieForm> AddToThisOpt = declaratieFormRepository.findById(form.getId());
		DeclaratieForm AddToThis=AddToThisOpt.orElseThrow(DeclaratieFormNotFoundException::new);
		Declaratie savedDeclaratie = this.save(declaratie);
		AddToThis.getDeclaraties().add(savedDeclaratie);
		declaratieFormRepository.save(AddToThis);
		return savedDeclaratie;
	}
	
	@Transactional
	public Declaratie Update(Declaratie declaratie) throws DeclaratieNotFoundException {
		Optional<Declaratie> UpdatableDeclaratieOpt = declaratieRepository.findById(declaratie.getId());
		Declaratie UpdatableDeclaratie = UpdatableDeclaratieOpt.orElseThrow(DeclaratieNotFoundException::new);
		// welke velden mogen later nog aangepast worden
		UpdatableDeclaratie=declaratie;
		return save(UpdatableDeclaratie);
	}
	
}
