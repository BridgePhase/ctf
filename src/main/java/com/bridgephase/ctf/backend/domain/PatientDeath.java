package com.bridgephase.ctf.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDeath {
	
	@JsonProperty("patientdeathdate")
	private String patientDeathDate;
	
	@JsonProperty("patientdeathdateformat")
	private String patientDeathDateFormat;

	public String getPatientDeathDate() {
		return patientDeathDate;
	}

	public void setPatientDeathDate(String patientDeathDate) {
		this.patientDeathDate = patientDeathDate;
	}

	public String getPatientDeathDateFormat() {
		return patientDeathDateFormat;
	}

	public void setPatientDeathDateFormat(String patientDeathDateFormat) {
		this.patientDeathDateFormat = patientDeathDateFormat;
	}
}
