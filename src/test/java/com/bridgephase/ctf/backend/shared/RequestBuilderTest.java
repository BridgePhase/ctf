package com.bridgephase.ctf.backend.shared;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bridgephase.ctf.backend.domain.enumeration.DataContext;
import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;

public class RequestBuilderTest {

	@Test
	public void testWithProtocolAndHost() {
		String result = RequestBuilder.builder(Protocol.HTTP, "open.fda.gov").build();
		assertTrue("Real result was: " + result, result.contains("http://open.fda.gov"));
	}

	@Test
	public void testWithDataNoun() {
		for (DataNoun noun : DataNoun.values()) {
			String result = RequestBuilder.builder(Protocol.HTTP, "open.fda.gov").withDataNoun(noun).build();
			assertTrue("Real result was: " + result, result.contains("/" + noun.toString().toLowerCase() + "/"));
		}
	}

	@Test
	public void testWithDataContext() {
		for (DataContext context : DataContext.values()) {
			String result = RequestBuilder.builder(Protocol.HTTP, "open.fda.gov").withContext(context).build();
			assertTrue("Real result was: " + result, result.contains("/" + context.toString().toLowerCase() + ".json"));
		}
	}

	@Test
	public void canHandleLimits() {
		String result = RequestBuilder.builder(Protocol.HTTP, "open.fda.gov")
			.withLimit(100)
			.build();
		assertTrue("Real result was: " + result, result.contains("limit=100"));

	}
}
