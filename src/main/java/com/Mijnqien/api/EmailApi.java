package com.Mijnqien.api;

import java.time.LocalDate;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Ondersteunend.EmailServer;
import com.Mijnqien.domain.EmailBericht;
import com.Mijnqien.domain.trainee.DeclaratieForm;
@Path("/Email/")
@Component
public class EmailApi {
	
	LocalDate ld = LocalDate.now();
	
	@Autowired
	EmailServer emailServer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postMail(EmailBericht email) {
		System.out.println(email.getEmail());
		emailServer.send(email);
		return Response.accepted().build();
	}
	
	@POST
	@Path("/herinnering1")
	public void herinnering1DeclaratieForm() {
		emailServer.send("testqien@gmail.com","HERINNERING: Declaratieformulier maand " + ld.getMonthValue(),
		"Over 7 dagen is de maand voorbij en de status van jouw declaratieformulier is INAFWACHTING"
		+ ".\n\n Vergeet niet je declaratieformulier!");
	}
	
	@POST
	@Path("/herinnering2")
	public void herinnering2DeclaratieForm() {
		emailServer.send("testqien@gmail.com","HERINNERING: Declaratieformulier maand " + ld.getMonthValue(),
		"Over 3 dagen is de maand voorbij en de status van jouw declaratieformulier is INAFWACHTING"
		+ ".\n\n Vergeet niet je declaratieformulier!");
	}
	
	@POST
	@Path("/herinnering3")
	public void herinnering3DeclaratieForm() {
		emailServer.send("testqien@gmail.com","HERINNERING: Declaratieformulier maand " + ld.getMonthValue(),
		"De maand is voorbij en de status van jouw declaratieformulier is INAFWACHTING"
		+ ".\n\n Vergeet niet je declaratieformulier!");
	}
	
	@POST
	@Path("/herinnering4")
	public void herinnering4DeclaratieForm() {
		emailServer.send("testqien@gmail.com","HERINNERING: Declaratieformulier maand " + ld.getMonthValue(),
		"Drie dagen geleden was de maand voorbij en de status van jouw declaratieformulier is INAFWACHTING"
		+ ".\n\n Vergeet niet je declaratieformulier!");
	}
	
}
