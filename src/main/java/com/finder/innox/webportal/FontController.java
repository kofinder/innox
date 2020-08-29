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

import com.finder.innox.core.dto.FontsDTO;
import com.finder.innox.core.services.FontsService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class FontController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FontsService fontsService;

	@ModelAttribute(name = "images")
	public String getImagePath() {
		return CommonConstant.IMAGE_PATH;
	}

	@GetMapping("/font_setup")
	public String fontManageGet(@RequestParam(name = "fontId", required = false) Long fontId, Model model,
			HttpServletRequest request) {
		logger.info("fontManageGet() >> Start");
		if (fontId != null && fontId.compareTo(Long.valueOf(0)) == 1) {
			model.addAttribute("fontDTO", fontsService.getFontDataById(fontId));
		} else {
			model.addAttribute("fontDTO", new FontsDTO());
		}
		commonModelSetup(model);
		logger.info("fontManageGet() >> End");
		return "font_setup";
	}

	@PostMapping("/font_setup")
	public String fontManagePost(@ModelAttribute(name = "fontDTO") FontsDTO fontDTO, RedirectAttributes attributes,
			HttpServletRequest request) {
		try {
			FontsDTO fontsDTO = fontsService.fontManage(fontDTO);
			if (fontsDTO != null) {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("fontManagePost() >> " + e.getMessage(), e);
		}
		return "redirect:font_setup.html";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.FONTS_TITLE);
		model.addAttribute("fontList", fontsService.getFontList(0));
		model.addAttribute("statusList", CommonStatus.values());
	}

}
