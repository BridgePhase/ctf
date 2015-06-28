package com.bridgephase.ctf.view.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * This controller is responsible for providing health check urls and the template for rendering 
 * the homepage.
 * 
 * @author Jaime Garcia
 */
@Controller
public class HomeController implements ErrorController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Value("${production:false}")
	private String inProduction;
	
	
	@Autowired
	private MappingJackson2HttpMessageConverter converter;
	
	/**
	 * This is the health-check url that can be invoked to make sure the application is up and running.
	 * @return returns the version number of the currently deployed application.
	 */
	@RequestMapping(value = "/alive")
	@ResponseBody
	public String alive() {
		return version();
	}
	
	private String version() {
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
		model.addAttribute("version", version());
		model.addAttribute("production", Boolean.parseBoolean(inProduction));
		return "home";
	}
	
	@RequestMapping(value = "/partials/{partial}")
	public String partial(Model model, @PathVariable("partial") String partial) {
		model.addAttribute("version", version());
		model.addAttribute("production", Boolean.parseBoolean(inProduction));
		return "modules/" + partial;
	}
	
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
		logger.error("Did not find the address being requested, returning an error page: {}", request.getRequestURL());
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("version", version());
		mav.addObject("production", Boolean.parseBoolean(inProduction));
	    mav.setViewName("errorpage");
	    return mav;
	}
	
	@Override
	public String getErrorPath() {
		return "/errorpage";
	}

}
