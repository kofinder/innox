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

import com.finder.innox.core.dto.ZoneDTO;
import com.finder.innox.core.services.ZoneService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class ZoneController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ZoneService zoneService;

	@GetMapping("/zone_setup")
	public String zoneManageGet(@RequestParam(name = "zoneId", required = false) Long zoneId, Model model,
			HttpServletRequest request) {
		commonModelSetup(model);
		if (zoneId != null && zoneId.compareTo(Long.valueOf(0)) == 1) {
			model.addAttribute("zoneDTO", zoneService.getZoneDataById(zoneId));
		} else {
			model.addAttribute("zoneDTO", new ZoneDTO());
		}
		return "zone_setup";
	}

	@PostMapping("/zone_setup")
	public String zoneManagePost(@ModelAttribute(name = "zoneDTO") ZoneDTO zoneDTO, RedirectAttributes attributes,
			HttpServletRequest request) {

		try {
			ZoneDTO zoneDto = zoneService.zoneManage(zoneDTO);
			if (zoneDto != null) {
				if (zoneDto.getSeq() > 0) {
					attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
				} else {
					attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("zoneManagePost() >> " + e.getMessage(), e);
		}

		return "redirect:zone_setup.html";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.ZONE_TITLE);
		model.addAttribute("zoneList", zoneService.getAllZoneList());
	}

}
