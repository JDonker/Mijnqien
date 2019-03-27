package com.Mijnqien.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.UrenForm;
import com.Mijnqien.service.UrenFormService;

@Component
@Path("urenform")

public class UrenFormAPI {

	@Autowired
	UrenFormService urenFormService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public Iterable<UrenForm> getUrenForms(){
		Iterable<UrenForm> urenForms = urenFormService.findAlleUrenForms();
		return urenForms;
	}
	
}
