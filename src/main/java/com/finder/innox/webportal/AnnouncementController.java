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

import com.finder.innox.core.dto.AnnouncementDTO;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.AnnouncementService;
import com.finder.innox.core.services.UserService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.NotificationTypeEnum;
import com.finder.innox.utils.PageTitleConstant;
import com.finder.innox.utils.UserRoleEnum;

@Controller
public class AnnouncementController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AnnouncementService announcementService;

	@Autowired
	private UserService userService;

	@ModelAttribute(name = "images")
	public String getImagePath() {
		return CommonConstant.IMAGE_PATH;
	}

	@ModelAttribute(name = "pageTitle")
	public String getPageTitle() {
		return PageTitleConstant.ANNOUNCEMENT;
	}

	@GetMapping("/announcement_setup")
	public String announcementManageGet(@RequestParam(name = "announcementId", required = false) Long announcementId,
			Model model, HttpServletRequest httpRequest) {
		logger.info("announcementManageGet() >>> Start");
		if (announcementId != null && announcementId.compareTo(0L) > 0) {
			model.addAttribute("announcementDTO", announcementService.getDataById(announcementId));
		} else {
			model.addAttribute("announcementDTO", new AnnouncementDTO());
		}

		model.addAttribute("statusList", CommonStatus.values());
		model.addAttribute("notiTypeList", NotificationTypeEnum.values());
		logger.info("announcementManageGet() >>> End");
		return "announcement_setup";
	}

	@PostMapping("/announcement_setup")
	public String announcementManagePost(@ModelAttribute(name = "announcementDTO") AnnouncementDTO announcementDTO,
			RedirectAttributes attributes, HttpServletRequest httpRequest) {

		try {
			Principal principal = httpRequest.getUserPrincipal();
			if (principal != null) {
				UserDTO userDto = userService.findByName(principal.getName(), UserRoleEnum.ROLE_ADMIN.getCode());
				announcementDTO.setUserDTO(userDto);
			}

			AnnouncementDTO announcementDto = announcementService.manageAnnouncement(announcementDTO);
			if (announcementDto != null) {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.info("announcementManagePost() >>> " + e.getMessage());
		}

		return "redirect:announcement_search.html";
	}

	@GetMapping("/announcement_search")
	public String announcementSearchGet(Model model, HttpServletRequest httpRequest) {
		model.addAttribute("announcementSearchDTO", new AnnouncementDTO());
		model.addAttribute("announcementList", announcementService.searchAnnouncementData(new AnnouncementDTO()));
		model.addAttribute("statusList", CommonStatus.values());
		model.addAttribute("notiTypeList", NotificationTypeEnum.values());
		return "announcement_search";
	}

	@PostMapping("/announcement_search")
	public String announcementSearchPost(
			@ModelAttribute(name = "announcementSearchDTO") AnnouncementDTO announcementSearchDTO, Model model,
			HttpServletRequest httpRequest) {

		try {
			model.addAttribute("announcementSearchDTO", announcementSearchDTO);
			model.addAttribute("announcementList", announcementService.searchAnnouncementData(announcementSearchDTO));
			model.addAttribute("statusList", CommonStatus.values());
			model.addAttribute("notiTypeList", NotificationTypeEnum.values());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("announcementSearchPost() >> " + e.getMessage());
		}

		return "announcement_search";
	}

}
