package com.matte.exemplarservice.domain;

public class HelloWorld {

	private String title;
	private String message;
	
	public HelloWorld(String title, String message) {
		
		if(!validConstructorParameters(title, message)) {
			throw new IllegalArgumentException();
		}
		
		this.title = title;
		this.message = message;
	}
	
	private boolean validConstructorParameters(String... stringParams) {
		for(String param : stringParams) {
			if(param.isEmpty() || param == null) {
				return false;
			}
		}
		
		return true;
	}
	
	public String getTitle() {
		return title;
	}
	public String getMessage() {
		return message;
	}
	
}
