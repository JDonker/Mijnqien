package com.Mijnqien.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.service.DeclaratieFormService;


//adress in de api
@Path("/DeclaratieForm")
@Component
public class DeclaratieFormApi {
	
	@Autowired
	DeclaratieFormService declaratieFormService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGroep(){
		Iterable <DeclaratieForm> gebruikers = declaratieFormService.findAlleDeclaratieForms();
		return Response.ok(gebruikers).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postDeclaratieForm(DeclaratieForm declaratieForm) {
		DeclaratieForm declaratieFormGesaved = declaratieFormService.save(declaratieForm);
		return Response.accepted(declaratieFormGesaved.getId()).build();
	}
}
