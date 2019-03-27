package com.Mijnqien.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.Trainee;
import com.Mijnqien.repository.TraineeRepository;

@Component
@Transactional

public class TraineeService {
	@Autowired
	TraineeRepository traineeRepository;
	
	public Iterable<Trainee> findAlleTrainees(){
		Iterable<Trainee> resultaat = traineeRepository.findAll();
		return resultaat;
	}

	
}
