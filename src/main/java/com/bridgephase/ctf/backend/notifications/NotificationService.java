package com.bridgephase.ctf.backend.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bridgephase.ctf.backend.domain.SearchCountResponse;
import com.bridgephase.ctf.backend.domain.SearchCountResult;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.RecallClassification;
import com.bridgephase.ctf.backend.fda.OpenFdaService;
import com.bridgephase.ctf.model.jpa.Notification;
import com.bridgephase.ctf.model.repository.NotificationRepository;

@Service 
public class NotificationService {
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
	
	@Autowired
	protected NotificationRepository repository;
	
	@Autowired
	protected OpenFdaService openFda;
	
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
		List<Notification> notifications = generateNotifications();
		// Only remove the existing notifications is new ones are generated.
		if (!notifications.isEmpty()) {
			repository.deleteAll();
		}
		repository.save(notifications);
	}
	
	private List<Notification> handleEmpty() {
		logger.info("No notifications were available. Attempting to update.");
		updateNotifications();
		List<Notification> items = repository.findAll();
		logger.info(String.format("%s notifications were added.", items.size()));
		return items;
	}
	
	private List<Notification> generateNotifications() {
		List<Notification> notifications = new ArrayList<>();
		notifications.add(drugReaction());
		notifications.add(recallNotification(DataNoun.DEVICE));
		notifications.add(drugLabel());
		notifications.add(recallNotification(DataNoun.FOOD));
		notifications.add(deviceEvent());
		notifications.add(recallNotification(DataNoun.DRUG));
		return notifications;
	}
	
	private Notification drugReaction() {
		Notification notification = new Notification();
		String medication = randomMedication();
		SearchCountResponse response = openFda.mostCommonReactionTypes(medication);
		List<SearchCountResult> results = response.getResults();
		String reaction = "happiness";
		for (SearchCountResult result : results) {
			String text = result.getTerm();
			if (!StringUtils.isEmpty(text)) {
				reaction = text.toLowerCase();
				break;
			}
		}
		notification.setHeadline(String.format("The most commonly reported reaction to %s is %s.", medication, reaction));
		return notification;
	}
	
	private Notification drugLabel() {
		Notification notification = new Notification();
		String purpose = randomDrugPurpose();
		SearchCountResponse response = openFda.drugPurposeRoute(purpose);
		List<SearchCountResult> results = response.getResults();
		String route = "unknown";
		int max = 0, total = 0;
		for (SearchCountResult result : results) {
			int current = result.getCount();
			total += current;
			if (current > max) {
				max = current;
				route = result.getTerm().toLowerCase();
			}
		}
		double ratio = 100 * ((double)max / total);
		int percent = (int)Math.floor(ratio);
		notification.setHeadline(String.format("Of drugs with a purpose of %s, %d%% are administered via %s route.",purpose, percent, route));
		return notification;
	}
	
	private Notification recallNotification(DataNoun noun) {
		Notification notification = new Notification();
		RecallClassification classification = randomRecallClassification();
		SearchCountResponse response = openFda.recallClassifications(noun);
		List<SearchCountResult> results = response.getResults();
		int value = 0, total = 0;
		for (SearchCountResult result : results) {
			String term = result.getTerm();
			if (term.toLowerCase().equals("class")) {
				continue;
			}
			int current = result.getCount();
			if (classification.shortLabel().equals(term.toUpperCase())) {
				value = current;
			}
			total += current;
		}
		double ratio = 100 * ((double)value / total);
		int percent = (int)Math.floor(ratio);
		notification.setHeadline(
				String.format("Of the %d %s recalls initiated within the past year, %d%% are considered %s.", 
				total, noun.toString().toLowerCase(), percent, classification.description()));
		return notification;
	}
	
	private Notification deviceEvent() {
		Notification notification = new Notification();
		String operator = randomDeviceOperator();
		SearchCountResponse response = openFda.deviceEventCountByOperator(operator);
		List<SearchCountResult> results = response.getResults();
		String eventType = "unkown";
		int max = 0, total = 0;
		for (SearchCountResult result : results) {
			int current = result.getCount();
			total += current;
			if (current > max) {
				max = current;
				eventType = result.getTerm().toLowerCase();
			}
		}
		double ratio = 100 * ((double)max / total);
		int percent = (int)Math.floor(ratio);
		notification.setHeadline(String.format("Device events in which a %s was the operator, were reported due to %s %d%% "
				+ "of the time.", operator, eventType, percent));
		return notification;
	}
	
	private String randomMedication() {
		Random random = new Random();
		int value = random.nextInt(5);
		String med = "Tylenol";
		switch (value) {
		case 0:
			med = "Tylenol";
			break;
		case 1:
			med = "Robitussin";
			break;
		case 2:
			med = "Nyquil";
			break;
		case 3:
			med = "Claritin";
			break;
		case 4:
			med = "Tums";
			break;
		}
		return med;
	}
	
	private String randomDrugPurpose() {
		Random random = new Random();
		int value = random.nextInt(5);
		String med = "pain relief";
		switch (value) {
		case 0:
			med = "antiseptic";
			break;
		case 1:
			med = "anti inflammatory";
			break;
		case 2:
			med = "antihistamine";
			break;
		case 3:
			med = "stimulant";
			break;
		case 4:
			med = "sunscreen";
			break;
		}
		return med;
	}
	
	private String randomDeviceOperator() {
		Random random = new Random();
		int value = random.nextInt(5);
		String med = "Dentist";
		switch (value) {
		case 0:
			med = "Nurse";
			break;
		case 1:
			med = "Physician";
			break;
		case 2:
			med = "Biomedical engineer";
			break;
		case 3:
			med = "Phlebotomist";
			break;
		case 4:
			med = "Dentist";
			break;
		}
		return med;
	}
	
	private RecallClassification randomRecallClassification() {
		Random random = new Random();
		int value = random.nextInt(3);
		RecallClassification classification = RecallClassification.CLASS_I;
		switch (value) {
		case 0:
			classification = RecallClassification.CLASS_I;
			break;
		case 1:
			classification = RecallClassification.CLASS_II;
			break;
		case 2:
			classification = RecallClassification.CLASS_III;
			break;
		}
		return classification;
	}
}
