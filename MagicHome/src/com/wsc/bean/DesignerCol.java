package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * ���ʦ�ղر��̳����ղ���
 * @author Administrator
 *
 */
public class DesignerCol extends CollectBean {
	
	private String designer_id; // ���ʦ���
	private Designer designer; // ���ʦ

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
