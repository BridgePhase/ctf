package com.bridgephase.ctf.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.shared.RequestBuilder;

@Controller
public class QueryController {
	
	@RequestMapping(value = "/{noun}")
	@ResponseBody
	public EnforcementReportResponse noun(@PathVariable("noun") String noun) {
		DataNoun dataNoun = DataNoun.valueOf(noun.toUpperCase());
		RestTemplate restTemplate = new RestTemplate();
		EnforcementReportResponse result = restTemplate.getForObject(
				RequestBuilder.builder().withDataNoun(dataNoun).build(), 
				EnforcementReportResponse.class);
		return result;
	}
}
