package com.finder.ecoshop.webportal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finder.ecoshop.core.dto.BannerDTO;
import com.finder.ecoshop.core.services.BannerService;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonUtil;
import com.finder.ecoshop.utils.PageTitleConstant;

@Controller
public class BannerController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private BannerService bannerService;

	@GetMapping("/banner_setup.html")
	public String bannerSetupGet(@RequestParam(name = "bannerId", required = false) Long bannerId, Model model, HttpServletRequest request) {
		logger.info("bannerSetupGet() >> Start");
		commonModel(model);
		if(bannerId != null && bannerId > 0) {
			BannerDTO bannerDTO  =  bannerService.getBannerById(bannerId);
			if(!CommonUtil.isEmpty(bannerDTO.getImagePath())) {
				bannerDTO.setImagePath(CommonConstant.IMAGE_PATH + bannerDTO.getImagePath());
			}
			model.addAttribute("bannerDTO", bannerDTO);
		}else {
			model.addAttribute("bannerDTO", new BannerDTO());
		}
		
		model.addAttribute("bannerList", bannerService.getAllBannerList());
		logger.info("bannerSetupGet() >> End");
		return "banner_setup";
	}

	@PostMapping("/banner_setup.html")
	public String bannerSetupPost(@ModelAttribute(name = "bannerDTO") BannerDTO bannerDTO,
			RedirectAttributes attributes, HttpServletRequest request) {
		logger.info("bannerSetupPost() >> Start");

		try {
			BannerDTO bannerDto = bannerService.saveBanner(bannerDTO);
			if (bannerDto != null) {
				attributes.addFlashAttribute("message", "Banner Saved Successfully!");
			} else {
				attributes.addFlashAttribute("errorMsg", "Banner Saving Failed !");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("bannerSetupPost() >> " + e.getMessage(), e);
			attributes.addAttribute("errorMsg", e.getMessage());
		}

		logger.info("bannerSetupPost() >> End");
		return "redirect:banner_setup.html";
	}
	
	private void commonModel(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.BANNER_TITLE);
	}

}
