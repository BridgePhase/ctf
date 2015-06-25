package com.bridgephase.ctf.backend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchCountResponse extends FdaApiResponse {
	
	private List<SearchCountResult> results;

	public List<SearchCountResult> getResults() {
		return results;
	}

	public void setResults(List<SearchCountResult> results) {
		this.results = results;
	}
}
