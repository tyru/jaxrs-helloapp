package com.github.tyru.jaxrshelloapp;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api/v1")
//@DataSourceDefinition(
//        name = "java:/h2ds",
//        user = "sa",
//        password = "sa",
//        className = "org.h2.jdbcx.JdbcDataSource",
//        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
public class HelloApp extends Application {

	public static final String JSON_HYPER_SCHEMA_FILE = "/schemas/hello-schema.json";
}