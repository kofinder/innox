package com.finder.innox.webportal;

import java.security.Principal;

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

import com.finder.innox.core.dto.TownshipDTO;
import com.finder.innox.core.services.StateService;
import com.finder.innox.core.services.TownshipService;
import com.finder.innox.core.services.ZoneService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class TownshipController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TownshipService townshipService;

	@Autowired
	private StateService stateService;

	@Autowired
	private ZoneService zoneService;

	@GetMapping("/township_setup")
	public String townshipSetupGet(@RequestParam(name = "townshipId", required = false) Long townshipId, Model model,
			HttpServletRequest request) {
		if (townshipId != null && townshipId.compareTo(Long.valueOf(0)) == 1) {
			logger.info("townshipSetupGet() >> Township Id : " + townshipId);
			model.addAttribute("townshipDTO", townshipService.getTownshipDataById(townshipId));
		} else {
			model.addAttribute("townshipDTO", new TownshipDTO());
		}
		commonModelSetup(model);
		return "township_setup";
	}

	@PostMapping("/township_setup")
	public String townshipSetupPost(@ModelAttribute(name = "townshipDTO") TownshipDTO townshipDTO,
			RedirectAttributes attributes, HttpServletRequest request) {

		try {
			Principal principal = request.getUserPrincipal();
			TownshipDTO townshipDto = townshipService.townshipManage(townshipDTO,
					principal == null ? "" : principal.getName());
			if (townshipDto != null) {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("townshipSetupPost() >> " + e.getMessage(), e);
		}

		return "redirect:township_setup.html";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.TOWNSHIP_TITLE);
		model.addAttribute("stateList", stateService.getStateDataList());
		model.addAttribute("zoneList", zoneService.getAllZoneList());
		model.addAttribute("townshipList", townshipService.searchTownshipList());
	}
}
