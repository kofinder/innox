package com.finder.innox.restful;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.finder.innox.annotation.InnoxShopApi;
import com.finder.innox.core.dto.OrderDTO;
import com.finder.innox.core.dto.OrderItemDTO;
import com.finder.innox.core.dto.PaymentTypeDTO;
import com.finder.innox.core.dto.ShoppingCartDTO;
import com.finder.innox.core.dto.UserAddressDTO;
import com.finder.innox.core.dto.UserDTO;
import com.finder.innox.core.services.OrderItemService;
import com.finder.innox.core.services.OrderService;
import com.finder.innox.core.services.PaymentTypeService;
import com.finder.innox.core.services.ShoppingCartService;
import com.finder.innox.core.services.UserAddressService;
import com.finder.innox.core.services.UserService;
import com.finder.innox.exception.ProcessException;
import com.finder.innox.exception.ProcessException.ErrorType;
import com.finder.innox.request.OrderConfirmRequest;
import com.finder.innox.request.OrderPreloadRequest;
import com.finder.innox.response.OrderConfirmResponse;
import com.finder.innox.response.OrderHistoryListResponse;
import com.finder.innox.response.OrderHistoryResponse;
import com.finder.innox.response.OrderItemResponse;
import com.finder.innox.response.OrderPreloadResponse;
import com.finder.innox.response.PaymentTypeResponse;
import com.finder.innox.response.Response;
import com.finder.innox.response.UserResponse;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonStatus;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.FieldError;
import com.finder.innox.utils.FieldError.ErrorMessage;
import com.finder.innox.utils.FieldError.FieldCode;
import com.finder.innox.utils.JsonUtil;
import com.finder.innox.utils.OrderStatusEnum;
import com.finder.innox.utils.ProductTypeEnum;
import com.finder.innox.utils.UserRoleEnum;

