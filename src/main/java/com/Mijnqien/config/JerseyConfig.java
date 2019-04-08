package com.Mijnqien.config;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.Mijnqien.api.DeclaratieApi;
import com.Mijnqien.api.DeclaratieFormApi;
import com.Mijnqien.api.EmailApi;
import com.Mijnqien.api.FileUploadService;
import com.Mijnqien.api.ProfielAPI;
import com.Mijnqien.api.ReisApi;
import com.Mijnqien.api.TraineeApi;
import com.Mijnqien.api.UrenFormAPI;
import com.Mijnqien.api.UrenPerDagAPI;
import com.Mijnqien.api.UurTypeAPI;
import com.Mijnqien.api.UserApi;


import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(TraineeApi.class);
		register(DeclaratieApi.class);
		register(DeclaratieFormApi.class);
		register(ReisApi.class);
		register(EmailApi.class);
		register(ProfielAPI.class);
		register(UrenFormAPI.class);
		register(UrenPerDagAPI.class);
		register(UurTypeAPI.class);
		register(FileUploadService.class);
		register(UserApi.class);
		register(MultiPartFeature.class);
	}
}