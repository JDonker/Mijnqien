package com.Mijnqien.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Exceptions.UrenFormNotFoundException;
import com.Mijnqien.Exceptions.UrenPerDagNotFoundException;
import com.Mijnqien.Trainee.UrenForm;
import com.Mijnqien.Trainee.UrenPerDag;
import com.Mijnqien.repository.UrenFormRepository;
import com.Mijnqien.repository.UrenPerDagRepository;

@Component
@Transactional

public class UrenPerDagService {
	@Autowired
	UrenPerDagRepository urenPerDagRepository;
	
	@Autowired
	UrenFormRepository urenFormRepository;
	
	public Iterable<UrenPerDag> findAlleUrenPerDag(){
		Iterable<UrenPerDag> resultaat = urenPerDagRepository.findAll();
		return resultaat;
	}
	
	public UrenPerDag findById(Long id) throws UrenPerDagNotFoundException{
	Optional<UrenPerDag> urenPerDagOPT = urenPerDagRepository.findById(id);
	return urenPerDagOPT.orElseThrow(UrenPerDagNotFoundException::new);
			
}
	
	public UrenPerDag saveUrenPerDag(UrenPerDag urenPerDag) {
		return urenPerDagRepository.save(urenPerDag);

	}
	
	@Transactional
	public UrenPerDag saveInForm(UrenPerDag urenPerDag, UrenForm form) throws UrenFormNotFoundException  {
		Optional<UrenForm> AddToThisOpt = urenFormRepository.findById(form.getId());
		UrenForm AddToThis=AddToThisOpt.orElseThrow(UrenFormNotFoundException::new);
		UrenPerDag savedUrenPerDag = this.saveUrenPerDag(urenPerDag);
		AddToThis.getUrenPerDag().add(savedUrenPerDag);
		urenFormRepository.save(AddToThis);
		return savedUrenPerDag;
	}

	@Transactional
	public UrenPerDag Update(UrenPerDag urenPerDag) throws UrenPerDagNotFoundException {
		Optional<UrenPerDag> UpdatableUrenPerDagOpt = urenPerDagRepository.findById(urenPerDag.getId());
		UrenPerDag UpdatableUrenPerDag = UpdatableUrenPerDagOpt.orElseThrow(UrenPerDagNotFoundException::new);
		// welke velden mogen later nog aangepast worden
		UpdatableUrenPerDag=urenPerDag;
		return saveUrenPerDag(UpdatableUrenPerDag);
	}
}
