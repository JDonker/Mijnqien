package com.Mijnqien.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.Mijnqien.domain.trainee.DeclaratieForm;
import com.Mijnqien.domain.trainee.Stat;

public interface DeclaratieFormRepository extends CrudRepository <DeclaratieForm, Long>  {
	
	Iterable<DeclaratieForm> findAllByStatus(Stat status);

}
