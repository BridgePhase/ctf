package com.bridgephase.ctf.backend.notifications;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgephase.ctf.backend.fda.OpenFdaService;
import com.bridgephase.ctf.model.jpa.Notification;
import com.bridgephase.ctf.model.repository.NotificationRepository;

@Service 
public class NotificationService {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private OpenFdaService openFda;
	
	@Transactional
	public List<Notification> getNotifications() {
		logger.info("Retreiving notificaitons.");
		List<Notification> items = repository.findAll();
		logger.info(String.format("%s notifications were retrieved.", items.size()));
		if (items.isEmpty()) {
			items = handleEmpty();
		}
		return items;
	}
	
	@Transactional
	public void updateNotifications() {
		logger.info("Updating notifications.");
		repository.deleteAll();
		List<Notification> notifications = generateNotifications();
		repository.save(notifications);
	}
	
	private List<Notification> handleEmpty() {
		logger.info("No notifications were available. Attempting to update.");
		updateNotifications();
		List<Notification> items = repository.findAll();
		logger.info(String.format("%s notifications were retrieved.", items.size()));
		return items;
	}
	
	private List<Notification> generateNotifications() {
		List<Notification> notifications = new ArrayList<>();
		notifications.add(drugReaction());
		notifications.add(deviceRecall());
		notifications.add(drugLabel());
		notifications.add(foodRecall());
		notifications.add(deviceEvent());
		notifications.add(drugRecall());
		return notifications;
	}
	
	private Notification drugReaction() {
		Notification notification = new Notification();
		notification.setHeadline("The most common reaction to Tylenol is nausea.");
		return notification;
	}
	
	private Notification drugLabel() {
		Notification notification = new Notification();
		notification.setHeadline("Of pain relievers on the market, 87% are administered topically.");
		return notification;
	}
	
	private Notification drugRecall() {
		Notification notification = new Notification();
		notification.setHeadline("In the past year, 11 drugs have been recalled.");
		return notification;
	}
	
	private Notification deviceEvent() {
		Notification notification = new Notification();
		notification.setHeadline("Two people were recently hospitalized while using beard trimmers.");
		return notification;
	}
	
	private Notification deviceRecall() {
		Notification notification = new Notification();
		notification.setHeadline("The most recently recalled device was the Super Soaker 5000");
		return notification;
	}
	
	private Notification foodRecall() {
		Notification notification = new Notification();
		notification.setHeadline("The most recently recalled food was cumin.");
		return notification;
	}
}
