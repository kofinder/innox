package com.finder.innox.webportal;

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

import com.finder.innox.core.dto.CustomItemDTO;
import com.finder.innox.core.dto.CustomItemLayoutDTO;
import com.finder.innox.core.dto.CustomProductDTO;
import com.finder.innox.core.services.CategoryService;
import com.finder.innox.core.services.ColorService;
import com.finder.innox.core.services.CustomItemLayoutService;
import com.finder.innox.core.services.CustomItemService;
import com.finder.innox.core.services.CustomProductService;
import com.finder.innox.core.services.SizeService;
import com.finder.innox.core.services.SubCategoryService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.ItemSizeCategoryEnum;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class CustomProductController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private CustomProductService customProductService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private CustomItemService customItemService;

	@Autowired
	private CustomItemLayoutService customLayoutService;

	@Autowired
	private ColorService colorService;
	
	@Autowired
	private SizeService sizeService;

	@ModelAttribute(name = "images")
	public String getRootImagePath() {
		return CommonConstant.IMAGE_PATH;
	}

	@GetMapping("/custom_product_setup")
	public String getCustomProductData(@RequestParam(name = "customPrdId", required = false) Long customPrdId,
			@RequestParam(name = "customItemId", required = false) Long customItemId,
			@RequestParam(name = "customLayoutId", required = false) Long customLayoutId,
			@RequestParam(name = "tab", required = false) Integer tab, Model model, HttpServletRequest request) {
		logger.info("CustomProductController :: getCustomProductData() >> Start");

		// custom product
		if (customPrdId != null && customPrdId.compareTo(0L) > 0) {
			model.addAttribute("customProductDTO", customProductService.getCustomProductById(customPrdId));
			model.addAttribute("subCategroyList", subCategoryService.getAllActiveSubCategoryList());

			// custom item
			if (customItemId != null && customItemId.compareTo(0L) > 0) {
				logger.info("CustomProductController :: getCustomProductData() >> Custom Item Id : " + customItemId);
				model.addAttribute("customItemDTO", customItemService.getCustomItemById(customItemId));
				if (tab == null || tab != 3) {
					model.addAttribute("tab", 2);
				}

				// custom item layout
				if (customLayoutId != null && customLayoutId.compareTo(0L) > 0) {
					model.addAttribute("customItemLayoutDTO",
							customLayoutService.getCustomItemLayoutbyId(customLayoutId));
					model.addAttribute("tab", 3);
				} else {
					model.addAttribute("customItemLayoutDTO", new CustomItemLayoutDTO());
				}

				model.addAttribute("customItemLayoutList",
						customLayoutService.getCustomItemLayoutListByItemId(customItemId));
			} else {
				model.addAttribute("customItemDTO", new CustomItemDTO());
				model.addAttribute("customItemLayoutDTO", new CustomItemLayoutDTO());
			}

			model.addAttribute("customItemList", customItemService.getCustomItemListByCustomProductId(customPrdId));
		} else {
			model.addAttribute("customProductDTO", new CustomProductDTO());
			model.addAttribute("customItemDTO", new CustomItemDTO());
			model.addAttribute("customItemLayoutDTO", new CustomItemLayoutDTO());
		}
		commomModelSetup(model);

		logger.info("CustomProductController :: getCustomProductData() >> End");
		return "custom_product_setup";
	}

	@PostMapping("/custom_product_setup")
	public String customProductManage(@ModelAttribute(name = "customProductDTO") CustomProductDTO customProductDTO,
			RedirectAttributes attributes, HttpServletRequest request) {
		logger.info("CustomProductController :: customProductManage() >> Start");
		try {
			// TODO user information from spring security

			CustomProductDTO customProductDto = customProductService.manageCustomProduct(customProductDTO, "");
			if (customProductDto != null) {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CustomProductController :: customProductManage() >> " + e.getMessage());
		}

		logger.info("CustomProductController :: customProductManage() >> End");
		return "redirect:custom_product_setup.html";
	}

	@PostMapping("/custom_item_setup")
	public String customItemManage(@ModelAttribute(name = "customItemDTO") CustomItemDTO customItemDTO,
			@ModelAttribute(name = "customProductId") Long customProductId, RedirectAttributes attributes,
			HttpServletRequest request) {

		logger.info("CustomProductController :: customItemManage() >> Start");

		try {
			// TODO add valid duplicate logic
			CustomProductDTO customProductDTO = new CustomProductDTO();
			customProductDTO.setSeq(customProductId);
			customItemDTO.setCustomProductDTO(customProductDTO);

			CustomItemDTO customItemDto = customItemService.customItemManage(customItemDTO);
			if (customItemDto != null) {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
			}

			attributes.addFlashAttribute("tab", 2);
			attributes.addAttribute("customPrdId", customItemDto.getCustomProductDTO().getSeq());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CustomProductController :: customItemManage() >> " + e.getMessage());
		}

		logger.info("CustomProductController :: customItemManage() >> End");
		return "redirect:custom_product_setup.html";
	}

	@PostMapping("/custom_layout_setup")
	public String customItemLayoutManage(
			@ModelAttribute(name = "CustomItemLayoutDTO") CustomItemLayoutDTO customItemLayoutDTO,
			@ModelAttribute(name = "customProductId") Long customProductId,
			@ModelAttribute(name = "customItemId") Long customItemId, RedirectAttributes attributes,
			HttpServletRequest request) {

		try {
			CustomItemDTO customItemDTO = new CustomItemDTO();
			customItemDTO.setSeq(customItemId);
			customItemLayoutDTO.setCustomItemDTO(customItemDTO);

			if (customLayoutService.isValidLayoutName(customItemId, customItemLayoutDTO.getLayoutName(),
					customItemLayoutDTO.getSeq()) == 1) {
				CustomItemLayoutDTO customItemLayoutDto = customLayoutService
						.manageCustomItemLayout(customItemLayoutDTO);

				if (customItemLayoutDto != null) {
					attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_SUCCESS.getDesc());
				} else {
					attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.SAVE_FAILED.getDesc());
				}
			} else {
				attributes.addFlashAttribute("customItemLayoutDTO", customItemLayoutDTO);
				attributes.addFlashAttribute(CommonConstant.UI_MESSGAE, MessageEnum.LAYOUT_NAME_EXIST.getDesc());
			}

			attributes.addFlashAttribute("tab", 3);
			attributes.addAttribute("tab", 3);
			attributes.addAttribute("customPrdId", customProductId);
			attributes.addAttribute("customItemId", customItemId);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("customItemLayoutManage() >> " + e.getMessage(), e);
		}
		return "redirect:custom_product_setup.html";
	}

	@GetMapping(value = "/custom_product_search")
	public String customProductSearchGet(Model model, HttpServletRequest request) {
		commomModelSetup(model);
		model.addAttribute("searchCusPrdDTO", new CustomProductDTO());
		model.addAttribute("cusProductList", new ArrayList<CustomProductDTO>());
		return "custom_product_search";
	}

	@PostMapping(value = "custom_product_search")
	public String customProductSearchPost(@ModelAttribute(name = "searchCusPrdDTO") CustomProductDTO searchCusPrdDTO,
			Model model, HttpServletRequest request) {
		commomModelSetup(model);
		model.addAttribute("searchCusPrdDTO", searchCusPrdDTO);

		if (searchCusPrdDTO.getCategoryDTO() != null && searchCusPrdDTO.getCategoryDTO().getSeq() > 0) {
			model.addAttribute("subCategroyList",
					subCategoryService.getAllSubCategoryListByCatId(searchCusPrdDTO.getCategoryDTO().getSeq(), 0));
		}

		model.addAttribute("cusProductList", customProductService.searchCustomProduct(searchCusPrdDTO));

		return "custom_product_search";
	}

	// TODO To implement custom product search
	private void commomModelSetup(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.CUSTOM_PRODUCT);
		model.addAttribute("categroyList", categoryService.getAllCategoryList());
		model.addAttribute("statusList", CommonStatus.values());
		model.addAttribute("colorList", colorService.getAllColorList());
		model.addAttribute("sizeCategoryList", ItemSizeCategoryEnum.values());
		model.addAttribute("itemSizeList", sizeService.getAllSize());
	}

}
