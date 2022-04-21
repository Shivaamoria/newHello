package com.SampleTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SampleTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleTestApplication.class, args);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "Welcome to My Seat Brother";
	}

}
