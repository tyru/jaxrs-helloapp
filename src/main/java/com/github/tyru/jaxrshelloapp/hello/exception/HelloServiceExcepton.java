package com.github.tyru.jaxrshelloapp.hello.exception;

import javax.ws.rs.core.Response;

@lombok.Getter
public class HelloServiceExcepton extends Exception {
	private Exception exception;
	private Response status;

	public HelloServiceExcepton(Exception exception, Response status) {
		this.exception = exception;
		this.status = status;
	}
}
