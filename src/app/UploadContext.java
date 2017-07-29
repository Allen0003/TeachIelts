package app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import bo.IeltsBo;
import entity.FileContext;
import util.Const;

@Path("/upload")
public class UploadContext {

	@Context
	private HttpServletRequest request;

	@POST
	public void doUplaoding(String context) {
		// FileContext context
		IeltsBo bo = null;
		request.getSession(true);
		// TODO changing to AOP
		if (request.getAttribute("isLogin") != null && request.getAttribute("isLogin").equals(Const.isLogin)) {

		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();

		FileContext fileContext = new FileContext();
		fileContext.setContext(context);
		fileContext.setCategorization("R");
		fileContext.setShow(true);
		fileContext.setUser("Chris");
		fileContext.setSysTime(dateFormat.format(date));
		try {
			bo = new IeltsBo();
			bo.setContext(fileContext);
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

	}

}
