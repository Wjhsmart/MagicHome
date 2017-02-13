package com.wsc.dao;

import java.util.List;

import com.wsc.bean.DesignerCase;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.parentbean.Pager;

public interface DesignerCaseDAO {

	/**
	 * 分页查询所有设计师案例
	 * @return
	 */
	public Pager<DesignerCase> queryByPage(int pageNo, int pageSize);
	
	/**
	 * 查询前1条设计师案例
	 * @return
	 */
	public DesignerCase queryDesignerCase();
	
	/**
	 * 根据id查询前4条指定设计师案例
	 * @return
	 */
	public List<DesignerCase> queryDesignerCaseByTop4(String designer_id);
	
	/**
	 * 根据id查询设计师案例的详细信息
	 * @param id
	 * @return
	 */
	public DesignerCase queryDesignerCaseById(String id);
	
	/**
	 * 根据设计师id查询此设计师的所有案例，分页查询
	 * @param designer_id
	 * @return
	 */
	public Pager<DesignerCase> queryDesignerCaseByDesignerId(int pageNo, int pageSize, String designer_id);
	
	/**
	 * 根据设计师id查询该设计师有多少案例
	 * @param designer_id
	 * @return
	 */
	public int queryCountByDesignerId(String designer_id);
	

	/**
	 * 根据DesignerCase对象添加装修案例
	 */
	public void addDesignerCase(DesignerCase designerCase);
	
	/**
	 * 根据传递过来company对象更新数据库
	 */
	public void updateCase(DesignerCase designerCase);
	
	/**
	 * 根据用户的id查询设计师案例收藏表
	 * @param product_id
	 * @return
	 */
	public List<DesignerCaseCol> queryDesignerCaseColByProductId(String customer_id);
	
	
}
