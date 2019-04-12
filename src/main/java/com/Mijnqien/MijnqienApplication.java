package com.Mijnqien;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.Mijnqien.service.UserService;



@SpringBootApplication
public class MijnqienApplication {

	public static void main(String[] args) {
		//EmailServer srv = EmailServer.getInstance();
		//srv.send("jasperdonker@gmail.com", "johoe", "dit is het bericht");
		SpringApplication.run(MijnqienApplication.class, args);
		//ExecutorService sm = Executors.newCachedThreadPool();
		//DagelijkseCheck check = new DagelijkseCheck(sm);
		//sm.execute(check);
	}
	
    @Bean
    CommandLineRunner init(UserService userService) {
        return (args) -> userService.initUsers();
    }

}
