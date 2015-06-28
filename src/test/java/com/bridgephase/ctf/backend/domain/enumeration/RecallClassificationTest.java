package com.bridgephase.ctf.backend.domain.enumeration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class RecallClassificationTest {

	@Test
	public void correctShortLabels() {
		assertEquals("I", RecallClassification.CLASS_I.shortLabel());
		assertEquals("II", RecallClassification.CLASS_II.shortLabel());
		assertEquals("III", RecallClassification.CLASS_III.shortLabel());
	}
	
	@Test
	public void valueOf() {
		assertEquals(RecallClassification.CLASS_I, RecallClassification.valueOf("CLASS_I"));
	}
	
	@Test
	public void parseHandlesProblems() {
		assertNull(RecallClassification.parse("Not A real Key"));
	}
}
