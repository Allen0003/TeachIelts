package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import bo.IeltsBo;
import entity.Comment;
import util.Const;

@Path("/addComment")

public class AddComment {

	@Context
	private HttpServletRequest request;

	@POST
	public Response addComment(Comment comment) {
		IeltsBo bo = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		comment.setSysTime(dateFormat.format(date));
		try {
			bo = new IeltsBo();
			bo.setComment(comment);
		} catch (Exception e) {
			Const.LOGGER.log(Level.WARNING, e.toString(), e);
			return Response.status(403).build();
		} finally {
			if (bo != null) {
				try {
					bo.disconnect();
				} catch (Exception e) {

				}
			}
		}
		return Response.status(200).build();
	}
}
