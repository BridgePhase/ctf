package com.bridgephase.ctf.backend.domain;

import com.bridgephase.ctf.backend.shared.Formats;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugEvent {
	
	private static final String SERIOUSNESS_YES_INDICATOR = "1";
	
	private DrugEventPatient patient;
	
	/** <p>Header<p> */
	@JsonProperty("safetyreportid")
	private String safetyReportId;
	
	@JsonProperty("safetyreportversion")
	private String safetyReportVersion;
	
	@JsonProperty("receivedate")
	private String receiveDate;
	
	@JsonProperty("receivedateformat")
	private String receiveDateFormat;
	
	@JsonProperty("receiptdate")
	private String receiptDate;
	
	@JsonProperty("receiptdateformat")
	private String receiptDateFormat;
	
	private String serious;
	
	@JsonProperty("seriousnesscongenitalanomali")
	private String seriousnessCongenitalAnomali;
	
	@JsonProperty("seriousnessdeath")
	private String seriousnessDeath;
	
	@JsonProperty("seriousnessdisabling")
	private String seriousnessDisabling;
	
	@JsonProperty("seriousnesshospitalization")
	private String seriousnessHospitalization;
	
	@JsonProperty("seriousnesslifethreatening")
	private String seriousnessLifeThreatening;
	
	@JsonProperty("seriousnessother")
	private String seriousnessOther;
	
	@JsonProperty("transmissiondate")
	private String transmissionDate;
	
	@JsonProperty("transmissiondateformat")
	private String transmissionDateFormat;
	
	private String duplicate;
	
	@JsonProperty("companynumb")
	private String companyNumb;
	
	@JsonProperty("occurcountry")
	private String occurCountry;
	
	@JsonProperty("primarysourcecountry")
	private String primarySourceCountry;
	
	@JsonProperty("openfda")
	private OpenFda openFda;

	public DrugEventPatient getPatient() {
		return patient;
	}

	public void setPatient(DrugEventPatient patient) {
		this.patient = patient;
	}

	public String getSafetyReportId() {
		return safetyReportId;
	}

	public void setSafetyReportId(String safetyReportId) {
		this.safetyReportId = safetyReportId;
	}

	public String getSafetyReportVersion() {
		return safetyReportVersion;
	}

	public void setSafetyReportVersion(String safetyReportVersion) {
		this.safetyReportVersion = safetyReportVersion;
	}

	public String getReceiveDate() {
		return receiveDate;
	}

	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}

	public String getReceiveDateFormat() {
		return receiveDateFormat;
	}

	public void setReceiveDateFormat(String receiveDateFormat) {
		this.receiveDateFormat = receiveDateFormat;
	}

	public String getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getReceiptDateFormat() {
		return receiptDateFormat;
	}

	public void setReceiptDateFormat(String receiptDateFormat) {
		this.receiptDateFormat = receiptDateFormat;
	}

	public String getSerious() {
		return serious;
	}

	public void setSerious(String serious) {
		this.serious = serious;
	}

	public String getSeriousnessCongenitalAnomali() {
		return seriousnessCongenitalAnomali;
	}

	public void setSeriousnessCongenitalAnomali(String seriousnessCongenitalAnomali) {
		this.seriousnessCongenitalAnomali = seriousnessCongenitalAnomali;
	}

	public String getSeriousnessDeath() {
		return seriousnessDeath;
	}

	public void setSeriousnessDeath(String seriousnessDeath) {
		this.seriousnessDeath = seriousnessDeath;
	}

	public String getSeriousnessDisabling() {
		return seriousnessDisabling;
	}

	public void setSeriousnessDisabling(String seriousnessDisabling) {
		this.seriousnessDisabling = seriousnessDisabling;
	}

	public String getSeriousnessHospitalization() {
		return seriousnessHospitalization;
	}

	public void setSeriousnessHospitalization(String seriousnessHospitalization) {
		this.seriousnessHospitalization = seriousnessHospitalization;
	}

	public String getSeriousnessLifeThreatening() {
		return seriousnessLifeThreatening;
	}

	public void setSeriousnessLifeThreatening(String seriousnessLifeThreatening) {
		this.seriousnessLifeThreatening = seriousnessLifeThreatening;
	}

	public String getSeriousnessOther() {
		return seriousnessOther;
	}

	public void setSeriousnessOther(String seriousnessOther) {
		this.seriousnessOther = seriousnessOther;
	}

	public String getTransmissionDate() {
		return transmissionDate;
	}

	public void setTransmissionDate(String transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	public String getTransmissionDateFormat() {
		return transmissionDateFormat;
	}

	public void setTransmissionDateFormat(String transmissionDateFormat) {
		this.transmissionDateFormat = transmissionDateFormat;
	}

	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}

	public String getCompanyNumb() {
		return companyNumb;
	}

	public void setCompanyNumb(String companyNumb) {
		this.companyNumb = companyNumb;
	}

	public String getOccurCountry() {
		return occurCountry;
	}

	public void setOccurCountry(String occurCountry) {
		this.occurCountry = occurCountry;
	}

	public String getPrimarySourceCountry() {
		return primarySourceCountry;
	}

	public void setPrimarySourceCountry(String primarySourceCountry) {
		this.primarySourceCountry = primarySourceCountry;
	}

	public OpenFda getOpenFda() {
		return openFda;
	}

	public void setOpenFda(OpenFda openFda) {
		this.openFda = openFda;
	}
	
	@JsonProperty("dangerousEffects")
	public String getAdverseEventDescription() {
		StringBuilder builder = new StringBuilder();
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessDeath)) {
			builder.append("Death");
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessLifeThreatening)) {
			if (builder.length() > 0) {
				builder.append(", ");
			}
			builder.append("Life threathening");
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessDisabling)) {
			if (builder.length() > 0) {
				builder.append(", ");
			}
			builder.append("Disabling");
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessHospitalization)) {
			if (builder.length() > 0) {
				builder.append(", ");
			}
			builder.append("Hospitalization");
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessCongenitalAnomali)) {
			if (builder.length() > 0) {
				builder.append(", ");
			}
			builder.append("Congenital anomali");
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessOther)) {
			if (builder.length() > 0) {
				builder.append(", ");
			}
			builder.append("Other");
		}
		return builder.toString();
	}

	@JsonProperty("mostDangerousEffect")
	public String getMostDangerousEffect() {
		if (!SERIOUSNESS_YES_INDICATOR.equals(serious)) {
			return "Not serious";
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessDeath)) {
			return "Death";
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessLifeThreatening)) {
			return "Life threathening";
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessDisabling)) {
			return "Disabling";
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessHospitalization)) {
			return "Hospitalization";
		}
		if (SERIOUSNESS_YES_INDICATOR.equals(seriousnessCongenitalAnomali)) {
			return "Congenital anomali";
		}
		return "Other";
	}

	@JsonProperty("receiveDateIso")
	public String getReceiveDateAsIso() {
		return Formats.dateAsIso(Formats.openFdaDate(receiveDate));
	}

}
