package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.Comment;
import util.Const;
import util.Util;

public class CommentDao {
	Connection conn;

	public CommentDao(Connection conn) {
		this.conn = conn;
	}

	public boolean setComment(Comment comment) throws Exception {
		String sql = "insert into Ielts.Comment (Id, comment, sysUser, sysTime, email) values(?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Util.decryptID(comment.getId()));
		pstmt.setString(2, comment.getComment());
		pstmt.setString(3, comment.getSysUser());
		pstmt.setString(4, comment.getSysTime());
		pstmt.setString(5, comment.getEmail());
		return pstmt.executeUpdate() == Const.sqlOK;
	}

	public ArrayList<Comment> getCommentId(String id) throws Exception {
		String sql = "select * from Ielts.Comment where Id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Util.decryptID(id));
		ResultSet rs = pstmt.executeQuery();
		Comment comment = null;
		ArrayList<Comment> comments = new ArrayList<Comment>();

		while (rs.next()) {
			comment = new Comment();
			comment.setId(Util.encryptID(rs.getString("Id")));
			comment.setComment(rs.getString("Comment"));
			comment.setSysUser(rs.getString("sysUser"));
			comment.setSysTime(rs.getString("sysTime"));
			comments.add(comment);
		}
		return comments;
	}

	public boolean delComment(String id, String sysTime) throws Exception {
		String sql = "delete from Ielts.Comment where id = ? and sysTime = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Util.decryptID(id));
		pstmt.setString(2, sysTime);
		return pstmt.executeUpdate() == Const.sqlOK;
	}

}
