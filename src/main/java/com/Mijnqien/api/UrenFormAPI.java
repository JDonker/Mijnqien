package com.Mijnqien.api;

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

import com.Mijnqien.Exceptions.UrenFormNotFoundException;
import com.Mijnqien.Ondersteunend.EmailServer;
import com.Mijnqien.Ondersteunend.ReadProperties;
import com.Mijnqien.Trainee.UrenForm;
import com.Mijnqien.Trainee.UrenPerDag;
import com.Mijnqien.service.UrenFormService;
import com.Mijnqien.Trainee.Stat;

@Component
@Path("urenform")

public class UrenFormAPI {

	@Autowired
	UrenFormService urenFormService;
	
	@Autowired
	EmailServer emailserver; 

	@GET
	@Produces(MediaType.APPLICATION_JSON)

	public Iterable<UrenForm> getUrenForms() {
		Iterable<UrenForm> urenForms = urenFormService.findAlleUrenForms();
		return urenForms;
	}
	
	@GET
	@Path("/admin/")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<UrenForm> getUrenFormsInAfwachting(){
		Iterable<UrenForm> urenForms =urenFormService.findAlleByStat(Stat.INAFWACHTING);
		return urenForms;
	}
	
	@GET
	@Path("/admin2/")
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<UrenForm> getUrenFormsIngediend(){
		Iterable<UrenForm> urenForms =urenFormService.findAlleByStat(Stat.INGEDIEND);
		return urenForms;
	}
	
	
	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inDatabaseStoppen(UrenForm urenForm) {
		UrenForm urenForm2 = urenFormService.saveUrenForm(urenForm);
		return Response.ok(urenForm2).build();
	}

	@PUT
	@Path("/verzend/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response verzendUrenForm(@PathParam("FormID") long FormID) {
		try {
			UrenForm urenForm = urenFormService.findById(FormID);
			if(urenForm.getStat() == Stat.INAFWACHTING) {
				urenForm.setStat(Stat.INGEDIEND);
				ReadProperties.readConfig();
				urenForm = urenFormService.saveUrenForm(urenForm);
				emailserver.send(ReadProperties.setAdminMail,"Gebruikersnaam" + "urenForm " + urenForm.getMaand().getMonth().toString() + " " + urenForm.getMaand().getYear(),"");
				return Response.status(Status.ACCEPTED).build();
			}
			return Response.status(Status.BAD_REQUEST).build();			
		}catch(UrenFormNotFoundException e){
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@PUT
	@Path("/afkeur/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response afkeurUrenForm(@PathParam("FormID") long FormID) {
		try {
			UrenForm urenForm = urenFormService.findById(FormID);
			if(urenForm.getStat() == Stat.INGEDIEND) {
				urenForm.setStat(Stat.WIJZIGEN);
				ReadProperties.readConfig();
				urenForm = urenFormService.saveUrenForm(urenForm);
				emailserver.send(ReadProperties.setAdminMail,"Gebruikersnaam" + "urenForm " + urenForm.getMaand().getMonth().toString() + " " + urenForm.getMaand().getYear(),"");
				return Response.status(Status.ACCEPTED).build();
			}
			return Response.status(Status.BAD_REQUEST).build();			
		}catch(UrenFormNotFoundException e){
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
	
	@PUT
	@Path("/goedkeur/{FormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response goedkeurUrenForm(@PathParam("FormID") long FormID) {
		try {
			UrenForm urenForm = urenFormService.findById(FormID);
			if(urenForm.getStat() == Stat.INGEDIEND) {
				urenForm.setStat(Stat.GOEDGEKEURD);
				ReadProperties.readConfig();
				urenForm = urenFormService.saveUrenForm(urenForm);
				emailserver.send(ReadProperties.setAdminMail,"Gebruikersnaam" + "urenForm " + urenForm.getMaand().getMonth().toString() + " " + urenForm.getMaand().getYear(),"");
				return Response.status(Status.ACCEPTED).build();
			}
			return Response.status(Status.BAD_REQUEST).build();			
		}catch(UrenFormNotFoundException e){
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}

}
