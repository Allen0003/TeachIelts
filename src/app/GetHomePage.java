package app;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bo.IeltsBo;
import util.Const;

@Path("/getHomePage")
public class GetHomePage {
	@Context
	private HttpServletRequest request;

	@GET
	public Response getHomeContext() {
		IeltsBo bo = null;
		String result = null;
		try {
			bo = new IeltsBo();
			result = bo.getHomeContext();
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
