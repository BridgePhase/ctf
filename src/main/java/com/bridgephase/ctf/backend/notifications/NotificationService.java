package com.bridgephase.ctf.backend.notifications;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgephase.ctf.model.jpa.Notification;
import com.bridgephase.ctf.model.repository.NotificationRepository;

@Service 
public class NotificationService {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	private NotificationRepository repository;
	
	@Transactional
	public List<Notification> getNotifications() {
		logger.info("Retreiving notificaitons.");
		// TODO - Update result list with a limit or time threshold (e.g. 5 days ago).
		List<Notification> items = repository.findAll();
		logger.info(String.format("A total of %s notifications were located.", items.size()));
		return items;
	}
	
	@Transactional
	public void updateNotifications() {
		logger.info("Updating notifications.");
		for (int i = 0; i < 6; i++) {
			Notification item = new Notification();
			item.setHeadline("Headline " + i);
			repository.save(item);
		}
		List<Notification> savedItems = repository.findAll();
		logger.info(String.format("A total of %s notifications were located.", savedItems.size()));
	}
}
