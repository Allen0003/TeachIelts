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
		String sql = "select * from Ielts.Categorization order by orderBy";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		ManuBar manuBar = null;
		ResultSet rs = pstmt.executeQuery();
		ArrayList<ManuBar> manuBars = new ArrayList<ManuBar>();

		while (rs.next()) {
			manuBar = new ManuBar();
			manuBar.setName(rs.getString("Name"));
			manuBar.setValue(rs.getString("Value"));
			manuBar.setSubClass(rs.getString("subClass"));
			manuBar.setMain(rs.getString("isMain").equals(Const.isMain));
			manuBar.setOrder(rs.getString("orderBy") != null ? rs.getString("orderBy") : "");
			manuBar.setDrop(rs.getString("isDrop").equals(Const.isDrop));
			manuBars.add(manuBar);
		}
		return manuBars;
	}

	public void delManuBar() throws Exception {
		String sql = " delete from Ielts.Categorization ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}

	public void addManuBars(ArrayList<ManuBar> inpus) throws Exception {
		String sql = " insert into Ielts.Categorization"
				+ " (Name,Value,subClass,isMain,orderBy,isDrop) Values (?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		for (ManuBar manuBar : inpus) {
			pstmt.setString(1, manuBar.getName());
			pstmt.setString(2, manuBar.getValue());
			pstmt.setString(3, manuBar.getSubClass());
			pstmt.setString(4, manuBar.isMain() ? "Y" : "F");
			pstmt.setString(5, manuBar.getOrder());
			pstmt.setString(6, manuBar.isDrop() ? "Y" : "F");
			pstmt.addBatch();
		}
		pstmt.executeBatch();
		conn.commit();
	}
}
