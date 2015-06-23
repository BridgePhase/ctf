package com.bridgephase.ctf.backend.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DrugEventPatientTest {

	@Test
	public void returnsPatientSexAsFriendlyString() {
		DrugEventPatient patient = new DrugEventPatient();
		assertEquals("Unknown", patient.getPatientSexString());
		patient.setPatientSex("1");
		assertEquals("Male", patient.getPatientSexString());
		patient.setPatientSex("2");
		assertEquals("Female", patient.getPatientSexString());
	}
}
