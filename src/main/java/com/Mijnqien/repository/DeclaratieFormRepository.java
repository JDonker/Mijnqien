package com.Mijnqien.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.Trainee.Stat;

public interface DeclaratieFormRepository extends CrudRepository <DeclaratieForm, Long>  {
	
	Iterable<DeclaratieForm> findAllByStatus(Stat status);

}
