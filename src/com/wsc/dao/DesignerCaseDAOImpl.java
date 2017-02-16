package com.wsc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.DesignerCase;
import com.wsc.bean.DesignerCaseCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class DesignerCaseDAOImpl extends BaseDAO implements DesignerCaseDAO {

	private DesignerCase setDesignerCase(DesignerCase designerCase, ResultSet rs, Connection conn) throws SQLException {
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
	
	@Override
	public Pager<DesignerCase> queryByPage(int pageNo, int pageSize) {
		Pager<DesignerCase> pager = new Pager<DesignerCase>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_designer_case where id not in(select top " + top1 + " id from t_designer_case) order by designer_id desc");
			ResultSet rs = ps.executeQuery();
			List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
			while (rs.next()) {
				DesignerCase designerCase = new DesignerCase();
				setDesignerCase(designerCase, rs, conn);
				designerCases.add(designerCase);
			}
			pager.setResult(designerCases);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public DesignerCase queryDesignerCase() {
		DesignerCase designerCase = new DesignerCase();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 1 * from t_designer_case");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				designerCase = SetDAOUtil.setDesignerCase(designerCase, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designerCase;
	}

	@Override
	public List<DesignerCase> queryDesignerCaseByTop4(String designer_id) {
		List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top 4 * from t_designer_case where designer_id = ?");
			ps.setString(1, designer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DesignerCase designerCase = new DesignerCase();
				designerCase = SetDAOUtil.setDesignerCase(designerCase, rs, conn);
				designerCases.add(designerCase);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designerCases;
	}

	@Override
	public DesignerCase queryDesignerCaseById(String id) {
		DesignerCase designerCase = new DesignerCase();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_designer_case where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				designerCase = SetDAOUtil.setDesignerCase(designerCase, rs, conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designerCase;
	}

	@Override
	public Pager<DesignerCase> queryDesignerCaseByDesignerId(int pageNo, int pageSize, String designer_id) {
		Pager<DesignerCase> pager = new Pager<DesignerCase>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_designer_case where id not in(select top " + top1 + "id from t_designer_case) and designer_id = ? order by created_time desc");
			ps.setString(1, designer_id);
			ResultSet rs = ps.executeQuery();
			List<DesignerCase> designerCases = new ArrayList<DesignerCase>();
			while (rs.next()) {
				DesignerCase designerCase = new DesignerCase();
				setDesignerCase(designerCase, rs, conn);
				designerCases.add(designerCase);
			}
			pager.setResult(designerCases);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public int queryCountByDesignerId(String designer_id) {
		getConn();
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement("select count(id) from t_designer_case where designer_id = ?");
			ps.setString(1, designer_id);
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
	public void addDesignerCase(DesignerCase designerCase) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_designer_case(designer_id, name, plot_name, style, image_1, image_2, image_3, image_4, image_5, des, created_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, designerCase.getDesigner_id());
			ps.setString(2, designerCase.getName());
			ps.setString(3, designerCase.getPlot_name());
			ps.setString(4, designerCase.getStyle());
			ps.setString(5, designerCase.getImage1());
			ps.setString(6, designerCase.getImage2());
			ps.setString(7, designerCase.getImage3());
			ps.setString(8, designerCase.getImage4());
			ps.setString(9, designerCase.getImage5());
			ps.setString(10, designerCase.getDes());
			ps.setDate(11, new Date(designerCase.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}
	
	@Override
	public void updateCase(DesignerCase designerCase) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("update t_designer_case set designer_id = ?, name = ?, plot_name = ?, style = ?, image_1 = ?, image_2 = ?, image_3 = ?, image_4 = ?, image_5 = ?, des = ?, created_time = ? where id = ?");
			ps.setString(1, designerCase.getDesigner_id());
			ps.setString(2, designerCase.getName());
			ps.setString(3, designerCase.getPlot_name());
			ps.setString(4, designerCase.getStyle());
			ps.setString(5, designerCase.getImage1());
			ps.setString(6, designerCase.getImage2());
			ps.setString(7, designerCase.getImage3());
			ps.setString(8, designerCase.getImage4());
			ps.setString(9, designerCase.getImage5());
			ps.setString(10, designerCase.getDes());
			ps.setDate(11, new Date(designerCase.getCreated_time().getTime()));
			ps.setString(12, designerCase.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

	@Override
	public List<DesignerCaseCol> queryDesignerCaseColByProductId(String customer_id) {
		List<DesignerCaseCol> designerCaseCols = new ArrayList<DesignerCaseCol>();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from t_designer_case_col where customer_id = ?");
			ps.setString(1, customer_id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DesignerCaseCol designerCaseCol = new DesignerCaseCol();
				designerCaseCol = SetDAOUtil.setDesignerCaseCol(designerCaseCol, rs, conn);
				designerCaseCols.add(designerCaseCol);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return designerCaseCols;
	}
	

}
