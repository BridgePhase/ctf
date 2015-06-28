package com.bridgephase.ctf.backend.fda;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestOperations;

import com.bridgephase.ctf.backend.domain.DrugEventResponse;
import com.bridgephase.ctf.backend.domain.EnforcementReport;
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
			.withField("distribution_pattern", "nationwide VA")
			.withExactField("status", "Ongoing")
			.withDateRangeField("recall_initiation_date", sixMonthsAgo, today)
			.build();
		service.latestFoodRecallsByState("VA");
		
		verify(mockRest).getForObject(
			expectUrl(DataNoun.FOOD, DataContext.ENFORCEMENT, expectedSearchQuery), 
			EnforcementReportResponse.class);
	}
	
	@Test
	public void returnsResultsSortedByClassificationAndDate() {
		setupMockResponseForFoodRecallQuery("VA");
		
		EnforcementReportResponse response = service.latestFoodRecallsByState("VA");
		
		verifySortOrderOfFoodRecalls(response);
	}
	
	private void setupMockResponseForFoodRecallQuery(String state) {
		EnforcementReportResponse mockResponse = new EnforcementReportResponse();
		List<EnforcementReport> reports = new ArrayList<EnforcementReport>(10);
		reports.add(enforcementReport("Product 1", "Class II", "20140401", state));
		reports.add(enforcementReport("Product 2", "Class II", "20140101", state));
		reports.add(enforcementReport("Product 3", "Class I", "20140401", state));
		reports.add(enforcementReport("Product 4", "Class I", "20140101", state));
		reports.add(enforcementReport("Product 5", "Class III", "20140501", state));
		reports.add(enforcementReport("Product 6", "Class III", "20140301", state));
		mockResponse.setResults(reports);
		doReturn(mockResponse).when(mockRest).getForObject(any(URI.class), eq(EnforcementReportResponse.class));
	}

	private EnforcementReport enforcementReport(String product, String classification, 
		String dateAsString, String state) {
		EnforcementReport report = new EnforcementReport();
		report.setProductDescription(product);
		report.setClassification(classification);
		report.setRecallInitiationDate(dateAsString);
		report.setDistributionPattern(state);
		return report;
	}

	private void verifySortOrderOfFoodRecalls(EnforcementReportResponse response) {
		for (EnforcementReport r : response.getResults()) {
			System.out.println(r.getProductDescription()  + " - " + r.getClassification() + " - " + r.getRecallInitiationDate());
		}
		Iterator<EnforcementReport> actualReports = response.getResults().iterator();
		// sort by classification ascending, date descending
		verifyProductIs("Product 3", actualReports.next());
		verifyProductIs("Product 4", actualReports.next());
		verifyProductIs("Product 1", actualReports.next());
		verifyProductIs("Product 2", actualReports.next());
		verifyProductIs("Product 5", actualReports.next());
		verifyProductIs("Product 6", actualReports.next());
	}

	private void verifyProductIs(String productDescription, EnforcementReport report) {
		assertEquals(productDescription, report.getProductDescription());
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
