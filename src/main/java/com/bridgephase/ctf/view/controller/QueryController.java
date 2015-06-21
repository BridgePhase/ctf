package com.bridgephase.ctf.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.fda.OpenFdaService;

@Controller
public class QueryController {
	
	@Autowired
	private OpenFdaService openFda;
	
	@RequestMapping(value = "/food/{state}")
	@ResponseBody
	public EnforcementReportResponse foodRecallsByState(@PathVariable("state") String state) {
		return openFda.latestFoodRecallsByState("VA");
	}
}
