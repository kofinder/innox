package com.finder.ecoshop.core.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Kywee Zay
 *
 */
@Entity
@Table(name = "custom_item")
public class CustomItem extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -4579336299986944357L;

	@ManyToOne
	@JoinColumn(name = "custom_product_id")
	private CustomProduct customProduct;

	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@Column(name = "item_code")
	private String itemCode;

	@Column(name = "item_name")
	private String itemName;

	@Column(name = "item_price")
	private BigDecimal itemPrice;

	public CustomProduct getCustomProduct() {
		return customProduct;
	}

	public void setCustomProduct(CustomProduct customProduct) {
		this.customProduct = customProduct;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

}
