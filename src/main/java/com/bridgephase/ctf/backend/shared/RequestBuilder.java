package com.bridgephase.ctf.backend.shared;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;

/**
 * Helps build OpenFDA.gov http requests
 * @author Kane Gyovai & Jaime Garcia 
 *
 */
public class RequestBuilder {
	
	private static final Logger logger = LoggerFactory.getLogger(RequestBuilder.class);

	private String protocol;
	private String host;
	private String context;
	private String format = ".json";
	private String noun;
	private String search = "";
	private String count = "";
	private int limit = 1;
	
	protected RequestBuilder(Protocol protocol, String host) {
		withProtocol(protocol);
		this.host = host;
	}
	
	public static RequestBuilder builder(Protocol protocol, String host) {
		return new RequestBuilder(protocol, host);
	}
	
	public RequestBuilder withProtocol(Protocol protocol) {
		this.protocol = protocol.toString().toLowerCase();
		return this;
	}
	
	public RequestBuilder withDataNoun(DataNoun noun) {
		this.noun = noun.toString().toLowerCase();
		return this;
	}
	
	public RequestBuilder withContext(DataContext context) {
		this.context = context.toString().toLowerCase();
		return this;
	}
	
	public RequestBuilder withSearch(String search) {
		this.search = search;
		return this;
	}
	
	public RequestBuilder withCount(String count) {
		this.count = count;
		return this;
	}
	
	public RequestBuilder withLimit(int limit) {
		this.limit = limit;
		return this;
	}
	
	public String build() {
		StringBuilder builder = new StringBuilder();
		builder.append(protocol)
			.append("://").append(host)
			.append("/").append(noun)
			.append("/").append(context)
			.append(format)
			.append("?").append("api_key=").append(KeyStore.API)
			.append("&search=")
			.append(search)
			.append("&count=")
			.append(count)
			.append("&limit=" + limit);
		
		logger.debug("Request string generated: " + builder.toString());
		return builder.toString();
	}
	
	public URI buildUri() {
		try {
			return new URI(build());
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
