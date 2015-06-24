package com.bridgephase.ctf.backend.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public final class Cron {
	
	private static final Logger logger = LoggerFactory.getLogger(Cron.class);
	
	@Scheduled(cron = "${cron_notifications:0 * * * * *}")
	public void generateNotifications() {
		logger.info("Updating notifications.");
	}
}
