package com.bridgephase.ctf.backend.fda;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestOperations;

import com.bridgephase.ctf.backend.domain.DrugEventResponse;
import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;
import com.bridgephase.ctf.backend.shared.RequestBuilder;
import com.bridgephase.ctf.backend.shared.SearchBuilder;

public class OpenFdaServiceTest {

	private static final String TEST_PROTOCOL = "http";
	private static final String TEST_HOST = "test.fda.gov";
	
	private OpenFdaService service;
	private RestOperations mockRest;
	
	@Before
	public void setup() {
		mockRest = mock(RestOperations.class);
		service = new OpenFdaService(TEST_PROTOCOL, TEST_HOST, mockRest);
	}
	
	@Test
	public void correctRestUrlIsCreatedForFoodRecallsByState() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.MONTH, -6);
		Date sixMonthsAgo = calendar.getTime();
		String expectedSearchQuery = SearchBuilder.builder()
			.withField("distribution_pattern", "nationwide")
			.withField("distribution_pattern", "VA")
			.withExactField("status", "Ongoing")
			.withDateRangeField("recall_initiation_date", sixMonthsAgo, today)
			.build();
		service.latestFoodRecallsByState("VA");
		
		verify(mockRest).getForObject(
			expectUrl(DataNoun.FOOD, DataContext.ENFORCEMENT, expectedSearchQuery), 
			EnforcementReportResponse.class);
	}
	
	@Test
	public void correctRestUrlIsCreatedForSearchesForMedications() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date oneYearAgo = calendar.getTime();
		String expectedSearchQuery = SearchBuilder.builder()
			.withField("patient.drug.medicinalproduct", "Tylenol")
			.withDateRangeField("receiptdate", oneYearAgo, today)
			.build();
		
		List<String> medications = new ArrayList<String>();
		medications.add("Tylenol");
		service.searchAdverseDrugEvents(medications);
		
		verify(mockRest).getForObject(
			expectUrl(DataNoun.DRUG, DataContext.EVENT, expectedSearchQuery), DrugEventResponse.class);
	}
	
	private URI expectUrl(DataNoun noun, DataContext context, String search) {
		return RequestBuilder.builder(Protocol.valueOf(TEST_PROTOCOL.toUpperCase()), TEST_HOST)
			.withDataNoun(noun)
			.withContext(context)
			.withSearch(search)
			.withLimit(100)
			.buildUri();
	}
}
