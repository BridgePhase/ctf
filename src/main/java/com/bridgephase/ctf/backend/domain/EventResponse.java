package com.bridgephase.ctf.backend.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class EventResponse extends FdaApiResponse {
	
private List<Event> results;
	
	public List<Event> getResults() {
		return results;
	}
	
	public void setResults(List<Event> results) {
		this.results = results;
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
