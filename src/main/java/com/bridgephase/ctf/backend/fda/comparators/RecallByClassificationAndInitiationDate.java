package com.bridgephase.ctf.backend.fda.comparators;

import java.util.Comparator;

import com.bridgephase.ctf.backend.domain.EnforcementReport;

public class RecallByClassificationAndInitiationDate implements Comparator<EnforcementReport> {
	@Override
	public int compare(EnforcementReport report1, EnforcementReport report2) {
		int initialCompare = report1.getRecallInitiationDate().compareToIgnoreCase(report2.getRecallInitiationDate());
		// the classifications are already different, we can stop here
		if (initialCompare == 0) {
			return report1.getClassification().compareToIgnoreCase(report2.getClassification());
		} else {
			return -1 * initialCompare;
		}
	}
}
