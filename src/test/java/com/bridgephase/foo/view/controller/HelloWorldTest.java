package com.bridgephase.foo.view.controller;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

public class HelloWorldTest {

	@Test
	public void sampleTest() {
		Assert.assertEquals("hello", new HelloWorldController().hello(Mockito.mock(Model.class)));
	}
}
