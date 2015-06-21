package com.bridgephase.ctf.backend.shared;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

public class SearchBuilderTest {

	@Test
	public void canHandleSingleField() {
		String searchQuery = SearchBuilder.builder()
			.withField("blab", "value")
			.build();
		assertEquals("blab:value", searchQuery);
	}
	
	@Test
	public void canHandleMultipleFields() {
		String searchQuery = SearchBuilder.builder()
			.withField("blab", "helloWorld")
			.withField("now_say", "byeWorld")
			.build();
		assertEquals("blab:helloWorld+AND+now_say:byeWorld", searchQuery);
	}
	
	@Test
	public void canHandleDateRanges() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String searchQuery = SearchBuilder.builder()
			.withDateRangeField("blab", 
				format.parse("2012-01-23"),
				format.parse("2013-05-06"))
			.build();
		assertEquals("blab:[2012-01-23+TO+2013-05-06]", searchQuery);
	}
	
	@Test
	public void canPerformExactMatches() {
		String searchQuery = SearchBuilder.builder()
			.withExactField("blab", "value")
			.build();
		assertEquals("blab:\"value\"", searchQuery);
	}
}
