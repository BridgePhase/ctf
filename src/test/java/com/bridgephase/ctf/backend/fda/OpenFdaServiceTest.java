package com.bridgephase.ctf.backend.fda;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestOperations;

import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;
import com.bridgephase.ctf.backend.shared.RequestBuilder;

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
		service.latestFoodRecallsByState("VA");
		
		verify(mockRest).getForObject(
			expectUrl(DataNoun.FOOD, DataContext.ENFORCEMENT, null), EnforcementReportResponse.class);
	}
	
	private String expectUrl(DataNoun noun, DataContext context, String search) {
		return RequestBuilder.builder(Protocol.valueOf(TEST_PROTOCOL.toUpperCase()), TEST_HOST)
			.withDataNoun(noun)
			.withContext(context)
			.build();
	}
}
