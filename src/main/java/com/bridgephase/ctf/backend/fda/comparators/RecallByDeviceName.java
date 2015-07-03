package com.bridgephase.ctf.backend.fda.comparators;

import java.util.Comparator;

import com.bridgephase.ctf.backend.domain.Device;
import com.bridgephase.ctf.backend.domain.DeviceEvent;

public class RecallByDeviceName implements Comparator<DeviceEvent> {

	@Override
	public int compare(DeviceEvent event1, DeviceEvent event2) {
		Device device1 = event1.getDevice().get(0);
		Device device2 = event2.getDevice().get(0);
		return device1.getBrandName().compareToIgnoreCase(device2.getBrandName());
	}
}
