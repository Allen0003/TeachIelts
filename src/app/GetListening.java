package app;

import java.util.ArrayList;
import java.util.logging.Level;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bo.IeltsBo;
import entity.FileContext;
import util.Const;

//TODO
@Path("/listening")
public class GetListening {
	@GET
	public Response getListening() {
		ArrayList<FileContext> result = null;
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getContext(Const.listening);

		} catch (Exception e) {
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
		} finally {
			if (bo != null) {
				try {
					bo.disconnect();
				} catch (Exception e) {

				}
			}
		}
		return Response.status(200).entity(new Gson().toJson(result)).build();
	}
}
