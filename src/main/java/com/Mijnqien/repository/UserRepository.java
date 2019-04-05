package com.Mijnqien.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {

}