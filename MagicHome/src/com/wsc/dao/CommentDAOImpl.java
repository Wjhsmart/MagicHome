package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Comment;
import com.wsc.common.SetDAOUtil;

public class CommentDAOImpl extends BaseDAO implements CommentDAO {

	@Override
	public List<Comment> queryComentByTop3() {
		List<Comment> comments = new ArrayList<Comment>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 3 * from t_comment order by created_time desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Comment comment = new Comment();
				comment = SetDAOUtil.setComment(comment, rs, conn);
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return comments;
	}

	@Override
	public void addComment(Comment comment) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_comment(customer_id, content, created_time) values(?, ?, ?)");
			ps.setString(1, comment.getCustomer_id());
			ps.setString(2, comment.getContent());
			ps.setDate(3, new Date(comment.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	
}
