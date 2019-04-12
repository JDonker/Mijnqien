package com.Mijnqien.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.domain.trainee.UrenPerDag;

@Repository
public interface UrenPerDagRepository extends CrudRepository <UrenPerDag, Long> {

}
