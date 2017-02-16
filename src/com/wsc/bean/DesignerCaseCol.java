package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * 设计师案例收藏表， 继承至收藏类
 * @author Administrator
 *
 */
public class DesignerCaseCol extends CollectBean {
	
	private String case_id; // 设计师案例编号
	private DesignerCase designerCase; // 设计师案例

	public DesignerCase getDesignerCase() {
		return designerCase;
	}

	public void setDesignerCase(DesignerCase designerCase) {
		this.designerCase = designerCase;
	}

	public String getCase_id() {
		return case_id;
	}

	public void setCase_id(String case_id) {
		this.case_id = case_id;
	}


}
