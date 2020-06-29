package com.finder.ecoshop.webportal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.finder.ecoshop.core.dto.UserDTO;
import com.finder.ecoshop.core.services.UserService;

@Controller
public class AuthController {

	@Autowired
	private UserService userSerivce;

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new UserDTO());
		return "register";
	}

	@PostMapping("/saveUser")
	public String submit(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "error";
		}
		
		userSerivce.save(user);

		return "login";
	}
}
