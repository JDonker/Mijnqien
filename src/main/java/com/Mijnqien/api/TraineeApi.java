package com.Mijnqien.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Trainee.DeclaratieForm;
import com.Mijnqien.Trainee.Profiel;
import com.Mijnqien.Trainee.Trainee;
import com.Mijnqien.service.DeclaratieFormService;
import com.Mijnqien.service.ProfielService;
import com.Mijnqien.service.TraineeService;

@Component
@Path("trainee")

public class TraineeApi {

	@Autowired
	TraineeService traineeService;
	
	@Autowired
	DeclaratieFormService declaratieFormService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Trainee> getTrainees(){
		Iterable<Trainee> trainees = traineeService.findAlleTrainees();
		return trainees;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("trainee/{traineeID}/declaratie/{FormID}")
	public Response getTraineeDeclaraties(@PathParam("traineeID") Long traineeID,@PathParam("FormID") Long declaratieFormID ){
		try {
			Trainee dezeTrainee = traineeService.findTraineeById(traineeID);
			DeclaratieForm ditFormulier = declaratieFormService.findById(declaratieFormID);
			if(dezeTrainee.getDeclaratieFormulieren().contains(ditFormulier)) {
				return Response.ok(ditFormulier).build();
			} 
			return Response.status(Status.BAD_REQUEST).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND.getStatusCode(),e.getMessage()).build();
		}
	}
	
	@POST
	public Response inDatabaseStoppen(Trainee trainee) {
		Trainee tr2 = traineeService.saveTrainee(trainee);
		return Response.ok(tr2).build();
	}
}

