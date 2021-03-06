package com.Mijnqien.api;

import java.time.LocalDate;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Exceptions.DeclaratieFormNotFoundException;
import com.Mijnqien.Exceptions.ReisNotFoundException;
import com.Mijnqien.domain.trainee.DeclaratieForm;
import com.Mijnqien.domain.trainee.Reis;
import com.Mijnqien.service.DeclaratieFormService;
import com.Mijnqien.service.ReisService;

@Path("/DeclaratieForm/Reis")
@Component
public class ReisApi {
	
	@Autowired
	ReisService reisService;
	
	@Autowired
	DeclaratieFormService declaratieFormService;
	
	@GET
	@Path("/{FormID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGroep(@PathParam("FormID") long FormID) {
		try {
			DeclaratieForm decForm= declaratieFormService.findById(FormID);
			return Response.ok(decForm.getReizen()).build();
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Path("/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postDeclaratie(Reis reis,@PathParam("FormID") long FormID) {
		if(reis.getDatum()==null) {
			reis.setDatum(LocalDate.now());
		}
		try {
			DeclaratieForm decForm= declaratieFormService.findById(FormID);
			if (decForm.bewerkbaar()) {
				Reis reisSaved= reisService.saveInForm(reis, decForm);	
				return Response.accepted(reisSaved.getId()).build();
			} else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response putDeclaratie(@PathParam("FormID") long FormID,Reis reis) {
		try {
			Reis gevondenreis = reisService.findById(reis.getId());
			DeclaratieForm decForm= declaratieFormService.findById(FormID);
			if (decForm.getReizen().contains(gevondenreis)&&decForm.bewerkbaar()) {
				Reis geupdateReis = reisService.Update(reis);
				return Response.accepted(geupdateReis.getId()).build();
			}
			return Response.status(Status.BAD_REQUEST).build();
		} catch (DeclaratieFormNotFoundException|ReisNotFoundException e ) {
			return Response.status(Status.NOT_FOUND.getStatusCode(),e.toString()).build();
		}
	}
}


