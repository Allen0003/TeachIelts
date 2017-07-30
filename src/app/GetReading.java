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
@Path("/reading")
public class GetReading {
	@GET
	public Response getReading() {
		ArrayList<FileContext> result = null;
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getContext(Const.reading);

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