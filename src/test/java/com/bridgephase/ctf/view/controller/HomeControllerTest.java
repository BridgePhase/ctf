package com.bridgephase.ctf.view.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class HomeControllerTest {

	private HomeController controller;
	
	@Before
	public void setup() {
		controller = spy(new HomeController());
	}
	
	@Test
	public void homeReturnsCorrectBinding() {
		assertEquals("home", controller.home(mock(Model.class)));
	}

	@Test
	public void aliveReturnsVersionsWhenOverwritten() {
		BufferedReader fakeReader = new BufferedReader(new StringReader("fake-version-junit"));
		doReturn(fakeReader).when(controller).versionReader();
		
		assertEquals("fake-version-junit", controller.alive());
	}
	
	@Test
	public void aliveCanHandleAnExceptionResadingVersionFile() throws IOException {
		BufferedReader fakeReader = mock(BufferedReader.class);
		doThrow(new IOException()).when(fakeReader).readLine();
		doReturn(fakeReader).when(controller).versionReader();
		
		assertEquals("No version information available", controller.alive());
	}
	
	@Test
	public void partialsReturnTheCorrectUrlForPartialTemplates() {
		assertEquals("modules/mypartial", controller.partial(mock(Model.class), "mypartial"));
	}
	
	@Test
	public void errorHandlerProvidesCorrectInformation() throws Exception {
		BufferedReader fakeReader = new BufferedReader(new StringReader("fake-version-junit"));
		doReturn(fakeReader).when(controller).versionReader();
		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		doReturn(new StringBuffer("http://notarealserver.com")).when(mockRequest).getRequestURL();

		ModelAndView result = controller.handleError(mockRequest);
		
		assertEquals("fake-version-junit", result.getModel().get("version"));
	}
	
	@Test
	public void correctErrorPath() {
		assertEquals("/errorpage", controller.getErrorPath());
	}
}
