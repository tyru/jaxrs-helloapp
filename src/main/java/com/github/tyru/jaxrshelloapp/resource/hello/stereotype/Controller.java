package com.github.tyru.jaxrshelloapp.resource.hello.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Stereotype;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import com.github.tyru.jaxrshelloapp.resource.hello.interceptor.LoggingAround;

import javax.enterprise.inject.Alternative;


@Alternative
@Target( {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Stereotype
@ApplicationScoped
@Consumes("application/json; charset=UTF-8")
@Produces("application/json; charset=UTF-8")
@LoggingAround
public @interface Controller {
}
