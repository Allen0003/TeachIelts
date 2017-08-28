package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HomePageDao {
	Connection conn;

	public HomePageDao(Connection conn) {
		this.conn = conn;
	}

	public void delHomePage() throws Exception {
		String sql = "delete from Ielts.HomeContext";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.executeUpdate();
	}

	public void addHomePage(String input) throws Exception {
		String sql = " insert into Ielts.HomeContext (context) Values (?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, input);
		pstmt.executeUpdate();
	}

	public String getHomePage() throws Exception {
		String sql = " select * from Ielts.HomeContext ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		return rs.next() ? rs.getString("context") != null ? rs.getString("context") : "" : "";
	}

}
