package com.wsc.dao;

import java.util.List;

import com.wsc.bean.Comment;

public interface CommentDAO {

	/**
	 * 查询用户评论表的前三条数据
	 * @return
	 */
	public List<Comment> queryComentByTop3();
	
	/**
	 * 根据Comment对象添加评论内容
	 * @param comment
	 */
	public void addComment(Comment comment);
}
