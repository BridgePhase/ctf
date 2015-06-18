package com.bridgephase.foo.view.controller;

import org.junit.Assert;
import org.junit.Test;

public class HelloWorldTest {

	@Test
	public void sampleTest() {
		Assert.assertEquals("nothello", new HelloWorldController().hello());
	}
}
