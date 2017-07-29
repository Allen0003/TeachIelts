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
import entity.FileContext;
import util.Const;

@Path("/reading")
public class GetContext {
	@Context
	private HttpServletRequest request;

	@GET
	public Response getReading() {
		ArrayList<FileContext> result = null;
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getContext("R");

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

	// @Path("/listening")
	// public void getListening() {
	//
	// }
	//
	// @Path("/speaking")
	// public void getSpeaking() {
	//
	// }
	//
	// @Path("/writing1")
	// public void getWriting1() {
	//
	// }
	//
	// @Path("/writing2")
	// public void getWriting2() {
	//
	// }

}
