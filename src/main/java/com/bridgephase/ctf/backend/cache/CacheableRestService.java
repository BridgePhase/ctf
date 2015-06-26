package com.bridgephase.ctf.backend.cache;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import com.bridgephase.ctf.model.jpa.CacheEntry;
import com.bridgephase.ctf.model.repository.CacheEntryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service(value="CachedRestOperations")
public class CacheableRestService extends RestTemplate implements CacheService {
	private static final Logger logger = LoggerFactory.getLogger(CacheableRestService.class);
	
	@Autowired
	private RestOperations operations;
	
	@Autowired
	private CacheEntryRepository repo;
	
	@Autowired
	@Qualifier("ArrayAwareConverter")
	private MappingJackson2HttpMessageConverter converter;
	
	@Override
	public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> urlVariables)
		throws RestClientException {
		return super.getForObject(url, responseType, urlVariables);
	}
	
	@Override
	public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
		CacheEntry entry = repo.findOne(url.toString());
		T response = null;

		if (entry == null) {
			logger.info("Serving real response for {}", url);
			response = operations.getForObject(url, responseType);
			entry = new CacheEntry();
			entry.setKey(url.toString());
			ObjectMapper mapper = converter.getObjectMapper();
			try {
				entry.setPayload(mapper.writeValueAsString(response));
				logger.info("Saving cached response for {}", url);
				repo.save(entry);
			} catch (JsonProcessingException e) {
				logger.warn(
					String.format("Failed to save response to {}", url), 
					e);
			}
		} else {
			logger.info("Serving cached response for {} last updated at {}", 
				url, entry.getLastUpdated());
			String json = entry.getPayload();
			ObjectMapper mapper = converter.getObjectMapper();
			try {
				return mapper.readValue(json, responseType);
			} catch (IOException e) {
				logger.warn("Failed to return cached entry for {}, returning real response instead", url);
				return operations.getForObject(url, responseType);
			}
		}
		return response; 
	}
	
	@Override
	@Transactional
	public void clearCacheEntriesOlderThan(Date date) {
		Iterable<CacheEntry> results = repo.findByLastUpdatedBefore(date);
		if (logger.isInfoEnabled()) {
			for (CacheEntry entry : results) {
				logger.info("Deleting cache entry {}", entry);
			}
		}
		repo.delete(results);
	}
}
