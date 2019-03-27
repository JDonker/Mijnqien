package com.Mijnqien.api;

import java.util.Optional;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Exceptions.DeclaratieFormNotFoundException;
import com.Mijnqien.Exceptions.DeclaratieNotFoundException;
import com.Mijnqien.Trainee.Declaratie;
import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.service.DeclaratieFormService;
import com.Mijnqien.service.DeclaratieService;

@Path("/DeclaratieForm/")
@Component
public class DeclaratieEndpoint {
	
	@Autowired
	DeclaratieService declaratieService;
	
	@Autowired
	DeclaratieFormService declaratieFormService;
	
	@GET
	@Path("/{FormID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGroep(@PathParam("FormID") long FormID) {
		Optional<DeclaratieForm> decFormOpt = declaratieFormService.findById(FormID);
		try {
			DeclaratieForm decForm= decFormOpt.orElseThrow(DeclaratieNotFoundException::new);
			return Response.ok(decForm.getDeclaraties()).build();
		} catch (DeclaratieNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postDeclaratie(Declaratie declaratie,@PathParam("FormID") long FormID) {
		Optional<DeclaratieForm> decFormOpt = declaratieFormService.findById(FormID);
		try {
			DeclaratieForm decForm= decFormOpt.orElseThrow(DeclaratieFormNotFoundException::new);
			Declaratie declaratiesaved= declaratieService.saveInForm(declaratie, decForm);	
			return Response.accepted().build();
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	
}
