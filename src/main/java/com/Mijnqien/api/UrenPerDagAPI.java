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
import com.Mijnqien.Exceptions.UrenPerDagNotFoundException;
import com.Mijnqien.domain.trainee.UrenForm;
import com.Mijnqien.domain.trainee.UrenPerDag;
import com.Mijnqien.service.UrenFormService;
import com.Mijnqien.service.UrenPerDagService;

@Component
@Path("urenperdag")

public class UrenPerDagAPI {

	@Autowired
	UrenPerDagService urenPerDagService;
	
	@Autowired
	UrenFormService urenFormService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<UrenPerDag> getUrenPerDag(){
		Iterable<UrenPerDag> urenPerDag = urenPerDagService.findAlleUrenPerDag();
		return urenPerDag;
	}

	@GET
	@Path("/{UrenFormID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUrenPerDag(@PathParam("UrenFormID") long UrenFormID){
		try {
			UrenPerDag urenPerDag = urenPerDagService.findById(UrenFormID);
			return Response.ok(urenPerDag).build();
		} catch(UrenPerDagNotFoundException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inDatabaseStoppen(UrenPerDag urenPerDag) {
		UrenPerDag urenPerDag2 = urenPerDagService.saveUrenPerDag(urenPerDag);
		return Response.ok(urenPerDag2).build();
	}
	
	@POST
	@Path("/{UrenFormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postUrenPerDag(UrenPerDag urenPerDag,@PathParam("UrenFormID") long UrenFormID) {
		try {
			UrenForm urenFormOpt = urenFormService.findById(UrenFormID);
			UrenForm urenForm= urenFormOpt;
			UrenPerDag urenPerDagsaved= urenPerDagService.saveInForm(urenPerDag, urenForm);	
			return Response.accepted().build();
		} catch (UrenFormNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@PUT
	@Path("/{UrenFormID}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response putUrenPerDag(UrenPerDag urenPerDag,@PathParam("UrenFormID") long UrenFormID) {
				
		try {
			UrenForm urenFormOpt = urenFormService.findById(UrenFormID);
			UrenPerDag gevondenUrenPerDag = urenPerDagService.findById(urenPerDag.getId());
			UrenForm urenForm= urenFormOpt;
			if (urenForm.getUrenPerDag().contains(gevondenUrenPerDag)) {
				UrenPerDag geupdateUrenPerDag = urenPerDagService.Update(urenPerDag);
				return Response.accepted(geupdateUrenPerDag.getId()).build();
			}
			return Response.status(Status.BAD_REQUEST).build();
		} catch (UrenFormNotFoundException|UrenPerDagNotFoundException e ) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response putUrenAndroid(UrenPerDag urenPerDag) {	
		try {
				UrenPerDag gevondenUrenPerDag = urenPerDagService.findById(urenPerDag.getId());
				gevondenUrenPerDag  = urenPerDagService.Update(urenPerDag);
				return Response.accepted(gevondenUrenPerDag).build();
		} catch (UrenPerDagNotFoundException e ) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	
	
}



