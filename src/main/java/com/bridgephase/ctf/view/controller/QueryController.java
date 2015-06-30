package com.bridgephase.ctf.view.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.FdaApiResponse;
import com.bridgephase.ctf.backend.domain.Meta;
import com.bridgephase.ctf.backend.domain.MetaResult;
import com.bridgephase.ctf.backend.domain.SearchCountResponse;
import com.bridgephase.ctf.backend.domain.SearchCountResult;
import com.bridgephase.ctf.backend.domain.dto.AgeGroupCountSummary;
import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.fda.OpenFdaService;
import com.bridgephase.ctf.backend.notifications.NotificationService;
import com.bridgephase.ctf.model.jpa.Notification;

@Controller
public class QueryController {
	
	private OpenFdaService openFda;
	private NotificationService notificationService;
	
	@Autowired
	public QueryController(OpenFdaService openFda, NotificationService notifications) {
		this.openFda = openFda;
		this.notificationService = notifications;
	}
	
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
	
	@RequestMapping(value = "/api/drug/search")
	@ResponseBody
	public FdaApiResponse searchDrugs(@RequestParam("medications") String query) {
		List<String> medications = new ArrayList<String>();
		for (String med : query.split(",")) {
			medications.add(med.trim());
		}
		return openFda.searchAdverseDrugEvents(medications);
	}
	
	@RequestMapping(value = "/api/{noun}/{context}/latest")
	@ResponseBody
	public FdaApiResponse latest(@PathVariable("noun") String noun, @PathVariable("context") String context) {
		DataNoun dataNoun = DataNoun.valueOf(noun.toUpperCase());
		DataContext dataContext = DataContext.valueOf(context.toUpperCase());
		return openFda.latest(dataNoun, dataContext);
	}
	
	@RequestMapping(value = "/app/notifications")
	@ResponseBody
	public List<Notification> notifications() {
		return notificationService.getNotifications();
	}
	
	@RequestMapping(value = "/api/drug/stats")
	@ResponseBody
	public Map<String, AgeGroupCountSummary> countAdverseEffects(
		@RequestParam("medications") String medication) {
		Map<String, AgeGroupCountSummary> response = new HashMap<String, AgeGroupCountSummary>();
		List<String> medications = new ArrayList<String>();
		for (String med : medication.split(",")) {
			medications.add(med.trim());
		}
		response.put("deaths", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medications, "seriousnessdeath")));
		response.put("congenital", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medications, "seriousnesscongenitalanomali")));
		response.put("disabling", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medications, "seriousnessdisabling")));
		response.put("hospitalization", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medications, "seriousnesshospitalization")));
		response.put("threatening", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medications, "seriousnesslifethreatening")));
		response.put("other", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medications, "seriousnessother")));
		return response;
	}
	
	private AgeGroupCountSummary toCountSummary(SearchCountResponse response) {
		AgeGroupCountSummary r = new AgeGroupCountSummary();
		for (SearchCountResult result : response.getResults()) {
			r.add(result.getTerm(), result.getCount());
		}
		return r;
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
	@ResponseBody
	public FdaApiResponse handleErrors(Exception e) {
		// this exception is usually caused because OpenFDA sends us a
		// 404 error which shouldn't be the case it should return 
		// a 200 with an appropriately empty result set
		FdaApiResponse response = new FdaApiResponse();
		Meta meta = new Meta();
		meta.setResults(new MetaResult());
		meta.getResults().setTotal(0);
		response.setMeta(meta);
		return response;
	}
}
