package com.bridgephase.ctf.backend.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Patient {
	
	/**
	 * Number identifying this particular patient. For example, the first patient object will 
	 * have the value 1. This is an enumeration corresponding to the number of patients 
	 * involved in an adverse event.
	 */
	@JsonProperty("patient_sequence_number")
    private String patientSequenceNumber;
	
	/**
	 * Date the report about this patient was received.
	 */
	@JsonProperty("date_received")
	private String dateReceived;

	/**
	 * Treatment the patient received.
	 */
	@JsonProperty("sequence_number_treatment")
    private String sequenceNumberTreatment;

	/**
	 * Outcome associated with the adverse event for this patient. Expect wide variability in this 
	 * field; each string in the list of strings may contain multiple outcomes, separated by 
	 * commas, and with numbers, which may or may not be related to the patient_sequence_number.
		Life Threatening
		Hospitalization
		Disability
		Congenital Anomaly
		Required Intervention
		Other
		Invalid Data
		Unknown
		No Information
		Not Applicable
		Death
	*/
	@JsonProperty("sequence_number_outcome")
    private List<String> sequenceNumberOutcome;

	public String getPatientSequenceNumber() {
		return patientSequenceNumber;
	}

	public void setPatientSequenceNumber(String patientSequenceNumber) {
		this.patientSequenceNumber = patientSequenceNumber;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getSequenceNumberTreatment() {
		return sequenceNumberTreatment;
	}

	public void setSequenceNumberTreatment(String sequenceNumberTreatment) {
		this.sequenceNumberTreatment = sequenceNumberTreatment;
	}

	public List<String> getSequenceNumberOutcome() {
		return sequenceNumberOutcome;
	}

	public void setSequenceNumberOutcome(List<String> sequenceNumberOutcome) {
		this.sequenceNumberOutcome = sequenceNumberOutcome;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
