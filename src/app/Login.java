package app;

import java.util.logging.Level;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import bo.IeltsBo;
import entity.User;
import util.Const;

@Path("/login")
public class Login {

	@Context
	private HttpServletRequest request;

	@POST
	public String doLogin(User user) {
		IeltsBo bo = null;
		try {
			bo = new IeltsBo();
			if (bo.checkLogin(user)) {
				request.getSession(true);
				request.setAttribute("isLogin", Const.isLogin);
			}
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
		return Const.home;
	}
}