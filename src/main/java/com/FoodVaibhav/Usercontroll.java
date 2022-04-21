package com.FoodVaibhav;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Usercontroll {
	@RequestMapping("/")
	public String getAllItems() {
		return "Hello";
	}

}
