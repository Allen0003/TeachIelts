package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.ManuBar;
import util.Const;

public class ManuBarDao {
	Connection conn;

	public ManuBarDao(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<ManuBar> getManuBar() throws Exception {
		String sql = "select * from Ielts.Categorization order by orderBy  ";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ManuBar manuBar = null;
		ResultSet rs = pstmt.executeQuery();
		ArrayList<ManuBar> manuBars = new ArrayList<ManuBar>();

		while (rs.next()) {
			manuBar = new ManuBar();
			manuBar.setName(rs.getString("Name"));
			manuBar.setValue(rs.getString("Value"));
			manuBar.setSubClass(rs.getString("subClass") != null ? rs.getString("subClass").split(",") : null);
			manuBar.setMain(rs.getString("isMain").equals(Const.isMain));
			manuBar.setOrder(rs.getString("orderBy") != null ? rs.getString("orderBy") : "");
			manuBar.setDrop(rs.getString("isMain").equals(Const.isDrop));
			manuBars.add(manuBar);
		}
		return manuBars;
	}
}
