package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entity.FileUpload;
import util.Const;
import util.Util;

public class FileDao {
	Connection conn;

	public FileDao(Connection conn) {
		this.conn = conn;
	}

	public void addFile(FileUpload fileUpload) throws Exception {
		String sql = "insert into Ielts.File (id, fileName, File_byte, sysUser, SysTime) values (?,?,?,?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Util.decryptID(fileUpload.getId()));
		pstmt.setString(2, fileUpload.getFileName());
		pstmt.setBytes(3, fileUpload.getFile_byte());
		pstmt.setString(4, fileUpload.getSysUser());
		pstmt.setString(5, fileUpload.getSysTime());
		pstmt.executeUpdate();
	}

	public FileUpload getFile(String fileId) throws Exception {
		String sql = "select * from Ielts.File where FileId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setString(1, Util.decryptID(fileId));
		ResultSet rs = pstmt.executeQuery();
		FileUpload fileUpload = null;
		if (rs.next()) {
			fileUpload = new FileUpload();
			fileUpload.setId(Util.encryptID(rs.getString("Id")));
			fileUpload.setFile_byte(rs.getBytes("File_byte"));
			fileUpload.setFileName(rs.getString("fileName"));
			fileUpload.setSysTime(rs.getString("sysUser"));
			fileUpload.setSysTime(rs.getString("sysTime"));
		}
		return fileUpload;
	}

	public ArrayList<FileUpload> getFile() throws Exception {
		String sql = "select * from Ielts.File";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		FileUpload fileUpload = null;
		ArrayList<FileUpload> fileUploads = new ArrayList<FileUpload>();
		while (rs.next()) {
			fileUpload = new FileUpload();
			fileUpload.setId(Util.encryptID(rs.getString("Id")));
			fileUpload.setFile_byte(rs.getBytes("File_byte"));
			fileUpload.setFileName(rs.getString("fileName"));
			fileUpload.setSysTime(rs.getString("sysUser"));
			fileUpload.setSysTime(rs.getString("sysTime"));
			fileUpload.setFileId(Util.encryptID(rs.getString("FileId")));
			fileUploads.add(fileUpload);
		}
		return fileUploads;
	}

	public boolean delFile(String fileId) throws Exception {
		String sql = "delete from Ielts.File where FileId = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, Util.decryptID(fileId));
		return pstmt.executeUpdate() == Const.sqlOK;
	}
}
