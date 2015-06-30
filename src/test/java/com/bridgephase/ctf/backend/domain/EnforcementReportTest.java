package com.bridgephase.ctf.backend.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bridgephase.ctf.backend.domain.enumeration.RecallClassification;

public class EnforcementReportTest {

	@Test
	public void friendlyProductDescriptionTrimsAtUPC() {
		EnforcementReport report = new EnforcementReport();
		report.setProductType("Food");
		report.setProductDescription("Trader Joe's Nuts Raw California Walnut Pieces NET WT. 16oz. (1 LB) 454g UPC "
			+ "00373685 Trader Joe's Nuts Raw California Walnut Halves & Pieces NET WT. 16 OZ (1 LB) "
			+ "454g UPC 00943338 Trader Joe's Nuts Raw California Walnut Baking Pieces NET WT. 16 OZ "
			+ "(1 LB) 454g UPC 00519342 Trader Joe's Nuts Raw California Premium Walnut Halves NET "
			+ "WT. 16 OZ (1 LB) 454g UPC 00519328 Trader Joe's Organic Raw Walnut Halves & Pieces "
			+ "NET WT. 16 OZ (1 LB) 454g UPC 00586627 The products are packaged in clear bags with "
			+ "UPC Codes printed on the back. For Raw California Walnut products, the \"BEST BY\""
			+ " dates and Lot Numbers can be found printed on the back of the packages. For the "
			+ "Organic Raw Walnut products, the \"BEST BY\" dates can be found printed on the front"
			+ " of the packages.");
		assertEquals("Trader Joe's Nuts Raw California Walnut Pieces NET WT. 16oz. (1 LB) 454g", 
			report.getFriendlyProductDescription());
	}
	
	@Test
	public void friendlyProductDescriptionTrimsAtSentence() {
		EnforcementReport report = new EnforcementReport();
		report.setProductType("Food");
		report.setProductDescription("Kraft Macaroni & Cheese Boxed Dinner Original Flavor, 7.25oz.,"
			+ "packaged in blue and yellow cardboard box. There are four different package types "
			+ "affected by this recall: 1) single 7.25 oz boxes, 3-pack box of 7.25 oz boxes, "
			+ "4-pack shrink wrap of 7.25 oz boxes, and 5-pack shrink wrap of 7.25 oz boxes.");
		assertEquals("Kraft Macaroni & Cheese Boxed Dinner Original Flavor, 7.25oz.", 
			report.getFriendlyProductDescription());
	}
	
	@Test
	public void friendlyClassification() {
		EnforcementReport report = new EnforcementReport();
		report.setClassification("Class I");
		assertEquals("Class I - " + RecallClassification.CLASS_I.description(), 
			report.getClassificationDescription());
		report.setClassification("Class II");
		assertEquals("Class II - " + RecallClassification.CLASS_II.description(), 
			report.getClassificationDescription());
		report.setClassification("Class III");
		assertEquals("Class III - " + RecallClassification.CLASS_III.description(), 
			report.getClassificationDescription());
	}
}
