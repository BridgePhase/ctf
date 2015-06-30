package com.bridgephase.ctf.backend.task;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bridgephase.ctf.backend.cache.CacheService;
import com.bridgephase.ctf.backend.notifications.NotificationService;

@Component
public final class Cron {
	
	private static final Logger logger = LoggerFactory.getLogger(Cron.class);
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private CacheService cacheService;
	
	@Scheduled(cron = "${cron_notifications:0 * * * * *}")
	public void updateNotifications() {
		logger.info("Cron task - Updating notifications initiated");
		notificationService.updateNotifications();
	}

	@Scheduled(cron = "${cron_cache_clear:0 * * * * *}")
	public void clearCache() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -1);
		logger.info("Cron task - Clearing cache entries older than {}", cal.getTime());
		cacheService.clearCacheEntriesOlderThan(cal.getTime());
	}

}
