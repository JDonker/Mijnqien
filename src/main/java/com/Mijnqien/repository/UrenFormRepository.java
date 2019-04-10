package com.Mijnqien.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.Trainee.Stat;
import com.Mijnqien.Trainee.UrenForm;

@Repository
public interface UrenFormRepository extends CrudRepository <UrenForm, Long> {
	
	 Iterable<UrenForm> findAllByStat(Stat stat);

}
