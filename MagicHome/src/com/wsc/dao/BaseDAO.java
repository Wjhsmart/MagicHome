package com.wsc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDAO {

	protected Connection conn;
	
	public void getConn() {
		try {
			Context context = new InitialContext();
			Object obj = context.lookup("java:comp/env/wsc/sqlserver");
			if (obj instanceof DataSource) {
				DataSource dataSource = (DataSource) obj;
				conn = dataSource.getConnection();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
