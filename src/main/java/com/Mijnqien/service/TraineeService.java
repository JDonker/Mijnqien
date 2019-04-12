package com.Mijnqien.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Mijnqien.Exceptions.TraineeNotFoundException;
import com.Mijnqien.domain.trainee.Trainee;
import com.Mijnqien.repository.TraineeRepository;


@Transactional
@Service
public class TraineeService {
	@Autowired
	TraineeRepository traineeRepository;
	
	public Iterable<Trainee> findAlleTrainees(){
		Iterable<Trainee> resultaat = traineeRepository.findAll();
		return resultaat;
	}
	
	public Trainee findTraineeById(long id) throws TraineeNotFoundException {
		return traineeRepository.findById(id).orElseThrow(TraineeNotFoundException::new);
	}
	
	public Trainee findTraineeByEmail(String email) throws TraineeNotFoundException {
		return traineeRepository.findByEmailAdres(email).orElseThrow(TraineeNotFoundException::new);
	}


	public Trainee saveTrainee(Trainee trainee) {
	return traineeRepository.save(trainee);

}
	
}
