package com.bridgephase.ctf.view.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.FdaApiResponse;
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
	
	@Autowired
	private OpenFdaService openFda;
	
	@Autowired
	private NotificationService notificationService;
	
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
	
	@RequestMapping(value = "/api/drug/search", method = RequestMethod.POST)
	@ResponseBody
	public FdaApiResponse searchDrugs(@RequestBody List<String> medications) {
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
	
	@RequestMapping(value = "/api/drug/{medication}/stats")
	@ResponseBody
	public Map<String, AgeGroupCountSummary> countAdverseEffects(@PathVariable("medication") String medication) {
		Map<String, AgeGroupCountSummary> response = new HashMap<String, AgeGroupCountSummary>();
		response.put("deaths", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medication, "seriousnessdeath")));
		response.put("congenital", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medication, "seriousnesscongenitalanomali")));
		response.put("disabling", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medication, "seriousnessdisabling")));
		response.put("hospitalization", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medication, "seriousnesshospitalization")));
		response.put("threatening", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medication, "seriousnesslifethreatening")));
		response.put("other", 
			toCountSummary(openFda.adverseDrugEventsByTypeGroupBy(medication, "seriousnessother")));
		return response;
	}
	
	private AgeGroupCountSummary toCountSummary(SearchCountResponse response) {
		AgeGroupCountSummary r = new AgeGroupCountSummary();
		for (SearchCountResult result : response.getResults()) {
			r.add(result.getTerm(), result.getCount());
		}
		return r;
	}
}
