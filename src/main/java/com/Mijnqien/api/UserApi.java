package com.Mijnqien.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.User;
import com.Mijnqien.service.UserService;

@Component
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserApi {

	@Autowired
	UserService userService;
	
	@GET
	public Iterable<User> getUsers(){
		Iterable<User> users = userService.findAlleUsers();
		return users;
	}
	
	@POST
	public Response inDatabaseStoppen(User user) {
		User user2 = userService.saveUser(user);
		return Response.ok(user2).build();
	}
	
}
