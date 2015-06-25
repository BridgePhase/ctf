package com.bridgephase.ctf.backend.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bridgephase.ctf.backend.notifications.NotificationService;

@Component
public final class Cron {
	
	private static final Logger logger = LoggerFactory.getLogger(Cron.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@Scheduled(cron = "${cron_notifications:0 * * * * *}")
	public void updateNotifications() {
		logger.info("Cron task initiated.");
		notificationService.updateNotifications();
	}
}
