package com.bridgephase.ctf.backend.shared;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class SearchBuilderTest {

	@Test
	public void canHandleSingleField() throws UnsupportedEncodingException {
		String searchQuery = SearchBuilder.builder()
			.withField("blab", "value")
			.build();
		assertEquals(URLEncoder.encode("blab:value", "UTF-8"), searchQuery);
	}
	
	@Test
	public void canHandleMultipleFields() throws UnsupportedEncodingException {
		String searchQuery = SearchBuilder.builder()
			.withField("blab", "helloWorld")
			.withField("now_say", "byeWorld")
			.build();
		assertEquals("blab%3AhelloWorld%20AND%20now_say%3AbyeWorld", searchQuery);
	}
	
	@Test
	public void canHandleDateRanges() throws ParseException, UnsupportedEncodingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String searchQuery = SearchBuilder.builder()
			.withDateRangeField("blab", 
				format.parse("2012-01-23"),
				format.parse("2013-05-06"))
			.build();
		assertEquals("blab%3A%5B2012-01-23%20TO%202013-05-06%5D", searchQuery);
	}
	
	@Test
	public void canPerformExactMatches() throws UnsupportedEncodingException {
		String searchQuery = SearchBuilder.builder()
			.withExactField("blab", "value")
			.build();
		assertEquals(URLEncoder.encode("blab:\"value\"", "UTF-8"), searchQuery);
	}
}
