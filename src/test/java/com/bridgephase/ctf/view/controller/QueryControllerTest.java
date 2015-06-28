package com.bridgephase.ctf.view.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.bridgephase.ctf.backend.domain.DeviceEventResponse;
import com.bridgephase.ctf.backend.domain.DrugEventResponse;
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

public class QueryControllerTest {

	private OpenFdaService mockFdaService;
	private NotificationService mockNotificationService;

	private QueryController controller;
	
	@Before
	public void setup() {
		mockFdaService = mock(OpenFdaService.class);
		mockNotificationService = mock(NotificationService.class);
		controller = new QueryController(mockFdaService, mockNotificationService);
	}
	
	@Test
	public void foodRecallsByStatePassesSameParametersToService() {
		EnforcementReportResponse response = new EnforcementReportResponse();
		doReturn(response).when(mockFdaService).latestFoodRecallsByState("DC");
		
		assertSame(response, controller.foodRecallsByState("DC"));
	}
	
	@Test
	public void deviceRecalleventPassesInvokesService() {
		DeviceEventResponse response = new DeviceEventResponse();
		doReturn(response).when(mockFdaService).deviceDeathRecallEvent();
		
		assertSame(response, controller.deviceRecallEvent());
	}
	
	@Test
	public void drugSearchSplitsSearchQueryByCommas() {
		DrugEventResponse response = new DrugEventResponse();
		List<String> expectedParams = new LinkedList<String>();
		expectedParams.add("VA");
		expectedParams.add("DC");
		expectedParams.add("MD");
		doReturn(response).when(mockFdaService).searchAdverseDrugEvents(expectedParams);
		
		assertSame(response, controller.searchDrugs("VA,DC,MD"));
	}
	
	@Test
	public void latestBuildsUpCorrectServiceRequests() {
		verifyLatestNounAndContext("device", "enforcement");
		verifyLatestNounAndContext("food", "event");
		verifyLatestNounAndContext("drug", "label");
	}
	
	@Test
	public void notifications() {
		List<Notification> notifications = new LinkedList<Notification>();
		doReturn(notifications).when(mockNotificationService).getNotifications();
		
		assertSame(notifications, controller.notifications());
	}
	
	@Test
	public void countAdverseEffectsParsesOutMedicationsListByComma() {
		SearchCountResponse mockResponse = new SearchCountResponse();
		List<SearchCountResult> countResult = new LinkedList<SearchCountResult>();
		SearchCountResult result = new SearchCountResult();
		result.setTerm("12");
		result.setCount(123);
		countResult.add(result);
		mockResponse.setResults(countResult);
		List<String> expectedParams = new LinkedList<String>();
		expectedParams.add("AH");
		expectedParams.add("DF");
		expectedParams.add("GH");
		doReturn(mockResponse).when(mockFdaService).adverseDrugEventsByTypeGroupBy(
			eq(expectedParams), anyString());
		
		Map<String, AgeGroupCountSummary> realResult = controller.countAdverseEffects("AH , DF ,GH ");
		
		assertEquals(Integer.valueOf(123), 
			realResult.get("deaths").decadeMap().get("11-20"));
	}
	
	@Test
	public void exceptionHandlerReturnsEmptyResult() {
		FdaApiResponse response = controller.handleErrors(new Exception());
		assertNotNull(response.getMeta());
		assertNotNull(response.getMeta().getResults());
		assertEquals(Integer.valueOf(0), response.getMeta().getResults().getTotal());
	}
	
	private void verifyLatestNounAndContext(String noun, String context) {
		FdaApiResponse response1 = new DrugEventResponse();
		DataNoun dataNoun = DataNoun.valueOf(noun.toUpperCase());
		DataContext dataContext = DataContext.valueOf(context.toUpperCase());
		doReturn(response1).when(mockFdaService).latest(dataNoun, dataContext);
		
		assertSame(response1, controller.latest(noun, context));
		
	}
}
