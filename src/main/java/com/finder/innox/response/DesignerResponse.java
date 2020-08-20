package com.finder.innox.response;

import java.io.Serializable;

public class DesignerResponse implements Serializable {

	private static final long serialVersionUID = 3566377528552541004L;

	private long designer_id;

	private String designer_name;

	private String designer_avatar;

	public long getDesigner_id() {
		return designer_id;
	}

	public void setDesigner_id(long designer_id) {
		this.designer_id = designer_id;
	}

	public String getDesigner_name() {
		return designer_name;
	}

	public void setDesigner_name(String designer_name) {
		this.designer_name = designer_name;
	}

	public String getDesigner_avatar() {
		return designer_avatar;
	}

	public void setDesigner_avatar(String designer_avatar) {
		this.designer_avatar = designer_avatar;
	}

}
