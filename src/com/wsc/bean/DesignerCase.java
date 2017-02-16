package com.wsc.bean;

import com.wsc.parentbean.CaseBean;
/**
 * ���ʦ������ �̳���������
 * @author Administrator
 *
 */
public class DesignerCase extends CaseBean {
	
	private String designer_id; // ���ʦ���
	private Designer designer; // ���ʦ
	private boolean collected; // �Ƿ��ղ�

	public boolean isCollected() {
		return collected;
	}

	public void setCollected(boolean collected) {
		this.collected = collected;
	}

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
		return "DesignerCase [designer_id=" + designer_id + "]";
	}

}
