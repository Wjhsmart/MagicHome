package com.wsc.service;

import java.util.List;

import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCol;
import com.wsc.parentbean.Pager;

public interface DesignerService {

	/**
	 * ��ҳ��ѯ����ָ���Ƿ��Ѿ���˵Ĺ�˾
	 * @return
	 */
	public Pager<Designer> queryByPage(int pageNo, int pageSize, String checked);
	
	/**
	 * �������״̬��ѯ���ʦ�ĸ���
	 * @param checked
	 * @return
	 */
	public int queryCountByDesignerChecked(String checked);
	
	
	/**
	 * ���ݴ��ݹ���Designer����������ݿ�
	 */
	public void updateDesigner(Designer designer);

	/**
	 * ��ѯ���ʦǰ4������
	 * @return
	 */
	public List<Designer> queryDesignerByTop4();
	
	/**
	 * �����û���id��ѯ���ʦ�ղر�
	 * @param product_id
	 * @return
	 */
	public List<DesignerCol> queryDesignerColByCustomerId(String customer_id);
	
	/**
	 * ����id��ѯ���ʦ����ϸ��Ϣ
	 * @param id
	 * @return
	 */
	public Designer queryDesignerById(String id);
	
	/**
	 * ����designer����������ʦ
	 * @param designer
	 */
	public void addDesigner(Designer designer);
	
	/**
	 * �������������ѯ��ǰ���ʦ����Ϣ
	 * @param email
	 * @param pwd
	 * @return
	 */
	public Designer queryCurr(String email, String pwd);
	
	/**
	 * ��������������ʦ
	 * @param email
	 * @return
	 */
	public Designer queryDesignerByEmail(String email);
}
