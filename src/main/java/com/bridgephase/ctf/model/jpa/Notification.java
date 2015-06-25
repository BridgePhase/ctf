package com.bridgephase.ctf.model.jpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Notification {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
	private String headline;
	
	public Notification() {}
	
	public Notification(String headline) {
		this.headline = headline;
	}
	
	public String getHeadline() {
		return headline; 
	}
	
	public void setHeadline(String headline) {
		this.headline = headline;
	}
	
	@Override
    public String toString() {
        return String.format(
                "Notification[id=%d, headline='%s']",
                id, headline);
    }
}
