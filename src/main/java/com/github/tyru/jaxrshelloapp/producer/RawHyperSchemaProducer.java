package com.github.tyru.jaxrshelloapp.producer;

import java.io.IOException;
import java.io.InputStream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;

import com.github.tyru.jaxrshelloapp.HelloApp;

/**
 * This producer is ApplicationScoped due to avoiding a disk I/O when reading a
 * json schema file.
 *
 * @author tyru
 *
 */
@ApplicationScoped
class RawHyperSchemaProducer {

	@Named("RawHyperSchema")
	@Produces
	public String getRawHyperSchema() throws IOException {
		try (InputStream inputStream = getClass().getResourceAsStream(HelloApp.JSON_HYPER_SCHEMA_FILE)) {
			return IOUtils.toString(inputStream);
		}
	}
}