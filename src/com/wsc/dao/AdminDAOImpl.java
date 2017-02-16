package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Admin;
import com.wsc.parentbean.Pager;

public class AdminDAOImpl extends BaseDAO implements AdminDAO {
	
	private Admin setAdmin(Admin admin, ResultSet rs) throws SQLException {
		admin.setId(rs.getString("id"));
		admin.setE_mail(rs.getString("email"));
		admin.setPassword(rs.getString("password"));
		admin.setName(rs.getString("name"));
		admin.setPhone(rs.getString("phone"));
		admin.setRole(rs.getString("role"));
		admin.setCreated_time(rs.getDate("created_time"));
		admin.setLast_login_time(rs.getDate("last_login_time"));
		admin.setLogin_count(rs.getInt("login_count"));
		admin.setStatus(rs.getString("status"));
		return admin;
	}

	@Override
	public List<Admin> queryAll() {
		getConn();
		List<Admin> admins = new ArrayList<Admin>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_admin order by created_time desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin();
				setAdmin(admin, rs);
				admins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return admins;
	}

	@Override
	public Admin queryCurr(String name, String pwd) {
		getConn();
		Admin admin = new Admin();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_admin where email = ? and password = ?");
			ps.setString(1, name);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				setAdmin(admin, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return admin;
	}

	@Override
	public void updateAdmin(Admin admin) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_admin set password = ?, name = ?, phone = ?, status = ?, last_login_time = ?, login_count = ? where email = ?");
			ps.setString(1, admin.getPassword());
			ps.setString(2, admin.getName());
			ps.setString(3, admin.getPhone());
			ps.setString(4, admin.getStatus());
			ps.setDate(5, new Date(admin.getLast_login_time().getTime()));
			ps.setInt(6, admin.getLogin_count());
			ps.setString(7, admin.getE_mail());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public void addAdmin(Admin admin) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_admin(email, password, name, phone, role, created_time, last_login_time, login_count, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, admin.getE_mail());
			ps.setString(2, admin.getPassword());
			ps.setString(3, admin.getName());
			ps.setString(4, admin.getPhone());
			ps.setString(5, admin.getRole());
			ps.setDate(6, new Date(admin.getCreated_time().getTime()));
			ps.setDate(7, new Date(admin.getLast_login_time().getTime()));
			ps.setInt(8, admin.getLogin_count());
			ps.setString(9, admin.getStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Pager<Admin> queryByPage(int pageNo, int pageSize) {
		Pager<Admin> pager = new Pager<Admin>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_admin where id not in(select top " + top1 + "id from t_admin) order by created_time desc");
			ResultSet rs = ps.executeQuery();
			List<Admin> admins = new ArrayList<Admin>();
			while (rs.next()) {
				Admin admin = new Admin();
				setAdmin(admin, rs);
				admins.add(admin);
			}
			pager.setResult(admins);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public Admin queryAdmin(String email) {
		Admin admin = new Admin();
		getConn();
		
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_admin where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				setAdmin(admin, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return admin;
	}

	@Override
	public List<Admin> conditionQueryAdmin(String email, String name) {
		List<Admin> admins = new ArrayList<Admin>();
		getConn();
		PreparedStatement ps = null;
		try {
			if (email != null && !email.equals("") && name != null && !name.equals("")) {
				ps = conn.prepareStatement("select * from t_admin where email like(?) and name like(?)");
				ps.setString(1, "%" + email + "%");
				ps.setString(2, "%" + name + "%");
				
			} else {
				if (email != null && !email.equals("")) {
					ps = conn.prepareStatement("select * from t_admin where email like(?)");
					ps.setString(1, "%" + email + "%");
				} else if (name != null && !name.equals("")) {
					ps = conn.prepareStatement("select * from t_admin where name like(?)");
					ps.setString(1, "%" + name + "%");
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Admin admin = new Admin();
				setAdmin(admin, rs);
				admins.add(admin);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return admins;
	}

}
