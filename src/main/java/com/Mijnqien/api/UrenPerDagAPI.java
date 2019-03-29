package com.Mijnqien.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.UrenPerDag;
import com.Mijnqien.service.UrenPerDagService;

@Component
@Path("urenperdag")

public class UrenPerDagAPI {

	@Autowired
	UrenPerDagService urenPerDagService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public Iterable<UrenPerDag> getUrenPerDag(){
		Iterable<UrenPerDag> urenPerDag = urenPerDagService.findAlleUrenPerDag();
		return urenPerDag;
	}
}

