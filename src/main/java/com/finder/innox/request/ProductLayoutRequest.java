package com.finder.innox.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductLayoutRequest implements Serializable {

	private static final long serialVersionUID = -3846296678462401820L;

	private long product_layout_id;

	private String created_image;

	private List<Long> product_artworks = new ArrayList<Long>();

	private List<Long> product_fonts = new ArrayList<Long>();

	public long getProduct_layout_id() {
		return product_layout_id;
	}

	public void setProduct_layout_id(long product_layout_id) {
		this.product_layout_id = product_layout_id;
	}

	public String getCreated_image() {
		return created_image;
	}

	public void setCreated_image(String created_image) {
		this.created_image = created_image;
	}

	public List<Long> getProduct_artworks() {
		return product_artworks;
	}

	public void setProduct_artworks(List<Long> product_artworks) {
		this.product_artworks = product_artworks;
	}

	public List<Long> getProduct_fonts() {
		return product_fonts;
	}

	public void setProduct_fonts(List<Long> product_fonts) {
		this.product_fonts = product_fonts;
	}

}
