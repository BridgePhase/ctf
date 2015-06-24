package com.bridgephase.ctf.backend.notifications;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bridgephase.ctf.model.jpa.Notification;

@Service
public class NotificationService {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	/**
	 * 
	 * @return
	 */
	public List<Notification> getNotifications() {
		logger.info("Retreiving notificaitons.");
		List<Notification> items = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Notification item = new Notification();
			item.setHeadline("Headline " + i);
			items.add(item);
		}
		return items;
	}
	
	/**
	 * 
	 */
	public void updateNotifications() {
		logger.info("Updating notifications.");
		List<Notification> notifications = generateNotifications();
		saveNotifications(notifications);
	}
	
	private List<Notification> generateNotifications() {
		List<Notification> notifications = new ArrayList<>();
		return notifications;
	}
	
	private void saveNotifications(List<Notification> notifications) {
		logger.info("Saving notifications.");
	}
}
