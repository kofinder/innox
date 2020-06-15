package com.finder.ecoshop.webportal;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.finder.ecoshop.core.dto.ProductDTO;
import com.finder.ecoshop.core.services.BrandService;
import com.finder.ecoshop.core.services.CategoryService;
import com.finder.ecoshop.core.services.ProductService;
import com.finder.ecoshop.core.services.SubCategoryService;
import com.finder.ecoshop.utils.CommonConstant;
import com.finder.ecoshop.utils.CommonStatus;
import com.finder.ecoshop.utils.PageTitleConstant;

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
	
	@ModelAttribute(name = "images")
	public String getImageServerPath() {
		return CommonConstant.IMAGE_PATH;
	}
	
	@GetMapping("/product_setup")
	public String productSetupGet(Model model, HttpServletRequest request) {
		logger.info("productSetupGet() >> Start");
		
		CommonModelSetUp(model);
		model.addAttribute("productDTO", new ProductDTO());
		
		logger.info("productSetupGet() >> End");
		return "product_setup";
	}
	
	private void CommonModelSetUp(Model model) {
		model.addAttribute("pageTitle", PageTitleConstant.PRODUCT);
		model.addAttribute("categroyList", categoryService.getAllCategoryList());
		model.addAttribute("brandList", brandService.getAllBrandList());
		model.addAttribute("statusList", CommonStatus.values());
	}
}
