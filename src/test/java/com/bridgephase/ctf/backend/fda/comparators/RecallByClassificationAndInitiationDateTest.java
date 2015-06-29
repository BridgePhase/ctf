package com.bridgephase.ctf.backend.fda.comparators;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.bridgephase.ctf.backend.domain.EnforcementReport;


public class RecallByClassificationAndInitiationDateTest {
	
	private RecallByClassificationAndInitiationDate comparator;
	
	@Before
	public void setup() {
		comparator = new RecallByClassificationAndInitiationDate();
	}
	
	@Test
	public void testCompare() {
		testClassificationsDifferent();
		testClassificationsSameAndDateDifferent();
		testSame();
	}
	
	private void testClassificationsDifferent() {
		EnforcementReport report1 = new EnforcementReport();
		report1.setClassification("classification");
		
		EnforcementReport report2 = new EnforcementReport();
		report2.setClassification("other");
		
		int result = comparator.compare(report1, report2);
		assertTrue(result < 0);
		
		report2.setClassification("another");
		result = comparator.compare(report1, report2);
		assertTrue(result > 0);
	}
	
	private void testClassificationsSameAndDateDifferent() {
		EnforcementReport report1 = new EnforcementReport();
		report1.setClassification("classification");
		report1.setRecallInitiationDate("20150514");
		
		EnforcementReport report2 = new EnforcementReport();
		report2.setClassification("CLASSIFICATION");
		report2.setRecallInitiationDate("20140514");
		
		int result = comparator.compare(report1, report2);
		assertTrue(result < 0);
		
		report2.setRecallInitiationDate("20160514");
		result = comparator.compare(report1, report2);
		assertTrue(result > 0);
	}
	
	private void testSame() {
		EnforcementReport report1 = new EnforcementReport();
		report1.setClassification("classification");
		report1.setRecallInitiationDate("20150514");
		
		EnforcementReport report2 = new EnforcementReport();
		report2.setClassification("CLASSIFICATION");
		report2.setRecallInitiationDate("20150514");
		
		int result = comparator.compare(report1, report2);
		assertEquals(result, 0);
	}
}
