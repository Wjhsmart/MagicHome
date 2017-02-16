package com.wsc.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.wsc.bean.Appointment;
import com.wsc.bean.Comment;
import com.wsc.bean.Company;
import com.wsc.bean.CompanyCase;
import com.wsc.bean.CompanyCaseCol;
import com.wsc.bean.CompanyCol;
import com.wsc.bean.Customer;
import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCase;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.bean.DesignerCol;
import com.wsc.bean.Product;
import com.wsc.bean.ProductCol;
import com.wsc.bean.Supply;
import com.wsc.bean.SupplyActivity;

public class SetDAOUtil {

	public static Company setCompany(Company company, ResultSet rs) throws SQLException {
		company.setId(rs.getString("id"));
		company.setE_mail(rs.getString("email"));
		company.setPassword(rs.getString("password"));
		company.setName(rs.getString("name"));
		company.setPrincipal(rs.getString("principal"));
		company.setLogo(rs.getString("logo"));
		company.setAddress(rs.getString("address"));
		company.setPhone(rs.getString("phone"));
		company.setTel(rs.getString("tel"));
		company.setOpen_date(rs.getDate("open_date"));
		company.setLongitude(rs.getFloat("longitude"));
		company.setLatitude(rs.getFloat("latitude"));
		company.setDes(rs.getString("des"));
		company.setCreated_time(rs.getDate("created_time"));
		company.setChecked(rs.getString("checked"));
		company.setChecked_time(rs.getDate("checked_time"));
		company.setLast_login_time(rs.getDate("last_login_time"));
		company.setLogin_count(rs.getInt("login_count"));
		company.setStatus(rs.getString("status"));
		return company;
	}
	
