package com.bridgephase.ctf.backend.fda;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestOperations;

import com.bridgephase.ctf.backend.domain.DeviceEventResponse;
import com.bridgephase.ctf.backend.domain.DrugEventResponse;
import com.bridgephase.ctf.backend.domain.DrugLabelResponse;
import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.FdaApiResponse;
import com.bridgephase.ctf.backend.domain.SearchCountResponse;
import com.bridgephase.ctf.backend.domain.SearchCountResult;
import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;
import com.bridgephase.ctf.backend.shared.RequestBuilder;
import com.bridgephase.ctf.backend.shared.SearchBuilder;

@Service
public class OpenFdaService {

	private Protocol fdaProtocol;
	private String fdaHost;
	private RestOperations restOperations;
	
	@Autowired
	public OpenFdaService(
		@Value("${com.bridgephase.ctf.fdaProtocol}") String protocol,
		@Value("${com.bridgephase.ctf.fdaHost}") String host,
		@Qualifier("CachedRestOperations") RestOperations restTemplate) {
		this.fdaProtocol = Protocol.valueOf(protocol.toUpperCase());
		this.fdaHost = host;
		this.restOperations = restTemplate;
	}
	
	public EnforcementReportResponse latestFoodRecallsByState(String state) {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.MONTH, -6);
		Date sixMonthsAgo = calendar.getTime();
		String searchQuery = "";
		searchQuery = SearchBuilder.builder()
			.withField("distribution_pattern", "nationwide " + state)
			.withExactField("status", "Ongoing")
			.withDateRangeField("recall_initiation_date", sixMonthsAgo, today)
			.build();

