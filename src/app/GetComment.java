package app;

import java.util.ArrayList;
import java.util.logging.Level;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bo.IeltsBo;
import entity.Comment;
import util.Const;

@Path("/getComment/{id}")
public class GetComment {
	@GET
	public Response getComment(@PathParam("id") String id) {
		ArrayList<Comment> result = null;
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			result = bo.getCommentId(id);
		} catch (Exception e) {
			e.printStackTrace();
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
