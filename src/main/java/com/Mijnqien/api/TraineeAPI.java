package com.Mijnqien.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.Trainee;
import com.Mijnqien.service.TraineeService;

@Component
@Path("trainee")
public class TraineeAPI {

	@Autowired
	TraineeService traineeService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Trainee> getTrainees(){
		Iterable<Trainee> trainees = traineeService.findAlleTrainees();
		return trainees;
	}
	
	@POST
	public Response inDatabaseStoppen(Trainee trainee) {
		Trainee trainee2 = traineeService.saveTrainee(trainee);
		return Response.ok(trainee2).build();
	}
	
}
