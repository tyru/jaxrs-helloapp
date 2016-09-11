package com.github.tyru.jaxrshelloapp.hello;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

import com.github.tyru.jaxrshelloapp.filter.ValidateJSON;
import com.github.tyru.jaxrshelloapp.hello.exception.HelloServiceExcepton;
import com.github.tyru.jaxrshelloapp.hello.stereotype.Controller;
import com.github.tyru.jaxrshelloapp.utils.LogUtils;

@Controller
//@ApplicationScoped
//@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
//@LoggingAround
@Path("hello")
public class HelloResource {

	@Inject
	private HelloService service;
	@Context
	private UriInfo uriInfo;
	@Inject @Named("RawHyperSchema")
	private String rawHyperSchema;
	@Inject
	private Logger logger;

	@GET
	@Path("schema")
	public String getRawHyperSchema() {
		return rawHyperSchema;
	}

	@GET
	public List<HelloEntity> sayHelloInManyWays() {
		return service.getHelloMsgList();
	}

	@GET
	@Path("{lang}")
	public Response sayHelloIn(@PathParam("lang") String lang) {
		try {
			return Response.ok(service.getHelloMsg(lang)).build();
		} catch (HelloServiceExcepton e) {
			logger.error(LogUtils.toLoggingMsg(e));
			return e.getStatus();
		}
	}

	@POST
	@ValidateJSON
	public Response addMsg(HelloEntity hello) {
		try {
			service.addMsg(hello);
		} catch (HelloServiceExcepton e) {
			logger.error(LogUtils.toLoggingMsg(e));
			return e.getStatus();
		}
		URI createdUri = uriInfo.getAbsolutePathBuilder().path(hello.getLang()).build();
		return Response.created(createdUri).build();
	}

	@DELETE
	@Path("{lang}")
	@ValidateJSON
	public Response deleteMsg(@PathParam("lang") String lang) {
		service.deleteMsg(lang);
		return Response.noContent().build();
	}
}
