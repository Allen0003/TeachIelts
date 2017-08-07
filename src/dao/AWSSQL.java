package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.FileContext;
import util.Const;

public class AWSSQL {
	Connection conn;

	public AWSSQL(Connection conn) {
		this.conn = conn;
	}

	public void createDB() throws Exception {
		String sql = "CREATE DATABASE Ielts";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.execute();
	}

	public void createTable() throws Exception {

		String sql = "CREATE TABLE Ielts.Context" + " (Id INT(11) NOT NULL AUTO_INCREMENT,"
				+ " Categorization VARCHAR(5) NOT NULL, Context BLOB NULL DEFAULT NULL, IsShow VARCHAR(10) NOT NULL,"
				+ " SysUser VARCHAR(45) NOT NULL, SysTime DATE NOT NULL, Title VARCHAR(200) NULL DEFAULT NULL, PRIMARY KEY (Id));";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.execute();
	}

	public void insertOne() throws Exception {
		String sql = "INSERT INTO Ielts.Context (Categorization, IsShow, SysUser, SysTime) VALUES ('R', 'Y', 'Chris', '2017-08-07')";

		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.execute();
	}

	public void seeAll() throws Exception {
		String sql = "Select * from Ielts.Context";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		FileContext context = null;

		ArrayList<FileContext> contexts = new ArrayList<FileContext>();

		while (rs.next()) {
			context = new FileContext();
			context.setCategorization(rs.getString("categorization"));
			context.setContext(rs.getString("context"));
			context.setShow(rs.getString("IsShow").toUpperCase().equals(Const.isShow));
			context.setUser(rs.getString("sysUser"));
			context.setSysTime(rs.getString("sysTime"));
			context.setTitle(rs.getString("title"));
			contexts.add(context);
		}
		System.out.println(contexts.get(0).getCategorization());

	}

}
