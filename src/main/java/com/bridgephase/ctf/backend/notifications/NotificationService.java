package com.bridgephase.ctf.backend.notifications;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;

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
	private static final String[] medications = { "Tylenol", "Robitussin", 
		"Nyquil", "Claritin", "Tums" };
	private static final String[] drugPurpose = {"antiseptic", "anti inflammatory", "antihistamine", 
		"stimulant", "sunscreen", "pain relief"};
	private static final String[] deviceOperators = {"Dentist", "Nurse", "Physician", 
		"Biomedical Engineer", "Phlebotomist"};
	private static final RecallClassification[] recallClassifications = { RecallClassification.CLASS_I, 
		RecallClassification.CLASS_II, RecallClassification.CLASS_III };
	
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
		items = randomizedList(items);
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
		logger.info("Generating notifications.");
		List<Notification> notifications = new ArrayList<>();
		notifications.addAll(drugReactions());
		notifications.addAll(recallNotifications(DataNoun.DEVICE));
		notifications.addAll(drugLabels());
		notifications.addAll(recallNotifications(DataNoun.FOOD));
		notifications.addAll(deviceEvents());
		notifications.addAll(recallNotifications(DataNoun.DRUG));
		return notifications;
	}
	
	private List<Notification> randomizedList(List<Notification> input) {
		logger.info("Randomizing return list.");
		List<Notification> output = new ArrayList<>();
		Set<Notification> unique = new HashSet<>();
		String medication = randomMedication();
		String purpose = randomDrugPurpose();
		String operator = randomDeviceOperator();
		RecallClassification classification = randomRecallClassification();
		for (Notification notification : input) {
			String text = notification.getHeadline();
			if (StringUtils.isEmpty(text)) {
				continue;
			}
			if (text.contains(medication) || 
				text.contains(purpose) || 
				text.contains(operator)) {
				unique.add(notification);
				continue;
			}
			for (@SuppressWarnings("unused") DataNoun noun : DataNoun.values()) {
				if (text.contains(classification.description())) {
					unique.add(notification);
				}
			}
		}
		output.addAll(unique);
		return output;
	}
	
	private List<Notification> drugReactions() {
		List<Notification> notifications = new ArrayList<>();
		for (String medication: medications) {
			Notification notification = new Notification();
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
			notifications.add(notification);	
		}
		return notifications;
	}
	
	private List<Notification> drugLabels() {
		List<Notification> notifications = new ArrayList<>();
		for (String purpose : drugPurpose) {
			Notification notification = new Notification();
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
			notifications.add(notification);	
		}
		return notifications;
	}
	
	private List<Notification> recallNotifications(DataNoun noun) {
		List<Notification> notifications = new ArrayList<>();
		for (RecallClassification classification : recallClassifications) {
			Notification notification = new Notification();
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
					String.format("Of the %s %s recalls initiated within the past year, %d%% are considered %s.", 
					withCommas(total), noun.toString().toLowerCase(), percent, classification.description()));
			notifications.add(notification);	
		}
		return notifications;
	}
	
	private List<Notification> deviceEvents() {
		List<Notification> notifications = new ArrayList<>();
		for (String operator : deviceOperators) {
			Notification notification = new Notification();
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
			notifications.add(notification);	
		}
		return notifications;
	}
	
	private String randomMedication() {
		Random random = new Random();
		int value = random.nextInt(5);
		String med = medications[value];
		return med;
	}
	
	private String randomDrugPurpose() {
		Random random = new Random();
		int value = random.nextInt(6);
		String med = drugPurpose[value];
		return med;
	}
	
	private String randomDeviceOperator() {
		Random random = new Random();
		int value = random.nextInt(5);
		String med = deviceOperators[value];
		return med;
	}
	
	private RecallClassification randomRecallClassification() { 
		Random random = new Random();
		int value = random.nextInt(3);
		RecallClassification classification = recallClassifications[value];
		return classification;
	}
	
	private String withCommas(int value) {
		return NumberFormat.getNumberInstance(Locale.US).format(value);
	}
}
