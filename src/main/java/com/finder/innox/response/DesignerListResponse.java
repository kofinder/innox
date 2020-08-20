package com.finder.innox.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DesignerListResponse implements Serializable {

	private static final long serialVersionUID = -5307486619558107296L;

	private List<DesignerResponse> designers = new ArrayList<DesignerResponse>();

	public List<DesignerResponse> getDesigners() {
		return designers;
	}

	public void setDesigners(List<DesignerResponse> designers) {
		this.designers = designers;
	}

}
