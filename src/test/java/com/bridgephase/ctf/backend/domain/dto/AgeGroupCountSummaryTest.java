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
		summary.add("49", 8);
		summary.add("59", 8);
		summary.add("69", 8);
		summary.add("79", 8);
		summary.add("89", 8);
		summary.add("99", 8);
		summary.add("109", 8);
		summary.add("129", 8);
		Map<String, Integer> decades = summary.decadeMap();
		assertNotNull(decades);
		assertEquals(Integer.valueOf(3), decades.get(AgeGroupCountSummary.ELEVEN_TO_TWENTY));
		assertEquals(Integer.valueOf(6), decades.get(AgeGroupCountSummary.TWENTY_ONE_TO_THIRTY));
		assertEquals(Integer.valueOf(16), decades.get(AgeGroupCountSummary.THIRTY_ONE_TO_FORTY));
		assertEquals(Integer.valueOf(8), decades.get(AgeGroupCountSummary.FORTY_ONE_TO_FIFTY));
		assertEquals(Integer.valueOf(8), decades.get(AgeGroupCountSummary.FIFTY_ONE_TO_SIXTY));
		assertEquals(Integer.valueOf(8), decades.get(AgeGroupCountSummary.SIXTY_ONE_TO_SEVENTY));
		assertEquals(Integer.valueOf(8), decades.get(AgeGroupCountSummary.SEVENTY_ONE_TO_EIGHTY));
		assertEquals(Integer.valueOf(8), decades.get(AgeGroupCountSummary.EIGHTY_ONE_TO_NINETY));
		assertEquals(Integer.valueOf(8), decades.get(AgeGroupCountSummary.NINETY_ONE_TO_ONE_HUNDRED));
		assertEquals(Integer.valueOf(16), decades.get(AgeGroupCountSummary.OVER_ONE_HUNDRED));
	}
}
