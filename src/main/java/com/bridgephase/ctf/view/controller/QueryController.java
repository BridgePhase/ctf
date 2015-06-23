package com.bridgephase.ctf.view.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.FdaApiResponse;
import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.fda.OpenFdaService;

@Controller
public class QueryController {
	
	@Autowired
	private OpenFdaService openFda;
	
	@RequestMapping(value = "/api/food/{state}")
	@ResponseBody
	public EnforcementReportResponse foodRecallsByState(@PathVariable("state") String state) {
		return openFda.latestFoodRecallsByState(state);
	}
	
	@RequestMapping(value = "/api/device/recallEvent")
	@ResponseBody
	public FdaApiResponse deviceRecallEvent() {
		return openFda.deviceDeathRecallEvent();
	}
	
	@RequestMapping(value = "/api/{noun}/{context}/latest")
	@ResponseBody
	public FdaApiResponse latest(@PathVariable("noun") String noun, @PathVariable("context") String context) {
		DataNoun dataNoun = DataNoun.valueOf(noun.toUpperCase());
		DataContext dataContext = DataContext.valueOf(context.toUpperCase());
		return openFda.latest(dataNoun, dataContext);
	}
}
