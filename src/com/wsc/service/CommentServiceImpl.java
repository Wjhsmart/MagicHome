package com.wsc.service;

import java.util.List;

import com.wsc.bean.Comment;
import com.wsc.dao.CommentDAO;
import com.wsc.dao.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {

	private CommentDAO commentDAO;
	
	public CommentServiceImpl() {
		commentDAO = new CommentDAOImpl();
	}
	
	@Override
	public List<Comment> queryComentByTop3() {
		return commentDAO.queryComentByTop3();
	}

	@Override
	public void addComment(Comment comment) {
		commentDAO.addComment(comment);
	}

}
