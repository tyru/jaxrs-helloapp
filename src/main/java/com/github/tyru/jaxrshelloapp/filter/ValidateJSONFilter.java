package com.github.tyru.jaxrshelloapp.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import org.everit.json.schema.ValidationException;
import org.slf4j.Logger;

import com.github.tyru.jaxrshelloapp.utils.LogUtils;
import com.github.tyru.json.hyper.schema.HyperSchema;

@Provider
@Priority(Priorities.AUTHENTICATION)
@ValidateJSON
@RequestScoped
public class ValidateJSONFilter implements ContainerRequestFilter {

	@Inject
	private Logger logger;
	@Context
	ResourceInfo resInfo;
	@Inject
	private HyperSchema hyperSchema;

	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		logger.info("Start validation.");
		try {
			// Throws a ValidationException if accepted json is invalid
			hyperSchema.validate(context);
		} catch (ValidationException e) {
			logger.error(LogUtils.toLoggingMsg(e));
			context.abortWith(Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build());
		}
	}
}