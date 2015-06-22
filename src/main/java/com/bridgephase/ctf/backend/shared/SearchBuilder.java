package com.bridgephase.ctf.backend.shared;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchBuilder {
	private static final String FORMAT = "yyyy-MM-dd";
	private StringBuilder builder = new StringBuilder();

	private SearchBuilder() {
	}
	
	public static SearchBuilder builder() {
		return new SearchBuilder();
	}
	
	public String build() {
		return builder.toString();
	}
	
	public SearchBuilder withExactField(String field, String value) {
		addField();
		try {
			builder.append(URLEncoder.encode(field + ":\"" + value + "\"", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			builder.append(field + ":\"" + value + "\"");
		}
		return this;
	}
	
	public SearchBuilder withField(String field, String value) {
		addField();
		try {
			builder.append(URLEncoder.encode(field + ":" + value, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			builder.append(field + ":\"" + value + "\"");
		}
		return this;
	}
	
	public SearchBuilder withDateRangeField(String field, Date dateFrom, Date dateTo) {
		addField();
		SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT);
		try {
			builder.append(URLEncoder.encode(field + ":[", "UTF-8"))
				.append(dateFormat.format(dateFrom))
				.append("%20TO%20")
				.append(dateFormat.format(dateTo))
				.append(URLEncoder.encode("]", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			builder.append(field + ":[" + dateFormat.format(dateFrom) + 
				"+TO+" + dateFormat.format(dateTo) + "]");
		}
		return this;
	}
	
	private void addField() {
		if (builder.length() > 0) {
			builder.append("%20AND%20");
		}
	}
}
