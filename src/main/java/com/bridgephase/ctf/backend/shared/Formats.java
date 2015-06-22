package com.bridgephase.ctf.backend.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Formats {
	private static final String OPEN_FDA_DATE_FORMAT = "yyyyMMdd";
	private static final String ISO_8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

	public static final String dateAsIso(Date date) {
		return new SimpleDateFormat(ISO_8601_DATE_FORMAT).format(date);
	}
	
	public static final Date openFdaDate(String dateString) {
		try {
			return new SimpleDateFormat(OPEN_FDA_DATE_FORMAT).parse(dateString);
		} catch (ParseException e) {
			return null;
		}
	}
}
