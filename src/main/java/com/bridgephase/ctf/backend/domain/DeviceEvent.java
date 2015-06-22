package com.bridgephase.ctf.backend.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceEvent {
	
	@JsonProperty("adverse_event_flag")
    private String adverseEventFlag;
	
    @JsonProperty("product_problem_flag")
    private String productProblemFlag;
    
    @JsonProperty("date_of_event")
    private String dateOfEvent;
    
    @JsonProperty("date_report")
    private String dateReport;
    
    @JsonProperty("date_received")
    private String dateReceived;
    
    @JsonProperty("number_devices_in_event")
    private String numberDevicesInEvent;
    
    @JsonProperty("number_patients_in_event")
    private String numberPatientsInEvent;
	
	@JsonProperty("report_number")
	private String reportNumber;
	
	/** <p> Source <p>*/
	
    @JsonProperty("report_source_code")
    private String reportSourceCode;
    
    @JsonProperty("health_professional")
    private String healthProfessional;
    
    @JsonProperty("reporter_occupation_code")
    private String reporterOccupationCode;
    
    @JsonProperty("initial_report_to_fda")
    private String initialReportToFda;
    
    /** <p> Device <p> */
	private List<Device> device;
	
	/** <p> Manufacturer <p> */
	
	/** <p> Patient <p> */
	private List<Patient> patient;
	
	/** <p> Report Text <p> */
	@JsonProperty("mdr_text")
	private List<ReportText> reportText;

	/** <p> Reporter Dependent Fields <p> */
	
    @JsonProperty("type_of_report")
    private List<String> typeOfReport;
    
    @JsonProperty("date_facility_aware")
    private String dateFacilityAware;
    
    @JsonProperty("report_date")
    private String reportDate;
    
    @JsonProperty("report_to_fda")
    private String reportToFda;
    
    @JsonProperty("date_report_to_fda")
    private String dateReportToFda;
    
    @JsonProperty("report_to_manufacturer")
	private String reportToManufacturer;
    
    @JsonProperty("date_report_to_manufacturer")
    private String dateReportToManufacturer;
    
    @JsonProperty("event_location")
    private String eventLocation;
    
    /** <p>Distributor<p> */
    
    @JsonProperty("distributor_name")
    private String distributorName;
    
    @JsonProperty("distributor_address_1")
    private String distributorAddress1;
    
    @JsonProperty("distributor_address_2")
    private String distributorAddress2;
    
    @JsonProperty("distributor_city")
    private String distributorCity;
    
    @JsonProperty("distributor_state")
    private String distributorState;
    
    @JsonProperty("distributor_zip_code")
    private String distributorZipCode;
    
    @JsonProperty("distributor_zip_code_ext")
    private String distributorZipCodeExt;
    
	/** <p>Susepct Manufacturer<p> */
    @JsonProperty("manufacturer_name")
    private String manufacturerName;
    
    @JsonProperty("manufacturer_address_1")
    private String manufacturerAddress1;

    @JsonProperty("manufacturerAddress2")
    private String manufacturer_address_2;
    
    @JsonProperty("manufacturer_city")
    private String manufacturerCity;
    
    @JsonProperty("manufacturer_state")
    private String manufacturerState;
	
    @JsonProperty("manufacturer_zip_code")
    private String manufacturerZipCode;
    
    @JsonProperty("manufacturer_zip_code_ext")
    private String manufacturerZipCodeExt;
    
    @JsonProperty("manufacturer_country")
    private String manufacturerCountry;
    
    @JsonProperty("manufacturer_postal_code")
    private String manufacturerPostalCode;
    
	@JsonProperty("event_type")
    private String eventType;
    
	@JsonProperty("device_date_of_manufacturer")
    private String deviceDateOfManufacturer;

    @JsonProperty("single_use_flag")
    private String singleUseFlag;

    @JsonProperty("previous_use_code")
    private String previousUseCode;
    
    /** <p>Corrective or Remedial Action<p> */
    @JsonProperty("remedial_action")
    private List<String> remedialAction;
    
    @JsonProperty("removal_correction_number")
    private String removalCorrectionNumber;
    
    /** <p>Manufacturer Contact<p> */
    @JsonProperty("manufacturer_contact_t_name")
    private String manufacturerContactTitleName;
    
    @JsonProperty("manufacturer_contact_f_name")
    private String manufacturerContactFirstName;
    
    @JsonProperty("manufacturer_contact_l_name")
    private String manufacturercontactLastName;
    
    @JsonProperty("manufacturer_contact_address_1")
    private String manufacturerContactAddress1;
	
    @JsonProperty("manufacturer_contact_address_2")
    private String manufacturerContactAddress2;
    
    @JsonProperty("manufacturer_contact_city")
    private String manufacturerContactCity;
    
	@JsonProperty("manufacturer_contact_state")
    private String manufacturerContactState;
	
    @JsonProperty("manufacturer_contact_zip_code")
    private String manufacturerContactZipCode;
	
    @JsonProperty("manufacturer_contact_zip_ext")
    private String manufacturerContactZipExt;
	
    @JsonProperty("manufacturer_contact_postal_code")
    private String manufacturerContactPostalCode;
    
    @JsonProperty("manufacturer_contact_country")
    private String manufacturerContactCountry;
    
    /** <p>Manufacturer Contact Phone<p> */
    @JsonProperty("manufacturer_contact_phone_number")
    private String manufacturerContactPhoneNumber;
    
	@JsonProperty("manufacturer_contact_pcountry")
    private String manufacturerContactPhoneCountry;
    
    @JsonProperty("manufacturer_contact_area_code")
    private String manufacturerContactAreaCode;
   
    @JsonProperty("manufacturer_contact_exchange")
    private String manufacturerContactExchange;
    
    @JsonProperty("manufacturer_contact_extension")
    private String manufacturerContactExtension;
    
    @JsonProperty("manufacturer_contact_pcity")
    private String manufacturerContactPhoneCity;
    
    @JsonProperty("manufacturer_contact_plocal")
    private String manufacturerContactPhoneLocal;
    
    /** <p>Manufacturer Name and Address<p> */
    @JsonProperty("manufacturer_g1_name")
    private String manufacturerG1Name;
    
	@JsonProperty("manufacturer_g1_address_1")
    private String manufacturerG1Address1;
	
    @JsonProperty("manufacturer_g1_address_2")
    private String manufacturerG1Address2;
    
    @JsonProperty("manufacturer_g1_city")
    private String manufacturerG1City;
    
    @JsonProperty("manufacturer_g1_state")
    private String manufacturerG1State;
    
    @JsonProperty("manufacturer_g1_zip_code")
    private String manufacturerG1ZipCode;
    
    @JsonProperty("manufacturer_g1_zip_code_ext")
    private String manufacturerG1ZipCodeExt;
    
    @JsonProperty("manufacturer_g1_postal_code")
    private String manufacturerG1PostalCode;
    
    @JsonProperty("manufacturer_g1_country")
    private String manufacturerG1Country;
    
    /** <p>By Any Manufacturer<p> */
    @JsonProperty("date_manufacturer_received")
    private String dateManufacturerReceived;
    
	@JsonProperty("source_type")
    private List<String> sourceType;
	
    @JsonProperty("event_key")
    private String eventKey;
    
    @JsonProperty("mdr_report_key")
    private String mdrReportKey;
    
    @JsonProperty("manufacturer_link_flag")
    private String manufacturerLinkFlag;

    @JsonProperty("reprocessed_and_reused_flag")
    private String reprocessedAndReusedFlag;
    
	public String getAdverseEventFlag() {
		return adverseEventFlag;
	}

	public void setAdverseEventFlag(String adverseEventFlag) {
		this.adverseEventFlag = adverseEventFlag;
	}

	public String getProductProblemFlag() {
		return productProblemFlag;
	}

	public void setProductProblemFlag(String productProblemFlag) {
		this.productProblemFlag = productProblemFlag;
	}

	public String getDateOfEvent() {
		return dateOfEvent;
	}

	public void setDateOfEvent(String dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	public String getDateReport() {
		return dateReport;
	}

	public void setDateReport(String dateReport) {
		this.dateReport = dateReport;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getNumberDevicesInEvent() {
		return numberDevicesInEvent;
	}

	public void setNumberDevicesInEvent(String numberDevicesInEvent) {
		this.numberDevicesInEvent = numberDevicesInEvent;
	}

	public String getNumberPatientsInEvent() {
		return numberPatientsInEvent;
	}

	public void setNumberPatientsInEvent(String numberPatientsInEvent) {
		this.numberPatientsInEvent = numberPatientsInEvent;
	}

	public String getReportNumber() {
		return reportNumber;
	}

	public void setReportNumber(String reportNumber) {
		this.reportNumber = reportNumber;
	}

	public String getReportSourceCode() {
		return reportSourceCode;
	}

	public void setReportSourceCode(String reportSourceCode) {
		this.reportSourceCode = reportSourceCode;
	}

	public String getHealthProfessional() {
		return healthProfessional;
	}

	public void setHealthProfessional(String healthProfessional) {
		this.healthProfessional = healthProfessional;
	}

	public String getReporterOccupationCode() {
		return reporterOccupationCode;
	}

	public void setReporterOccupationCode(String reporterOccupationCode) {
		this.reporterOccupationCode = reporterOccupationCode;
	}

	public String getInitialReportToFda() {
		return initialReportToFda;
	}

	public void setInitialReportToFda(String initialReportToFda) {
		this.initialReportToFda = initialReportToFda;
	}

	public List<Device> getDevice() {
		return device;
	}

	public void setDevice(List<Device> device) {
		this.device = device;
	}

	public List<Patient> getPatient() {
		return patient;
	}

	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}

	public List<ReportText> getReportText() {
		return reportText;
	}

	public void setReportText(List<ReportText> reportText) {
		this.reportText = reportText;
	}

	public List<String> getTypeOfReport() {
		return typeOfReport;
	}

	public void setTypeOfReport(List<String> typeOfReport) {
		this.typeOfReport = typeOfReport;
	}

	public String getDateFacilityAware() {
		return dateFacilityAware;
	}

	public void setDateFacilityAware(String dateFacilityAware) {
		this.dateFacilityAware = dateFacilityAware;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getReportToFda() {
		return reportToFda;
	}

	public void setReportToFda(String reportToFda) {
		this.reportToFda = reportToFda;
	}

	public String getDateReportToFda() {
		return dateReportToFda;
	}

	public void setDateReportToFda(String dateReportToFda) {
		this.dateReportToFda = dateReportToFda;
	}

	public String getReportToManufacturer() {
		return reportToManufacturer;
	}

	public void setReportToManufacturer(String reportToManufacturer) {
		this.reportToManufacturer = reportToManufacturer;
	}

	public String getDateReportToManufacturer() {
		return dateReportToManufacturer;
	}

	public void setDateReportToManufacturer(String dateReportToManufacturer) {
		this.dateReportToManufacturer = dateReportToManufacturer;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getDistributorName() {
		return distributorName;
	}

	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}

	public String getDistributorAddress1() {
		return distributorAddress1;
	}

	public void setDistributorAddress1(String distributorAddress1) {
		this.distributorAddress1 = distributorAddress1;
	}

	public String getDistributorAddress2() {
		return distributorAddress2;
	}

	public void setDistributorAddress2(String distributorAddress2) {
		this.distributorAddress2 = distributorAddress2;
	}

	public String getDistributorCity() {
		return distributorCity;
	}

	public void setDistributorCity(String distributorCity) {
		this.distributorCity = distributorCity;
	}

	public String getDistributorState() {
		return distributorState;
	}

	public void setDistributorState(String distributorState) {
		this.distributorState = distributorState;
	}

	public String getDistributorZipCode() {
		return distributorZipCode;
	}

	public void setDistributorZipCode(String distributorZipCode) {
		this.distributorZipCode = distributorZipCode;
	}

	public String getDistributorZipCodeExt() {
		return distributorZipCodeExt;
	}

	public void setDistributorZipCodeExt(String distributorZipCodeExt) {
		this.distributorZipCodeExt = distributorZipCodeExt;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getManufacturerAddress1() {
		return manufacturerAddress1;
	}

	public void setManufacturerAddress1(String manufacturerAddress1) {
		this.manufacturerAddress1 = manufacturerAddress1;
	}

	public String getManufacturer_address_2() {
		return manufacturer_address_2;
	}

	public void setManufacturer_address_2(String manufacturer_address_2) {
		this.manufacturer_address_2 = manufacturer_address_2;
	}

	public String getManufacturerCity() {
		return manufacturerCity;
	}

	public void setManufacturerCity(String manufacturerCity) {
		this.manufacturerCity = manufacturerCity;
	}

	public String getManufacturerState() {
		return manufacturerState;
	}

	public void setManufacturerState(String manufacturerState) {
		this.manufacturerState = manufacturerState;
	}

	public String getManufacturerZipCode() {
		return manufacturerZipCode;
	}

	public void setManufacturerZipCode(String manufacturerZipCode) {
		this.manufacturerZipCode = manufacturerZipCode;
	}

	public String getManufacturerZipCodeExt() {
		return manufacturerZipCodeExt;
	}

	public void setManufacturerZipCodeExt(String manufacturerZipCodeExt) {
		this.manufacturerZipCodeExt = manufacturerZipCodeExt;
	}

	public String getManufacturerCountry() {
		return manufacturerCountry;
	}

	public void setManufacturerCountry(String manufacturerCountry) {
		this.manufacturerCountry = manufacturerCountry;
	}

	public String getManufacturerPostalCode() {
		return manufacturerPostalCode;
	}

	public void setManufacturerPostalCode(String manufacturerPostalCode) {
		this.manufacturerPostalCode = manufacturerPostalCode;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getDeviceDateOfManufacturer() {
		return deviceDateOfManufacturer;
	}

	public void setDeviceDateOfManufacturer(String deviceDateOfManufacturer) {
		this.deviceDateOfManufacturer = deviceDateOfManufacturer;
	}

	public String getSingleUseFlag() {
		return singleUseFlag;
	}

	public void setSingleUseFlag(String singleUseFlag) {
		this.singleUseFlag = singleUseFlag;
	}

	public String getPreviousUseCode() {
		return previousUseCode;
	}

	public void setPreviousUseCode(String previousUseCode) {
		this.previousUseCode = previousUseCode;
	}

	public List<String> getRemedialAction() {
		return remedialAction;
	}

	public void setRemedial_action(List<String> remedialAction) {
		this.remedialAction = remedialAction;
	}

	public String getRemovalCorrectionNumber() {
		return removalCorrectionNumber;
	}

	public void setRemovalCorrectionNumber(String removalCorrectionNumber) {
		this.removalCorrectionNumber = removalCorrectionNumber;
	}

	public String getManufacturerContactTitleName() {
		return manufacturerContactTitleName;
	}

	public void setManufacturerContactTitleName(String manufacturerContactTitleName) {
		this.manufacturerContactTitleName = manufacturerContactTitleName;
	}

	public String getManufacturerContactFirstName() {
		return manufacturerContactFirstName;
	}

	public void setManufacturerContactFirstName(String manufacturerContactFirstName) {
		this.manufacturerContactFirstName = manufacturerContactFirstName;
	}

	public String getManufacturercontactLastName() {
		return manufacturercontactLastName;
	}

	public void setManufacturercontactLastName(String manufacturercontactLastName) {
		this.manufacturercontactLastName = manufacturercontactLastName;
	}

	public String getManufacturerContactAddress1() {
		return manufacturerContactAddress1;
	}

	public void setManufacturerContactAddress1(String manufacturerContactAddress1) {
		this.manufacturerContactAddress1 = manufacturerContactAddress1;
	}

	public String getManufacturerContactAddress2() {
		return manufacturerContactAddress2;
	}

	public void setManufacturerContactAddress2(String manufacturerContactAddress2) {
		this.manufacturerContactAddress2 = manufacturerContactAddress2;
	}

	public String getManufacturerContactCity() {
		return manufacturerContactCity;
	}

	public void setManufacturerContactCity(String manufacturerContactCity) {
		this.manufacturerContactCity = manufacturerContactCity;
	}

	public String getManufacturerContactState() {
		return manufacturerContactState;
	}

	public void setManufacturerContactState(String manufacturerContactState) {
		this.manufacturerContactState = manufacturerContactState;
	}

	public String getManufacturerContactZipCode() {
		return manufacturerContactZipCode;
	}

	public void setManufacturerContactZipCode(String manufacturerContactZipCode) {
		this.manufacturerContactZipCode = manufacturerContactZipCode;
	}

	public String getManufacturerContactZipExt() {
		return manufacturerContactZipExt;
	}

	public void setManufacturerContactZipExt(String manufacturerContactZipExt) {
		this.manufacturerContactZipExt = manufacturerContactZipExt;
	}

	public String getManufacturerContactPostalCode() {
		return manufacturerContactPostalCode;
	}

	public void setManufacturerContactPostalCode(
			String manufacturerContactPostalCode) {
		this.manufacturerContactPostalCode = manufacturerContactPostalCode;
	}

	public String getManufacturerContactCountry() {
		return manufacturerContactCountry;
	}

	public void setManufacturerContactCountry(String manufacturerContactCountry) {
		this.manufacturerContactCountry = manufacturerContactCountry;
	}

	public String getManufacturerContactPhoneNumber() {
		return manufacturerContactPhoneNumber;
	}

	public void setManufacturerContactPhoneNumber(
			String manufacturerContactPhoneNumber) {
		this.manufacturerContactPhoneNumber = manufacturerContactPhoneNumber;
	}

	public String getManufacturerContactPhoneCountry() {
		return manufacturerContactPhoneCountry;
	}

	public void setManufacturerContactPhoneCountry(
			String manufacturerContactPhoneCountry) {
		this.manufacturerContactPhoneCountry = manufacturerContactPhoneCountry;
	}

	public String getManufacturerContactAreaCode() {
		return manufacturerContactAreaCode;
	}

	public void setManufacturerContactAreaCode(String manufacturerContactAreaCode) {
		this.manufacturerContactAreaCode = manufacturerContactAreaCode;
	}

	public String getManufacturerContactExchange() {
		return manufacturerContactExchange;
	}

	public void setManufacturerContactExchange(String manufacturerContactExchange) {
		this.manufacturerContactExchange = manufacturerContactExchange;
	}

	public String getManufacturerContactExtension() {
		return manufacturerContactExtension;
	}

	public void setManufacturerContactExtension(String manufacturerContactExtension) {
		this.manufacturerContactExtension = manufacturerContactExtension;
	}

	public String getManufacturerContactPhoneCity() {
		return manufacturerContactPhoneCity;
	}

	public void setManufacturerContactPhoneCity(String manufacturerContactPhoneCity) {
		this.manufacturerContactPhoneCity = manufacturerContactPhoneCity;
	}

	public String getManufacturerContactPhoneLocal() {
		return manufacturerContactPhoneLocal;
	}

	public void setManufacturerContactPhoneLocal(
			String manufacturerContactPhoneLocal) {
		this.manufacturerContactPhoneLocal = manufacturerContactPhoneLocal;
	}

	public String getManufacturerG1Name() {
		return manufacturerG1Name;
	}

	public void setManufacturerG1Name(String manufacturerG1Name) {
		this.manufacturerG1Name = manufacturerG1Name;
	}

	public String getManufacturerG1Address1() {
		return manufacturerG1Address1;
	}

	public void setManufacturerG1Address1(String manufacturerG1Address1) {
		this.manufacturerG1Address1 = manufacturerG1Address1;
	}

	public String getManufacturerG1Address2() {
		return manufacturerG1Address2;
	}

	public void setManufacturerG1Address2(String manufacturerG1Address2) {
		this.manufacturerG1Address2 = manufacturerG1Address2;
	}

	public String getManufacturerG1City() {
		return manufacturerG1City;
	}

	public void setManufacturerG1City(String manufacturerG1City) {
		this.manufacturerG1City = manufacturerG1City;
	}

	public String getManufacturerG1State() {
		return manufacturerG1State;
	}

	public void setManufacturerG1State(String manufacturerG1State) {
		this.manufacturerG1State = manufacturerG1State;
	}

	public String getManufacturerG1ZipCode() {
		return manufacturerG1ZipCode;
	}

	public void setManufacturerG1ZipCode(String manufacturerG1ZipCode) {
		this.manufacturerG1ZipCode = manufacturerG1ZipCode;
	}

	public String getManufacturerG1ZipCodeExt() {
		return manufacturerG1ZipCodeExt;
	}

	public void setManufacturerG1ZipCodeExt(String manufacturerG1ZipCodeExt) {
		this.manufacturerG1ZipCodeExt = manufacturerG1ZipCodeExt;
	}

	public String getManufacturerG1PostalCode() {
		return manufacturerG1PostalCode;
	}

	public void setManufacturerG1PostalCode(String manufacturerG1PostalCode) {
		this.manufacturerG1PostalCode = manufacturerG1PostalCode;
	}

	public String getManufacturerG1Country() {
		return manufacturerG1Country;
	}

	public void setManufacturerG1Country(String manufacturerG1Country) {
		this.manufacturerG1Country = manufacturerG1Country;
	}

	public String getDateManufacturerReceived() {
		return dateManufacturerReceived;
	}

	public void setDateManufacturerReceived(String dateManufacturerReceived) {
		this.dateManufacturerReceived = dateManufacturerReceived;
	}

	public List<String> getSourceType() {
		return sourceType;
	}

	public void setSourceType(List<String> sourceType) {
		this.sourceType = sourceType;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getMdrReportKey() {
		return mdrReportKey;
	}

	public void setMdrReportKey(String mdrReportKey) {
		this.mdrReportKey = mdrReportKey;
	}

	public String getManufacturerLinkFlag() {
		return manufacturerLinkFlag;
	}

	public void setManufacturerLinkFlag(String manufacturerLinkFlag) {
		this.manufacturerLinkFlag = manufacturerLinkFlag;
	}

	public String getReprocessedAndReusedFlag() {
		return reprocessedAndReusedFlag;
	}

	public void setReprocessedAndReusedFlag(String reprocessedAndReusedFlag) {
		this.reprocessedAndReusedFlag = reprocessedAndReusedFlag;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
