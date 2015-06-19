package com.bridgephase.ctf.backend.domain.enumeration;

public enum DataNoun { 
	DRUG("event"), 
	DEVICE("event"), 
	FOOD("enforcement");
	
	private final String context;
	
	DataNoun(String context) {
		this.context = context;
	}
	
	public String context() { return context; }
}
