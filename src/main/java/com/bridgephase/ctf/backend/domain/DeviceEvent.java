package com.bridgephase.ctf.backend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceEvent {
	
	private List<Device> devices;
	
	@JsonProperty("mdr_text")
	private List<ReportText> reportText;
	
	private List<Patient> patients;

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public List<ReportText> getReportText() {
		return reportText;
	}

	public void setReportText(List<ReportText> reportText) {
		this.reportText = reportText;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

}
