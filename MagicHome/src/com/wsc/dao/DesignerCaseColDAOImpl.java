package com.wsc.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.DesignerCaseCol;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class DesignerCaseColDAOImpl extends BaseDAO implements DesignerCaseColDAO {

	@Override
	public Pager<DesignerCaseCol> queryByPage(int pageNo, int pageSize, String customerId) {
		Pager<DesignerCaseCol> pager = new Pager<DesignerCaseCol>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_designer_case_col where id not in(select top " + top1 + " id from t_designer_case_col) and customer_id = ?");
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			List<DesignerCaseCol> designerCaseCols = new ArrayList<DesignerCaseCol>();
			while (rs.next()) {
				DesignerCaseCol designerCaseCol = new DesignerCaseCol();
				SetDAOUtil.setDesignerCaseCol(designerCaseCol, rs, conn);
				designerCaseCols.add(designerCaseCol);
			}
			pager.setResult(designerCaseCols);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}

	@Override
	public void addDesignerCaseCol(DesignerCaseCol designerCaseCol) {
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("insert into t_designer_case_col(customer_id, case_id, created_time) values(?, ?, ?)");
			ps.setString(1, designerCaseCol.getCustomer_id());
			ps.setString(2, designerCaseCol.getCase_id());
			ps.setDate(3, new Date(designerCaseCol.getCreated_time().getTime()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
	}

}
