package com.bridgephase.ctf.backend.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugEventResponse extends FdaApiResponse {
	
	private List<DrugEvent> results;
	
	public List<DrugEvent> getResults() {
		return results;
	}
	
	public void setResults(List<DrugEvent> results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
