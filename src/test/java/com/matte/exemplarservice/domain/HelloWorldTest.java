package com.matte.exemplarservice.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloWorldTest {

	@Test
	void testValidConstructorAndGetters() {
		String title = "this a title";
		String message = "this is a message";
		HelloWorld helloWorld = new HelloWorld(title, message);
		
		assertEquals(title, helloWorld.getTitle());
		assertEquals(message, helloWorld.getMessage());
	}

}
