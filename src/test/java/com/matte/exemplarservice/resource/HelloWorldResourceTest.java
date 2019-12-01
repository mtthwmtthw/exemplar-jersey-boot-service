package com.matte.exemplarservice.resource;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matte.exemplarservice.domain.HelloWorld;

public class HelloWorldResourceTest extends JerseyTest {

	ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @AfterEach
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

	@Override
	protected Application configure() {
		ResourceConfig config = new ResourceConfig();
		config.register(HelloWorldResource.class);
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	    context.refresh();
	    config.property("contextConfig", context);
	    
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		
		return config;
	}
	
	@Test
	public void getDefaultHelloWorldMessage() throws JsonMappingException, JsonProcessingException {
		Response response = target("/hello/world").request().get();

		assertEquals("Http Response should be 200: ", Status.OK.getStatusCode(), response.getStatus());
		assertEquals("Http Content-Type should be: ", MediaType.APPLICATION_JSON,
				response.getHeaderString(HttpHeaders.CONTENT_TYPE));

		String responseString = response.readEntity(String.class);
	    HelloWorld helloWorldResponse = objectMapper.readValue(responseString, HelloWorld.class);

		assertEquals("It's a wonderful time", helloWorldResponse.getTitle());
		assertEquals("For a hello world", helloWorldResponse.getMessage());
	}
}
