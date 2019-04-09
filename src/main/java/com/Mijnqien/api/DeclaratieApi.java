package com.Mijnqien.api;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

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
import org.springframework.web.bind.annotation.CrossOrigin;

import com.Mijnqien.User;
import com.Mijnqien.Exceptions.DeclaratieFormNotFoundException;
import com.Mijnqien.Exceptions.DeclaratieNotFoundException;
import com.Mijnqien.Trainee.Declaratie;
import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.Trainee.Stat;
import com.Mijnqien.service.DeclaratieFormService;
import com.Mijnqien.service.DeclaratieService;
import com.Mijnqien.service.UserService;


@Path("/DeclaratieForm")
@Component
public class DeclaratieApi {
	
	@Autowired
	DeclaratieService declaratieService;
	
	@Autowired
	DeclaratieFormService declaratieFormService;
	
	@Autowired
	UserService userService;
	
	
	@GET
	@Path("/{FormID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGroep(@PathParam("FormID") long FormID) {
		try {
			DeclaratieForm decForm= declaratieFormService.findById(FormID);
			return Response.ok(decForm.getDeclaraties()).build();
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@CrossOrigin
	@Path("/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postDeclaratie(@PathParam("FormID") long FormID,Declaratie declaratie) {
		System.out.println("hier");
		if(declaratie.getDatum()==null) {
			declaratie.setDatum(LocalDate.now());
		}
		try {
			DeclaratieForm decForm = declaratieFormService.findById(FormID);
			if (decForm.bewerkbaar()) {
				Declaratie declaratiesaved= declaratieService.saveInForm(declaratie, decForm);	
				return Response.accepted().build();
			} else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	//@PreAuthorize("isAuthenticated()")
	@PUT
	@Path("/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response putDeclaratie(Declaratie declaratie,@PathParam("FormID") long FormID) {
		if(declaratie!=null) {
			try  {
				Declaratie gevondenDeclaratie = declaratieService.findById(declaratie.getId());
				User user = new User();	
				DeclaratieForm decForm= declaratieFormService.findById(FormID);
				if (decForm.getDeclaraties().contains(gevondenDeclaratie)&&decForm.bewerkbaar()) {
					Declaratie geupdateDeclaratie = declaratieService.Update(declaratie);
					return Response.accepted(geupdateDeclaratie.getId()).build();
				}
				return Response.status(Status.BAD_REQUEST).build();
			} catch (DeclaratieFormNotFoundException|DeclaratieNotFoundException e ) {
				return Response.status(Status.NOT_FOUND.getStatusCode(),e.toString()).build();
			}
		} 
		return Response.status(Status.BAD_REQUEST).build();
	}
	

}