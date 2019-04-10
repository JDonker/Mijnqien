package com.Mijnqien.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.domain.trainee.UurType;
import com.Mijnqien.repository.UurTypeRepository;

@Component
@Transactional

public class UurTypeService {
	@Autowired
	UurTypeRepository uurTypeRepository;
	
	public Iterable<UurType> findAlleUurTypes(){
		Iterable<UurType> resultaat = uurTypeRepository.findAll();
		return resultaat;
	}

	public UurType saveUurType(UurType uurType) {
	return uurTypeRepository.save(uurType);

}
	
}
