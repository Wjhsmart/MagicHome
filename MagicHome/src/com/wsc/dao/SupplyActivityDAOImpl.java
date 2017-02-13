package com.wsc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wsc.bean.SupplyActivity;
import com.wsc.common.SetDAOUtil;
import com.wsc.parentbean.Pager;

public class SupplyActivityDAOImpl extends BaseDAO implements SupplyActivityDAO {

	private SupplyActivity setSupplyActivity(SupplyActivity supplyactivity, ResultSet rs, Connection conn) throws SQLException {
		supplyactivity.setId(rs.getString("id"));
		supplyactivity.setSupply_id(rs.getString("supply_id"));
		supplyactivity.setName(rs.getString("name"));
		supplyactivity.setImage(rs.getString("image"));
		supplyactivity.setDes(rs.getString("des"));
		supplyactivity.setCreated_time(rs.getDate("created_time"));
		supplyactivity.setSupply(SetDAOUtil.querySupplyById(conn, rs.getString("supply_id")));
		return supplyactivity;
	}
	
	@Override
	public Pager<SupplyActivity> queryByPage(int pageNo, int pageSize) {
		Pager<SupplyActivity> pager = new Pager<SupplyActivity>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_supply_activity where id not in(select top " + top1 + " id from t_supply_activity) order by supply_id desc");
			ResultSet rs = ps.executeQuery();
			List<SupplyActivity> supplyActivitys = new ArrayList<SupplyActivity>();
			while (rs.next()) {
				SupplyActivity supplyActivity = new SupplyActivity();
				supplyActivity = setSupplyActivity(supplyActivity, rs, conn);
				supplyActivitys.add(supplyActivity);
			}
			pager.setResult(supplyActivitys);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close();
		return pager;
	}
	
	@Override
	public Pager<SupplyActivity> queryBySupplyId(int pageNo, int pageSize, String supply_id) {
		Pager<SupplyActivity> pager = new Pager<SupplyActivity>();
		pager.setPageNo(pageNo);
		pager.setPageSize(pageSize);
		int top = pager.getPageSize();
		int top1 = (pager.getPageNo() - 1) * pager.getPageSize();
		getConn();
		try {
			PreparedStatement ps = conn.prepareStatement("select top " + top + " * from t_supply_activity where id not in(select top " + top1 + " id from t_supply_activity) and supply_id = ? order by created_time desc");
			ps.setString(1, supply_id);
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

}
