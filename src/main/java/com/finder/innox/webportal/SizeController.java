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

import com.finder.innox.core.dto.SizeDTO;
import com.finder.innox.core.services.SizeService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.ItemSizeCategoryEnum;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class SizeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SizeService sizeService;

	@GetMapping("/size_setup")
	public String sizeManageGet(@RequestParam(name = "sizeId", required = false) Long sizeId, Model model,
			HttpServletRequest request) {
		logger.info("sizeManageGet() >> Start");
		if (sizeId != null && sizeId > 0) {
			model.addAttribute("sizeDTO", sizeService.getSizeDataById(sizeId));
		} else {
			model.addAttribute("sizeDTO", new SizeDTO());
		}

		commonModelSetup(model);
		logger.info("sizeManageGet() >> End");
		return "size_setup";
	}

	@PostMapping("/size_setup")
	public String sizeManagePost(@ModelAttribute(name = "sizeDTO") SizeDTO sizeDTO, RedirectAttributes attributes,
			HttpServletRequest request) {
		logger.info("sizeManagePost() >> Start");
		try {
			SizeDTO sizeDto = sizeService.sizeManage(sizeDTO);
			if (sizeDto != null) {
				if (sizeDto.getSeq() > 0) {
					attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.UPDATE_SUCCESS.getDesc());
				} else {
					attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
				}

				attributes.addAttribute("colorId", sizeDto.getSeq());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.ERROR_MESSAGE.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("sizeManagePost() >> " + e.getMessage(), e);
		}
		logger.info("sizeManagePost() >> End");
		return "redirect:size_setup.html";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.PRODUCT_SIZE_TITLE);
		model.addAttribute("statusList", CommonStatus.values());
		model.addAttribute("sizeList", sizeService.getAllSize(0));
		model.addAttribute("itemSizeCategoryList", ItemSizeCategoryEnum.values());
	}

}
