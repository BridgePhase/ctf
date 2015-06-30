package com.bridgephase.ctf.backend.fda.comparators;

import java.util.Comparator;

import com.bridgephase.ctf.backend.domain.DeviceEvent;

public class RecallByDateOfEvent implements Comparator<DeviceEvent> {

	@Override
	public int compare(DeviceEvent event1, DeviceEvent event2) {
		return -1 * event1.getDateOfEvent().compareToIgnoreCase(event2.getDateOfEvent());
	}
}
