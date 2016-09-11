package com.github.tyru.jaxrshelloapp.producer;

import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONObject;

import com.github.tyru.json.hyper.schema.HyperSchema;
import com.github.tyru.json.hyper.schema.HyperSchemaBuilder;

/**
 * This producer is ApplicationScoped due to avoiding a disk I/O when reading a
 * json schema file.
 *
 * @author tyru
 *
 */
@ApplicationScoped
class HyperSchemaProducer {

	@Inject @Named("RawHyperSchema")
	private String rawHyperSchema;

	@Produces
	public HyperSchema getHyperSchema() throws IOException {
		return HyperSchemaBuilder.hyperSchema(new JSONObject(rawHyperSchema)).build();
	}
}