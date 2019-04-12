package com.Mijnqien.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/Admin").setViewName("Admin");
		registry.addViewController("/adminlog").setViewName("adminlog");
		registry.addViewController("/checkinputtest").setViewName("checkinputtest");
		registry.addViewController("/DeclaratieformulierInvullen").setViewName("DeclaratieformulierInvullen");
		registry.addViewController("/DynamischeUrenForm").setViewName("DynamischeUrenForm");
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/Inloggen").setViewName("Inloggen");
		registry.addViewController("/klantinlog").setViewName("klantinlog");
		registry.addViewController("/traineeinlog").setViewName("traineeinlog");
		registry.addViewController("/traineemaken").setViewName("traineemaken");
		registry.addViewController("/UrenformulierInvullen").setViewName("UrenformulierInvullen");
		registry.addViewController("/wwvergeten").setViewName("wwvergeten");
		registry.addViewController("/wwverz").setViewName("wwverz");
	}
}
