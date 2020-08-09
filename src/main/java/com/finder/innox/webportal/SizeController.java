package com.finder.innox.webportal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.innox.core.services.SizeService;

@Controller
public class SizeController {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private SizeService sizeService;

	@GetMapping("/size_setup")
	public String sizeManageGet(HttpServletRequest request) {
		
		return "seize_setup";
	}

}
