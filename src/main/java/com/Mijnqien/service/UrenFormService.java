package com.Mijnqien.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.UrenForm;
import com.Mijnqien.repository.UrenFormRepository;

@Component
@Transactional

public class UrenFormService {
	@Autowired
	UrenFormRepository urenFormRepository;
	
	public Iterable<UrenForm> findAlleUrenForms(){
		Iterable<UrenForm> resultaat = urenFormRepository.findAll();
		return resultaat;
	}

	public UrenForm saveUrenForm(UrenForm urenForm) {
	return urenFormRepository.save(urenForm);

}
	
}
