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

	private ArrayList<FileContext> doGetQuery(PreparedStatement pstmt) throws Exception {
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
			context.setId(rs.getString("Id"));
			contexts.add(context);
		}
		return contexts;
	}

	public ArrayList<FileContext> getContext() throws Exception {
		String sql = "select * from Ielts.Context";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		return doGetQuery(pstmt);
	}

	public ArrayList<FileContext> getContext(String categorization) throws Exception {
		String sql = "select * from Ielts.Context where categorization = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, categorization);
		return doGetQuery(pstmt);
	}

	public boolean setContext(FileContext fileContext) throws Exception {
		String sql = "insert into Ielts.Context " + "(Categorization, Context, IsShow, SysUser, SysTime, Title)"
				+ " values(?,?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, fileContext.getCategorization());
		pstmt.setString(2, fileContext.getContext());
		pstmt.setString(3, fileContext.isShow() ? "Y" : "N");
		pstmt.setString(4, fileContext.getUser());
		pstmt.setString(5, fileContext.getSysTime());
		pstmt.setString(6, fileContext.getTitle());
		return pstmt.executeUpdate() == Const.sqlOK;
	}

	public boolean delContext(int del_id) throws Exception {
		String sql = "delete from Ielts.Context where Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, del_id);
		return pstmt.executeUpdate() == Const.sqlOK;
	}

	public boolean updContext(FileContext fileContext) throws Exception {
		String sql = "update Ielts.Context set Context = ?, Title = ? where Id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, fileContext.getContext());
		pstmt.setString(2, fileContext.getTitle());
		pstmt.setString(3, fileContext.getId());
		return pstmt.executeUpdate() == Const.sqlOK;
	}

}
