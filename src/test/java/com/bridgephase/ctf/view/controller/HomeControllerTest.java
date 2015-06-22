package com.bridgephase.ctf.view.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;

public class HomeControllerTest {

	private HomeController controller;
	
	@Before
	public void setup() {
		controller = spy(new HomeController());
	}
	
	@Test
	public void homeReturnsCorrectBinding() {
		assertEquals("home", controller.home(Mockito.mock(Model.class)));
	}

	@Test
	public void aliveReturnsVersionsWhenOverwritten() {
		BufferedReader fakeReader = new BufferedReader(new StringReader("fake-version-junit"));
		doReturn(fakeReader).when(controller).versionReader();
		
		assertEquals("fake-version-junit", controller.alive());
	}
	
	@Test
	public void aliveCanHandleAnExceptionReadingVersionFile() throws IOException {
		BufferedReader fakeReader = mock(BufferedReader.class);
		doThrow(new IOException()).when(fakeReader).readLine();
		doReturn(fakeReader).when(controller).versionReader();
		
		assertEquals("No version information available", controller.alive());
	}
}
