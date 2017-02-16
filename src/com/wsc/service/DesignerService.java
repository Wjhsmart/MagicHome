package com.wsc.service;

import java.util.List;

import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCol;
import com.wsc.parentbean.Pager;

public interface DesignerService {

	/**
	 * 分页查询所有指定是否已经审核的公司
	 * @return
	 */
	public Pager<Designer> queryByPage(int pageNo, int pageSize, String checked);
	
	/**
	 * 根据审核状态查询设计师的个数
	 * @param checked
	 * @return
	 */
	public int queryCountByDesignerChecked(String checked);
	
	
	/**
	 * 根据传递过来Designer对象更新数据库
	 */
	public void updateDesigner(Designer designer);

	/**
	 * 查询设计师前4条数据
	 * @return
	 */
	public List<Designer> queryDesignerByTop4();
	
	/**
	 * 根据用户的id查询设计师收藏表
	 * @param product_id
	 * @return
	 */
	public List<DesignerCol> queryDesignerColByCustomerId(String customer_id);
	
	/**
	 * 根据id查询设计师的详细信息
	 * @param id
	 * @return
	 */
	public Designer queryDesignerById(String id);
	
	/**
	 * 根据designer对象添加设计师
	 * @param designer
	 */
	public void addDesigner(Designer designer);
	
	/**
	 * 根据邮箱密码查询当前设计师的信息
	 * @param email
	 * @param pwd
	 * @return
	 */
	public Designer queryCurr(String email, String pwd);
	
	/**
	 * 根据邮箱查找设计师
	 * @param email
	 * @return
	 */
	public Designer queryDesignerByEmail(String email);
}
