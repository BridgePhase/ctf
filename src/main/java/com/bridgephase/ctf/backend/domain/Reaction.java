package com.bridgephase.ctf.backend.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reaction {
	
	@JsonProperty("reactionmeddrapt")
	private List<String> reactionMeddraPt;
	
	@JsonProperty("reactionmeddraversionpt")
	private List<String> reactionMeddraVersionPt;
	
	@JsonProperty("reactionoutcome")
	private List<String> reactionOutcome;

	public List<String> getReactionMeddraPt() {
		return reactionMeddraPt;
	}

	public void setReactionMeddraPt(List<String> reactionMeddraPt) {
		this.reactionMeddraPt = reactionMeddraPt;
	}

	public List<String> getReactionMeddraVersionPt() {
		return reactionMeddraVersionPt;
	}

	public void setReactionMeddraVersionPt(List<String> reactionMeddraVersionPt) {
		this.reactionMeddraVersionPt = reactionMeddraVersionPt;
	}

	public List<String> getReactionOutcome() {
		return reactionOutcome;
	}

	public void setReactionOutcome(List<String> reactionOutcome) {
		this.reactionOutcome = reactionOutcome;
	}
}
