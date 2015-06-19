package com.bridgephase.ctf.view.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgephase.ctf.model.jpa.Person;
import com.bridgephase.ctf.model.repository.PersonRepository;

/**
 * This is a sample controller definition which shows both a <code>ResponseBody</code> response as well as a view
 * mapped response.
 * 
 * @author Jaime Garcia
 */
@Controller
public class HelloWorldController {

	@Autowired
	private PersonRepository personRepository;
	
	@Value("${production:false}")
	private String inProduction;
	
	/**
	 * This request mapping method will render a response of "Hello World".
	 * 
	 * @return A rendered response body of "Hello World"
	 */
	@RequestMapping(value = "/helloworld")
	@ResponseBody
	public String sayHello() {
		Person person = new Person("John", "Doe");
		person = personRepository.save(person);
		return "Hello World " + personRepository.findAll();
	}
	
	@RequestMapping(value = "/alive")
	@ResponseBody
	public String alive() {
		return "alive";
	}

	/**
	 * This request mapping method will render a view. The views are
	 * stored under the <code>/src/main/resources/public</code> directory, the returned
	 * value here should be relative to the <code>/public</code> folder listed previously.
	 * 
	 * @return the view name of "hello"
	 */
	@RequestMapping(value = "/")
	public String hello(Model model) {
		try {
			InputStream is = getClass().getResourceAsStream("/public/version");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String version = reader.readLine();
			reader.close();
			model.addAttribute("version", version);
		} catch (IOException e) {
			// exception is ignorable, we just don't have the version number for this page
			model.addAttribute("version", "Not available");
		}
		model.addAttribute("production", Boolean.FALSE);
		return "hello";
	}
}
