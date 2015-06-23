package com.bridgephase.ctf.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Drug {
	
	@JsonProperty("actiondrug")
	private String actionDrug;
	
	@JsonProperty("drugadditional")
	private String drugAdditional;
	
	@JsonProperty("drugcumulativedosagenumb")
	private String drugCumulativeDosage;
	
	/**
	 * The unit for drugcumulativedosagenumb
	 * 001 = kilograms (kg)
	 * 002 = grams (g)
	 * 003 = milligrams (Mg)
	 * 004 = micrograms
	 */
	@JsonProperty("drugcumulativedosageunit")
	private String drugCumulativeDosageUnit;
	
	@JsonProperty("drugdosageform")
	private String drugDosageForm;
	
	@JsonProperty("drugintervaldosagedefinition")
	private String drugIntervalDosageDefinition;
	
	@JsonProperty("drugintervaldosageunitnumb")
	private String drugIntervalDosageUnitNumb;
	
	@JsonProperty("drugrecurreadministration")
	private String drugRecurreAdministration;
	
	@JsonProperty("drugadministrationroute")
	private String drugAdministrationRoute;
	
	@JsonProperty("drugenddate")
	private String drugEndDate;
	
	@JsonProperty("drugenddateformat")
	private String drugEndDateFormat;
	
	@JsonProperty("drugstartdate")
	private String drugStartDate;
	
	@JsonProperty("drugstartdateformat")
	private String drugStartDateFormat;
	
	@JsonProperty("drugtreatmentduration")
	private String drugTreatmentDuration;
	
	@JsonProperty("drugtreatmentdurationunit")
	private String drugTreatmentDurationUnit;
	
	@JsonProperty("medicinalproduct")
	private String medicinalProduct;
	
}
