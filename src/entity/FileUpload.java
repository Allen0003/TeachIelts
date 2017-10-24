package entity;

public class FileUpload {

	private String id;

	private String FileName;

	private byte[] File_byte;

	private String sysUser;

	private String SysTime;

	private String FileId;

	public String getFileId() {
		return FileId;
	}

	public void setFileId(String fileId) {
		FileId = fileId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public byte[] getFile_byte() {
		return File_byte;
	}

	public void setFile_byte(byte[] file_byte) {
		File_byte = file_byte;
	}

	public String getSysUser() {
		return sysUser;
	}

	public void setSysUser(String sysUser) {
		this.sysUser = sysUser;
	}

	public String getSysTime() {
		return SysTime;
	}

	public void setSysTime(String sysTime) {
		SysTime = sysTime;
	}

}
