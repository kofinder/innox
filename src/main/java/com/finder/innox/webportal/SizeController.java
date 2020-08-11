package com.finder.innox.webportal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.core.services.SizeService;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class SizeController {

	private Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private SizeService sizeService;

	@GetMapping("/size_setup")
	public String sizeManageGet(@RequestParam(name = "sizeId", required = false) Long sizeId, Model model,
			HttpServletRequest request) {
		// TODO to implement size manage flow
		model.addAttribute("pageTitle", PageTitleConstant.PRODUCT_SIZE);
		return "size_setup";
	}

}
