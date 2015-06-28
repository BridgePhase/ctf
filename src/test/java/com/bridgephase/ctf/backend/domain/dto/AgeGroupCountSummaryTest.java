package com.bridgephase.ctf.backend.domain.dto;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class AgeGroupCountSummaryTest {

	
	@Test
	public void groupsIntoDecadeGroupsAndReturnsMap() {
		AgeGroupCountSummary summary = new AgeGroupCountSummary();
		summary.add("2", 2);
		Map<String, Integer> decades = summary.decadeMap();
		assertNotNull(decades);
		assertEquals(Integer.valueOf(2), decades.get(AgeGroupCountSummary.TEN_AND_UNDER));
	}
	
	@Test
	public void groupsIntoDecadeGroupsAndReturnsMapWithMultipleResults() {
		AgeGroupCountSummary summary = new AgeGroupCountSummary();
		summary.add("12", 2);
		summary.add("17", 1);
		summary.add("22", 6);
		summary.add("32", 3);
		summary.add("35", 3);
		summary.add("37", 2);
		summary.add("39", 8);
		Map<String, Integer> decades = summary.decadeMap();
		assertNotNull(decades);
		assertEquals(Integer.valueOf(3), decades.get(AgeGroupCountSummary.ELEVEN_TO_TWENTY));
		assertEquals(Integer.valueOf(6), decades.get(AgeGroupCountSummary.TWENTY_ONE_TO_THIRTY));
		assertEquals(Integer.valueOf(16), decades.get(AgeGroupCountSummary.THIRTY_ONE_TO_FORTY));
	}
}
