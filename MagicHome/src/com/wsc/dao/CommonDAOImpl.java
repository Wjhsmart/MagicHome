package com.wsc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class CommonDAOImpl extends BaseDAO implements CommonDAO {

	@Override
	public int queryCount(String tableName) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from " + tableName + " where id not in(select app_id from t_appointment_view)");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	
	@Override
	public int queryCount(String tableName, String customerId) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from " + tableName + " where customer_id = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	
	@Override
	public void deleteDataEmail(String email, String tableName) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from " + tableName +" where email = ?");
			ps.setString(1, email);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();	
	}

	@Override
	public void deleteDataId(String id, String tableName) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete from " + tableName +" where id = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();	
	}

	@Override
	public boolean queryEmail(String email, String tableName) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from " + tableName +" where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();	
		return false;
	}

	@Override
	public int queryCountByChecked(String checked, String tableName) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from " + tableName + " where checked = ?");
			ps.setString(1, checked);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}

	@Override
	public void colData(String tableName, String field, String customer_id, String col_id, Date created_time) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into " + tableName +"(customer_id, " + field + ", created_time) values(?, ?, ?)");
			ps.setString(1, customer_id);
			ps.setString(2, col_id);
			ps.setDate(3, created_time);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public int queryCountByTableId(String id, String field, String tableName) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from " + tableName + " where " + field + " = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}


}
