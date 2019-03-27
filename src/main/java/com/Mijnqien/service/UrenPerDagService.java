package com.Mijnqien.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.UrenPerDag;
import com.Mijnqien.repository.UrenPerDagRepository;

@Component
@Transactional

public class UrenPerDagService {
	@Autowired
	UrenPerDagRepository urenPerDagRepository;
	
	public Iterable<UrenPerDag> findAlleUrenPerDag(){
		Iterable<UrenPerDag> resultaat = urenPerDagRepository.findAll();
		return resultaat;
	}

	
}