		return restOperations.getForObject(
			RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(DataNoun.FOOD)
				.withContext(DataContext.ENFORCEMENT)
				.withSearch(searchQuery)
				.withLimit(100)
				.buildUri(),
			EnforcementReportResponse.class);
	}
	
	public DeviceEventResponse deviceDeathRecallEvent() {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.MONTH, -12);
		Date sixMonthsAgo = calendar.getTime();
		String searchQuery = "";
		searchQuery = SearchBuilder.builder()
			.withField("remedial_action", "Recall")
			.withField("event_type", "Death")
			.withDateRangeField("date_of_event", sixMonthsAgo, today)
			.build();
		
		return restOperations.getForObject(
				RequestBuilder.builder(fdaProtocol, fdaHost)
					.withDataNoun(DataNoun.DEVICE)
					.withContext(DataContext.EVENT)
					.withSearch(searchQuery)
					.withLimit(100)
					.buildUri(),
					DeviceEventResponse.class);
	}
	
	public FdaApiResponse latest(DataNoun noun, DataContext context) {
		switch (context) {
			case ENFORCEMENT:
				return enforcement(noun);
			case LABEL:
				if (!DataNoun.DRUG.equals(noun)) {
					throw new IllegalArgumentException(errorResponse(noun, context));
				}
				return drugLabel();
			case EVENT:
				switch (noun) {
					case DRUG:
						return drugEvent();
					case DEVICE:
						return deviceEvent();
					default:
						throw new IllegalArgumentException(errorResponse(noun, context));
				}
			default:
				throw new IllegalArgumentException(errorResponse(noun, context));
		}
	}
	
	public FdaApiResponse enforcement(DataNoun noun) {
		return restOperations.getForObject(
			RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(noun)
				.withContext(DataContext.ENFORCEMENT)
				.build(),
			EnforcementReportResponse.class);
	}
	
	public FdaApiResponse drugLabel() {
		return restOperations.getForObject(
				RequestBuilder.builder(fdaProtocol, fdaHost)
					.withDataNoun(DataNoun.DRUG)
					.withContext(DataContext.LABEL)
					.build(),
				DrugLabelResponse.class);
	}
	
	public DrugEventResponse drugEvent() {
		return restOperations.getForObject(
				RequestBuilder.builder(fdaProtocol, fdaHost)
					.withDataNoun(DataNoun.DRUG)
					.withContext(DataContext.EVENT)
					.build(),
				DrugEventResponse.class);
	}
	
	public DeviceEventResponse deviceEvent() {
		return restOperations.getForObject(
				RequestBuilder.builder(fdaProtocol, fdaHost)
					.withDataNoun(DataNoun.DEVICE)
					.withContext(DataContext.EVENT)
					.build(),
				DeviceEventResponse.class);
	}
	
	private String errorResponse(DataNoun noun, DataContext context) {
		return String.format("The combination of data noun = %s and data context = %s are invalid.", 
				noun.toString(), 
				context.toString());
	}

	public DrugEventResponse searchAdverseDrugEvents(List<String> medications) {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.YEAR, -1);
		Date oneYearAgo = calendar.getTime();
		String searchQuery = "";
		SearchBuilder searchBuilder = SearchBuilder.builder();
		for (String medication : medications) {
			searchBuilder.withField("patient.drug.medicinalproduct", medication);
		}
		searchQuery = searchBuilder.withDateRangeField("receiptdate", oneYearAgo, today)
			.build();

		RequestBuilder builder = RequestBuilder.builder(fdaProtocol, fdaHost)
			.withDataNoun(DataNoun.DRUG)
			.withSearch(searchQuery)
			.withLimit(100)
			.withContext(DataContext.EVENT);
		return restOperations.getForObject(builder.buildUri(), DrugEventResponse.class);
	}
	
	public SearchCountResponse mostCommonReactionTypes(String medication) {
		String searchQuery = SearchBuilder.builder()
				.withField("patient.drug.medicinalproduct", medication)
				.build();
		
		RequestBuilder builder = RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(DataNoun.DRUG)
				.withSearch(searchQuery)
				.withCount("patient.reaction.reactionmeddrapt.exact")
				.withLimit(50)
				.withContext(DataContext.EVENT);
			return restOperations.getForObject(builder.buildUri(), SearchCountResponse.class);
	}
	
	public SearchCountResponse drugPurposeRoute(String purpose) {
		String searchQuery = SearchBuilder.builder()
				.withField("purpose", purpose)
				.build();
		
		RequestBuilder builder = RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(DataNoun.DRUG)
				.withSearch(searchQuery)
				.withCount("openfda.route")
				.withLimit(50)
				.withContext(DataContext.LABEL);
			return restOperations.getForObject(builder.buildUri(), SearchCountResponse.class);
	}
	
	public SearchCountResponse deviceEventCountByOperator(String operator) {
		String searchQuery = SearchBuilder.builder()
				.withField("device.device_operator", operator)
				.build();
		
		RequestBuilder builder = RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(DataNoun.DEVICE)
				.withSearch(searchQuery)
				.withCount("event_type.exact")
				.withLimit(100)
				.withContext(DataContext.EVENT);
			return restOperations.getForObject(builder.buildUri(), SearchCountResponse.class);
	}
	
	public SearchCountResponse recallClassifications(DataNoun noun) {
		Calendar calendar = Calendar.getInstance();
		Date today = calendar.getTime();
		calendar.add(Calendar.MONTH, -12);
		Date sixMonthsAgo = calendar.getTime();
		String searchQuery = "";
		searchQuery = SearchBuilder.builder()
			.withDateRangeField("recall_initiation_date", sixMonthsAgo, today)
			.build();
		
		RequestBuilder builder = RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(noun)
				.withSearch(searchQuery)
				.withCount("classification")
				.withLimit(5)
				.withContext(DataContext.ENFORCEMENT);
		return restOperations.getForObject(builder.buildUri(), SearchCountResponse.class);
	}
	
	public SearchCountResponse adverseDrugEventsByTypeGroupBy(String drug, String effect) {
		String searchQuery = SearchBuilder.builder()
			.withField("patient.drug.medicinalproduct", drug)
			.withField("patient.patientonsetageunit", "801")
			.withField(effect, "1")
			.build();
		RequestBuilder builder = RequestBuilder.builder(fdaProtocol, fdaHost)
			.withDataNoun(DataNoun.DRUG)
			.withContext(DataContext.EVENT)
			.withSearch(searchQuery)
			.withCount("patient.patientonsetage")
			.withLimit(100);
		SearchCountResponse response = new SearchCountResponse();
		try {
			response = restOperations.getForObject(builder.buildUri(), SearchCountResponse.class); 
		} catch (HttpClientErrorException e) {
			// some error happened
			List<SearchCountResult> results = new ArrayList<SearchCountResult>();
			response.setResults(results);
			e.printStackTrace();
		}
		return response;
	}
}
