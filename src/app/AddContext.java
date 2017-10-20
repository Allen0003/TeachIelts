package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import com.sun.jersey.spi.container.ResourceFilters;

import bo.IeltsBo;
import entity.FileContext;
import entity.Upload;
import filter.CheckLogin;
import util.Const;

@Path("/upload")

public class AddContext {

	@Context
	private HttpServletRequest request;

	@POST
	@ResourceFilters(CheckLogin.class)
	public Response doUplaoding(Upload upload) {

		IeltsBo bo = null;
		HttpSession session = request.getSession(true);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();

		FileContext fileContext = new FileContext();
		fileContext.setTitle(upload.getTitle());
		fileContext.setContext(upload.getContext());
		fileContext.setCategorization(upload.getCategorization());
		fileContext.setShow(true);
		fileContext
				.setUser(session.getAttribute("username") != null ? session.getAttribute("username").toString() : "");
		fileContext.setSysTime(dateFormat.format(date));
		try {
			bo = new IeltsBo();
			bo.setContext(fileContext);
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
