package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Company;
import com.wsc.bean.Product;
import com.wsc.bean.Supply;
import com.wsc.bean.SupplyActivity;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class SupplyDAOImpl extends BaseDAO implements SupplyDAO {

	@Override
	public Pager<Supply> queryByPage(int pageNo, int pageSize, String checked) {
		Pager<Supply> pager = new Pager<Supply>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_supply where id not in(select top " + top1 + "id from t_supply) and checked = ? order by created_time desc");
			ps.setString(1, checked);
			ResultSet rs = ps.executeQuery();
			List<Supply> supplys = new ArrayList<Supply>();
			while (rs.next()) {
				Supply supply = new Supply();
				SetDAOUtil.setSupply(supply, rs);
				supplys.add(supply);
			}
			pager.setResult(supplys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public List<Supply> querySupAll() {
		getConn();
		List<Supply> supplys = new ArrayList<Supply>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_supply");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Supply supply = new Supply();
				SetDAOUtil.setSupply(supply, rs);
				supplys.add(supply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return supplys;
	}

	@Override
	public Supply queryCurr(String email, String pwd) {
		getConn();
		Supply supply = new Supply();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_supply where email = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				supply = SetDAOUtil.setSupply(supply, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return supply;
	}

	@Override
	public void addSupply(Supply supply) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_supply(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			ps.setString(1, supply.getE_mail());
			ps.setString(2, supply.getPassword());
			ps.setString(3, supply.getName());
			ps.setString(4, supply.getPrincipal());
			ps.setString(5, supply.getLogo());
			ps.setString(6, supply.getAddress());
			ps.setString(7, supply.getPhone());
			ps.setString(8, supply.getTel());
			ps.setDate(9, new Date(supply.getOpen_date().getTime()));
			ps.setFloat(10, supply.getLongitude());
			ps.setFloat(11, supply.getLatitude());
			ps.setString(12, supply.getDes());
			ps.setDate(13, new Date(supply.getCreated_time().getTime()));
			ps.setString(14, supply.getChecked());
			ps.setDate(15, new Date(supply.getChecked_time().getTime()));
			ps.setDate(16, new Date(supply.getLast_login_time().getTime()));
			ps.setInt(17, supply.getLogin_count());
			ps.setString(18, supply.getStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public void updateSupply(Supply supply) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_supply set password = ?, name = ?, principal = ?, logo = ?, address = ?, phone = ?, tel = ?, open_date = ?, longitude = ?, latitude = ?, des = ?, created_time = ?, checked = ?, checked_time = ?, last_login_time = ?, login_count = ?, status = ? where email = ? ");
			ps.setString(1, supply.getPassword());
			ps.setString(2, supply.getName());
			ps.setString(3, supply.getPrincipal());
			ps.setString(4, supply.getLogo());
			ps.setString(5, supply.getAddress());
			ps.setString(6, supply.getPhone());
			ps.setString(7, supply.getTel());
			ps.setDate(8, new Date(supply.getOpen_date().getTime()));
			ps.setFloat(9, supply.getLongitude());
			ps.setFloat(10, supply.getLatitude());
			ps.setString(11, supply.getDes());
			ps.setDate(12, new Date(supply.getCreated_time().getTime()));
			ps.setString(13, supply.getChecked());
			ps.setDate(14, new Date(supply.getChecked_time().getTime()));
			ps.setDate(15, new Date(supply.getLast_login_time().getTime()));
			ps.setInt(16, supply.getLogin_count());
			ps.setString(17, supply.getStatus());
			ps.setString(18, supply.getE_mail());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	@Override
	public List<Product> queryProAll() {
		getConn();
		List<Product> products = new ArrayList<Product>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_product");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				SetDAOUtil.setProduct(product, rs, conn);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return products;
	}

	@Override
	public Pager<Product> queryByPage(int pageNo, int pageSize) {
		Pager<Product> pager = new Pager<Product>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_product where id not in(select top " + top1 + " id from t_product) order by created_time desc");
			ResultSet rs = ps.executeQuery();
			List<Product> products = new ArrayList<Product>();
			while (rs.next()) {
				Product product = new Product();
				SetDAOUtil.setProduct(product, rs, conn);
				products.add(product);
			}
			pager.setResult(products);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public void addProduct(Product product) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_product(supply_id, name, price, sale_price, image, des, created_time, status) values(?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, product.getSupply_id());
			ps.setString(2, product.getName());
			ps.setFloat(3, product.getPrice());
			ps.setFloat(4, product.getSale_price());
			ps.setString(5, product.getImage());
			ps.setString(6, product.getDes());
			ps.setDate(7, new Date(product.getCreated_time().getTime()));
			ps.setString(8, product.getStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Product queryProById(String id) {
		getConn();
		Product product = new Product();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_product where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				SetDAOUtil.setProduct(product, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return product;
	}

	@Override
	public void deleteProById(String id) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete t_product where id = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	
	@Override
	public List<SupplyActivity> queryActAll() {
		getConn();
		List<SupplyActivity> activitys = new ArrayList<SupplyActivity>();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_supply_activity");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SupplyActivity activity = new SupplyActivity();
				SetDAOUtil.setSupplyActivity(activity, rs, conn);
				activitys.add(activity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return activitys;
	}

	@Override
	public Pager<SupplyActivity> queryByActivityPage(int pageNo, int pageSize) {
		Pager<SupplyActivity> pager = new Pager<SupplyActivity>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_supply_activity where id not in(select top " + top1 + " id from t_supply_activity) order by created_time desc");
			ResultSet rs = ps.executeQuery();
			List<SupplyActivity> activitys = new ArrayList<SupplyActivity>();
			while (rs.next()) {
				SupplyActivity activity = new SupplyActivity();
				SetDAOUtil.setSupplyActivity(activity, rs, conn);
				activitys.add(activity);
			}
			pager.setResult(activitys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}
	
	@Override
	public void addActivity(SupplyActivity activity) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_supply_activity(supply_id, name, image, des, created_time) values(?, ?, ?, ?, ?)");
			ps.setString(1, activity.getSupply_id());
			ps.setString(2, activity.getName());
			ps.setString(3, activity.getImage());
			ps.setString(4, activity.getDes());
			ps.setDate(5, new Date(activity.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		
	}

	@Override
	public void deleteActById(String id) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("delete t_supply_activity where id = ?");
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public SupplyActivity queryActById(String id) {
		getConn();
		SupplyActivity activity = new SupplyActivity();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_supply_activity where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				SetDAOUtil.setSupplyActivity(activity, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return activity;
	}

	@Override
	public void updateAct(SupplyActivity activity) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_supply_activity set supply_id = ?, name = ?, image = ?, des = ?, created_time = ? where id = ?");
			ps.setString(1, activity.getSupply_id());
			ps.setString(2, activity.getName());
			ps.setString(3, activity.getImage());
			ps.setString(4, activity.getDes());
			ps.setDate(5, new Date(activity.getCreated_time().getTime()));
			ps.setString(6, activity.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Company queryCurrCom(String email, String pwd) {
		getConn();
		Company company = new Company();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company where email = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				company = SetDAOUtil.setCompany(company, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return company;
	}
	@Override
	public void updateCompany(Company company) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_company set password = ?, name = ?, principal = ?, logo = ?, address = ?, phone = ?, tel = ?, open_date = ?, longitude = ?, latitude = ?, des = ?, created_time = ?, checked = ?, checked_time = ?, last_login_time = ?, login_count = ? where email = ? ");
			ps.setString(1, company.getPassword());
			ps.setString(2, company.getName());
			ps.setString(3, company.getPrincipal());
			ps.setString(4, company.getLogo());
			ps.setString(5, company.getAddress());
			ps.setString(6, company.getPhone());
			ps.setString(7, company.getTel());
			ps.setDate(8, new Date(company.getOpen_date().getTime()));
			ps.setFloat(9, company.getLongitude());
			ps.setFloat(10, company.getLatitude());
			ps.setString(11, company.getDes());
			ps.setDate(12, new Date(company.getCreated_time().getTime()));
			ps.setString(13, company.getChecked());
			ps.setDate(14, new Date(company.getChecked_time().getTime()));
			ps.setDate(15, new Date(company.getLast_login_time().getTime()));
			ps.setInt(16, company.getLogin_count());
			ps.setString(17, company.getE_mail());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	@Override
	public void addCompany(Company company) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_company(email, password, name, principal, logo, address, phone, tel, open_date, longitude, latitude, des, created_time, checked, checked_time, last_login_time, login_count, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
			ps.setString(1, company.getE_mail());
			ps.setString(2, company.getPassword());
			ps.setString(3, company.getName());
			ps.setString(4, company.getPrincipal());
			ps.setString(5, company.getLogo());
			ps.setString(6, company.getAddress());
			ps.setString(7, company.getPhone());
			ps.setString(8, company.getTel());
			ps.setDate(9, new Date(company.getOpen_date().getTime()));
			ps.setFloat(10, company.getLongitude());
			ps.setFloat(11, company.getLatitude());
			ps.setString(12, company.getDes());
			ps.setDate(13, new Date(company.getCreated_time().getTime()));
			ps.setString(14, company.getChecked());
			ps.setDate(15, new Date(company.getChecked_time().getTime()));
			ps.setDate(16, new Date(company.getLast_login_time().getTime()));
			ps.setInt(17, company.getLogin_count());
			ps.setString(18, company.getStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public Supply querySupplyByEmail(String email) {
		Supply supply = new Supply();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_supply where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				supply = SetDAOUtil.setSupply(supply, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return supply;
	}

}