@InnoxShopApi(apiPath = InnoxApiConstant.API_AUTH_RESOURCES_NAME)
public class OrderApiController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private PaymentTypeService paymentTypeService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserAddressService userAddressService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@PostMapping(path = InnoxApiConstant.API_ORDER_PRELOAD, produces = "application/json; charset=utf-8")
	public String orderPerload(@RequestBody OrderPreloadRequest orderRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String result = "";
		ProcessException pe = null;
		List<FieldError> errorList = new ArrayList<FieldError>();
		Response<OrderPreloadResponse> apiResponse = new Response<OrderPreloadResponse>();

		try {

			Long customerId = Long.valueOf(httpRequest.getHeader(CommonConstant.API_REQUEST_HEADER_CUSTOMER_ID));

			if (orderRequest.getCart_ids() == null || orderRequest.getCart_ids().isEmpty()) {
				errorList.add(new FieldError(FieldCode.SHOPPING_CART_ID.getCode(),
						ErrorMessage.SHOPPING_CART_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				// user info
				UserDTO userDto = userService.getUserById(customerId);

				// user address
				UserAddressDTO userAddressDto = userAddressService.getUserAddressByUserId(customerId);

				// payment types
				List<PaymentTypeDTO> paymentTypeList = paymentTypeService
						.getAllPaymentTypeList(CommonStatus.ACTIVE.getCode());

				// cart items
				List<ShoppingCartDTO> cartItemList = shoppingCartService
						.getShoppingCartsForOrderPreload(orderRequest.getCart_ids());

				OrderPreloadResponse response = new OrderPreloadResponse();

				UserResponse userResponse = new UserResponse();
				if (userDto != null) {
					userResponse.setUser_id(userDto.getSeq());
					userResponse.setName(userDto.getUserName());
					userResponse.setPhone_no(userDto.getPhoneNo());
				}

				if (userAddressDto != null) {
					if (userAddressDto.getStateDTO() != null) {
						userResponse.setState_id(userAddressDto.getStateDTO().getSeq());
						userResponse.setState_name(userAddressDto.getStateDTO().getName());
					}

					if (userAddressDto.getTownshipDTO() != null) {
						userResponse.setTownship_id(userAddressDto.getTownshipDTO().getSeq());
						userResponse.setTownship_name(userAddressDto.getTownshipDTO().getTownshipName());

						// delivery cost
						if (userAddressDto.getTownshipDTO().getZoneDTO() != null) {
							response.setDelivery_cost(userAddressDto.getTownshipDTO().getZoneDTO().getDeliveryFee());
							response.setDelivery_cost_text(CommonUtil.formatBigDecimalAsCurrency(
									userAddressDto.getTownshipDTO().getZoneDTO().getDeliveryFee(),
									CommonConstant.CURRENCY_CODE_KS));
						}
					}

					userResponse.setDetail_address(userAddressDto.getDetailAddress());
				}
				response.setUser_detail(userResponse);

				paymentTypeList.forEach(payment -> {
					PaymentTypeResponse paymentResponse = new PaymentTypeResponse();
					paymentResponse.setPayment_type_id(payment.getSeq());
					paymentResponse.setName(payment.getName());
					paymentResponse.setCode(payment.getCode());
					paymentResponse.setIs_offline(payment.getIsOffline());
					paymentResponse
							.setPayment_image(CommonUtil.prepareImagePath(payment.getPaymentTypeImage(), httpRequest));
					response.getPayment_types().add(paymentResponse);
				});

				// total cost
				BigDecimal totalItemCost = BigDecimal.ZERO;

				// shopping cart data
				for (ShoppingCartDTO cart : cartItemList) {
					OrderItemResponse orderItemResponse = new OrderItemResponse();
					orderItemResponse.setCart_id(cart.getSeq());
					orderItemResponse.setProduct_id(cart.getProductDTO().getSeq());
					orderItemResponse.setProduct_name(cart.getProductDTO().getName());
					orderItemResponse.setColor_name(cart.getColorDTO().getColorName());
					orderItemResponse.setSize_name(cart.getSize().getSizeName());
					orderItemResponse.setImage_path(
							CommonUtil.prepareImagePath(cart.getProductDTO().getImagePath1(), httpRequest));
					orderItemResponse.setUnit_price(cart.getProductDTO().getPrice());
					orderItemResponse.setUnit_price_text(CommonUtil.formatBigDecimalAsCurrency(
							cart.getProductDTO().getPrice(), CommonConstant.CURRENCY_CODE_KS));

					// sub total cost
					orderItemResponse.setQuantity(cart.getQuantity());
					orderItemResponse
							.setSub_total(cart.getProductDTO().getPrice().multiply(new BigDecimal(cart.getQuantity())));

					totalItemCost = totalItemCost.add(orderItemResponse.getSub_total());

					response.getOrder_items().add(orderItemResponse);
				}

				response.setTotal_item_cost(totalItemCost);
				response.setTotal_item_cost_text(
						CommonUtil.formatBigDecimalAsCurrency(totalItemCost, CommonConstant.CURRENCY_CODE_KS));
				response.setTotal_cost(response.getTotal_item_cost().add(response.getDelivery_cost()));
				response.setTotal_cost_text(CommonUtil.formatBigDecimalAsCurrency(response.getTotal_cost(),
						CommonConstant.CURRENCY_CODE_KS));

				apiResponse.setData(response);
				apiResponse.setResponseMessage("Order preload is success!");

			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("orderPerload() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@PostMapping(path = InnoxApiConstant.API_ORDER_CONFIRM, produces = "application/json; charset=utf-8")
	public String orderConfirm(@RequestBody OrderConfirmRequest confirmRequest, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<OrderConfirmResponse> apiResponse = new Response<OrderConfirmResponse>();

		try {
			isValidOrderConfirmRequest(confirmRequest, errorList);

			Long customerId = Long.valueOf(httpRequest.getHeader(CommonConstant.API_REQUEST_HEADER_CUSTOMER_ID));

			if (errorList.size() == 0) {
				// order confirm
				OrderDTO orderDTO = orderService.orderConfirm(confirmRequest, customerId);

				if (orderDTO != null) {

					OrderConfirmResponse orderConfirmResponse = new OrderConfirmResponse();

					orderConfirmResponse.setOrder_id(orderDTO.getSeq());
					orderConfirmResponse.setInvoice_number(orderDTO.getInvoiceNumber());
					orderConfirmResponse.setOrder_status(orderDTO.getOrderStatus());
					orderConfirmResponse.setOrder_status_text(orderDTO.getOrderStatusText());
					orderConfirmResponse.setPayment_status(orderDTO.getPaymentStatus());
					orderConfirmResponse.setPayment_status_text(orderDTO.getPaymentStatusText());
					orderConfirmResponse.setDelivery_fee(orderDTO.getDeliveryFee());
					orderConfirmResponse.setDelivery_fee_text(orderDTO.getDeliveryFeeText());
					orderConfirmResponse.setTotal_cost(orderDTO.getTotalCost());
					orderConfirmResponse.setTotal_cost_text(orderDTO.getTotalCostText());

					List<OrderItemDTO> orderItemList = orderItemService.getOrderItemListByOrderId(orderDTO.getSeq());
					if (orderItemList != null && !orderItemList.isEmpty()) {
						for (OrderItemDTO dto : orderItemList) {
							OrderItemResponse orderItemResponse = new OrderItemResponse();
							orderItemResponse.setProduct_id(dto.getProductDTO().getSeq());
							orderItemResponse.setProduct_name(dto.getProductDTO().getName());
							if (dto.getProductDTO().getIsCustomProduct() == ProductTypeEnum.INSTOCK.getCode()) {
								orderItemResponse.setColor_name(dto.getColorDTO().getColorName());
								orderItemResponse.setSize_name(dto.getSizeDTO().getSizeName());
							}
							orderItemResponse.setImage_path(
									CommonUtil.prepareImagePath(dto.getProductDTO().getImagePath1(), httpRequest));
							orderItemResponse.setUnit_price(dto.getProductDTO().getPrice());
							orderItemResponse.setUnit_price_text(CommonUtil.formatBigDecimalAsCurrency(
									dto.getProductDTO().getPrice(), CommonConstant.CURRENCY_CODE_KS));

							// sub total cost
							orderItemResponse.setQuantity(dto.getQuantity());
							orderItemResponse.setSub_total(
									dto.getProductDTO().getPrice().multiply(new BigDecimal(dto.getQuantity())));

							orderConfirmResponse.getOrder_items().add(orderItemResponse);

						}
					}

					// user info
					UserDTO userDto = userService.getUserById(customerId);

					UserResponse userResponse = new UserResponse();
					if (userDto != null) {
						userResponse.setUser_id(userDto.getSeq());
						userResponse.setName(userDto.getUserName());
						userResponse.setPhone_no(userDto.getPhoneNo());
					}

					if (orderDTO.getStateDTO() != null) {
						userResponse.setState_id(orderDTO.getStateDTO().getSeq());
						userResponse.setState_name(orderDTO.getStateDTO().getName());
					}

					if (orderDTO.getTownshipDTO() != null) {
						userResponse.setTownship_id(orderDTO.getTownshipDTO().getSeq());
						userResponse.setTownship_name(orderDTO.getTownshipDTO().getTownshipName());
					}

					userResponse.setDetail_address(orderDTO.getDeliveryAddress());
					orderConfirmResponse.setUser_detail(userResponse);

					apiResponse.setData(orderConfirmResponse);
					apiResponse.setResponseMessage("Order confirm is successful");

				} else {
					httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
					pe.setFieldErrorList(errorList);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("orderConfirm() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_ORDER_HISTORY, produces = "application/json; charset=utf-8")
	public String orderHistorys(@PathVariable(name = "order_status") String orderStatus,
			@RequestParam(name = "start_date", required = false) String startDate,
			@RequestParam(name = "end_date", required = false) String endDate,
			@RequestParam(name = "page_no", required = false) Integer pageNo, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<OrderHistoryListResponse> apiResponse = new Response<OrderHistoryListResponse>();

		try {

			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			long customerId = 0;
			if (!(authentication instanceof AnonymousAuthenticationToken)) {
				UserDTO userDto = userService.findByName(authentication.getName(), UserRoleEnum.ROLE_USER.getCode());
				customerId = userDto.getSeq();
			}
			if (customerId <= 0) {
				errorList.add(
						new FieldError(FieldCode.USER_NAME.getCode(), ErrorMessage.USER_NAME_REQUIRED.getMessage()));
			}

			List<Integer> orderStatusList = new ArrayList<Integer>();
			if (CommonUtil.isEmpty(orderStatus)) {
				errorList.add(new FieldError(FieldCode.ORDER_STATUS.getCode(),
						ErrorMessage.ORDER_STATUS_REQUIRED.getMessage()));
			} else {
				String[] orderStatusArr = orderStatus.split(",");
				for (String status : orderStatusArr) {
					orderStatusList.add(Integer.valueOf(status));
				}
			}

			if (errorList.size() == 0) {
				OrderHistoryListResponse response = new OrderHistoryListResponse();
				List<OrderDTO> orderDtos = orderService.getOrderHistory(orderStatusList, startDate, endDate,
						customerId);
				orderDtos.forEach(order -> {
					OrderHistoryResponse historyResponse = new OrderHistoryResponse(order);
					response.getOrder_historys().add(historyResponse);
				});

				response.setTotal_order_count(orderDtos == null || orderDtos.isEmpty() ? 0 : orderDtos.size());
				apiResponse.setData(response);
				apiResponse.setResponseMessage("Order history retrieval is successful");
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("orderHistorys() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@GetMapping(path = InnoxApiConstant.API_ORDER_DETAIL, produces = "application/json; charset=utf-8")
	public String orderDetail(@PathVariable(name = "order_id") Long orderId, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<OrderConfirmResponse> apiResponse = new Response<OrderConfirmResponse>();

		try {

			if (orderId == null || orderId <= 0) {
				errorList.add(new FieldError(FieldCode.ORDER.getCode(), ErrorMessage.ORDER_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				OrderDTO orderDTO = orderService.getOrderDTOById(orderId);

				if (orderDTO != null) {

					OrderConfirmResponse orderConfirmResponse = new OrderConfirmResponse();

					orderConfirmResponse.setOrder_id(orderDTO.getSeq());
					orderConfirmResponse.setInvoice_number(orderDTO.getInvoiceNumber());
					orderConfirmResponse.setOrder_status(orderDTO.getOrderStatus());
					orderConfirmResponse.setOrder_status_text(orderDTO.getOrderStatusText());
					orderConfirmResponse.setPayment_status(orderDTO.getPaymentStatus());
					orderConfirmResponse.setPayment_status_text(orderDTO.getPaymentStatusText());
					orderConfirmResponse.setDelivery_fee(orderDTO.getDeliveryFee());
					orderConfirmResponse.setDelivery_fee_text(orderDTO.getDeliveryFeeText());
					orderConfirmResponse.setTotal_cost(orderDTO.getTotalCost());
					orderConfirmResponse.setTotal_cost_text(orderDTO.getTotalCostText());
					orderConfirmResponse
							.setIs_cancel_order(OrderStatusEnum.PROCESSING.getCode() == orderDTO.getOrderStatus());

					List<OrderItemDTO> orderItemList = orderItemService.getOrderItemListByOrderId(orderDTO.getSeq());
					if (orderItemList != null && !orderItemList.isEmpty()) {
						for (OrderItemDTO dto : orderItemList) {
							OrderItemResponse orderItemResponse = new OrderItemResponse();
							orderItemResponse.setProduct_id(dto.getProductDTO().getSeq());
							orderItemResponse.setProduct_name(dto.getProductDTO().getName());
							orderItemResponse.setImage_path(
									CommonUtil.prepareImagePath(dto.getProductDTO().getImagePath1(), httpRequest));
							orderItemResponse.setUnit_price(dto.getProductDTO().getPrice());
							orderItemResponse.setUnit_price_text(CommonUtil.formatBigDecimalAsCurrency(
									dto.getProductDTO().getPrice(), CommonConstant.CURRENCY_CODE_KS));

							// sub total cost
							orderItemResponse.setQuantity(dto.getQuantity());
							orderItemResponse.setSub_total(
									dto.getProductDTO().getPrice().multiply(new BigDecimal(dto.getQuantity())));

							orderConfirmResponse.getOrder_items().add(orderItemResponse);

						}
					}

					// user info
					UserDTO userDto = userService.getUserById(orderDTO.getCustomerDTO().getSeq());

					UserResponse userResponse = new UserResponse();
					if (userDto != null) {
						userResponse.setUser_id(userDto.getSeq());
						userResponse.setName(userDto.getUserName());
						userResponse.setPhone_no(userDto.getPhoneNo());
					}

					if (orderDTO.getStateDTO() != null) {
						userResponse.setState_id(orderDTO.getStateDTO().getSeq());
						userResponse.setState_name(orderDTO.getStateDTO().getName());
					}

					if (orderDTO.getTownshipDTO() != null) {
						userResponse.setTownship_id(orderDTO.getTownshipDTO().getSeq());
						userResponse.setTownship_name(orderDTO.getTownshipDTO().getTownshipName());
					}

					userResponse.setDetail_address(orderDTO.getDeliveryAddress());
					orderConfirmResponse.setUser_detail(userResponse);

					apiResponse.setData(orderConfirmResponse);
					apiResponse.setResponseMessage("Order detail retrieval is successful");

				} else {
					httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
					pe.setFieldErrorList(errorList);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("orderDetail() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	@PutMapping(path = InnoxApiConstant.API_ORDER_CANCEL, produces = "application/json; charset=utf-8")
	public String cancelOrder(@PathVariable(name = "order_id") Long orderId, HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {

		String result = "";
		List<FieldError> errorList = new ArrayList<FieldError>();
		ProcessException pe = null;
		Response<OrderConfirmResponse> apiResponse = new Response<OrderConfirmResponse>();

		try {

			if (orderId == null || orderId <= 0) {
				errorList.add(new FieldError(FieldCode.ORDER.getCode(), ErrorMessage.ORDER_ID_REQUIRED.getMessage()));
			}

			if (errorList.size() == 0) {
				OrderDTO orderDTO = orderService.cancelOrder(orderId);

				if (orderDTO != null) {

					OrderConfirmResponse orderConfirmResponse = new OrderConfirmResponse();

					orderConfirmResponse.setOrder_id(orderDTO.getSeq());
					orderConfirmResponse.setInvoice_number(orderDTO.getInvoiceNumber());
					orderConfirmResponse.setOrder_status(orderDTO.getOrderStatus());
					orderConfirmResponse.setOrder_status_text(orderDTO.getOrderStatusText());
					orderConfirmResponse.setPayment_status(orderDTO.getPaymentStatus());
					orderConfirmResponse.setPayment_status_text(orderDTO.getPaymentStatusText());
					orderConfirmResponse.setDelivery_fee(orderDTO.getDeliveryFee());
					orderConfirmResponse.setDelivery_fee_text(orderDTO.getDeliveryFeeText());
					orderConfirmResponse.setTotal_cost(orderDTO.getTotalCost());
					orderConfirmResponse.setTotal_cost_text(orderDTO.getTotalCostText());
					orderConfirmResponse
							.setIs_cancel_order(OrderStatusEnum.PROCESSING.getCode() == orderDTO.getOrderStatus());

					List<OrderItemDTO> orderItemList = orderItemService.getOrderItemListByOrderId(orderDTO.getSeq());
					if (orderItemList != null && !orderItemList.isEmpty()) {
						for (OrderItemDTO dto : orderItemList) {
							OrderItemResponse orderItemResponse = new OrderItemResponse();
							orderItemResponse.setProduct_id(dto.getProductDTO().getSeq());
							orderItemResponse.setProduct_name(dto.getProductDTO().getName());
							orderItemResponse.setImage_path(
									CommonUtil.prepareImagePath(dto.getProductDTO().getImagePath1(), httpRequest));
							orderItemResponse.setUnit_price(dto.getProductDTO().getPrice());
							orderItemResponse.setUnit_price_text(CommonUtil.formatBigDecimalAsCurrency(
									dto.getProductDTO().getPrice(), CommonConstant.CURRENCY_CODE_KS));

							// sub total cost
							orderItemResponse.setQuantity(dto.getQuantity());
							orderItemResponse.setSub_total(
									dto.getProductDTO().getPrice().multiply(new BigDecimal(dto.getQuantity())));

							orderConfirmResponse.getOrder_items().add(orderItemResponse);

						}
					}

					// user info
					UserDTO userDto = userService.getUserById(orderDTO.getCustomerDTO().getSeq());

					UserResponse userResponse = new UserResponse();
					if (userDto != null) {
						userResponse.setUser_id(userDto.getSeq());
						userResponse.setName(userDto.getUserName());
						userResponse.setPhone_no(userDto.getPhoneNo());
					}

					if (orderDTO.getStateDTO() != null) {
						userResponse.setState_id(orderDTO.getStateDTO().getSeq());
						userResponse.setState_name(orderDTO.getStateDTO().getName());
					}

					if (orderDTO.getTownshipDTO() != null) {
						userResponse.setTownship_id(orderDTO.getTownshipDTO().getSeq());
						userResponse.setTownship_name(orderDTO.getTownshipDTO().getTownshipName());
					}

					userResponse.setDetail_address(orderDTO.getDeliveryAddress());
					orderConfirmResponse.setUser_detail(userResponse);

					apiResponse.setData(orderConfirmResponse);
					apiResponse.setResponseMessage("Order cancel is successful");

				} else {
					httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
					pe = new ProcessException(ErrorType.INVALID_DATA, httpResponse);
					pe.setFieldErrorList(errorList);
				}
			} else {
				httpResponse.setStatus(HttpStatus.SC_BAD_REQUEST);
				pe = new ProcessException(ErrorType.MULTIPLE_ERROR, httpResponse);
				pe.setFieldErrorList(errorList);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("cancelOrder() >> " + e.getMessage(), e);
			httpResponse.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			pe = new ProcessException(ErrorType.GENERAL, httpResponse);
		}

		result = JsonUtil.formatJsonResponse(apiResponse, pe);
		return result;
	}

	private void isValidOrderConfirmRequest(OrderConfirmRequest confirmRequest, List<FieldError> errorList) {
		if (confirmRequest.getCart_ids() == null || confirmRequest.getCart_ids().isEmpty()) {
			errorList.add(new FieldError(FieldCode.SHOPPING_CART_ID.getCode(),
					ErrorMessage.SHOPPING_CART_ID_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(confirmRequest.getPaymen_type_code())) {

		}

		if (confirmRequest.getState_id() == null || confirmRequest.getState_id() <= 0) {
			errorList.add(new FieldError(FieldCode.STATE_ID.getCode(), ErrorMessage.STATE_ID_REQUIRED.getMessage()));
		}

		if (confirmRequest.getTownship_id() == null || confirmRequest.getTownship_id() <= 0) {
			errorList.add(new FieldError(FieldCode.TOWNSHIP.getCode(), ErrorMessage.TOWNSHIP_ID_REQUIRED.getMessage()));
		}

		if (CommonUtil.isEmpty(confirmRequest.getDetail_address())) {
			errorList.add(new FieldError(FieldCode.PAYMENT_TYPE_CODE.getCode(),
					ErrorMessage.PAYMENT_TYPE_CODE_REQUIRED.getMessage()));
		}
	}

}
