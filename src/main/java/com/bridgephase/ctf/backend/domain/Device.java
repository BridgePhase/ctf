package com.bridgephase.ctf.backend.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
	
	@JsonProperty("device_sequence_number")
	private String deviceSequenceNumber;
	
	@JsonProperty("device_event_key")
	private String deviceEventKey;
	
	@JsonProperty("date_received")
    private String dateReceived;
	
    @JsonProperty("brand_name")
	private String brandName;
    
    @JsonProperty("generic_name")
    private String genericName;
    
    @JsonProperty("device_report_product_code")
    private String deviceReportProductCode;
    
    @JsonProperty("model_number")
    private String modelNumber;
    
    @JsonProperty("catalog_number")
    private String catalogNumber;
    
    @JsonProperty("lot_number")
    private String lotNumber;
    
    @JsonProperty("other_id_number")
    private String otherIdNumber;
	
    @JsonProperty("expiration_date_of_device")
    private String expirationDateOfDevice;
    
    @JsonProperty("device_age_text")
    private String deviceAgeText;
    
    @JsonProperty("device_availability")
    private String deviceAvailability;
    
    @JsonProperty("date_returned_to_manufacturer")
    private String dateReturnedToManufacturer;
    
    @JsonProperty("device_evaluated_by_manufacturer")
    private String deviceEvaluatedByManufacturer;
    
    @JsonProperty("device_operator")
    private String deviceOperator;
    
    @JsonProperty("implant_flag")
    private String implantFlag;
    
    @JsonProperty("date_removed_flag")
    private String dateRemovedFlag;

    @JsonProperty("manufacturer_d_name")
    private String manufacturerName;
    
    @JsonProperty("manufacturer_d_address_1")
    private String manufacturerAddress1;
    
    @JsonProperty("manufacturer_d_address_2")
    private String manufacturerAddress2;
    
    @JsonProperty("manufacturer_d_city")
    private String manufacturerCity;

    @JsonProperty("manufacturer_d_state")
    private String manufacturerState;
    
    @JsonProperty("manufacturer_d_country")
    private String manufacturerCountry;
    
    @JsonProperty("manufacturer_d_zip_code")
    private String manufacturerZipCode;
    
    @JsonProperty("manufacturer_d_zip_code_ext")
    private String manufacturerZipCodeExt;
    
    @JsonProperty("manufacturer_d_postal_code")
    private String manufacturerPostalCode;

    /**
     * Documentation unavailable.
     */
    @JsonProperty("baseline_510_k__exempt_flag")
    private String baseline510KNumber;

    /**
     * Documentation unavailable.
     */
    @JsonProperty("baseline_510_k__exempt_flag")
    private String baseline510KExemptFlag;

    /**
     * Documentation unavailable.
     */
    @JsonProperty("baseline_510_k__flag")
    private String baseline510KFlag;

	public String getDeviceSequenceNumber() {
		return deviceSequenceNumber;
	}

	public void setDeviceSequenceNumber(String deviceSequenceNumber) {
		this.deviceSequenceNumber = deviceSequenceNumber;
	}

	public String getDeviceEventKey() {
		return deviceEventKey;
	}

	public void setDeviceEventKey(String deviceEventKey) {
		this.deviceEventKey = deviceEventKey;
	}

	public String getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getGenericName() {
		return genericName;
	}

	public void setGenericName(String genericName) {
		this.genericName = genericName;
	}

	public String getDeviceReportProductCode() {
		return deviceReportProductCode;
	}

	public void setDeviceReportProductCode(String deviceReportProductCode) {
		this.deviceReportProductCode = deviceReportProductCode;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(String modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public String getLotNumber() {
		return lotNumber;
	}

	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}

	public String getOtherIdNumber() {
		return otherIdNumber;
	}

	public void setOtherIdNumber(String otherIdNumber) {
		this.otherIdNumber = otherIdNumber;
	}

	public String getExpirationDateOfDevice() {
		return expirationDateOfDevice;
	}

	public void setExpirationDateOfDevice(String expirationDateOfDevice) {
		this.expirationDateOfDevice = expirationDateOfDevice;
	}

	public String getDeviceAgeText() {
		return deviceAgeText;
	}

	public void setDeviceAgeText(String deviceAgeText) {
		this.deviceAgeText = deviceAgeText;
	}

	public String getDeviceAvailability() {
		return deviceAvailability;
	}

	public void setDeviceAvailability(String deviceAvailability) {
		this.deviceAvailability = deviceAvailability;
	}

	public String getDateReturnedToManufacturer() {
		return dateReturnedToManufacturer;
	}

	public void setDateReturnedToManufacturer(String dateReturnedToManufacturer) {
		this.dateReturnedToManufacturer = dateReturnedToManufacturer;
	}

	public String getDeviceEvaluatedByManufacturer() {
		return deviceEvaluatedByManufacturer;
	}

	public void setDeviceEvaluatedByManufacturer(
			String deviceEvaluatedByManufacturer) {
		this.deviceEvaluatedByManufacturer = deviceEvaluatedByManufacturer;
	}

	public String getDeviceOperator() {
		return deviceOperator;
	}

	public void setDeviceOperator(String deviceOperator) {
		this.deviceOperator = deviceOperator;
	}

	public String getImplantFlag() {
		return implantFlag;
	}

	public void setImplantFlag(String implantFlag) {
		this.implantFlag = implantFlag;
	}

	public String getDateRemovedFlag() {
		return dateRemovedFlag;
	}

	public void setDateRemovedFlag(String dateRemovedFlag) {
		this.dateRemovedFlag = dateRemovedFlag;
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

	public String getManufacturerAddress2() {
		return manufacturerAddress2;
	}

	public void setManufacturerAddress2(String manufacturerAddress2) {
		this.manufacturerAddress2 = manufacturerAddress2;
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

	public String getManufacturerCountry() {
		return manufacturerCountry;
	}

	public void setManufacturerCountry(String manufacturerCountry) {
		this.manufacturerCountry = manufacturerCountry;
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

	public String getManufacturerPostalCode() {
		return manufacturerPostalCode;
	}

	public void setManufacturerPostalCode(String manufacturerPostalCode) {
		this.manufacturerPostalCode = manufacturerPostalCode;
	}

	public String getBaseline510KNumber() {
		return baseline510KNumber;
	}

	public void setBaseline510KNumber(String baseline510kNumber) {
		baseline510KNumber = baseline510kNumber;
	}

	public String getBaseline510KExemptFlag() {
		return baseline510KExemptFlag;
	}

	public void setBaseline510KExemptFlag(String baseline510kExemptFlag) {
		baseline510KExemptFlag = baseline510kExemptFlag;
	}

	public String getBaseline510KFlag() {
		return baseline510KFlag;
	}

	public void setBaseline510KFlag(String baseline510kFlag) {
		baseline510KFlag = baseline510kFlag;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
