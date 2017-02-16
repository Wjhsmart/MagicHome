package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * 设计师收藏表，继承至收藏类
 * @author Administrator
 *
 */
public class DesignerCol extends CollectBean {
	
	private String designer_id; // 设计师编号
	private Designer designer; // 设计师

	public Designer getDesigner() {
		return designer;
	}

	public void setDesigner(Designer designer) {
		this.designer = designer;
	}

	public String getDesigner_id() {
		return designer_id;
	}

	public void setDesigner_id(String designer_id) {
		this.designer_id = designer_id;
	}

	@Override
	public String toString() {
		return "DesignerCol [designer_id=" + designer_id + "]";
	}

}
