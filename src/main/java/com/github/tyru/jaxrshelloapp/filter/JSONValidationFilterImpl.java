package com.github.tyru.jaxrshelloapp.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.ws.rs.Priorities;
import javax.ws.rs.ext.Provider;

import com.github.tyru.jaxrshelloapp.HelloApp;

import me.tyru.json.hyper.schema.HyperSchema;
import me.tyru.json.hyper.schema.HyperSchemaBuilder;
import me.tyru.json.hyper.schema.filter.AbstractJaxrsJSONValidationFilter;

@Provider
@Priority(Priorities.AUTHENTICATION)
@ApplicationScoped
public class JSONValidationFilterImpl extends AbstractJaxrsJSONValidationFilter {
	@Named("AbstractJaxrsJSONValidationFilter.hyperSchema")
	@Produces
	public HyperSchema injectHyperSchema() throws IOException {
		return HyperSchemaBuilder.createHyperSchema(HelloApp.JSON_HYPER_SCHEMA_FILE);
	}
}
