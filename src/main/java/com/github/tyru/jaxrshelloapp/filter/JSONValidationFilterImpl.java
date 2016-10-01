package com.github.tyru.jaxrshelloapp.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.ws.rs.Priorities;
import javax.ws.rs.ext.Provider;

import me.tyru.json.hyper.schema.HyperSchema;
import me.tyru.json.hyper.schema.filter.AbstractJSONValidationFilter;

@Provider
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class JSONValidationFilterImpl extends AbstractJSONValidationFilter {
	@Named("JSONValidationFilter.hyperSchema")
	@Produces
	public HyperSchema injectHyperSchema() throws IOException {
		return createHyperSchema("/schemas/hello-schema.json");
	}
}
