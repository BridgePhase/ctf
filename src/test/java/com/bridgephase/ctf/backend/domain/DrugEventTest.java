package com.bridgephase.ctf.backend.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DrugEventTest {
	
	@Test
	public void returnsNonSeriousWhenNotSerious() {
		DrugEvent event = new DrugEvent();
		event.setSerious("0");
		assertEquals("Not serious", event.getMostDangerousEffect());
	}
	
	@Test
	public void returnsDateFormattedAsIso() {
		DrugEvent event = new DrugEvent();
		event.setReceiveDate("20140520");
		assertEquals("2014-05-20T00:00:00", event.getReceiveDateAsIso());
	}
	
	@Test
	public void returnsCorrectMostSeriousEffect() {
		DrugEvent event = new DrugEvent();
		event.setSerious("1");

		event.setSeriousnessOther("1");
		assertEquals("Other", event.getMostDangerousEffect());
		
		event.setSeriousnessHospitalization("1");
		assertEquals("Hospitalization", event.getMostDangerousEffect());

		event.setSeriousnessDisabling("1");
		assertEquals("Disabling", event.getMostDangerousEffect());
		
		event.setSeriousnessLifeThreatening("1");
		assertEquals("Life threathening", event.getMostDangerousEffect());

		event.setSeriousnessDeath("1");
		assertEquals("Death", event.getMostDangerousEffect());
	}
	
	@Test
	public void returnsCorrectListOfAllSeriousness() {
		DrugEvent event = new DrugEvent();
		event.setSerious("1");

		event.setSeriousnessOther("1");
		assertEquals("Other", event.getAdverseEventDescription());
		
		event.setSeriousnessHospitalization("1");
		assertEquals("Hospitalization, Other", event.getAdverseEventDescription());

		event.setSeriousnessDisabling("1");
		assertEquals("Disabling, Hospitalization, Other", 
			event.getAdverseEventDescription());
		
		event.setSeriousnessLifeThreatening("1");
		assertEquals("Life threathening, Disabling, Hospitalization, Other", 
			event.getAdverseEventDescription());

		event.setSeriousnessDeath("1");
		assertEquals("Death, Life threathening, Disabling, Hospitalization, Other",
			event.getAdverseEventDescription());
	}

}
