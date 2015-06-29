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
		assertEquals(RecallClassification.CLASS_II, RecallClassification.valueOf("CLASS_II"));
		assertEquals(RecallClassification.CLASS_III, RecallClassification.valueOf("CLASS_III"));
	}
	
	@Test
	public void correctDescriptions() {
		assertEquals(RecallClassification.CLASS_I.description(), "dangerous or defective");
		assertEquals(RecallClassification.CLASS_II.description(), "to pose only a slight threat of serious nature");
		assertEquals(RecallClassification.CLASS_III.description(), "unlikely to cause any adverse health reaction");
	}
	
	@Test
	public void parseHandlesProblems() {
		assertNull(RecallClassification.parse("Not A real Key"));
	}
	
	@Test
	public void testParse() {
		for (RecallClassification value : RecallClassification.values()) {
			assertEquals(value, RecallClassification.parse(value.label()));
		}
	}
}
