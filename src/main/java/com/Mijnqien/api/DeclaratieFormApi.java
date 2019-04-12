package com.Mijnqien.api;

import java.time.LocalDate;

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
import com.Mijnqien.Ondersteunend.EmailServer;
import com.Mijnqien.Ondersteunend.ReadProperties;
import com.Mijnqien.domain.trainee.DeclaratieForm;
import com.Mijnqien.domain.trainee.Stat;
import com.Mijnqien.domain.trainee.UrenForm;
import com.Mijnqien.service.DeclaratieFormService;
import com.Mijnqien.service.DeclaratieService;


//adress in de api
@Path("/DeclaratieForm")
@Component
public class DeclaratieFormApi {
	
	@Autowired
	DeclaratieFormService declaratieFormService;
	
	@Autowired
	DeclaratieService declaratieService;
	
	@Autowired
	EmailServer emailserver; 
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listGroep(){
		Iterable <DeclaratieForm> gebruikers = declaratieFormService.findAlleDeclaratieForms();
		return Response.ok(gebruikers).build();
	}
	
	
	@POST
	@Path("/newform/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postDeclaratieForm(DeclaratieForm declaratieForm) {
		// check of een user al declaratie formulier voor bepaalde maand heeft
		DeclaratieForm declaratieFormGesaved = declaratieFormService.save(declaratieForm);
		return Response.accepted(declaratieFormGesaved.getId()).build();
	}
	
	@PUT
	@Path("/verzend/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response verzendDeclaratieForm(@PathParam("FormID") long FormID) {
		// bouw check in of het een bepaalde gebruiker is
		try {
			DeclaratieForm decForm= declaratieFormService.findById(FormID);
			if (decForm.getStatus()==Stat.INAFWACHTING) {
				decForm.setStatus(Stat.INGEDIEND);
				ReadProperties.readConfig();
				decForm = declaratieFormService.save(decForm);
				emailserver.send(ReadProperties.setAdminMail,"Gebruikersnaam" + "declaratie " + decForm.getMaand().getMonth().toString() + " " + decForm.getMaand().getYear(),"");
				return Response.status(Status.ACCEPTED).build();
			}
			return Response.status(Status.BAD_REQUEST).build();
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	
	@PUT
	@Path("/verwerk/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response verwerkDeclaratieForm(@PathParam("FormID") long FormID) {
		// check of iemand admin is
		
		try {
			DeclaratieForm decForm= declaratieFormService.findById(FormID);
			if (decForm.getStatus()==Stat.INGEDIEND) {
				decForm.setStatus(Stat.GOEDGEKEURD);
				ReadProperties.readConfig();
				decForm = declaratieFormService.save(decForm);
				emailserver.send("jasperdonker@gmail.com","Declaratie " + decForm.getMaand().getMonth().toString() + " " + decForm.getMaand().getYear() + " goedgekeurd!","");
				return Response.status(Status.ACCEPTED).build();
			}
			return Response.status(Status.BAD_REQUEST).build();
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	@PUT
	@Path("/weiger/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response weigerDeclaratieForm(@PathParam("FormID") long FormID) {
		// check of iemand admin is
		
		try {
			DeclaratieForm decForm= declaratieFormService.findById(FormID);
			if (decForm.getStatus()==Stat.INGEDIEND) {
				decForm.setStatus(Stat.WIJZIGEN);
				ReadProperties.readConfig();
				decForm = declaratieFormService.save(decForm);
				emailserver.send("jasperdonker@gmail.com","Declaratie " + decForm.getMaand().getMonth().toString() + " " + decForm.getMaand().getYear() + " afgekeurd!","");
				return Response.status(Status.ACCEPTED).build();
			}
			return Response.status(Status.BAD_REQUEST).build();
		} catch (DeclaratieFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Path("/admin/inafwachting")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<DeclaratieForm> getDeclaratieFormsInAfwachting(){
		Iterable<DeclaratieForm> decForms = declaratieFormService.findAllByStat(Stat.INAFWACHTING);
		return decForms;
	}
	
	@GET
	@Path("/admin/ingediend")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<DeclaratieForm> getDeclaratieFormsIngediend(){
		Iterable<DeclaratieForm> decForms =declaratieFormService.findAllByStat(Stat.INGEDIEND);
		return decForms;
	}
	
	@GET
	@Path("/admin/wijzigen")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<DeclaratieForm> getDeclaratieFormsWijzigen(){
		Iterable<DeclaratieForm> decForms =declaratieFormService.findAllByStat(Stat.WIJZIGEN);
		return decForms;
	}
	
	@GET
	@Path("/admin/goedgekeurd")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<DeclaratieForm> getDeclaratieFormsGoedgekeurd(){
		Iterable<DeclaratieForm> decForms =declaratieFormService.findAllByStat(Stat.GOEDGEKEURD);
		return decForms;
	}
	
}