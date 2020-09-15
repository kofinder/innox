package com.finder.innox.core.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.finder.innox.core.domain.ShoppingCart;

public class ShoppingCartDTO implements Serializable {

	private static final long serialVersionUID = -6540442009746140847L;

	private long seq;

	private UserDTO customer;

	private ProductDTO productDTO;

	private ColorDTO colorDTO;

	private SizeDTO size;

	private int quantity;

	private boolean isCustomProduct;

	private BigDecimal totalAmount;

	private String totalAmountText;

	private BigDecimal productSubTotal;

	public ShoppingCartDTO() {
		super();
	}

	public ShoppingCartDTO(ShoppingCart shoppingCart) {
		super();
		this.seq = shoppingCart.getSeq();
		this.customer = new UserDTO(shoppingCart.getCustomer());
		this.productDTO = new ProductDTO(shoppingCart.getProduct());
		this.colorDTO = new ColorDTO(shoppingCart.getColor());
		this.size = new SizeDTO(shoppingCart.getSize());
		this.quantity = shoppingCart.getQuantity() == null ? 0 : shoppingCart.getQuantity();
		this.isCustomProduct = shoppingCart.getIsCustomProduct() == null ? false : shoppingCart.getIsCustomProduct();
	}

	public long getSeq() {
		return seq;
	}

	public void setSeq(long seq) {
		this.seq = seq;
	}

	public UserDTO getCustomer() {
		return customer;
	}

	public void setCustomer(UserDTO customer) {
		this.customer = customer;
	}

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public ColorDTO getColorDTO() {
		return colorDTO;
	}

	public void setColorDTO(ColorDTO colorDTO) {
		this.colorDTO = colorDTO;
	}

	public SizeDTO getSize() {
		return size;
	}

	public void setSize(SizeDTO size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isCustomProduct() {
		return isCustomProduct;
	}

	public void setCustomProduct(boolean isCustomProduct) {
		this.isCustomProduct = isCustomProduct;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTotalAmountText() {
		return totalAmountText;
	}

	public void setTotalAmountText(String totalAmountText) {
		this.totalAmountText = totalAmountText;
	}

	public BigDecimal getProductSubTotal() {
		return productSubTotal;
	}

	public void setProductSubTotal(BigDecimal productSubTotal) {
		this.productSubTotal = productSubTotal;
	}

}
