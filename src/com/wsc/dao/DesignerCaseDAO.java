package com.wsc.dao;

import java.util.List;

import com.wsc.bean.DesignerCase;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.parentbean.Pager;

public interface DesignerCaseDAO {

	/**
	 * ��ҳ��ѯ�������ʦ����
	 * @return
	 */
	public Pager<DesignerCase> queryByPage(int pageNo, int pageSize);
	
	/**
	 * ��ѯǰ1�����ʦ����
	 * @return
	 */
	public DesignerCase queryDesignerCase();
	
	/**
	 * ����id��ѯǰ4��ָ�����ʦ����
	 * @return
	 */
	public List<DesignerCase> queryDesignerCaseByTop4(String designer_id);
	
	/**
	 * ����id��ѯ���ʦ��������ϸ��Ϣ
	 * @param id
	 * @return
	 */
	public DesignerCase queryDesignerCaseById(String id);
	
	/**
	 * �������ʦid��ѯ�����ʦ�����а�������ҳ��ѯ
	 * @param designer_id
	 * @return
	 */
	public Pager<DesignerCase> queryDesignerCaseByDesignerId(int pageNo, int pageSize, String designer_id);
	
	/**
	 * �������ʦid��ѯ�����ʦ�ж��ٰ���
	 * @param designer_id
	 * @return
	 */
	public int queryCountByDesignerId(String designer_id);
	

	/**
	 * ����DesignerCase�������װ�ް���
	 */
	public void addDesignerCase(DesignerCase designerCase);
	
	/**
	 * ���ݴ��ݹ���company����������ݿ�
	 */
	public void updateCase(DesignerCase designerCase);
	
	/**
	 * �����û���id��ѯ���ʦ�����ղر�
	 * @param product_id
	 * @return
	 */
	public List<DesignerCaseCol> queryDesignerCaseColByProductId(String customer_id);
	
	
}
