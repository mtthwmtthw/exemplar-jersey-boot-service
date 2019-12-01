package com.matte.exemplarservice.domain;

public class HelloWorld {

	private String title;
	private String message;
	
	public HelloWorld() {
		
	}
	public HelloWorld(String title, String message) {
		this.title = title;
		this.message=message;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public String getMessage() {
		return message;
	}
	
}
