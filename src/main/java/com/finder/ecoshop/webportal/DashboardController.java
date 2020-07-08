package com.finder.ecoshop.webportal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.ecoshop.core.domain.User;
import com.finder.ecoshop.core.services.UserService;

@Controller
public class DashboardController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/admin")
	public String admin() {
		List<User> userList = userService.getAllUser();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + userList.size());
		return "dashboard";
	}

	@GetMapping("/dashboard")
	public String dashboard() {
		List<User> userList = userService.getAllUser();
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + userList.size());
		return "dashboard";
	}
}
