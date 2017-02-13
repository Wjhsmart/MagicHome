package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Customer;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO {

	@Override
	public Pager<Customer> queryByPage(int pageNo, int pageSize) {
		Pager<Customer> pager = new Pager<Customer>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_customer where id not in(select top " + top1 + "id from t_customer) order by created_time desc");
			ResultSet rs = ps.executeQuery();
			List<Customer> customers = new ArrayList<Customer>();
			while (rs.next()) {
				Customer customer = new Customer();
				SetDAOUtil.setCustomer(customer, rs);
				customers.add(customer);
			}
			pager.setResult(customers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public Customer queryCustomer(String email) {
		Customer customer = new Customer();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_customer where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = SetDAOUtil.setCustomer(customer, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return customer;
	}

	@Override
	public void updateCustomer(Customer customer) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_customer set password = ?, name = ?, phone = ?, plot_name = ?, address = ?, status = ?, last_login_time = ?, login_count = ? where email = ?");
			ps.setString(1, customer.getPassword());
			ps.setString(2, customer.getName());
			ps.setString(3, customer.getPhone());
			ps.setString(4, customer.getPlot_name());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getStatus());
			ps.setDate(7, new Date(customer.getLast_login_time().getTime()));
			ps.setInt(8, customer.getLogin_count());
			ps.setString(9, customer.getE_mail());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public List<Customer> conditionQueryCustomer(String email, String name) {
		List<Customer> customers = new ArrayList<Customer>();
		getConn();
		PreparedStatement ps = null;
		try {
			if (email != null && !email.equals("") && name != null && !name.equals("")) {
				ps = conn.prepareStatement("select * from t_customer where email like(?) and name like(?)");
				ps.setString(1, "%" + email + "%");
				ps.setString(2, "%" + name + "%");
				
			} else {
				if (email != null && !email.equals("")) {
					ps = conn.prepareStatement("select * from t_customer where email like(?)");
					ps.setString(1, "%" + email + "%");
				} else if (name != null && !name.equals("")) {
					ps = conn.prepareStatement("select * from t_customer where name like(?)");
					ps.setString(1, "%" + name + "%");
				}
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				SetDAOUtil.setCustomer(customer, rs);
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		close();
		return customers;
	}

	@Override
	public void addCustomer(Customer customer) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_customer(email, password, name, phone, plot_name, address, created_time, last_login_time, login_count, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, customer.getE_mail());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getName());
			ps.setString(4, customer.getPhone());
			ps.setString(5, customer.getPlot_name());
			ps.setString(6, customer.getAddress());
			ps.setDate(7, new Date(customer.getCreated_time().getTime()));
			ps.setDate(8, new Date(customer.getLast_login_time().getTime()));
			ps.setInt(9, customer.getLogin_count());
			ps.setString(10, customer.getStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Customer queryCustomer(String email, String password) {
		getConn();
		Customer customer = new Customer();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_customer where email = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = SetDAOUtil.setCustomer(customer, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return customer;
	}

}
