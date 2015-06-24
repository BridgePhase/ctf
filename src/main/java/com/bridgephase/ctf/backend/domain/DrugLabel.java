package com.bridgephase.ctf.backend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugLabel {
	
	private String setId;
	private String id;
	private String version;
	private String effectiveTime;
	private List<String> drugAbuseAndDependence;
	private List<String> controlledSubstance;
	private List<String> abuse;
	private List<String> dependence;
	private List<String> overdosage;
	private List<String> adverseReactions;
	private List<String> drugInteractions;
	private List<String> drugAndOrLaboratoryTestInteractions;
	private List<String> clinicalPharmacology;
	private List<String> mechanismOfAction;
	private List<String> indicationsAndUsage;
	private List<String> contraindications;
	private List<String> dosageAndAdministration;
	private List<String> dosageFormsAndStrengths;
	private List<String> purpose;
	private List<String> description;
	private List<String> activeIngredient;
	private List<String> inactiveIngredient;
	private List<String> splProductDataElements;
	private List<String> splPatientPackageInsert;
	private List<String> informationForPatients;
	private List<String> informationForOwnersOrCaregivers;
	private List<String> instructionsForUse;
	private List<String> askDoctor;
	private List<String> askDoctorOrPharmacist;
	private List<String> doNotUse;
	private List<String> keepOutOfReachOfChildren;
	private List<String> otherSafetyInformatoin;
	private List<String> questions;
	private List<String> stopUse;
	private List<String> whenUsing;
	
	
	/** <p>Special populations<p> */
	private List<String> pregnancy;
	private List<String> nursingMothers;
	private List<String> pediatricUse;
	private List<String> geriatricUse;
	
	/** <p>Warnings<p> */
	private List<String> boxedWarning;
	private List<String> warningsAndPrecautions;
	private List<String> userSafetyWarnings;
	private List<String> precautions;
	private List<String> warnings;
	private List<String> generalPrecautions;
	
	private OpenFda openFda;

	public String getSetId() {
		return setId;
	}

	public void setSetId(String setId) {
		this.setId = setId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEffectiveTime() {
		return effectiveTime;
	}

	public void setEffectiveTime(String effectiveTime) {
		this.effectiveTime = effectiveTime;
	}

	public List<String> getDrugAbuseAndDependence() {
		return drugAbuseAndDependence;
	}

	public void setDrugAbuseAndDependence(List<String> drugAbuseAndDependence) {
		this.drugAbuseAndDependence = drugAbuseAndDependence;
	}

	public List<String> getControlledSubstance() {
		return controlledSubstance;
	}

	public void setControlledSubstance(List<String> controlledSubstance) {
		this.controlledSubstance = controlledSubstance;
	}

	public List<String> getAbuse() {
		return abuse;
	}

	public void setAbuse(List<String> abuse) {
		this.abuse = abuse;
	}

	public List<String> getDependence() {
		return dependence;
	}

	public void setDependence(List<String> dependence) {
		this.dependence = dependence;
	}

	public List<String> getOverdosage() {
		return overdosage;
	}

	public void setOverdosage(List<String> overdosage) {
		this.overdosage = overdosage;
	}

	public List<String> getAdverseReactions() {
		return adverseReactions;
	}

	public void setAdverseReactions(List<String> adverseReactions) {
		this.adverseReactions = adverseReactions;
	}

	public List<String> getDrugInteractions() {
		return drugInteractions;
	}

	public void setDrugInteractions(List<String> drugInteractions) {
		this.drugInteractions = drugInteractions;
	}

	public List<String> getDrugAndOrLaboratoryTestInteractions() {
		return drugAndOrLaboratoryTestInteractions;
	}

	public void setDrugAndOrLaboratoryTestInteractions(
			List<String> drugAndOrLaboratoryTestInteractions) {
		this.drugAndOrLaboratoryTestInteractions = drugAndOrLaboratoryTestInteractions;
	}

	public List<String> getClinicalPharmacology() {
		return clinicalPharmacology;
	}

	public void setClinicalPharmacology(List<String> clinicalPharmacology) {
		this.clinicalPharmacology = clinicalPharmacology;
	}

	public List<String> getMechanismOfAction() {
		return mechanismOfAction;
	}

	public void setMechanismOfAction(List<String> mechanismOfAction) {
		this.mechanismOfAction = mechanismOfAction;
	}

	public List<String> getIndicationsAndUsage() {
		return indicationsAndUsage;
	}

	public void setIndicationsAndUsage(List<String> indicationsAndUsage) {
		this.indicationsAndUsage = indicationsAndUsage;
	}

	public List<String> getContraindications() {
		return contraindications;
	}

	public void setContraindications(List<String> contraindications) {
		this.contraindications = contraindications;
	}

	public List<String> getDosageAndAdministration() {
		return dosageAndAdministration;
	}

	public void setDosageAndAdministration(List<String> dosageAndAdministration) {
		this.dosageAndAdministration = dosageAndAdministration;
	}

	public List<String> getDosageFormsAndStrengths() {
		return dosageFormsAndStrengths;
	}

	public void setDosageFormsAndStrengths(List<String> dosageFormsAndStrengths) {
		this.dosageFormsAndStrengths = dosageFormsAndStrengths;
	}

	public List<String> getPurpose() {
		return purpose;
	}

	public void setPurpose(List<String> purpose) {
		this.purpose = purpose;
	}

	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public List<String> getActiveIngredient() {
		return activeIngredient;
	}

	public void setActiveIngredient(List<String> activeIngredient) {
		this.activeIngredient = activeIngredient;
	}

	public List<String> getInactiveIngredient() {
		return inactiveIngredient;
	}

	public void setInactiveIngredient(List<String> inactiveIngredient) {
		this.inactiveIngredient = inactiveIngredient;
	}

	public List<String> getSplProductDataElements() {
		return splProductDataElements;
	}

	public void setSplProductDataElements(List<String> splProductDataElements) {
		this.splProductDataElements = splProductDataElements;
	}

	public List<String> getSplPatientPackageInsert() {
		return splPatientPackageInsert;
	}

	public void setSplPatientPackageInsert(List<String> splPatientPackageInsert) {
		this.splPatientPackageInsert = splPatientPackageInsert;
	}

	public List<String> getInformationForPatients() {
		return informationForPatients;
	}

	public void setInformationForPatients(List<String> informationForPatients) {
		this.informationForPatients = informationForPatients;
	}

	public List<String> getInformationForOwnersOrCaregivers() {
		return informationForOwnersOrCaregivers;
	}

	public void setInformationForOwnersOrCaregivers(
			List<String> informationForOwnersOrCaregivers) {
		this.informationForOwnersOrCaregivers = informationForOwnersOrCaregivers;
	}

	public List<String> getInstructionsForUse() {
		return instructionsForUse;
	}

	public void setInstructionsForUse(List<String> instructionsForUse) {
		this.instructionsForUse = instructionsForUse;
	}

	public List<String> getAskDoctor() {
		return askDoctor;
	}

	public void setAskDoctor(List<String> askDoctor) {
		this.askDoctor = askDoctor;
	}

	public List<String> getAskDoctorOrPharmacist() {
		return askDoctorOrPharmacist;
	}

	public void setAskDoctorOrPharmacist(List<String> askDoctorOrPharmacist) {
		this.askDoctorOrPharmacist = askDoctorOrPharmacist;
	}

	public List<String> getDoNotUse() {
		return doNotUse;
	}

	public void setDoNotUse(List<String> doNotUse) {
		this.doNotUse = doNotUse;
	}

	public List<String> getKeepOutOfReachOfChildren() {
		return keepOutOfReachOfChildren;
	}

	public void setKeepOutOfReachOfChildren(List<String> keepOutOfReachOfChildren) {
		this.keepOutOfReachOfChildren = keepOutOfReachOfChildren;
	}

	public List<String> getOtherSafetyInformatoin() {
		return otherSafetyInformatoin;
	}

	public void setOtherSafetyInformatoin(List<String> otherSafetyInformatoin) {
		this.otherSafetyInformatoin = otherSafetyInformatoin;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public List<String> getStopUse() {
		return stopUse;
	}

	public void setStopUse(List<String> stopUse) {
		this.stopUse = stopUse;
	}

	public List<String> getWhenUsing() {
		return whenUsing;
	}

	public void setWhenUsing(List<String> whenUsing) {
		this.whenUsing = whenUsing;
	}

	public List<String> getPregnancy() {
		return pregnancy;
	}

	public void setPregnancy(List<String> pregnancy) {
		this.pregnancy = pregnancy;
	}

	public List<String> getNursingMothers() {
		return nursingMothers;
	}

	public void setNursingMothers(List<String> nursingMothers) {
		this.nursingMothers = nursingMothers;
	}

	public List<String> getPediatricUse() {
		return pediatricUse;
	}

	public void setPediatricUse(List<String> pediatricUse) {
		this.pediatricUse = pediatricUse;
	}

	public List<String> getGeriatricUse() {
		return geriatricUse;
	}

	public void setGeriatricUse(List<String> geriatricUse) {
		this.geriatricUse = geriatricUse;
	}

	public List<String> getBoxedWarning() {
		return boxedWarning;
	}

	public void setBoxedWarning(List<String> boxedWarning) {
		this.boxedWarning = boxedWarning;
	}

	public List<String> getWarningsAndPrecautions() {
		return warningsAndPrecautions;
	}

	public void setWarningsAndPrecautions(List<String> warningsAndPrecautions) {
		this.warningsAndPrecautions = warningsAndPrecautions;
	}

	public List<String> getUserSafetyWarnings() {
		return userSafetyWarnings;
	}

	public void setUserSafetyWarnings(List<String> userSafetyWarnings) {
		this.userSafetyWarnings = userSafetyWarnings;
	}

	public List<String> getPrecautions() {
		return precautions;
	}

	public void setPrecautions(List<String> precautions) {
		this.precautions = precautions;
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	public List<String> getGeneralPrecautions() {
		return generalPrecautions;
	}

	public void setGeneralPrecautions(List<String> generalPrecautions) {
		this.generalPrecautions = generalPrecautions;
	}

	public OpenFda getOpenFda() {
		return openFda;
	}

	public void setOpenFda(OpenFda openFda) {
		this.openFda = openFda;
	}
}
