package app;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import bo.IeltsBo;
import util.Const;

@Path("/updHomePage")
public class UpdHomePage {

	@Context
	private HttpServletRequest request;

	@POST
	public Response UpdHomeContext(String homePage) {
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			bo.updHomeContext(homePage);
		} catch (Exception e) {
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
		}
		if (bo != null) {
			try {
				bo.disconnect();
			} catch (Exception e) {

			}
		}
		return Response.status(200).build();
	}
}
