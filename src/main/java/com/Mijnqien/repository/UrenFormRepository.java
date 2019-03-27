package com.Mijnqien.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.Trainee.UrenForm;

@Repository
public interface UrenFormRepository extends CrudRepository <UrenForm, Long> {

}
