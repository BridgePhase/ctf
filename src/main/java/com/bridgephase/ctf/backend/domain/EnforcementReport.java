package com.bridgephase.ctf.backend.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EnforcementReport {
	
	private String reasonForRecall;
	private String status;
	@JsonProperty("distribution_pattern")
	private String distributionPattern;
	private String productQuantity;
	private String recallInitiationDate;
	private String state;
	private String eventId;
	private String productType;
	private String productDescription;
	private String country;
	private String city;
	private String recallingFirm;
	private String voluntaryMandated;
	private String codeInfo;
	private String reportDate;
	private String classification;
	private String initialFirmNotification;
	private OpenFda openFda;
	private String recallNumber;
	
	public String getReasonForRecall() {
		return reasonForRecall;
	}

	public void setReasonForRecall(String reasonForRecall) {
		this.reasonForRecall = reasonForRecall;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDistributionPattern() {
		return distributionPattern;
	}

	public void setDistributionPattern(String distributionPattern) {
		this.distributionPattern = distributionPattern;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getRecallInitiationDate() {
		return recallInitiationDate;
	}

	public void setRecallInitiationDate(String recallInitiationDate) {
		this.recallInitiationDate = recallInitiationDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRecallingFirm() {
		return recallingFirm;
	}

	public void setRecallingFirm(String recallingFirm) {
		this.recallingFirm = recallingFirm;
	}

	public String getVoluntaryMandated() {
		return voluntaryMandated;
	}

	public void setVoluntaryMandated(String voluntaryMandated) {
		this.voluntaryMandated = voluntaryMandated;
	}

	public String getCodeInfo() {
		return codeInfo;
	}

	public void setCodeInfo(String codeInfo) {
		this.codeInfo = codeInfo;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getInitialFirmNotification() {
		return initialFirmNotification;
	}

	public void setInitialFirmNotification(String initialFirmNotification) {
		this.initialFirmNotification = initialFirmNotification;
	}

	public OpenFda getOpenFda() {
		return openFda;
	}

	public void setOpenFda(OpenFda openFda) {
		this.openFda = openFda;
	}

	public String getRecallNumber() {
		return recallNumber;
	}

	public void setRecallNumber(String recallNumber) {
		this.recallNumber = recallNumber;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
