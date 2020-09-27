package com.finder.innox.restful;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.ShoppingCartDTO;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.ShoppingCartService;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.request.CustomProductRequest;
import com.finder.innox.request.InstockShoppingCartRequest;
import com.finder.innox.response.AddToCartResponse;
import com.finder.innox.response.Response;
import com.finder.innox.response.ShoppingCartListResponse;
import com.finder.innox.response.ShoppingCartResponse;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;
import com.finder.innox.utils.UserRoleEnum;

@InnoxShopApi(apiPath = InnoxApiConstant.API_AUTH_RESOURCES_NAME)
public class ShoppingCartApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private UserService userService;

	@PostMapping(path = InnoxApiConstant.API_INSTOCK_ADD_TO_CART)
	public String instockProductAddToCart(@RequestBody InstockShoppingCartRequest requestData,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		Response<AddToCartResponse> apiResponse = new Response<AddToCartResponse>();
		List<FieldError> errorList = new ArrayList<FieldError>();

		try {
			AddToCartResponse response = new AddToCartResponse();
			Principal principal = httpRequest.getUserPrincipal();
			if (principal != null) {
				UserDTO userDto = userService.findByName(principal.getName(), UserRoleEnum.ROLE_USER.getCode());
				requestData.setCustomer_id(userDto.getSeq());
			}

			checkValidAddToCartData(requestData, errorList);

			if (errorList.size() == 0) {
				Long cartId = shoppingCartService.instockAddToCart(requestData);
				if (cartId != null && cartId > 0) {
					response.setMessage("You have added to your shopping cart!");

					apiResponse.setData(response);
					apiResponse.setResponseMessage("Instock add to cart is success!");
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("instockProductAddToCart() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@PutMapping(path = InnoxApiConstant.API_INSTOCK_ADD_TO_CART)
	public String updateAddToCart(@RequestParam(name = "cart_id") long cart_id,
			@RequestParam(name = "quantity") int quantity, HttpServletRequest request,
			HttpServletResponse httpResponse) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<AddToCartResponse> apiResponse = new Response<AddToCartResponse>();

		try {
			AddToCartResponse response = new AddToCartResponse();
			checkValidUpdateCartData(cart_id, quantity, errorList);

			if (errorList.size() == 0) {

				InstockShoppingCartRequest updateCartRequest = new InstockShoppingCartRequest();
				updateCartRequest.setShopping_cart_id(cart_id);
				updateCartRequest.setQuantity(quantity);

				Principal principal = request.getUserPrincipal();
				if (principal != null) {
					UserDTO userDto = userService.findByName(principal.getName(), UserRoleEnum.ROLE_USER.getCode());
					updateCartRequest.setCustomer_id(userDto.getSeq());
				}

				ShoppingCartDTO shoppingCartDTO = shoppingCartService.updateShoppingCart(updateCartRequest);
				response.setMessage("Shopping cart update is success!");
				response.setTotal_amount(shoppingCartDTO.getTotalAmount());
				response.setTotal_amount_text(shoppingCartDTO.getTotalAmountText());

				apiResponse.setData(response);
				apiResponse.setResponseMessage("Shopping cart update is success!");
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateAddToCart() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_SHOPPING_CART_LIST)
	public String getShoppingCartList(HttpServletRequest request) {
		String result = "";
		ProcessException pe = null;
		Response<ShoppingCartListResponse> apiResponse = new Response<ShoppingCartListResponse>();
		Principal principal = request.getUserPrincipal();
		long customerId = 0;
		if (principal != null) {
			UserDTO userDto = userService.findByName(principal.getName(), UserRoleEnum.ROLE_USER.getCode());
			customerId = userDto.getSeq();
		}

		ShoppingCartListResponse response = new ShoppingCartListResponse();
		BigDecimal totalAmount = BigDecimal.ZERO;
		List<ShoppingCartDTO> cartDtos = shoppingCartService.getShoppingCartListByCusId(customerId);
		for (ShoppingCartDTO cart : cartDtos) {
			ShoppingCartResponse cartResponse = ShoppingCartResponse.transferDtoToResponseData(cart, request);
			response.getShopping_carts().add(cartResponse);

			totalAmount = totalAmount.add(cart.getProductSubTotal());
		}

		response.setTotal_amount(totalAmount);
		response.setTotal_amount_text(
				CommonUtil.formatBigDecimalAsCurrency(response.getTotal_amount(), CommonConstant.CURRENCY_CODE_KS));

		apiResponse.setData(response);
		apiResponse.setResponseMessage("Data retrieval is success!");

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@DeleteMapping(path = InnoxApiConstant.API_INSTOCK_ADD_TO_CART)
	public String deleteShoppingCarts(@RequestBody Map<String, List<Long>> cart_ids, HttpServletRequest request,
			HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		Response<ShoppingCartListResponse> apiResponse = new Response<ShoppingCartListResponse>();

		try {
			Principal principal = request.getUserPrincipal();
			long customerId = 0;
			if (principal != null) {
				UserDTO userDto = userService.findByName(principal.getName(), UserRoleEnum.ROLE_USER.getCode());
				customerId = userDto.getSeq();
			}

			ShoppingCartListResponse response = new ShoppingCartListResponse();
			BigDecimal totalAmount = BigDecimal.ZERO;
			List<ShoppingCartDTO> cartDtos = shoppingCartService.deleteShoppingCarts(cart_ids.get("cart_ids"),
					customerId);
			for (ShoppingCartDTO cart : cartDtos) {
				ShoppingCartResponse cartResponse = ShoppingCartResponse.transferDtoToResponseData(cart, request);
				response.getShopping_carts().add(cartResponse);

				totalAmount = totalAmount.add(cart.getProductSubTotal());
			}

			response.setTotal_amount(totalAmount);
			response.setTotal_amount_text(
					CommonUtil.formatBigDecimalAsCurrency(response.getTotal_amount(), CommonConstant.CURRENCY_CODE_KS));

			apiResponse.setData(response);
			apiResponse.setResponseMessage("Shopping cart delete is success!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("deleteShoppingCarts() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@PostMapping(path = InnoxApiConstant.API_CUSTOM_ADD_TO_CART)
	public String customProductAddToCart(@RequestBody CustomProductRequest customProductRequest,
			HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<AddToCartResponse> apiResponse = new Response<AddToCartResponse>();

		try {
			// TODO custom add to cart
			Principal principal = httpRequest.getUserPrincipal();
			String customerName = "";
			if (principal != null) {
				customerName = principal.getName();
			}

			checValidCustomAddToCart(customProductRequest, errorList);

			if (errorList.size() == 0) {
				Long productId = shoppingCartService.customAddToCart(customProductRequest, customerName);
				if (productId != null) {
					AddToCartResponse response = new AddToCartResponse();
					response.setMessage("You have added to your shopping cart!");

					apiResponse.setData(response);
					apiResponse.setResponseMessage("Instock add to cart is success!");
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("customProductAddToCart() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	private void checValidCustomAddToCart(CustomProductRequest customProductRequest, List<FieldError> errorList) {
		if (customProductRequest.getCustom_product_id() <= 0) {
			errorList.add(new FieldError(FieldCode.CUSTOM_PRODUCT_ID.getCode(),
					ErrorMessage.CUSTOM_PRODUCT_ID_REQUIRED.getMessage()));
		}

		if (customProductRequest.getCustom_item_id() <= 0) {
			errorList.add(new FieldError(FieldCode.CUSTOM_ITEM_ID.getCode(),
					ErrorMessage.CUSTOM__ITEM_REQUIRED.getMessage()));
		}

		if (customProductRequest.getProduct_layout() == null || customProductRequest.getProduct_layout().isEmpty()) {
			errorList.add(new FieldError(FieldCode.CUSTOM_PRODUCT_LAYOUT.getCode(),
					ErrorMessage.CUSTOM_PRODUCT_LAYOUT_LIST.getMessage()));
		}

		if (customProductRequest.getProduct_sizes() == null || customProductRequest.getProduct_sizes().isEmpty()) {
			errorList.add(new FieldError(FieldCode.ARTWORK.getCode(), ErrorMessage.ARTWORK_ID_REQUIRED.getMessage()));
		}

		customProductRequest.getProduct_layout().forEach(layout -> {
			if (layout.getProduct_layout_id() <= 0) {
				errorList.add(new FieldError(FieldCode.CUSTOM_PRODUCT_LAYOUT.getCode(),
						ErrorMessage.CUSTOM_PRODUCT_LAYOUT_REQUIRED.getMessage()));
			}

			if (CommonUtil.isEmpty(layout.getCreated_image())) {
				errorList.add(new FieldError(FieldCode.CUSTOM_PRODUCT_LAYOUT.getCode(),
						ErrorMessage.CUSTOM_LAYOUT_IMAGE_REQUIRED.getMessage()));
			}
		});
	}

	private void checkValidUpdateCartData(long cart_id, int quantity, List<FieldError> errorList) {

		if (cart_id <= 0) {
			errorList.add(new FieldError(FieldCode.SHOPPING_CART_ID.getCode(),
					ErrorMessage.SHOPPING_CART_ID_REQUIRED.getMessage()));
		}

		if (quantity <= 0) {
			errorList.add(
					new FieldError(FieldCode.QUANTITY_REQUIRED.getCode(), ErrorMessage.QUANTITY_REQUIRED.getMessage()));
		}
	}

	private void checkValidAddToCartData(InstockShoppingCartRequest requestData, List<FieldError> errorList) {

		if (requestData.getCustomer_id() <= 0) {
			errorList.add(
					new FieldError(FieldCode.CUSTOMER_ID.getCode(), ErrorMessage.CUSTOMER_ID_REQUIRED.getMessage()));
		}

		if (requestData.getProduct_id() <= 0) {
			errorList
					.add(new FieldError(FieldCode.PRODUCT_ID.getCode(), ErrorMessage.PRODUCT_ID_REQUIRED.getMessage()));
		}

		if (requestData.getColor_id() <= 0) {
			errorList.add(new FieldError(FieldCode.COLOR_ID.getCode(), ErrorMessage.COLOR_ID_REQUIRED.getMessage()));
		}

		if (requestData.getSize_id() <= 0) {
			errorList.add(new FieldError(FieldCode.SIZE_ID.getCode(), ErrorMessage.SIZE_ID_REQUIRED.getMessage()));
		}

		if (requestData.getQuantity() <= 0) {
			errorList.add(
					new FieldError(FieldCode.QUANTITY_REQUIRED.getCode(), ErrorMessage.QUANTITY_REQUIRED.getMessage()));
		}
	}

}
