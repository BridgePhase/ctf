package com.bridgephase.ctf.view.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This controller is responsible for providing health check urls and the template for rendering 
 * the homepage.
 * 
 * @author Jaime Garcia
 */
@Controller
public class HomeController implements ErrorController {
	
	@Value("${production:false}")
	private String inProduction;
	
	/**
	 * This is the health-check url that can be invoked to make sure the application is up and running.
	 * @return returns the version number of the currently deployed application.
	 */
	@RequestMapping(value = "/alive")
	@ResponseBody
	public String alive() {
		try {
			BufferedReader reader = versionReader();
			String version = reader.readLine();
			reader.close();
			return version;
		} catch (IOException e) {
			// exception is ignorable, we just don't have the version number for this page
			return "No version information available";
		}
	}
	
	/**
	 * This method returns a buffered reader that is linked to the application version file in the
	 * system. When performing automated builds in a pipeline, the version file at 
	 * <code>src/main/resources/public/version</code> should be overwritten with the correct
	 * tagged version of the deployed artifacts.
	 * 
	 * This method is defined as protected to allow unit tests to mock out the behavior of the
	 * version file.
	 * 
	 * @return A buffered readder to the version file for the application.
	 */
	protected BufferedReader versionReader(){
		InputStream is = getClass().getResourceAsStream("/public/version");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		return reader;
	}

	/**
	 * This request mapping method will render a view. The views are
	 * stored under the <code>/src/main/resources/templates/views</code> directory, the returned
	 * value here should be relative to the <code>/templates/views</code> folder listed previously.
	 * 
	 * @return the view name of "home"
	 */
	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("version", alive());
		model.addAttribute("production", Boolean.parseBoolean(inProduction));
		return "home";
	}
	
	@RequestMapping(value = "/partials/{partial}")
	public String partial(Model model, @PathVariable("partial") String partial) {
		model.addAttribute("version", alive());
		model.addAttribute("production", Boolean.parseBoolean(inProduction));
		return "modules/" + partial;
	}
	
	@RequestMapping(value = "/error")
	public String error(Model model, HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("An error occurred");
		model.addAttribute("version", alive());
		model.addAttribute("production", Boolean.parseBoolean(inProduction));
        return "errorpage";
    }
	
	@Override
	public String getErrorPath() {
		return "/error";
	}
}
