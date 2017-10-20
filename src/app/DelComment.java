package app;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.container.ResourceFilters;

import bo.IeltsBo;
import filter.CheckLogin;
import util.Const;

@Path("/delComment")
public class DelComment {
	@Context
	private HttpServletRequest request;

	@GET
	@ResourceFilters(CheckLogin.class)
	public Response delContext(@QueryParam("id") String id, @QueryParam("sysTime") String sysTime) {

		IeltsBo bo = null;
		boolean result = false;
		try {
			bo = new IeltsBo();
			result = bo.delComment(id, sysTime);
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
