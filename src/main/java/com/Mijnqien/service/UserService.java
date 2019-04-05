package com.Mijnqien.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.User;
import com.Mijnqien.repository.UserRepository;

@Component
@Transactional

public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public Iterable<User> findAlleUsers(){
		Iterable<User> resultaat = userRepository.findAll();
		return resultaat;
	}

	public User saveUser(User user) {
	return userRepository.save(user);

}
	
}
