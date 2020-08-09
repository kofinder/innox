package com.finder.innox.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.BannerDTO;
import com.finder.innox.core.dto.CategoryDTO;
import com.finder.innox.core.dto.ProductDTO;
import com.finder.innox.core.services.BannerService;
import com.finder.innox.core.services.CategoryService;
import com.finder.innox.core.services.ProductService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.response.BannerResponse;
import com.finder.innox.response.CategoryResponse;
import com.finder.innox.response.HomePageResponse;
import com.finder.innox.response.ProductResponse;
import com.finder.innox.response.Response;
import com.finder.innox.utils.JsonUtil;

@InnoxShopApi(apiPath = InnoxApiConstant.API_RESOURCES_NAME)
public class HomeApiController {

	private final Logger logger = LogManager.getLogger(this.getClass());

	@Autowired
	private BannerService bannerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping(path = InnoxApiConstant.API_HOME_PAGE_DATA)
	public String homePageData(HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		Response<HomePageResponse> apiResponse = new Response<HomePageResponse>();
		HomePageResponse response = new HomePageResponse();
		try {

			List<BannerDTO> bannerDtoList = bannerService.getAllBannerList();
			bannerDtoList.forEach(banner -> {
				BannerResponse bannerResponse = new BannerResponse(banner, request);
				response.getBanner_list().add(bannerResponse);
			});

			// popular product list
			List<ProductDTO> productList = productService.getPopularProductList();
			productList.forEach(product -> {
				ProductResponse productResponse = new ProductResponse(product, request);
				response.getPopular_product_list().add(productResponse);
			});

			// promotion product list
			List<ProductDTO> promotionList = productService.getPromotionProductList();
			promotionList.forEach(promotion -> {
				ProductResponse productResponse = new ProductResponse(promotion, request);
				response.getPromotion_product_list().add(productResponse);
			});

			// feature category list
			List<CategoryDTO> categoryList = categoryService.getFeatureCategoryList();
			categoryList.forEach(category -> {
				CategoryResponse categoryResponse = new CategoryResponse(category, request);
				response.getCategory_list().add(categoryResponse);
			});

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Data retrieval is success");

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("homePageData() >> " + e.getMessage(), e);
			pe = new ProcessException(ErrorType.GENERAL);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

}
