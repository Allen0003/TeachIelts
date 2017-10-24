package app;

import java.util.ArrayList;
import java.util.logging.Level;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import com.google.gson.Gson;

import bo.IeltsBo;
import entity.FileUpload;
import util.Const;

@Path("/file/")
public class GetFile {
	@GET
	@Path("getFile")
	public Response getFile(@QueryParam(value = "id") final String fileId) {
		FileUpload result = null;
		IeltsBo bo = null;
		StreamingOutput fileStream = null;
		try {
			bo = new IeltsBo();
			result = bo.getFile(fileId);
			fileStream = new StreamingOutput() {
				@Override
				public void write(java.io.OutputStream output) {
					try {
						// FIXME
						IeltsBo bo2 = new IeltsBo();
						FileUpload result2 = bo2.getFile(fileId);
						output.write(result2.getFile_byte());
						output.flush();
					} catch (Exception e) {
						e.printStackTrace();
						Const.LOGGER.log(Level.WARNING, e.toString(), e);
					}
				}
			};
		} catch (Exception e) {
			e.printStackTrace();
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
		}
		if (bo != null) {
			try {
				bo.disconnect();
			} catch (Exception e) {
			}
		}
		return Response.ok(fileStream, MediaType.APPLICATION_OCTET_STREAM)
				.header("content-disposition", "attachment; filename =" + result.getFileName()).build();
	}

	@GET
	@Path("getFileAll")
	public Response getFileAll() {
		ArrayList<FileUpload> result = new ArrayList<FileUpload>();
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getFile();

		} catch (Exception e) {
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
		}
		if (bo != null) {
			try {
				bo.disconnect();
			} catch (Exception e) {
			}
		}
		return Response.status(200).entity(new Gson().toJson(result)).build();
	}
}
