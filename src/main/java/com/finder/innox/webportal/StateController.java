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

import com.finder.innox.core.dto.StateDTO;
import com.finder.innox.core.services.StateService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class StateController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StateService stateService;

	@GetMapping("state_setup")
	public String stateManageGet(@RequestParam(name = "stateId", required = false) Long stateId, Model model,
			HttpServletRequest request) {
		logger.info("stateManageGet() >> Start");
		commonModelSetup(model);

		if (stateId != null && stateId.compareTo(Long.valueOf(0)) == 1) {
			model.addAttribute("stateDTO", stateService.getStateDataById(stateId));
		} else {
			model.addAttribute("stateDTO", new StateDTO());
		}

		return "state_setup";
	}

	@PostMapping("/state_setup")
	public String stateManagePost(@ModelAttribute(name = "stateDTO") StateDTO stateDTO, HttpServletRequest request,
			RedirectAttributes attributes) {
		try {
			Principal principal = request.getUserPrincipal();
			StateDTO stateDto = stateService.stateManage(stateDTO, principal == null ? null : principal.getName());

			if (stateDto != null) {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE,  MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE,  MessageEnum.SAVE_FAILED.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("stateManagePost >> " + e.getMessage() + e);
		}
		return "redirect:state_setup.html";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.STATE_TITLE);
		model.addAttribute("stateList", stateService.getStateDataList(0));
	}

}
