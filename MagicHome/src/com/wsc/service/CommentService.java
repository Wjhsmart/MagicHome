package com.wsc.service;

import java.util.List;

import com.wsc.bean.Comment;

public interface CommentService {

	/**
	 * ��ѯ�û����۱��ǰ��������
	 * @return
	 */
	public List<Comment> queryComentByTop3();
	
	/**
	 * ����Comment���������������
	 * @param comment
	 */
	public void addComment(Comment comment);
}
