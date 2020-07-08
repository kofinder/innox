package com.finder.ecoshop.core.domain;

import java.io.Serializable;

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
@Table(name = "custom_item_size")
public class CustomItemSize extends CommonEntity implements Serializable {

	private static final long serialVersionUID = -4682780832284402490L;

	@ManyToOne
	@JoinColumn(name = "custom_item_id")
	private CustomItem customItem;

	@ManyToOne
	@JoinColumn(name = "size_id")
	private Size size;

	@ManyToOne
	@JoinColumn(name = "created_by_id")
	private User user;

	public CustomItem getCustomItem() {
		return customItem;
	}

	public void setCustomItem(CustomItem customItem) {
		this.customItem = customItem;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
