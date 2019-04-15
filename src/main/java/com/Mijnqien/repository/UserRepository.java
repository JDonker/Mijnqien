package com.Mijnqien.repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.Mijnqien.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
	
	User findByUsername(String username);
}