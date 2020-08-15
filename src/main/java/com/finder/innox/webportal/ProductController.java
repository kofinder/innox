package com.finder.innox.webportal;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.finder.innox.core.dto.ProductDTO;
import com.finder.innox.core.dto.ProductImageDTO;
import com.finder.innox.core.dto.SubCategoryDTO;
import com.finder.innox.core.services.BrandService;
import com.finder.innox.core.services.CategoryService;
import com.finder.innox.core.services.ColorService;
import com.finder.innox.core.services.ProductService;
import com.finder.innox.core.services.SizeService;
import com.finder.innox.core.services.SubCategoryService;
import com.finder.innox.response.CommonAjaxResponse;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.JsonUtil;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.PageTitleConstant;

@Controller
public class ProductController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private SubCategoryService subCategoryService;

	@Autowired
	private ColorService colorService;

	@Autowired
	private SizeService sizeService;

	@ModelAttribute(name = "images")
	public String getImageServerPath() {
		return CommonConstant.IMAGE_PATH;
	}

	@GetMapping("/product_setup")
	public String productSetupGet(@RequestParam(name = "prdId", required = false) Long productId, Model model,
			HttpServletRequest request) {
		logger.info("productSetupGet() >> Start");

		CommonModelSetUp(model);

		if (productId != null && productId.compareTo(0L) > 0) {
			ProductDTO productDTO = productService.getProductDataById(productId);
			model.addAttribute("productDTO", productDTO);
			model.addAttribute("subCategroyList",
					subCategoryService.getAllSubCategoryListByCatId(productDTO.getCategoryDTO().getSeq(), 0));
		} else {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductImageDTOs(new ArrayList<ProductImageDTO>());
			model.addAttribute("productDTO", productDTO);
		}

		logger.info("productSetupGet() >> End");
		return "product_setup";
	}

	@PostMapping("/product_setup")
	public String productSetupPost(@ModelAttribute(name = "productDTO") ProductDTO productDTO,
			RedirectAttributes attributes, HttpServletRequest request) {
		try {
			ProductDTO productDto = productService.manageProduct(productDTO);
			if (productDto != null) {
				attributes.addFlashAttribute("message", MessageEnum.SAVE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute("errorMsg", MessageEnum.SAVE_FAILED.getDesc());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("productSetupPost() >> " + e.getMessage());
		}
		return "redirect:product_setup.html";
	}

	@GetMapping("/product_search")
	public String productSearchGet(Model model, HttpServletRequest request) {

		model.addAttribute("productList", productService.productSearch(null));
		model.addAttribute("pageTitle", PageTitleConstant.PRODUCT_SEARCH);
		return "product_search";
	}

	@PostMapping("/subCategoryByCategoryAjax")
	@ResponseBody
	public Object getSubCategoryByCategoryAjax(@RequestBody Long categoryId) {
		System.out.println("getSubCategoryByCategoryAjax() >> Category Id : " + categoryId);
		List<SubCategoryDTO> subCategoryList = subCategoryService.getAllSubCategoryListByCatId(categoryId, 0);
		CommonAjaxResponse<List<SubCategoryDTO>> response = new CommonAjaxResponse<List<SubCategoryDTO>>();
		response.setResponseCode("200");
		response.setResponseMessage("Data retrieval is successful");
		response.setData(subCategoryList);
		return JsonUtil.toJSON(response);
	}

	private void CommonModelSetUp(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.PRODUCT);
		model.addAttribute("categroyList", categoryService.getAllCategoryList());
		model.addAttribute("brandList", brandService.getAllBrandList());
		model.addAttribute("statusList", CommonStatus.values());
		model.addAttribute("colorList", colorService.getAllColorList(CommonStatus.ACTIVE.getCode()));
		model.addAttribute("sizeList", sizeService.getAllSize(CommonStatus.ACTIVE.getCode()));
	}
}
