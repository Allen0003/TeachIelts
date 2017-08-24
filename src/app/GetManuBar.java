package app;

import java.util.ArrayList;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bo.IeltsBo;
import entity.ManuBar;
import util.Const;

@Path("/manuBar")
public class GetManuBar {

	@Context
	private HttpServletRequest request;

	@GET
	// TODO
	// @ResourceFilters(CheckLogin.class)
	public Response getManu() {
		ArrayList<ManuBar> result = null;
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getManuBars();
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
