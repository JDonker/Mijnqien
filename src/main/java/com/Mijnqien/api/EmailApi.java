package com.Mijnqien.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Mijnqien.Ondersteunend.EmailServer;
import com.Mijnqien.domain.EmailBericht;
@Path("/Email/")
@Component
public class EmailApi {
	
	@Autowired
	EmailServer emailServer;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response postMail(EmailBericht email) {
		System.out.println(email.getEmail());
		emailServer.send(email);
		return Response.accepted().build();
	}
}
