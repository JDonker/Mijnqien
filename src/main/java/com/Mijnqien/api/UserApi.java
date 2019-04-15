package com.Mijnqien.api;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import com.Mijnqien.User;
import com.Mijnqien.service.UserService;

@CrossOrigin("*")
@Path("/user")
@Controller
public class UserApi {

	@Autowired
	UserService userService;
	
	@Autowired
	public UserApi(UserService userService) {
	    this.userService = userService;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity getUserByUsername(@PathParam("username") @NotBlank String username) {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}
	
	@GET
	@Path("/exist/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity isUserExist(@PathParam("username") @NotBlank String username) {
		return ResponseEntity.ok(userService.isUserExists(username));
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity addUser(@RequestBody @Valid User user) {
		return ResponseEntity.ok(userService.addUser(user));
	}
	
	@DELETE
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResponseEntity deleteUser(@PathParam("username") @NotBlank String username) {
	    return ResponseEntity.ok(userService.deleteUser(username));
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Iterable<User> getUsers(){
		Iterable<User> users = userService.findAlleUsers();
		return users;
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inDatabaseStoppen(User user) {
		User user2 = userService.saveUser(user);
		return Response.ok(user2).build();
	}
	
}
