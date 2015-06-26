package com.bridgephase.ctf.backend.domain.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AgeGroupCountSummary {
	public static final String TEN_AND_UNDER = "0-10";
	public static final String ELEVEN_TO_TWENTY = "11-20";
	public static final String TWENTY_ONE_TO_THIRTY = "21-30";
	public static final String THIRTY_ONE_TO_FORTY = "31-40";
	public static final String FORTY_ONE_TO_FIFTY = "41-50";
	public static final String FIFTY_ONE_TO_SIXTY = "51-60";
	public static final String SIXTY_ONE_TO_SEVENTY = "61-70";
	public static final String SEVENTY_ONE_TO_EIGHTY = "71-80";
	public static final String EIGHTY_ONE_TO_NINETY = "81-90";
	public static final String NINETY_ONE_TO_ONE_HUNDRED = "91-100";
	public static final String OVER_ONE_HUNDRED= "100+";

	private Map<String, Integer> decades = new HashMap<String, Integer>();
	
	public void add(String age, Integer count) {
		String key = determineDecade(age);
		Integer val = decades.get(key);
		if (val == null) {
			val = count;
		} else {
			val += count;
		}
		decades.put(key, val);
	}
	
	private String determineDecade(String num) {
		BigDecimal number = new BigDecimal(num);
		int i = number.intValue();
		int decade = i / 10;
		switch (decade) {
			case 0:
				return TEN_AND_UNDER;
			case 1:
				return ELEVEN_TO_TWENTY;
			case 2:
				return TWENTY_ONE_TO_THIRTY;
			case 3: 
				return THIRTY_ONE_TO_FORTY;
			case 4:
				return FORTY_ONE_TO_FIFTY;
			case 5: 
				return FIFTY_ONE_TO_SIXTY;
			case 6: 
				return SIXTY_ONE_TO_SEVENTY;
			case 7: 
				return SEVENTY_ONE_TO_EIGHTY;
			case 8: 
				return EIGHTY_ONE_TO_NINETY;
			case 9: 
				return NINETY_ONE_TO_ONE_HUNDRED;
			case 10:
				return OVER_ONE_HUNDRED;
			default : 
				return OVER_ONE_HUNDRED;
		}
	}

	@JsonProperty("data")
	public Map<String, Integer> decadeMap() {
		return decades;
	}

}
