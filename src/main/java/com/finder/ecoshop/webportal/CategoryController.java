package com.finder.ecoshop.webportal;

import java.util.ArrayList;

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

import com.finder.ecoshop.core.dto.CategoryDTO;
import com.finder.ecoshop.core.dto.SubCategoryDTO;
import com.finder.ecoshop.core.services.CategoryService;
import com.finder.ecoshop.core.services.SubCategoryService;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.MessageEnum;
import com.finder.ecoshop.utils.PageTitleConstant;

@Controller
public class CategoryController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	private final String CATEGORY_ID = "catId";
	private final String SUB_CAT_TAB = "2";

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@ModelAttribute(name = "images")
	public String getImagePath() {
		return CommonConstant.IMAGE_PATH;
	}

	@GetMapping("/category_setup.html")
	private String categorySetupGet(@RequestParam(name = "catId", required = false) Long catId,
			@RequestParam(name = "subCatId", required = false) Long subCatId, Model model, HttpServletRequest request) {
		logger.info("categorySetupGet() >> Start");
		// TODO implement category and sub category list when category id is include
		commonModelSetup(model);

		if (catId != null && catId > 0) {
			model.addAttribute("categoryDTO", categoryService.getCategoryById(catId));

			model.addAttribute("subCategoryList", subCategoryService.getAllSubCategoryListByCatId(catId));
		} else {
			model.addAttribute("categoryDTO", new CategoryDTO());
		}

		if (subCatId != null && subCatId > 0) {
			model.addAttribute("subCategoryDTO", subCategoryService.getSubCategoryById(subCatId));
			model.addAttribute("tab", SUB_CAT_TAB);
		} else {
			model.addAttribute("subCategoryDTO", new SubCategoryDTO());
		}

		logger.info("categorySetupGet() >> End");
		return "category_setup";
	}

	@PostMapping("/category_setup.html")
	public String categorySetupPost(@ModelAttribute(name = "categoryDTO") CategoryDTO categoryDTO,
			RedirectAttributes attributes, HttpServletRequest request) {
		try {
			CategoryDTO categoryDto = categoryService.manageCategory(categoryDTO);
			if (categoryDto != null) {
				attributes.addAttribute(CATEGORY_ID, categoryDto.getSeq());
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute("categoryDTO", categoryDTO);
				attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("categorySetupPost() >> " + e.getMessage(), e);
			attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE, e.getMessage());
		}
		return "redirect:category_setup.html";
	}

	@PostMapping("/sub_category_setup")
	public String subCategorySetupPost(@ModelAttribute(name = "subCategoryDTO") SubCategoryDTO subCategoryDTO,
			RedirectAttributes attributes, HttpServletRequest request) {
		logger.info("subCategorySetupPost() >> Start");

		try {
			SubCategoryDTO subCategoryDto = subCategoryService.subCategoryManage(subCategoryDTO);
			if (subCategoryDto != null) {
				attributes.addAttribute(CATEGORY_ID, subCategoryDto.getCategoryDTO().getSeq());
				attributes.addFlashAttribute("tab", SUB_CAT_TAB);
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute("tab", SUB_CAT_TAB);
				attributes.addFlashAttribute("subCategoryDTO", subCategoryDTO);
				attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("subCategorySetupPost() >> " + e.getMessage(), e);
			attributes.addFlashAttribute(CommonConstant.UI_ERROR_MESSGAE, e.getMessage());
		}

		logger.info("subCategorySetupPost() >> End");
		return "redirect:category_setup.html";
	}

	@GetMapping("/category_search.html")
	public String categorySearchGet(Model model, HttpServletRequest request) {
		commonModelSetup(model);
		model.addAttribute("searchCatDTO", new CategoryDTO());
		model.addAttribute("categoryList", new ArrayList<CategoryDTO>());
		return "category_search";
	}

	@PostMapping("/category_search.html")
	public String categorySearchPost(@ModelAttribute(name = "searchCatDTO") CategoryDTO searchCatDTO, Model model,
			HttpServletRequest request) {
		logger.info("categorySearchPost() >> Start");
		commonModelSetup(model);
		model.addAttribute("searchCatDTO", searchCatDTO);
		model.addAttribute("categoryList", categoryService.searchCategoryByData(searchCatDTO));
		logger.info("categorySearchPost() >> End");
		return "category_search";
	}

	private void commonModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.CATEGORY_TITLE);
		model.addAttribute("statusList", CommonStatus.values());
	}

}
