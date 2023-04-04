package com.uvsingh.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsersNotFoundException extends RuntimeException {
	public UsersNotFoundException(String msg) {
		super(msg);
	}
}
