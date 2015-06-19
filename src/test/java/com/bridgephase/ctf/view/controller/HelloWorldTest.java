package com.bridgephase.ctf.view.controller;

import org.junit.Assert;
import org.junit.Test;

import com.bridgephase.ctf.view.controller.HelloWorldController;

public class HelloWorldTest {

	@Test
	public void sampleTest() {
		Assert.assertEquals("hello", new HelloWorldController().hello());
	}
}
