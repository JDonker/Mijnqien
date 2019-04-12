package com.Mijnqien.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Exceptions.UrenFormNotFoundException;
import com.Mijnqien.domain.trainee.Stat;
import com.Mijnqien.domain.trainee.UrenForm;
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
	
	public Iterable<UrenForm> findAlleByStat(Stat stat){
		Iterable<UrenForm> resultaat = urenFormRepository.findAllByStat(stat);
		return resultaat;
	}

	public UrenForm saveUrenForm(UrenForm urenForm) {
	return urenFormRepository.save(urenForm);

}
	
	public UrenForm findById(long id) throws UrenFormNotFoundException{
	UrenForm resultaat = urenFormRepository.findById(id).orElseThrow(UrenFormNotFoundException::new);
	return resultaat;
}
	
}
