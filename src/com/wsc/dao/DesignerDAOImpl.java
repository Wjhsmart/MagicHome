package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.Designer;
import com.wsc.bean.DesignerCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class DesignerDAOImpl extends BaseDAO implements DesignerDAO {

	@Override
	public Pager<Designer> queryByPage(int pageNo, int pageSize, String checked) {
		Pager<Designer> pager = new Pager<Designer>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select distinct top " + top + " * from t_designer where id not in(select top " + top1 + " id from t_designer) and checked = ? order by created_time desc");
			ps.setString(1, checked);
			ResultSet rs = ps.executeQuery();
			List<Designer> designers = new ArrayList<Designer>();
			while (rs.next()) {
				Designer designer = new Designer();
				SetDAOUtil.setDesigner(designer, rs);
				designers.add(designer);
			}
			pager.setResult(designers);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public void updateDesigner(Designer designer) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer set password = ?, name = ?, headicon = ?, phone = ?, address = ?, experience = ?, style = ?, des = ?, created_time = ?, checked = ?, checked_time = ?, last_login_time = ?, login_count = ?, status = ? where email = ?");
			ps.setString(1, designer.getPassword());
			ps.setString(2, designer.getName());
			ps.setString(3, designer.getHead_icon());
			ps.setString(4, designer.getPhone());
			ps.setString(5, designer.getAddress());
			ps.setString(6, designer.getExperience());
			ps.setString(7, designer.getStyle());
			ps.setString(8, designer.getDes());
			ps.setDate(9, new Date(designer.getCreated_time().getTime()));
			ps.setString(10, designer.getChecked());
			ps.setDate(11, new Date(designer.getChecked_time().getTime()));
			ps.setDate(12, new Date(designer.getLast_login_time().getTime()));
			ps.setInt(13, designer.getLogin_count());
			ps.setString(14, designer.getStatus());
			ps.setString(15, designer.getE_mail());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public List<Designer> queryDesignerByTop4() {
		List<Designer> designers = new ArrayList<Designer>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 4 * from t_designer order by created_time desc");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Designer designer = new Designer();
				designer = SetDAOUtil.setDesigner(designer, rs);
				designers.add(designer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designers;
	}

	@Override
	public Designer queryDesignerById(String id) {
		Designer designer = new Designer();
		getConn();
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
		close();
		return designer;
	}
	
	@Override
	public void addDesigner(Designer designer ) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_designer(email, password, name, headicon, phone, address, experience, style, des, created_time, checked, checked_time, last_login_time, login_count, status) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, designer.getE_mail());
			ps.setString(2, designer.getPassword());
			ps.setString(3, designer.getName());
			ps.setString(4, designer.getHead_icon());
			ps.setString(5, designer.getPhone());
			ps.setString(6, designer.getAddress());
			ps.setString(7, designer.getExperience());
			ps.setString(8, designer.getStyle());
			ps.setString(9, designer.getDes());
			ps.setDate(10, new Date(designer.getCreated_time().getTime()));
			ps.setString(11, designer.getChecked());
			ps.setDate(12, new Date(designer.getChecked_time().getTime()));
			ps.setDate(13, new Date(designer.getLast_login_time().getTime()));
			ps.setInt(14, designer.getLogin_count());
			ps.setString(15, designer.getStatus());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	@Override
	public Designer queryCurr(String email, String pwd) {
		getConn();
		Designer designer = new Designer();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_designer where email = ? and password = ?");
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				designer = SetDAOUtil.setDesigner(designer, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designer;
	}
	
	@Override
	public Designer queryDesignerByEmail(String email) {
		Designer designer = new Designer();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_designer where email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				designer = SetDAOUtil.setDesigner(designer, rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designer;
	}

	@Override
	public List<DesignerCol> queryDesignerColByCustomerId(String customer_id) {
		List<DesignerCol> designerCols = new ArrayList<DesignerCol>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_designer_col where customer_id = ?");
			ps.setString(1, customer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DesignerCol designerCol = new DesignerCol();
				designerCol = SetDAOUtil.setDesignerCol(designerCol, rs, conn);
				designerCols.add(designerCol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designerCols;
	}

	@Override
	public int queryCountByDesignerChecked(String checked) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_designer where checked = ?");
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

}
