package com.chandra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WelcomeController {

	@RequestMapping("/welcome")
	public String welcomeMsg() {
		return "Welcome to Spring Security ";
	}
}
