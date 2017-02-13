package com.wsc.service;

import java.sql.Date;

public interface CommonService {

	/**
	 * 根据传递进来的邮箱查询指定数据表中是否存在该数据，存在返回true，不存在返回false
	 * @param email
	 * @param tableName
	 * @return
	 */
	public boolean queryEmail(String email, String tableName);
	
	/**
	 * 查询传递进来的表有多少条数据
	 * @return
	 */
	public int queryCount(String tableName);
	
	/**
	 * 根据邮箱删除指定表中的数据
	 * @param email
	 */
	public void deleteData(String email, String tableName);
	
	/**
	 * 根据状态来查询数据库，查询已审核和为审核的指定表名的个数
	 * @param status
	 * @return
	 */
	public int queryCountByChecked(String checked, String tableName);
	
	/**
	 * 根据传递进来表名，字段名，向表中添加用户id，收藏的id和收藏的时间
	 * @param tableName
	 * @param field
	 * @param customer_id
	 * @param col_id
	 * @param created_time
	 */
	public void colData(String tableName, String field, String customer_id, String col_id, Date created_time);
	
	/**
	 * 根据id查询指定表名和指定字段的个数
	 * @param supply_id
	 * @return
	 */
	public int queryCountByTableId(String id, String field, String tableName);
	
}
