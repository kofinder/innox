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
@Table(name = "custom_item_layout")
public class CustomItemLayout extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -1113219071929797029L;

	@ManyToOne
	@JoinColumn(name = "custom_item_id")
	private CustomItem customItem;

	@Column(name = "layout_name")
	private String layoutName;

	@Column(name = "layout_price")
	private BigDecimal layoutPrice;

	@Column(name = "layout_image_path")
	private String layoutImage;

	@Column(name = "created_by_id")
	private User user;

	public CustomItem getCustomItem() {
		return customItem;
	}

	public void setCustomItem(CustomItem customItem) {
		this.customItem = customItem;
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public BigDecimal getLayoutPrice() {
		return layoutPrice;
	}

	public void setLayoutPrice(BigDecimal layoutPrice) {
		this.layoutPrice = layoutPrice;
	}

	public String getLayoutImage() {
		return layoutImage;
	}

	public void setLayoutImage(String layoutImage) {
		this.layoutImage = layoutImage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
