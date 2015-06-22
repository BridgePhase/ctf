package com.bridgephase.ctf.backend.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportText {
	
	/**
	 * Patient which the narrative text or problem description is about. For reports that did not 
	 * involve a patient adverse event, this field will still often contain 1 even if the problem 
	 * description is just about the suspect medical device.
	 */
	@JsonProperty("patient_sequence_number")
	private String patient_sequence_number;
	
	/**
	 * Description of DeviceEvent or Problem = The problem (quality, performance, or safety concern) in 
	 * sufficient detail so that the circumstances surrounding the defect or malfunction of the 
	 * medical product can be understood. For patient adverse events, may include a description 
	 * of the event in detail using the reporter's own words, including a description of what 
	 * happened and a summary of all relevant clinical information (medical status prior to the 
	 * event; signs and/or symptoms; differential diagnosis for the event in question; clinical 
	 * course; treatment; outcome, etc.). If available and if relevant, may include synopses of 
	 * any office visit notes or the hospital discharge summary. This section may also contain 
	 * information about surgical procedures and laboratory tests. 
	 * 
	 * Manufacturer Evaluation Summary = If available, the results of any evaluation of a 
	 * malfunctioning device and, if known, any relevant maintenance/service information should 
	 * be included in this section.
	 * 
	 * Additional Manufacturer Narrative = Documentation forthcoming.
	*/
	@JsonProperty("text_type_code")
	private String textTypeCode;
	
	/**
	 * Narrative text or problem description.
	 */
	private String text;
	
	/**
	 * Documentation forthcoming.
	 */
	@JsonProperty("mdr_text_key")
	private String textKey;
	
	/**
	 * Documentation forthcoming.
	 */
	@JsonProperty("date_received")
	private String dateReceived;

	public String getPatient_sequence_number() {
		return patient_sequence_number;
	}

	public void setPatient_sequence_number(String patient_sequence_number) {
		this.patient_sequence_number = patient_sequence_number;
	}

	public String getTextTypeCode() {
		return textTypeCode;
	}

	public void setTextTypeCode(String textTypeCode) {
		this.textTypeCode = textTypeCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTextKey() {
		return textKey;
	}

	public void setTextKey(String textKey) {
		this.textKey = textKey;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
