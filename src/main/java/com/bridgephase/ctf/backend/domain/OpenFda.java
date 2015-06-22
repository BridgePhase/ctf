package com.bridgephase.ctf.backend.domain;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenFda {
	
	/** <p>National Drug Code (NDC)<p> */
	
	/**
	 * This corresponds to the NDA, ANDA, or BLA number reported by the labeler for products which 
	 * have the corresponding Marketing Category designated. If the designated Marketing Category 
	 * is OTC Monograph Final or OTC Monograph Not Final, then the application number will be the 
	 * CFR citation corresponding to the appropriate Monograph (e.g. “part 341”). For unapproved 
	 * drugs, this field will be null.
	 */
	@JsonProperty("application_number")
	private List<String> application_number;
	
	/**
	 * The brand or trade name of the product.
	 */
	@JsonProperty("brand_name")
    private List<String> brandName;
	
	/**
	 * The dosage form of the drug product.
	 */
	@JsonProperty("dosage_form")
	private List<String> dosageForm;
	
	/**
	 * The generic name of the drug product.
	 */
	@JsonProperty("generic_name")
    private List<String> genericName;
    
	/**
	 * Name of company corresponding to the labeler code segment of the NDC.
	 */
    @JsonProperty("manufacturer_name")
    private List<String> manufacturerName;
    
    /**
     * Documentation forthcoming.
     */
    @JsonProperty("is_original_packager")
    private Boolean isOriginalPackager;
    
    /**
     * The labeler manufacturer code and product code segments of the NDC number, separated by 
     * a hyphen.
     */
    @JsonProperty("product_ndc")
    private List<String> productNdc;
    
    /**
     * The type of drug product.
     */
    @JsonProperty("product_type")
    private List<String> productType;
    
    /**
     * The route of administration of the drug product.
     */
    private List<String> route;
    
    /**
     * The list of active ingredients of a drug product.
     */
    @JsonProperty("substance_name")
    private List<String> substanceName;
	
	/** <p>Structured Product Labeling (SPL)<p>*/
    
    /**
	 * A unique identifier for a particular version of a Structured Product Label for a product. 
	 * Also referred to as the document ID.
	 */
	@JsonProperty("spl_id")
    private List<String> structuredProductLabelId;
	
	/**
	 * A unique identifier for the Structured Product Label for a product, which is stable across 
	 * versions of the label.
	 */
    @JsonProperty("spl_set_id")
    private List<String> structuredProductLabelSetId;
    
    /**
     * Mechanism of action. Molecular, subcellular, or cellular level functional activity of a drug 
     * product’s pharmacologic class.
     */
    @JsonProperty("pharm_class_moa")
    private List<String> pharmClassMechanismOfAction;
    
    /**
     * Chemical structure. Chemical structure classification of a pharmacologic class.
     */
    @JsonProperty("pharm_class_cs")
    private List<String> pharmClassChemicalStructure;
    
    /**
     * Physiologic effect. Tissue, organ, or organ system level functional activity of a 
     * pharmacologic class.
     */
    @JsonProperty("pharm_class_pe")
    private List<String> pharmClassPhysiologicalEffect;
    
    /**
     * Established pharmacologic class. A pharmacologic class associated with an approved indication 
     * of an active moiety that the FDA has determined to be scientifically valid and clinically 
     * meaningful.
     */
    @JsonProperty("pharm_class_epc")
    private List<String> pharmClassEstablished;
    
    /**
     * Documentation forthcoming.
     */
    private List<String> upc;
	
    /**
     * The Unique Ingredient Identifier of the drug.
     */
	@JsonProperty("unii")
	private List<String> uniqueIngredientId;

	/**
	 * The RxNorm Concept Unique Identifier. RxCUI is a unique number that describes a semantic concept 
	 * about the drug product, including its ingredients, strength, and dose forms.
	 */
    @JsonProperty("rxcui")
    private List<String> rxNormConceptUniqueId;

    /**
     * Documentation unavailable.
     */
    private List<String> nui;

    /**
     * Documentation unavailable.
     */
    @JsonProperty("package_ndc")
    private List<String> packageNdc;
    
	
	public List<String> getApplication_number() {
		return application_number;
	}

	public void setApplication_number(List<String> application_number) {
		this.application_number = application_number;
	}

	public List<String> getBrandName() {
		return brandName;
	}

	public void setBrandName(List<String> brandName) {
		this.brandName = brandName;
	}

	public List<String> getDosageForm() {
		return dosageForm;
	}

	public void setDosageForm(List<String> dosageForm) {
		this.dosageForm = dosageForm;
	}

	public List<String> getGenericName() {
		return genericName;
	}

	public void setGenericName(List<String> genericName) {
		this.genericName = genericName;
	}

	public List<String> getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(List<String> manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public Boolean getIsOriginalPackager() {
		return isOriginalPackager;
	}

	public void setIsOriginalPackager(Boolean isOriginalPackager) {
		this.isOriginalPackager = isOriginalPackager;
	}

	public List<String> getProductNdc() {
		return productNdc;
	}

	public void setProductNdc(List<String> productNdc) {
		this.productNdc = productNdc;
	}

	public List<String> getProductType() {
		return productType;
	}

	public void setProductType(List<String> productType) {
		this.productType = productType;
	}

	public List<String> getRoute() {
		return route;
	}

	public void setRoute(List<String> route) {
		this.route = route;
	}

	public List<String> getSubstanceName() {
		return substanceName;
	}

	public void setSubstanceName(List<String> substanceName) {
		this.substanceName = substanceName;
	}

	public List<String> getStructuredProductLabelId() {
		return structuredProductLabelId;
	}

	public void setStructuredProductLabelId(List<String> structuredProductLabelId) {
		this.structuredProductLabelId = structuredProductLabelId;
	}

	public List<String> getStructuredProductLabelSetId() {
		return structuredProductLabelSetId;
	}

	public void setStructuredProductLabelSetId(
			List<String> structuredProductLabelSetId) {
		this.structuredProductLabelSetId = structuredProductLabelSetId;
	}

	public List<String> getPharmClassMechanismOfAction() {
		return pharmClassMechanismOfAction;
	}

	public void setPharmClassMechanismOfAction(
			List<String> pharmClassMechanismOfAction) {
		this.pharmClassMechanismOfAction = pharmClassMechanismOfAction;
	}

	public List<String> getPharmClassChemicalStructure() {
		return pharmClassChemicalStructure;
	}

	public void setPharmClassChemicalStructure(
			List<String> pharmClassChemicalStructure) {
		this.pharmClassChemicalStructure = pharmClassChemicalStructure;
	}

	public List<String> getPharmClassPhysiologicalEffect() {
		return pharmClassPhysiologicalEffect;
	}

	public void setPharmClassPhysiologicalEffect(
			List<String> pharmClassPhysiologicalEffect) {
		this.pharmClassPhysiologicalEffect = pharmClassPhysiologicalEffect;
	}

	public List<String> getPharmClassEstablished() {
		return pharmClassEstablished;
	}

	public void setPharmClassEstablished(List<String> pharmClassEstablished) {
		this.pharmClassEstablished = pharmClassEstablished;
	}

	public List<String> getUpc() {
		return upc;
	}

	public void setUpc(List<String> upc) {
		this.upc = upc;
	}

	public List<String> getUniqueIngredientId() {
		return uniqueIngredientId;
	}

	public void setUniqueIngredientId(List<String> uniqueIngredientId) {
		this.uniqueIngredientId = uniqueIngredientId;
	}

	public List<String> getRxNormConceptUniqueId() {
		return rxNormConceptUniqueId;
	}

	public void setRxNormConceptUniqueId(List<String> rxNormConceptUniqueId) {
		this.rxNormConceptUniqueId = rxNormConceptUniqueId;
	}

	public List<String> getNui() {
		return nui;
	}

	public void setNui(List<String> nui) {
		this.nui = nui;
	}

	public List<String> getPackageNdc() {
		return packageNdc;
	}

	public void setPackageNdc(List<String> packageNdc) {
		this.packageNdc = packageNdc;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
