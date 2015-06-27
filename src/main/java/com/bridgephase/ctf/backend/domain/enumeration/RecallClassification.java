package com.bridgephase.ctf.backend.domain.enumeration;

public enum RecallClassification {
	CLASS_I("Class I", "I", "dangerous or defective"),
	CLASS_II("Class II", "II", "to pose only a slight threat of serious nature"),
	CLASS_III("Class III", "III", "unlikely to cause any adverse health reaction");
	
	private final String label;
	private final String shortLabel;
	private final String description;
	
	private RecallClassification(String label, String shortLabel, String description) {
		this.label = label;
		this.shortLabel = shortLabel;
		this.description = description;
	}
	
	public String label() { return label; }
	public String shortLabel() { return shortLabel; }
	public String description() { return description; }

	public static RecallClassification parse(String key) {
		for (RecallClassification e : RecallClassification.values()) {
			if (e.label().equalsIgnoreCase(key)) {
				return e;
			}
		}
		return null;
	}
}
