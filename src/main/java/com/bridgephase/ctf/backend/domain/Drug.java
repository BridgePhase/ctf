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

	public String getActionDrug() {
		return actionDrug;
	}

	public void setActionDrug(String actionDrug) {
		this.actionDrug = actionDrug;
	}

	public String getDrugAdditional() {
		return drugAdditional;
	}

	public void setDrugAdditional(String drugAdditional) {
		this.drugAdditional = drugAdditional;
	}

	public String getDrugCumulativeDosage() {
		return drugCumulativeDosage;
	}

	public void setDrugCumulativeDosage(String drugCumulativeDosage) {
		this.drugCumulativeDosage = drugCumulativeDosage;
	}

	public String getDrugCumulativeDosageUnit() {
		return drugCumulativeDosageUnit;
	}

	public void setDrugCumulativeDosageUnit(String drugCumulativeDosageUnit) {
		this.drugCumulativeDosageUnit = drugCumulativeDosageUnit;
	}

	public String getDrugDosageForm() {
		return drugDosageForm;
	}

	public void setDrugDosageForm(String drugDosageForm) {
		this.drugDosageForm = drugDosageForm;
	}

	public String getDrugIntervalDosageDefinition() {
		return drugIntervalDosageDefinition;
	}

	public void setDrugIntervalDosageDefinition(String drugIntervalDosageDefinition) {
		this.drugIntervalDosageDefinition = drugIntervalDosageDefinition;
	}

	public String getDrugIntervalDosageUnitNumb() {
		return drugIntervalDosageUnitNumb;
	}

	public void setDrugIntervalDosageUnitNumb(String drugIntervalDosageUnitNumb) {
		this.drugIntervalDosageUnitNumb = drugIntervalDosageUnitNumb;
	}

	public String getDrugRecurreAdministration() {
		return drugRecurreAdministration;
	}

	public void setDrugRecurreAdministration(String drugRecurreAdministration) {
		this.drugRecurreAdministration = drugRecurreAdministration;
	}

	public String getDrugAdministrationRoute() {
		return drugAdministrationRoute;
	}

	public void setDrugAdministrationRoute(String drugAdministrationRoute) {
		this.drugAdministrationRoute = drugAdministrationRoute;
	}

	public String getDrugEndDate() {
		return drugEndDate;
	}

	public void setDrugEndDate(String drugEndDate) {
		this.drugEndDate = drugEndDate;
	}

	public String getDrugEndDateFormat() {
		return drugEndDateFormat;
	}

	public void setDrugEndDateFormat(String drugEndDateFormat) {
		this.drugEndDateFormat = drugEndDateFormat;
	}

	public String getDrugStartDate() {
		return drugStartDate;
	}

	public void setDrugStartDate(String drugStartDate) {
		this.drugStartDate = drugStartDate;
	}

	public String getDrugStartDateFormat() {
		return drugStartDateFormat;
	}

	public void setDrugStartDateFormat(String drugStartDateFormat) {
		this.drugStartDateFormat = drugStartDateFormat;
	}

	public String getDrugTreatmentDuration() {
		return drugTreatmentDuration;
	}

	public void setDrugTreatmentDuration(String drugTreatmentDuration) {
		this.drugTreatmentDuration = drugTreatmentDuration;
	}

	public String getDrugTreatmentDurationUnit() {
		return drugTreatmentDurationUnit;
	}

	public void setDrugTreatmentDurationUnit(String drugTreatmentDurationUnit) {
		this.drugTreatmentDurationUnit = drugTreatmentDurationUnit;
	}

	public String getMedicinalProduct() {
		return medicinalProduct;
	}

	public void setMedicinalProduct(String medicinalProduct) {
		this.medicinalProduct = medicinalProduct;
	}
}
