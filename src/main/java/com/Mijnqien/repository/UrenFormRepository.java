package com.Mijnqien.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.domain.trainee.DeclaratieForm;
import com.Mijnqien.domain.trainee.Stat;
import com.Mijnqien.domain.trainee.UrenForm;

@Repository
public interface UrenFormRepository extends CrudRepository <UrenForm, Long> {
	
	 Iterable<UrenForm> findAllByStat(Stat stat);

}
