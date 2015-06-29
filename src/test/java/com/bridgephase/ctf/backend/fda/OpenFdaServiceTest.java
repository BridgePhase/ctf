package com.bridgephase.ctf.backend.fda;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import com.bridgephase.ctf.backend.domain.DeviceEventResponse;
import com.bridgephase.ctf.backend.domain.DrugEventResponse;
import com.bridgephase.ctf.backend.domain.DrugLabelResponse;
import com.bridgephase.ctf.backend.domain.EnforcementReport;
import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.SearchCountResponse;
import com.bridgephase.ctf.backend.domain.SearchCountResult;
import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;
import com.bridgephase.ctf.backend.domain.enumeration.RecallClassification;
import com.bridgephase.ctf.backend.shared.RequestBuilder;
import com.bridgephase.ctf.backend.shared.SearchBuilder;

public class OpenFdaServiceTest {

	private static final String TEST_PROTOCOL = "http";
	private static final String TEST_HOST = "test.fda.gov";
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	private OpenFdaService service;
	private RestOperations mockRest;
	
	@Before
	public void setup() {
		mockRest = mock(RestOperations.class);
		service = new OpenFdaService(TEST_PROTOCOL, TEST_HOST, mockRest);
	}
	
	@Test
	public void correctRestUrlIsCreatedForFoodRecallsByState() {
		setupMockResponseForFoodRecallQuery("VA");
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
	
	@Test
	public void testDeviceDeathRecallEvent() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.MONTH, -12);
		Date sixMonthsAgo = calendar.getTime();
		String expectedSearchQuery = "";
		expectedSearchQuery = SearchBuilder.builder()
			.withField("remedial_action", "Recall")
			.withField("event_type", "Death")
			.withDateRangeField("date_of_event", sixMonthsAgo, today)
			.build();
		
		service.deviceDeathRecallEvent();
		
