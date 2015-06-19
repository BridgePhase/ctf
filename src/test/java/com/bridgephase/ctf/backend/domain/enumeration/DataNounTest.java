package com.bridgephase.ctf.backend.domain.enumeration;

import static org.junit.Assert.*;
import org.junit.Test;

public class DataNounTest {
	
	@Test
	public void testFromLabel() {
		for (DataNoun noun : DataNoun.values()) {
			DataNoun result = DataNoun.fromLabel(noun.label());
			assertTrue(result.equals(noun));
		}
	}
}
