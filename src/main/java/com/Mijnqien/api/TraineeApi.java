package com.Mijnqien.api;

import java.time.LocalDate;
import java.util.Random;

import javax.ws.rs.Consumes;
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

import com.Mijnqien.User;
import com.Mijnqien.domain.trainee.DeclaratieForm;
import com.Mijnqien.domain.trainee.Profiel;
import com.Mijnqien.domain.trainee.Trainee;
import com.Mijnqien.domain.trainee.UrenForm;
import com.Mijnqien.domain.trainee.UrenPerDag;
import com.Mijnqien.service.DeclaratieFormService;
import com.Mijnqien.service.ProfielService;
import com.Mijnqien.service.TraineeService;
import com.Mijnqien.service.UrenFormService;
import com.Mijnqien.service.UrenPerDagService;
import com.Mijnqien.service.UserService;

@Component
@Path("trainee")

public class TraineeApi {

	@Autowired
	TraineeService traineeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DeclaratieFormService declaratieFormService;
	
	@Autowired
	UrenPerDagService urenPerDagService;
	
	@Autowired
	UrenFormService urenFormService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Iterable<Trainee> getTrainees(){
		Iterable<Trainee> trainees = traineeService.findAlleTrainees();
		return trainees;
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{traineeID}/declaratie/{FormID}")
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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{traineeID}/declaratie")
	public Response postDeclaratieForm(DeclaratieForm declaratieForm, @PathParam("traineeID") Long traineeID){
		try {
			Trainee dezeTrainee = traineeService.findTraineeById(traineeID);
			DeclaratieForm declaratieFormGesaved = declaratieFormService.save(declaratieForm);
			dezeTrainee.getDeclaratieFormulieren().add(declaratieFormGesaved);
			traineeService.saveTrainee(dezeTrainee);
			return Response.ok(declaratieFormGesaved).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_FOUND.getStatusCode(),e.getMessage()).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{traineeID}/urenform")
	public Response postUrenForm(UrenForm urenForm, @PathParam("traineeID") Long traineeID){
		if (urenForm!=null) {
			try {
				Trainee dezeTrainee = traineeService.findTraineeById(traineeID);
				urenForm.setNaam(dezeTrainee.getEmailAdres());
				if(!dezeTrainee.getUrenFormulieren().contains(urenForm)) {
					UrenForm urenFormGesaved = urenFormService.saveUrenForm(urenForm);
					dezeTrainee.getUrenFormulieren().add(urenFormGesaved);
					LocalDate day = urenForm.getMaand().withDayOfMonth(1);
					while(urenForm.getMaand().getMonthValue()==day.getMonthValue()) {
						UrenPerDag dezedag = new UrenPerDag();
						dezedag.setDatum(day.toString());
						urenPerDagService.saveInForm(dezedag,urenForm);
						day=day.plusDays(1);
						
					}
					traineeService.saveTrainee(dezeTrainee);
					return Response.ok(urenFormService.findById(urenFormGesaved.getId())).build();
				}
			} catch (Exception e) {
				System.out.println("hoi");
				return Response.status(Status.NOT_FOUND.getStatusCode(),e.toString()).build();
			}
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/admin")
	public Response nieuweTrainee(Trainee trainee) {
		Trainee tr2 = traineeService.saveTrainee(trainee);
		User user=new User();
		user.setUsername(trainee.getEmailAdres());
		user.setRole("ROLE_TRAINEE");
		// add password generator
		user.setPassword("test");
		User savedUser = userService.saveUser(user);
		tr2.setUser(savedUser);
		Trainee tr3 = traineeService.saveTrainee(tr2);
		return Response.ok(tr3).build();
	}
	
	
	@POST
	public Response inDatabaseStoppen(Trainee trainee) {
		Trainee tr2 = traineeService.saveTrainee(trainee);
		return Response.ok(tr2).build();
	}
	
	

}

