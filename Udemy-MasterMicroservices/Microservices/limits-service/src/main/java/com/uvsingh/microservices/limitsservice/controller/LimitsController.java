package com.uvsingh.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uvsingh.microservices.limitsservice.bean.Limits;
import com.uvsingh.microservices.limitsservice.config.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	Configuration config;
	
	
	@GetMapping("/limits")
	public Limits retrieveLimits() {
//		return new Limits(1, 1000);
		return new Limits(config.getMinimum(), config.getMaximum());
	}
}
