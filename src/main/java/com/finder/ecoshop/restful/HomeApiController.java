package com.finder.ecoshop.restful;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.finder.ecoshop.annotation.EcoShopApi;
import com.finder.ecoshop.core.dto.BannerDTO;
import com.finder.ecoshop.core.services.BannerService;
import com.finder.ecoshop.exception.ProcessException;
import com.finder.ecoshop.exception.ProcessException.ErrorType;
import com.finder.ecoshop.response.BannerResponse;
import com.finder.ecoshop.response.HomePageResponse;
import com.finder.ecoshop.response.Response;
import com.finder.ecoshop.utils.JsonUtil;

@EcoShopApi(apiPath = EcoShopApiConstant.API_RESOURCES_NAME)
public class HomeApiController {
	
	private final Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	private BannerService bannerService;
	
	@GetMapping(path = EcoShopApiConstant.API_HOME_PAGE_DATA)
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
