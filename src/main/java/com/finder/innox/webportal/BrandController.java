package com.finder.innox.webportal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finder.innox.core.dto.BrandDTO;
import com.finder.innox.core.services.BrandService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class BrandController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BrandService brandService;

	@GetMapping("/brand_setup.html")
	public String brandSetupGet(@RequestParam(name = "brandId", required = false) Long brandId, Model model,
			HttpServletRequest request) {
		logger.info("brandSetupGet() >> Start");
		commonModelSetup(model);

		if (brandId == null || brandId <= 0) {
			model.addAttribute("brandDTO", new BrandDTO());
		} else {
			BrandDTO brandDTO  = brandService.getBrandById(brandId);
			if(!CommonUtil.isEmpty(brandDTO.getImagePath())) {
				brandDTO.setImagePath(CommonConstant.IMAGE_PATH + brandDTO.getImagePath());
			}
			model.addAttribute("brandDTO", brandDTO);
		}

		model.addAttribute("brandList", brandService.getAllBrandList());

		logger.info("brandSetupGet() >> End");
		return "brand_setup";
	}

	@PostMapping("/brand_setup.html")
	public String brandSetupPost(@ModelAttribute(name = "brandDTO") BrandDTO brandDTO, RedirectAttributes attributes,
			HttpServletRequest request) {

		try {

			BrandDTO brandDto = brandService.saveBrand(brandDTO);
			if (brandDto != null) {
				attributes.addFlashAttribute("message", "Brand Saved Successfully!");
			} else {
				attributes.addFlashAttribute("errorMsg", "Brand Saving Failed !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("brandSetupPost() >> " + e.getMessage(), e);
			attributes.addAttribute("errorMsg", e.getMessage());
		}

		return "redirect:brand_setup.html";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.BRAND_TITLE);
		model.addAttribute("statusList", CommonStatus.values());
	}

}
