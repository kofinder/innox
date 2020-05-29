package com.finder.ecoshop.webportal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@GetMapping("/dashboard")
	public String dashboard() {
		return "dashboard";
	}
}
