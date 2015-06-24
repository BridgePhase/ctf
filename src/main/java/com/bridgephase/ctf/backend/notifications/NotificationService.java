package com.bridgephase.ctf.backend.notifications;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bridgephase.ctf.backend.domain.Notification;

@Service
public class NotificationService {
	
	public List<Notification> getNotifications() {
		List<Notification> items = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Notification item = new Notification();
			item.setHeadline("Headline " + i);
			items.add(item);
		}
		return items;
	}

}
