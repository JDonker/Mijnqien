package com.Mijnqien.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Mijnqien.Exceptions.DeclaratieFormNotFoundException;
import com.Mijnqien.Exceptions.ReisNotFoundException;
import com.Mijnqien.Trainee.Reis;
import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.repository.DeclaratieFormRepository;
import com.Mijnqien.repository.ReisRepository;


@Service
public class ReisService {

	@Autowired
	DeclaratieFormRepository declaratieFormRepository;
	
	@Autowired 
	ReisRepository reisRepository;
	
	public Reis findById(Long id) throws ReisNotFoundException{
		Optional<Reis> reisOPT = reisRepository.findById(id);
		return reisOPT.orElseThrow(ReisNotFoundException::new);	
	}
	
	@Transactional
	public Reis save(Reis reis)  {
		return reisRepository.save(reis);
	}
	
	
	@Transactional
	public Reis saveInForm(Reis reis,DeclaratieForm form) throws DeclaratieFormNotFoundException  {
		Optional<DeclaratieForm> AddToThisOpt = declaratieFormRepository.findById(form.getId());
		DeclaratieForm AddToThis=AddToThisOpt.orElseThrow(DeclaratieFormNotFoundException::new);
		Reis savedReis = this.save(reis);
		AddToThis.getReizen().add(savedReis);
		declaratieFormRepository.save(AddToThis);
		return savedReis;
	}
	
	@Transactional
	public Reis Update(Reis reis) throws ReisNotFoundException {
		Optional<Reis> UpdatableReisOpt = reisRepository.findById(reis.getId());
		Reis UpdatableReis = UpdatableReisOpt.orElseThrow(ReisNotFoundException::new);
		// welke velden mogen later nog aangepast worden
		UpdatableReis=reis;
		return save(UpdatableReis);
	}
	
}
