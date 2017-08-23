package app;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import com.sun.jersey.spi.container.ResourceFilters;

import filter.CheckLogin;

@Path("/getSession")
public class GetSessionValue {

	@Context
	private HttpServletRequest request;

	@GET
	@ResourceFilters(CheckLogin.class)
	public String getSession(@QueryParam("id") String id) {
		return request.getSession(true).getAttribute(id) != null ? request.getSession(true).getAttribute(id).toString()
				: "";
	}

}
