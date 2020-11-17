package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finder.innox.core.domain.OrderItem;

public class OrderItemDTO implements Serializable {

	private static final long serialVersionUID = -672082614637528587L;

	private long seq;

	private OrderDTO orderDTO;

	private ProductDTO productDTO;

	private ColorDTO colorDTO;

	private SizeDTO sizeDTO;

	private BigDecimal unitPrice;

	private int quantity;

	private BigDecimal subTotal;

	public OrderItemDTO() {
		super();
	}

	public OrderItemDTO(OrderItem orderItem) {
		if (orderItem != null) {
			this.seq = orderItem.getSeq();
			this.orderDTO = new OrderDTO(orderItem.getOrder());
			this.productDTO = new ProductDTO(orderItem.getProduct());
			this.colorDTO = new ColorDTO(orderItem.getColor());
			this.sizeDTO = new SizeDTO(orderItem.getSize());
			this.unitPrice = orderItem.getUnitPrice();
			this.quantity = orderItem.getQuantity() == null ? 0 : orderItem.getQuantity();
			this.subTotal = orderItem.getSubTotal();

		}
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public OrderDTO getOrderDTO() {
		return orderDTO;
	}

	public void setOrderDTO(OrderDTO orderDTO) {
		this.orderDTO = orderDTO;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal;
	}

	public ColorDTO getColorDTO() {
		return colorDTO;
	}

	public void setColorDTO(ColorDTO colorDTO) {
		this.colorDTO = colorDTO;
	}

	public SizeDTO getSizeDTO() {
		return sizeDTO;
	}

	public void setSizeDTO(SizeDTO sizeDTO) {
		this.sizeDTO = sizeDTO;
	}

}
