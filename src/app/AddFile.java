package app;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import bo.IeltsBo;
import entity.FileUpload;
import util.Const;

@Path("/uploadFile")
public class AddFile {

	@Context
	private HttpServletRequest request;

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("fileId") String fileId) {

		HttpSession session = request.getSession(true);
		IeltsBo bo = null;

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();

		try {
			FileUpload fileUpload = new FileUpload();
			fileUpload.setId(fileId);
			fileUpload.setFileName(fileDetail.getFileName());
			fileUpload.setSysUser(
					session.getAttribute("username") != null ? session.getAttribute("username").toString() : "");
			fileUpload.setSysTime(dateFormat.format(date));
			fileUpload.setFile_byte(isToByteArray(uploadedInputStream));
			bo = new IeltsBo();
			bo.addFile(fileUpload);
		} catch (Exception e) {
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
			return Response.status(403).build();
		} finally {
			if (bo != null) {
				try {
					bo.disconnect();
				} catch (Exception e) {

				}
			}
		}

		String uploadedFileLocation = "/Users/allenwu/Downloads/" + fileDetail.getFileName();
		String output = "File uploaded to : " + uploadedFileLocation;

		return Response.status(200).entity(output).build();

	}

	private byte[] isToByteArray(InputStream is) throws Exception {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}
		buffer.flush();

		return buffer.toByteArray();
	}
}
