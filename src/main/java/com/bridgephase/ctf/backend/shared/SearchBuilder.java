package com.bridgephase.ctf.backend.shared;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class SearchBuilder {
	private static final String FORMAT = "yyyy-MM-dd";
	private Map<String, String> searchConditions;
	
	private SearchBuilder() {
		searchConditions = new LinkedHashMap<String, String>();
	}
	
	public static SearchBuilder builder() {
		return new SearchBuilder();
	}
	
	public String build() {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : searchConditions.entrySet()) {
			if (builder.length() > 0) {
				builder.append("+AND+");
			}
			builder.append(entry.getKey())
				.append(':')
				.append(entry.getValue());
		}
		return builder.toString();
	}
	
	public SearchBuilder withExactField(String field, String value) {
		searchConditions.put(field, '"' + (value) + '"');
		return this;
	}
	
	public SearchBuilder withField(String field, String value) {
		searchConditions.put(field, value);
		return this;
	}
	
	public SearchBuilder withDateRangeField(String field, Date dateFrom, Date dateTo) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		searchConditions.put(field, 
			"[" + dateFormat.format(dateFrom) + 
			"+TO+" + dateFormat.format(dateTo) + "]");
		return this;
	}
}
