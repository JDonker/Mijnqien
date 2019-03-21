package com.Mijnqien;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MijnqienApplication {

	public static void main(String[] args) {
		//EmailServer srv = EmailServer.getInstance();
		//srv.send("jasperdonker@gmail.com", "johoe", "dit is het bericht");
		//SpringApplication.run(MijnqienApplication.class, args);
		ExecutorService sm = Executors.newCachedThreadPool();
		DagelijkseCheck check = new DagelijkseCheck();
		sm.execute(check);
	}
}
