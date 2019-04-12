package com.Mijnqien.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.domain.trainee.Trainee;

@Repository
public interface TraineeRepository extends CrudRepository <Trainee, Long> {
	  Optional<Trainee> findByEmailAdres(String email);
}

