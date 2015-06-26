package com.bridgephase.ctf.model.jpa;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class CacheEntry {

	@Id
	private String key;
	
	@Lob
	private String payload;
	
	private Date lastUpdated = new Date();

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public String toString() {
		return "CacheEntry [key=" + key + ", lastUpdated=" + lastUpdated + "]";
	}
	
	
}
