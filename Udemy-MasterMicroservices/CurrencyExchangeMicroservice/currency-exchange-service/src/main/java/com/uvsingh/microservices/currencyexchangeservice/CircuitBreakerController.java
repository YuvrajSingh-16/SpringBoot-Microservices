package com.uvsingh.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
//	@Retry(name="sample-api", fallbackMethod = "hardcodedResponse")
	
//	@CircuitBreaker(name="sample-api", fallbackMethod = "hardcodedResponse")
//	@RateLimiter(name="default") // 10s => 10000 Calls to the sample api
	@Bulkhead(name="sample-api")
	public String sampleApi() {
		logger.info("Sample Api call received");
//		ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy-url", String.class);
//		return forEntity.getBody();
		return "Sample-API";
	}
	
	public String hardcodedResponse(Exception e) {
		return "fallback-response";
	}
}
