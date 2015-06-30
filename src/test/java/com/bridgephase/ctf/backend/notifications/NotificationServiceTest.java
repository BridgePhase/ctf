package com.bridgephase.ctf.backend.notifications;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import com.bridgephase.ctf.backend.domain.SearchCountResponse;
import com.bridgephase.ctf.backend.domain.SearchCountResult;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.RecallClassification;
import com.bridgephase.ctf.backend.fda.OpenFdaService;
import com.bridgephase.ctf.model.jpa.Notification;
import com.bridgephase.ctf.model.repository.NotificationRepository;


public class NotificationServiceTest {
	
	// Mocks
	private NotificationRepository repository;
	private OpenFdaService openFda;
	
	// Class being tested.
	private NotificationService service;
	
	private String DRUG_REACTION = "Drowsiness";
	private String DRUG_ROUTE = "Topical";
	private String RECALL = RecallClassification.CLASS_I.shortLabel();
	private String DEVICE_EVENT = "Injury";
	
	@Captor
	private ArgumentCaptor<List<Notification>> captor;
	
	@Before
	public void setup() {
		openFda = mock(OpenFdaService.class);
		repository = mock(NotificationRepository.class);
		service = new NotificationService();
		service.repository = repository;
		service.openFda = openFda;
		initDrugReactionResponse();
		initDrugLabelNotifications();
		initRecallNotifications();
		initDeviceEventNotifications();
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetNotifications() {
		testNotificationsReturnedFromDatabase();
		testNotificationsReturnedAfterUpdate();		
	}
	
	@SuppressWarnings("unchecked")
	private void testNotificationsReturnedFromDatabase() {
		List<Notification> mockResult = new ArrayList<Notification>();
		mockResult.add(new Notification("something"));
		when(repository.findAll()).thenReturn(mockResult);
		List<Notification> result = service.getNotifications();
		assertNotNull(result);
		verify(repository, times(0)).deleteAll();
		verify(repository, times(0)).save(any(List.class));
	}
	
	@SuppressWarnings("unchecked")
	private void testNotificationsReturnedAfterUpdate() {
		List<Notification> mockResult = new ArrayList<Notification>();
		List<Notification> filledMockResult = new ArrayList<Notification>();
		for (int i = 0; i < 25; i++) {
			filledMockResult.add(new Notification());
		}
		when(repository.findAll()).thenReturn(mockResult, filledMockResult);
		service.getNotifications();
		verify(repository).save(captor.capture());
		List<Notification> savedList = captor.getValue();
		assertEquals(savedList.size(), 25);
		verifySavedList(savedList);
		assertTrue(!savedList.equals(mockResult));
		verify(repository, times(1)).deleteAll();
		verify(repository, times(1)).save(any(List.class));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void testUpdateNotifications() {
		service.updateNotifications();
		verify(repository).save(captor.capture());
		List<Notification> savedList = captor.getValue();
		assertTrue(!savedList.isEmpty());
		verifySavedList(savedList);
		verify(repository, times(1)).deleteAll();
		verify(repository, times(1)).save(any(List.class));
	}
	
	private void initDrugReactionResponse() {
		SearchCountResponse mockResponse = new SearchCountResponse();
		List<SearchCountResult> mockResults = new ArrayList<>();
		mockResponse.setResults(mockResults);
		
		addSearchResult(mockResults, "", 100);
		addSearchResult(mockResults, DRUG_REACTION, 35);
		
		when(openFda.mostCommonReactionTypes(anyString())).thenReturn(mockResponse);
	}
	
	private void initDrugLabelNotifications() {
		SearchCountResponse mockResponse = new SearchCountResponse();
		List<SearchCountResult> mockResults = new ArrayList<>();
		mockResponse.setResults(mockResults);

		addSearchResult(mockResults, DRUG_ROUTE, 100);
		addSearchResult(mockResults, "Oral", 50);
		
		when(openFda.drugPurposeRoute(anyString())).thenReturn(mockResponse);
	}
	
	private void initRecallNotifications() {
		SearchCountResponse mockResponse = new SearchCountResponse();
		List<SearchCountResult> mockResults = new ArrayList<>();
		mockResponse.setResults(mockResults);

		addSearchResult(mockResults, RECALL, 100);
		addSearchResult(mockResults, RecallClassification.CLASS_II.shortLabel(), 60);
		
		when(openFda.recallClassifications(any(DataNoun.class))).thenReturn(mockResponse);
	}
	
	private void initDeviceEventNotifications() {
		SearchCountResponse mockResponse = new SearchCountResponse();
		List<SearchCountResult> mockResults = new ArrayList<>();
		mockResponse.setResults(mockResults);

		addSearchResult(mockResults, DEVICE_EVENT, 100);
		addSearchResult(mockResults, "Death", 50);
		
		when(openFda.deviceEventCountByOperator(anyString())).thenReturn(mockResponse);
	}
	
	private void addSearchResult(List<SearchCountResult> resultList, String term, Integer count) {
		SearchCountResult mockResult = new SearchCountResult();
		mockResult.setTerm(term);
		mockResult.setCount(count);
		resultList.add(mockResult);
	}
	
	private void verifySavedList(List<Notification> list) {
		verifyItem(list, 5, DRUG_REACTION);
		verifyItem(list, 25, RECALL);
		verifyItem(list, 6, DRUG_ROUTE);
		verifyItem(list, 25, RECALL);
		verifyItem(list, 5, DEVICE_EVENT);
		verifyItem(list, 25, RECALL);
	}
	
	private void verifyItem(List<Notification> savedList,int expectedCount, String keyWord) {
		int count = 0;
		for (Notification item : savedList) {
			if (item.getHeadline().contains(keyWord.toLowerCase())) {
				count++;
			}
		}
		assertEquals(count, expectedCount);
	}

}
