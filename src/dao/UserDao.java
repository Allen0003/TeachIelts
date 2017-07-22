package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;

import entity.User;

public class UserDao {
	Connection conn;

	public UserDao(Connection conn) {
		this.conn = conn;
	}

	public boolean checkLogin(User user) throws Exception {
		String sql = "select * from Ielts.User where username = ? and password = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		ResultSet rs = pstmt.executeQuery();
		return rs.next();
	}

}
