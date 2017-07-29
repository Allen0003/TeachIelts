package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.FileContext;
import util.Const;

public class ContextDao {
	Connection conn;

	public ContextDao(Connection conn) {
		this.conn = conn;
	}

	public ArrayList<FileContext> getContext(String categorization) throws Exception {
		String sql = "select * from Ielts.Context where categorization = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, categorization);
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
			contexts.add(context);
		}
		return contexts;
	}

	public boolean setContext(FileContext fileContext) throws Exception {
		String sql = "insert into Ielts.Context " + "(Categorization, Context, IsShow, SysUser, SysTime)"
				+ " values(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, fileContext.getCategorization());
		pstmt.setString(2, fileContext.getContext());
		pstmt.setString(3, fileContext.isShow() ? "Y" : "N");
		pstmt.setString(4, fileContext.getUser());
		pstmt.setString(5, fileContext.getSysTime());

		return pstmt.executeUpdate() == Const.sqlOK;
	}

}