	public static Company queryCompanyById(Connection conn, String id) {
		Company company = new Company();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				company = SetDAOUtil.setCompany(company, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
	
	public static Supply setSupply(Supply supply, ResultSet rs) throws SQLException {
		supply.setId(rs.getString("id"));
		supply.setE_mail(rs.getString("email"));
		supply.setPassword(rs.getString("password"));
		supply.setName(rs.getString("name"));
		supply.setPrincipal(rs.getString("principal"));
		supply.setLogo(rs.getString("logo"));
		supply.setAddress(rs.getString("address"));
		supply.setPhone(rs.getString("phone"));
		supply.setTel(rs.getString("tel"));
		supply.setOpen_date(rs.getDate("open_date"));
		supply.setLongitude(rs.getFloat("longitude"));
		supply.setLatitude(rs.getFloat("latitude"));
		supply.setDes(rs.getString("des"));
		supply.setCreated_time(rs.getDate("created_time"));
		supply.setChecked(rs.getString("checked"));
		supply.setChecked_time(rs.getDate("checked_time"));
		supply.setLast_login_time(rs.getDate("last_login_time"));
		supply.setLogin_count(rs.getInt("login_count"));
		supply.setStatus(rs.getString("status"));
		return supply;
	}
	
	public static Supply querySupplyById(Connection conn, String id) {
		Supply supply = new Supply();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_supply where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				supply = SetDAOUtil.setSupply(supply, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supply;
	}
	
	public static Designer setDesigner(Designer designer, ResultSet rs) throws SQLException {
		designer.setId(rs.getString("id"));
		designer.setE_mail(rs.getString("email"));
		designer.setPassword(rs.getString("password"));
		designer.setName(rs.getString("name"));
		designer.setHead_icon(rs.getString("headicon"));
		designer.setPhone(rs.getString("phone"));
		designer.setAddress(rs.getString("address"));
		designer.setExperience(rs.getString("experience"));
		designer.setStyle(rs.getString("style"));
		designer.setDes(rs.getString("des"));
		designer.setCreated_time(rs.getDate("created_time"));
		designer.setChecked(rs.getString("checked"));
		designer.setChecked_time(rs.getDate("checked_time"));
		designer.setLast_login_time(rs.getDate("last_login_time"));
		designer.setLogin_count(rs.getInt("login_count"));
		designer.setStatus(rs.getString("status"));
		return designer;
	}
	
	public static Designer queryDesignerById(Connection conn, String id) {
		Designer designer = new Designer();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_designer where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				designer = SetDAOUtil.setDesigner(designer, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return designer;
	}
	
	public static CompanyCase setCompanyCase(CompanyCase companyCase, ResultSet rs, Connection conn) throws SQLException {
		companyCase.setId(rs.getString("id"));
		companyCase.setCompany_id(rs.getString("company_id"));
		companyCase.setName(rs.getString("name"));
		companyCase.setPlot_name(rs.getString("plot_name"));
		companyCase.setStyle(rs.getString("style"));
		companyCase.setImage1(rs.getString("image_1"));
		companyCase.setImage2(rs.getString("image_2"));
		companyCase.setImage3(rs.getString("image_3"));
		companyCase.setImage4(rs.getString("image_4"));
		companyCase.setImage5(rs.getString("image_5"));
		companyCase.setDes(rs.getString("des"));
		companyCase.setCreated_time(rs.getDate("created_time"));
		companyCase.setCompany(SetDAOUtil.queryCompanyById(conn, rs.getString("company_id")));
		return companyCase;
	}
	
	public static CompanyCase queryCompanyCaseById(Connection conn, String id) {
		CompanyCase companyCase = new CompanyCase();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_company_case where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				companyCase = setCompanyCase(companyCase, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return companyCase;
	}
	
	public static DesignerCase setDesignerCase(DesignerCase designerCase, ResultSet rs, Connection conn) throws SQLException {
		designerCase.setId(rs.getString("id"));
		designerCase.setDesigner_id(rs.getString("designer_id"));
		designerCase.setName(rs.getString("name"));
		designerCase.setPlot_name(rs.getString("plot_name"));
		designerCase.setStyle(rs.getString("style"));
		designerCase.setImage1(rs.getString("image_1"));
		designerCase.setImage2(rs.getString("image_2"));
		designerCase.setImage3(rs.getString("image_3"));
		designerCase.setImage4(rs.getString("image_4"));
		designerCase.setImage5(rs.getString("image_5"));
		designerCase.setDes(rs.getString("des"));
		designerCase.setCreated_time(rs.getDate("created_time"));
		designerCase.setDesigner(SetDAOUtil.queryDesignerById(conn, rs.getString("designer_id")));
		return designerCase;
	}
	
	public static DesignerCase queryDesignerCaseById(Connection conn, String id) {
		DesignerCase designerCase = new DesignerCase();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_designer_case where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				designerCase = setDesignerCase(designerCase, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return designerCase;
	}
	
	public static Product setProduct(Product product, ResultSet rs, Connection conn) throws SQLException {
		product.setId(rs.getString("id"));
		product.setSupply_id(rs.getString("supply_id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getFloat("price"));
		product.setSale_price(rs.getFloat("sale_price"));
		product.setImage(rs.getString("image"));
		product.setDes(rs.getString("des"));
		product.setCreated_time(rs.getDate("created_time"));
		product.setStatus(rs.getString("status"));
		product.setSupply(SetDAOUtil.querySupplyById(conn, rs.getString("supply_id")));
		return product;
	}
	
	public static Product queryProductById(Connection conn, String id) {
		Product product = new Product();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_product where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = SetDAOUtil.setProduct(product, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	
	public static SupplyActivity setSupplyActivity(SupplyActivity supplyActivity, ResultSet rs, Connection conn) throws SQLException {
		supplyActivity.setId(rs.getString("id"));
		supplyActivity.setSupply_id(rs.getString("supply_id"));
		supplyActivity.setName(rs.getString("name"));
		supplyActivity.setImage(rs.getString("image"));
		supplyActivity.setDes(rs.getString("des"));
		supplyActivity.setCreated_time(rs.getDate("created_time"));
		supplyActivity.setSupply(SetDAOUtil.querySupplyById(conn, rs.getString("supply_id")));
		return supplyActivity;
	}
	
	public static SupplyActivity setSupplyActivityById(Connection conn, String id) {
		SupplyActivity supplyActivity = new SupplyActivity();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_supply_activity where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				supplyActivity = SetDAOUtil.setSupplyActivity(supplyActivity, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplyActivity;
	}
	
	public static Appointment setAppointment(Appointment appointment, ResultSet rs) throws SQLException {
		appointment.setCompany_id(rs.getString("company_id"));
		appointment.setId(rs.getString("id"));
		appointment.setName(rs.getString("name"));
		appointment.setPlot_name(rs.getString("plot_name"));
		appointment.setPhone(rs.getString("phone"));
		appointment.setArea(rs.getFloat("area"));
		appointment.setBudget(rs.getString("budget"));
		appointment.setWay(rs.getString("way"));
		appointment.setCreated_time(rs.getDate("created_time"));
		return appointment;
	}
	
	public static Appointment queryAppointmentById(Connection conn, String id) {
		Appointment appointment = new Appointment();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_appointment where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				appointment = SetDAOUtil.setAppointment(appointment, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appointment;
	}
	
	public static Customer setCustomer(Customer customer, ResultSet rs) throws SQLException {
		customer.setId(rs.getString("id"));
		customer.setE_mail(rs.getString("email"));
		customer.setPassword(rs.getString("password"));
		customer.setName(rs.getString("name"));
		customer.setPhone(rs.getString("phone"));
		customer.setPlot_name(rs.getString("plot_name"));
		customer.setAddress(rs.getString("address"));
		customer.setCreated_time(rs.getDate("created_time"));
		customer.setLast_login_time(rs.getDate("last_login_time"));
		customer.setLogin_count(rs.getInt("login_count"));
		customer.setStatus(rs.getString("status"));
		return customer;
	}
	
	public static Customer setCustomerById(Connection conn, String id) {
		Customer customer = new Customer();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_customer where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = SetDAOUtil.setCustomer(customer, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
	
	public static Comment setComment(Comment comment, ResultSet rs, Connection conn) throws SQLException {
		comment.setId(rs.getString("id"));
		comment.setCustomer_id(rs.getString("customer_id"));
		comment.setContent(rs.getString("content"));
		comment.setCustomer(SetDAOUtil.setCustomerById(conn, rs.getString("customer_id")));
		comment.setCreated_time(rs.getDate("created_time"));
		return comment;
	}
	
	public static ProductCol setProductCol(ProductCol productCol, ResultSet rs, Connection conn) throws SQLException {
		productCol.setId(rs.getString("id"));
		productCol.setCustomer_id(rs.getString("customer_id"));
		productCol.setProduct_id(rs.getString("product_id"));
		productCol.setCreated_time(rs.getDate("created_time"));
		productCol.setProduct(SetDAOUtil.queryProductById(conn, rs.getString("product_id")));
		return productCol;
	}
	
	public static DesignerCol setDesignerCol(DesignerCol designerCol, ResultSet rs, Connection conn) throws SQLException {
		designerCol.setId(rs.getString("id"));
		designerCol.setCustomer_id(rs.getString("customer_id"));
		designerCol.setDesigner_id(rs.getString("designer_id"));
		designerCol.setCreated_time(rs.getDate("created_time"));
		designerCol.setDesigner(SetDAOUtil.queryDesignerById(conn, rs.getString("designer_id")));
		return designerCol;
	}
	
	public static CompanyCol setCompanyCol(CompanyCol companyCol, ResultSet rs, Connection conn) throws SQLException {
		companyCol.setId(rs.getString("id"));
		companyCol.setCustomer_id(rs.getString("customer_id"));
		companyCol.setCompany_id(rs.getString("company_id"));
		companyCol.setCreated_time(rs.getDate("created_time"));
		companyCol.setCompany(SetDAOUtil.queryCompanyById(conn, rs.getString("company_id")));
		return companyCol;
	}
	
	public static CompanyCaseCol setCompanyCaseCol(CompanyCaseCol companyCaseCol, ResultSet rs, Connection conn) throws SQLException {
		companyCaseCol.setId(rs.getString("id"));
		companyCaseCol.setCustomer_id(rs.getString("customer_id"));
		companyCaseCol.setCase_id(rs.getString("case_id"));
		companyCaseCol.setCreated_time(rs.getDate("created_time"));
		companyCaseCol.setCompanyCase(SetDAOUtil.queryCompanyCaseById(conn, rs.getString("case_id")));
		return companyCaseCol;
	}
	
	public static DesignerCaseCol setDesignerCaseCol(DesignerCaseCol designerCaseCol, ResultSet rs, Connection conn) throws SQLException {
		designerCaseCol.setId(rs.getString("id"));
		designerCaseCol.setCustomer_id(rs.getString("customer_id"));
		designerCaseCol.setCase_id(rs.getString("case_id"));
		designerCaseCol.setCreated_time(rs.getDate("created_time"));
		designerCaseCol.setDesignerCase(SetDAOUtil.queryDesignerCaseById(conn, rs.getString("case_id")));
		return designerCaseCol;
	}
	
}
