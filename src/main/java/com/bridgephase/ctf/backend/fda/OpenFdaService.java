package com.bridgephase.ctf.backend.fda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.bridgephase.ctf.backend.domain.EnforcementReportResponse;
import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;
import com.bridgephase.ctf.backend.shared.RequestBuilder;

@Service
public class OpenFdaService {

	private Protocol fdaProtocol;
	private String fdaHost;
	private RestOperations restOperations;
	
	@Autowired
	public OpenFdaService(
		@Value("${com.bridgephase.ctf.fdaProtocol}") String protocol,
		@Value("${com.bridgephase.ctf.fdaHost}") String host,
		RestOperations restTemplate) {
		this.fdaProtocol = Protocol.valueOf(protocol.toUpperCase());
		this.fdaHost = host;
		this.restOperations = restTemplate;
	}
	
	public EnforcementReportResponse latestFoodRecallsByState(String state) {
		return restOperations.getForObject(
			RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(DataNoun.FOOD)
				.withContext(DataContext.ENFORCEMENT)
				.build(),
			EnforcementReportResponse.class);
	}
	
	public EnforcementReportResponse noun(DataNoun noun) {
		return restOperations.getForObject(
			RequestBuilder.builder(fdaProtocol, fdaHost)
				.withDataNoun(noun)
				.withContext(DataContext.ENFORCEMENT)
				.build(),
			EnforcementReportResponse.class);
	}
}
