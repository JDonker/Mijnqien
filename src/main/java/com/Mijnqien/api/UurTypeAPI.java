package com.Mijnqien.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.domain.trainee.UurType;
import com.Mijnqien.service.UurTypeService;

@Component
@Path("uurtype")

public class UurTypeAPI {

	@Autowired
	UurTypeService uurTypeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	
	public Iterable<UurType> getUurTypes(){
		Iterable<UurType> uurTypes = uurTypeService.findAlleUurTypes();
		return uurTypes;
	}
	
	@POST
	public Response inDatabaseStoppen(UurType uurType) {
		UurType uurType2 = uurTypeService.saveUurType(uurType);
		return Response.ok(uurType2).build();
	}
	
}
