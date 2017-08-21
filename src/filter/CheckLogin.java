package filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

import util.Const;

public class CheckLogin implements ResourceFilter, ContainerRequestFilter, ContainerResponseFilter {
	@Context
	private HttpServletRequest request;

	@Override
	// Do something with the incoming request here
	public ContainerRequest filter(ContainerRequest containerRequest) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("isLogin") == null || !session.getAttribute("isLogin").equals(Const.isLogin)) {
			ResponseBuilder builder = null;
			String response = "Please login first";
			builder = Response.status(Response.Status.UNAUTHORIZED).entity(response);
			throw new WebApplicationException(builder.build());
		}
		return containerRequest;
	}

	@Override
	// Do something with the outgoing response here
	public ContainerResponse filter(ContainerRequest containerRequest, ContainerResponse containerResponse) {
		return containerResponse;
	}

	@Override
	public ContainerRequestFilter getRequestFilter() {
		return this;
	}

	@Override
	public ContainerResponseFilter getResponseFilter() {
		return this;
	}
}
