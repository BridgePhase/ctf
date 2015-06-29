package com.bridgephase.ctf.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is the main driver of the application. Using Spring Boot, this runs the application in an embedded Tomcat
 * server.
 * 
 * Add any required configurations here. It's recommended to add the following configurations:
 * 
 * <ul>
 * 	<li>ViewConfiguration</li>
 *  <li>ServiceConfiguration</li>
 *  <li>RepositoryConfiguration</li>
 *  <li>SecurityConfiguration</li>
 * </ul>
 * 
 * This sample only includes ViewConfiguration
 * 
 * @author Jaime Garcia
 *
 */
@SpringBootApplication
@EnableScheduling
@Import(value = { 
	ViewConfiguration.class,
	ModelConfiguration.class 
})
public class Application extends SpringBootServletInitializer {
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
