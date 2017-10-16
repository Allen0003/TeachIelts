package app;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import bo.IeltsBo;
import util.Const;

@Path("/delContext")
public class DelContext {
	@Context
	private HttpServletRequest request;

	@GET
	// @ResourceFilters(CheckLogin.class)
	public Response delContext(@QueryParam("del_id") int del_id) {

		IeltsBo bo = null;
		boolean result = false;
		try {
			bo = new IeltsBo();
			result = bo.delContext(del_id);
		} catch (Exception e) {
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
		}
		if (bo != null) {
			try {
				bo.disconnect();
			} catch (Exception e) {

			}
		}
		return result ? Response.status(200).build() : Response.status(400).build();
	}
}
