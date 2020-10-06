package com.finder.innox.webportal;

import java.util.Date;
import java.util.List;

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

import com.finder.innox.core.dto.OrderDTO;
import com.finder.innox.core.dto.OrderItemDTO;
import com.finder.innox.core.services.OrderItemService;
import com.finder.innox.core.services.OrderService;
import com.finder.innox.utils.CommonConstant;
import com.finder.innox.utils.CommonUtil;
import com.finder.innox.utils.MessageEnum;
import com.finder.innox.utils.OrderStatusEnum;
import com.finder.innox.utils.PaymentStatusEnum;

@Controller
public class OrderController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderItemService orderItemService;

	@GetMapping("/order_search")
	public String searchOrderHistoryGet(Model model, HttpServletRequest request) {
		logger.info("searchOrderHistoryGet() >>> Start");
		model.addAttribute("orderStatusList", OrderStatusEnum.values());
		model.addAttribute("paymentStatusList", PaymentStatusEnum.values());

		OrderDTO orderDTO = (OrderDTO) model.asMap().get("orderDTO");

		if (orderDTO == null) {
			orderDTO = new OrderDTO();
			String currentDate = CommonUtil.changeDateToString(CommonConstant.STD_DATE_FORMAT_MM_dd_yyyy, new Date());
			orderDTO.setFromDate(currentDate);
			orderDTO.setToDate(currentDate);
		}

		model.addAttribute("orderDTO", orderDTO);
		model.addAttribute("orderHistoryList", orderService.searchOrderHistoryList(orderDTO));
		logger.info("searchOrderHistoryGet() >>> End");
		return "order_search";
	}

	@PostMapping("/order_search")
	public String searchOrderHistoryPost(@ModelAttribute OrderDTO orderDTO, RedirectAttributes attributes,
			HttpServletRequest request) {

		try {
			attributes.addFlashAttribute("orderStatusList", OrderStatusEnum.values());
			attributes.addFlashAttribute("paymentStatusList", PaymentStatusEnum.values());
			attributes.addFlashAttribute("orderDTO", orderDTO);
			attributes.addFlashAttribute("orderHistoryList", orderService.searchOrderHistoryList(orderDTO));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:order_search.html";
	}

	@GetMapping("/order_detail")
	public String orderDetailGet(@RequestParam(name = "orderId") Long orderId, Model model,
			HttpServletRequest request) {
		try {
			OrderDTO orderDTO = orderService.getOrderDTOById(orderId);
			List<OrderItemDTO> orderItemList = orderItemService.getOrderItemListByOrderId(orderId);

			model.addAttribute("orderDTO", orderDTO);
			model.addAttribute("orderItemList", orderItemList);
			model.addAttribute("orderStatusList", OrderStatusEnum.values());
			model.addAttribute("paymentStatusList", PaymentStatusEnum.values());

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("orderDetailGet() >> " + e.getMessage(), e);
		}
		return "order_detail";
	}

	@PostMapping("/order_detail")
	public String orderDetailPost(@ModelAttribute OrderDTO orderDTO, RedirectAttributes attributes,
			HttpServletRequest request) {
		try {
			OrderDTO orderDto = orderService.orderManage(orderDTO);
			if (orderDto != null) {
				attributes.addFlashAttribute("message", MessageEnum.UPDATE_SUCCESS.getDesc());
			} else {
				attributes.addFlashAttribute("errorMsg", MessageEnum.UPDATE_FAILED.getDesc());
			}

			attributes.addAttribute("orderId", orderDTO.getSeq());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("orderDetailPost() >> " + e.getMessage(), e);
		}
		return "redirect:order_detail.html";
	}

}
