package com.bridgephase.ctf.view.controller;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import com.bridgephase.ctf.view.controller.HelloWorldController;

public class HelloWorldTest {

	@Test
	public void sampleTest() {
		Assert.assertEquals("hello", new HelloWorldController().hello(Mockito.mock(Model.class)));
	}
}
