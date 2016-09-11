package com.github.tyru.jaxrshelloapp.hello;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;

import com.github.tyru.jaxrshelloapp.hello.exception.HelloServiceExcepton;
import com.github.tyru.jaxrshelloapp.utils.LogUtils;

@ApplicationScoped
public class HelloService {

	@Inject
	private EntityManager em;
	@Inject
	private Logger logger;

	public List<HelloEntity> getHelloMsgList() {
		return em.createNamedQuery("hello.findAll", HelloEntity.class).getResultList();
	}

	public HelloEntity getHelloMsg(String lang) throws HelloServiceExcepton {
		try {
			return em.createNamedQuery("hello.findOne", HelloEntity.class).setParameter("lang", lang).getSingleResult();
		} catch (NoResultException e) {
			logger.error(LogUtils.toLoggingMsg(e));
			throw new HelloServiceExcepton(e, Response.status(Status.NOT_FOUND).build());
		}
	}

	@Transactional
	public void addMsg(HelloEntity hello) throws HelloServiceExcepton {
		try {
			em.persist(hello);
		} catch (EntityExistsException e) {
			logger.error(LogUtils.toLoggingMsg(e));
			throw new HelloServiceExcepton(e, Response.status(Status.CONFLICT).build());
		}
	}

	@Transactional
	public void deleteMsg(String lang) {
		em.createNamedQuery("hello.deleteMsg").setParameter("lang", lang).executeUpdate();
	}
}
