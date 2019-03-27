package com.Mijnqien.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.Profiel;
import com.Mijnqien.service.ProfielService;

@Component
@Path("profiel")

public class ProfielAPI {

	@Autowired
	ProfielService profielService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public Iterable<Profiel> getProfielen(){
		Iterable<Profiel> profielen = profielService.findAlleProfielen();
		return profielen;
	}
	
}
