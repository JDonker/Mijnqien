package com.Mijnqien.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.Trainee.Profiel;

@Repository
public interface ProfielRepository extends CrudRepository <Profiel, Long> {

}