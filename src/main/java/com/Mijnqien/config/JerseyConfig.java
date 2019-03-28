package com.Mijnqien.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.Mijnqien.api.DeclaratieEndpoint;
import com.Mijnqien.api.DeclaratieFormEndpoint;
import com.Mijnqien.api.ProfielAPI;
import com.Mijnqien.api.TraineeAPI;
//import nl.mijnqien.trainees.api.TraineesAPI;
import com.Mijnqien.api.UrenFormAPI;
import com.Mijnqien.api.UrenPerDagAPI;
import com.Mijnqien.api.UurTypeAPI;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(TraineeAPI.class);
		register(DeclaratieEndpoint.class);
		register(DeclaratieFormEndpoint.class);
		register(ProfielAPI.class);
		register(UrenFormAPI.class);
		register(UrenPerDagAPI.class);
		register(UurTypeAPI.class);
	}
}
