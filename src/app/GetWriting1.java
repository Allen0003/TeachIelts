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

@Path("/writing1")
public class GetWriting1 {
	@GET
	public Response getWriting1() {
		ArrayList<FileContext> result = null;
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getContext(Const.writing1);

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
