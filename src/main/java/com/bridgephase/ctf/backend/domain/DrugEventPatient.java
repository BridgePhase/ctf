package com.bridgephase.ctf.backend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugEventPatient {
	
	@JsonProperty("patientonsetage")
	private String patientOnsetAge;
	
	@JsonProperty("patientonsetageunit")
	private String patientOnsetAgeUnit;
	
	@JsonProperty("patientsex")
	private String patientSex;
	
	@JsonProperty("patientweight")
	private String patientWeight;
	
	@JsonProperty("patientdeath")
	private List<PatientDeath> patientDeath;
	
	@JsonProperty("drug")
	private List<Drug> drugs;
	
	@JsonProperty("reaction")
	private List<Reaction> reaction;

	public String getPatientOnsetAge() {
		return patientOnsetAge;
	}

	public void setPatientOnsetAge(String patientOnsetAge) {
		this.patientOnsetAge = patientOnsetAge;
	}

	public String getPatientOnsetAgeUnit() {
		return patientOnsetAgeUnit;
	}

	public void setPatientOnsetAgeUnit(String patientOnsetAgeUnit) {
		this.patientOnsetAgeUnit = patientOnsetAgeUnit;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}

	public List<PatientDeath> getPatientDeath() {
		return patientDeath;
	}

	public void setPatientDeath(List<PatientDeath> patientDeath) {
		this.patientDeath = patientDeath;
	}

	public List<Drug> getDrugs() {
		return drugs;
	}

	public void setDrugs(List<Drug> drugs) {
		this.drugs = drugs;
	}
	
	public List<Reaction> getReaction() {
		return reaction;
	}

	public void setReaction(List<Reaction> reaction) {
		this.reaction = reaction;
	}
}
