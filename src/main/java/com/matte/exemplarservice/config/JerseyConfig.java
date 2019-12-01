package com.matte.exemplarservice.config;

import javax.inject.Named;

import org.glassfish.jersey.server.ResourceConfig;

@Named
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		packages("com.matte.exemplarservice.resource");
	}

}