		verify(mockRest).getForObject(
				expectUrl(DataNoun.DEVICE, DataContext.EVENT, expectedSearchQuery), 
				DeviceEventResponse.class);
	}
	
	@Test
	public void testLatest() {
		testLatestFailurePaths(DataNoun.DEVICE, DataContext.LABEL);;
		testLatestFailurePaths(DataNoun.FOOD, DataContext.EVENT);;
		testLatestFailurePaths(DataNoun.FOOD, DataContext.LABEL);;
		
		service.latest(DataNoun.DRUG, DataContext.EVENT);
		service.latest(DataNoun.DRUG, DataContext.LABEL);
		service.latest(DataNoun.DRUG, DataContext.ENFORCEMENT);
		
		service.latest(DataNoun.DEVICE, DataContext.EVENT);
		service.latest(DataNoun.DEVICE, DataContext.ENFORCEMENT);
		
		service.latest(DataNoun.FOOD, DataContext.ENFORCEMENT);
	}
	
	private void testLatestFailurePaths(DataNoun noun, DataContext context) {
		IllegalArgumentException e = null;
		try {
			service.latest(noun, context);
		} catch (IllegalArgumentException ex) {
			e = ex;
		}
		assertNotNull(e);
	}
	
	@Test
	public void testEnforcement() {
		for (DataNoun noun : DataNoun.values()) {
			service.enforcement(noun);
			verify(mockRest).getForObject(
					expectUrl(noun, DataContext.ENFORCEMENT, "", 1, ""), 
					EnforcementReportResponse.class);
		}
	}
	
	@Test
	public void testDrugLabel() {
			service.drugLabel();
			verify(mockRest).getForObject(
					expectUrl(DataNoun.DRUG, DataContext.LABEL, "", 1, ""), 
					DrugLabelResponse.class);
	}
	
	@Test
	public void testDrugEvent() {
			service.drugEvent();
			verify(mockRest).getForObject(
					expectUrl(DataNoun.DRUG, DataContext.EVENT, "", 1, ""), 
					DrugEventResponse.class);
	}
	
	@Test
	public void testDeviceEvent() {
			service.deviceEvent();
			verify(mockRest).getForObject(
					expectUrl(DataNoun.DEVICE, DataContext.EVENT, "", 1, ""), 
					DeviceEventResponse.class);
	}
	
	@Test
	public void testMostCommonReactionTypes() {
		SearchCountResponse mockResponse =initMockSearchCountReponse("Nausea", "Headache");
		String medication = "Tylenol";
		String expectedSearchQuery = SearchBuilder.builder()
				.withField("patient.drug.medicinalproduct", medication)
				.build();
		
		SearchCountResponse response = service.mostCommonReactionTypes(medication);
		verify(mockRest).getForObject(
				expectUrl(DataNoun.DRUG, DataContext.EVENT, expectedSearchQuery, 
						50, "patient.reaction.reactionmeddrapt.exact"), SearchCountResponse.class);
		assertTrue(mockResponse.equals(response));
	}
	
	@Test
	public void testDrugPurposeRoute() {
		SearchCountResponse mockResponse =initMockSearchCountReponse("Oral", "Topical");
		String purpose = "sleep aid";
		String expectedSearchQuery = SearchBuilder.builder()
				.withField("purpose", purpose)
				.build();
		
		SearchCountResponse response = service.drugPurposeRoute(purpose);
		verify(mockRest).getForObject(
				expectUrl(DataNoun.DRUG, DataContext.LABEL, expectedSearchQuery, 
						50, "openfda.route"), SearchCountResponse.class);
		assertTrue(mockResponse.equals(response));
	}
	
	@Test
	public void testDeviceEventCountByOperator() {
		SearchCountResponse mockResponse =initMockSearchCountReponse("Injury", "Death");
		String operator = "nurse";
		String expectedSearchQuery = SearchBuilder.builder()
				.withField("device.device_operator", operator)
				.build();
		
		SearchCountResponse response = service.deviceEventCountByOperator(operator);
		verify(mockRest).getForObject(
				expectUrl(DataNoun.DEVICE, DataContext.EVENT, expectedSearchQuery, 
						100, "event_type.exact"), SearchCountResponse.class);
		assertTrue(mockResponse.equals(response));
	}
	
	@Test
	public void testRecallClassifications() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.MONTH, -12);
		Date sixMonthsAgo = calendar.getTime();
		String expectedSearchQuery = "";
		expectedSearchQuery = SearchBuilder.builder()
			.withDateRangeField("recall_initiation_date", sixMonthsAgo, today)
			.build();
		
		SearchCountResponse mockResponse =initMockSearchCountReponse(
				RecallClassification.CLASS_I.shortLabel(),
				RecallClassification.CLASS_II.shortLabel(),
				RecallClassification.CLASS_III.shortLabel());
		
		for (DataNoun noun : DataNoun.values()) {
			SearchCountResponse response = service.recallClassifications(noun);
			verify(mockRest).getForObject(
					expectUrl(noun, DataContext.ENFORCEMENT, expectedSearchQuery, 
							5, "classification"), SearchCountResponse.class);
			assertTrue(mockResponse.equals(response));
		}
		
	}
	
	@Test
	public void testAdverseDrugEventsByTypeGroupBy() {
		SearchCountResponse mockResponse =initMockSearchCountReponse("0-10", "11-20", "21-30");
		List<String> drugs = new ArrayList<>();
		drugs.add("Zarah");
		drugs.add("Tylenol");
		drugs.add("Zycam");
		
		String effect = "seriousnessdeath";
		
		SearchBuilder searchBuilder = SearchBuilder.builder();
		for (String drug : drugs) {
			searchBuilder.withField("patient.drug.medicinalproduct", drug);
		}
		searchBuilder.withField("patient.patientonsetageunit", "801")
			.withField(effect, "1");
		String expectedSearchQuery = searchBuilder.build();
		
		SearchCountResponse response = service.adverseDrugEventsByTypeGroupBy(drugs, effect);
		verify(mockRest).getForObject(
				expectUrl(DataNoun.DRUG, DataContext.EVENT, expectedSearchQuery, 
						100, "patient.patientonsetage"), SearchCountResponse.class);
		assertTrue(mockResponse.equals(response));
		
		when(service.adverseDrugEventsByTypeGroupBy(drugs, effect)).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		response = service.adverseDrugEventsByTypeGroupBy(drugs, effect);
		assertTrue(!mockResponse.equals(response));
		assertTrue(response.getResults().isEmpty());
	}
	
	private URI expectUrl(DataNoun noun, DataContext context, String search) {
		return RequestBuilder.builder(Protocol.valueOf(TEST_PROTOCOL.toUpperCase()), TEST_HOST)
			.withDataNoun(noun)
			.withContext(context)
			.withSearch(search)
			.withLimit(100)
			.buildUri();
	}
	
	private URI expectUrl(DataNoun noun, DataContext context, String search, int limit, String count) {
		return RequestBuilder.builder(Protocol.valueOf(TEST_PROTOCOL.toUpperCase()), TEST_HOST)
			.withDataNoun(noun)
			.withContext(context)
			.withSearch(search)
			.withCount(count)
			.withLimit(limit)
			.buildUri();
	}
	
	private SearchCountResponse initMockSearchCountReponse(String... terms) {
		SearchCountResponse mockResponse = new SearchCountResponse();
		List<SearchCountResult> mockResults = new ArrayList<>();
		mockResponse.setResults(mockResults);
		int startCount = 100;
		for (String term : terms) {
			addSearchCountResult(mockResults, term, startCount--);
		}
		when(mockRest.getForObject(any(URI.class), eq(SearchCountResponse.class))).thenReturn(mockResponse);
		return mockResponse;
	}
	
	private void addSearchCountResult(List<SearchCountResult> resultList, String term, Integer count) {
		SearchCountResult mockResult = new SearchCountResult();
		mockResult.setTerm(term);
		mockResult.setCount(count);
		resultList.add(mockResult);
	}
	
}
