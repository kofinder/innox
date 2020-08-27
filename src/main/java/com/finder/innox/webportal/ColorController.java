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

import com.finder.innox.core.dto.ColorDTO;
import com.finder.innox.core.services.ColorService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class ColorController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ColorService colorService;

	@GetMapping("/color_setup")
	public String colorSetupGet(@RequestParam(name = "colorId", required = false) Long colorId, Model model,
			HttpServletRequest request) {

		logger.info("colorSetupGet() >> Start");

		if (colorId != null && colorId.compareTo(Long.valueOf(0)) > 0) {
			model.addAttribute("colorDTO", colorService.getColorDTO(colorId));
		} else {
			model.addAttribute("colorDTO", new ColorDTO());
		}

		commonModelSetup(model);

		logger.info("colorSetupGet() >> End");
		return "color_setup";
	}

	@PostMapping("/color_setup")
	public String colorSetupPost(@ModelAttribute(name = "colorDTO") ColorDTO colorDTO, RedirectAttributes attributes,
			HttpServletRequest request) {

		try {
			ColorDTO colorDto = colorService.colorManage(colorDTO);
			if (colorDto != null) {
				if (colorDTO.getSeq() > 0) {
					attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.UPDATE_SUCCESS.getDesc());
				} else {
					attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
				}

				attributes.addAttribute("colorId", colorDto.getSeq());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.ERROR_MESSAGE.getDesc());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("colorSetupPost() >> " + e.getMessage(), e);
		}

		return "redirect:color_setup.html";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.PRODUCT_COLOR_TITLE);
		model.addAttribute("statusList", CommonStatus.values());
		model.addAttribute("colorList", colorService.getAllColorList(0));
	}

}
