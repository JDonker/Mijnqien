package com.Mijnqien.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.Trainee.UurType;

@Repository
public interface UurTypeRepository extends CrudRepository <UurType, Long> {

}
