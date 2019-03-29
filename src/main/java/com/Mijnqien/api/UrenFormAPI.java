package com.Mijnqien.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	
	@POST
	public Response inDatabaseStoppen(UrenForm urenForm) {
		UrenForm urenForm2 = urenFormService.saveUrenForm(urenForm);
		return Response.ok(urenForm2).build();
	}
	
}
