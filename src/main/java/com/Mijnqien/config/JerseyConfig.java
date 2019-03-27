package com.Mijnqien.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.Mijnqien.api.TraineeAPI;
//import nl.mijnqien.trainees.api.TraineesAPI;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(TraineeAPI.class);
	}
}
