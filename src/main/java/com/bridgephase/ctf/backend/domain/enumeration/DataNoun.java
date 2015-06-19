package com.bridgephase.ctf.backend.domain.enumeration;

import java.util.HashMap;
import java.util.Map;

public enum DataNoun {
	DRUG("drug"),
	DEVICE("device"),
	FOOD("food");
	
	private final String label;
	private static final Map<String, DataNoun> nouns;
	
	static
	{
		nouns = new HashMap<>();
		for (DataNoun noun : DataNoun.values()) {
			nouns.put(noun.label(), noun);
		}
	}
	
	DataNoun(String label) {
		this.label = label;
	}
	
	public String label() { return label; }
	
	public static DataNoun fromLabel(String label) { return nouns.get(label); }
}
