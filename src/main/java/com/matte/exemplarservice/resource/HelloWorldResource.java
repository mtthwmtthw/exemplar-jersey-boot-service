package com.matte.exemplarservice.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.matte.exemplarservice.domain.HelloWorld;

@Path("/hello")
public class HelloWorldResource {

	@GET
	@Path("/world")
	@Produces("application/json")
	public HelloWorld returnHelloWorldMessage() {
		return new HelloWorld("It's a wonderful time", "For a hello world");
	}
}
