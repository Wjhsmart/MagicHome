package com.wsc.bean;

import com.wsc.parentbean.CollectBean;

/**
 * ���ʦ�����ղر� �̳����ղ���
 * @author Administrator
 *
 */
public class DesignerCaseCol extends CollectBean {
	
	private String case_id; // ���ʦ�������
	private DesignerCase designerCase; // ���ʦ����

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
