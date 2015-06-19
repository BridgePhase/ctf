package com.bridgephase.ctf.backend.shared;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bridgephase.ctf.backend.domain.enumeration.Protocol;

public class RequestBuilderTest {
	
	@Test
	public void testDefaults() {
		String result  = RequestBuilder.builder().build();
		assertTrue(!result.contains(RequestBuilder.PROTOCOL));
		assertTrue(!result.contains(RequestBuilder.HOST));
		assertTrue(!result.contains(RequestBuilder.NOUN));
		assertTrue(result.contains(RequestBuilder.EVENT));
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

}
