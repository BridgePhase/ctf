package com.bridgephase.ctf.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.shared.RequestBuilder;

@Controller
public class QueryController {
	
	@RequestMapping(value = "/drug")
	@ResponseBody
	public String drug() {
		return noun(DataNoun.DRUG);
	}
	
	@RequestMapping(value = "/device")
	@ResponseBody
	public String device() {
		return noun(DataNoun.DEVICE);
	}
	
	@RequestMapping(value = "/food")
	@ResponseBody
	public String food() {
		return noun(DataNoun.FOOD);
	}
	
	private String noun(DataNoun noun) {
		RestTemplate restTemplate = new RestTemplate();
		Object result = restTemplate.getForObject(
				RequestBuilder.builder().withDataNoun(noun).build(), 
				String.class);
		return result.toString();
	}

}
