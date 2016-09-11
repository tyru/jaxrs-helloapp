package com.github.tyru.jaxrshelloapp.producer;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @see http://d.hatena.ne.jp/nowokay/20131213
 */
@Dependent
class LoggerProducer {
	@Produces
    public Logger getLogger(InjectionPoint ip){
        return LoggerFactory.getLogger(ip.getMember().getDeclaringClass().getName());
    }
}