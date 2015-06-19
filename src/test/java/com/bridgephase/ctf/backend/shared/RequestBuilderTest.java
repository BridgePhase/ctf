package com.bridgephase.ctf.backend.shared;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bridgephase.ctf.backend.domain.enumeration.DataNoun;
import com.bridgephase.ctf.backend.domain.enumeration.Protocol;

public class RequestBuilderTest {
	
	@Test
	public void testDefaults() {
		String result  = RequestBuilder.builder().build();
		assertTrue(!result.contains(RequestBuilder.PROTOCOL));
		assertTrue(!result.contains(RequestBuilder.HOST));
		assertTrue(!result.contains(RequestBuilder.NOUN));
		assertTrue(!result.contains(RequestBuilder.ACTION));
		assertTrue(!result.contains(RequestBuilder.FORMAT));
		assertTrue(result.contains(KeyStore.API));
	}
	
	@Test
	public void testWithProtocol() {
		for (Protocol protocol : Protocol.values()) {
			String result = RequestBuilder.builder()
					.withProtocol(protocol)
					.build();
			assertTrue(!result.contains(RequestBuilder.PROTOCOL));
			assertTrue(result.contains(protocol.toString()));
		}
	}
	
	@Test
	public void testWithDataNoun() {
		for (DataNoun noun : DataNoun.values()) {
			String result = RequestBuilder.builder()
					.withDataNoun(noun)
					.build();
			assertTrue(!result.contains(RequestBuilder.NOUN));
			assertTrue(!result.contains(RequestBuilder.ACTION));
			assertTrue(result.contains(noun.toString()));
			assertTrue(result.contains(noun.context()));
		}
	}

}
