package com.github.tyru.jaxrshelloapp.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * This produces @RequestScoped EntityManager.
 * Because it is a bother to manage @ApplicationScoped EntityManager
 * which is not thread-safe.
 *
 * @author tyru
 *
 */
@RequestScoped
class EntityManagerProducer {
	@Produces
	private EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("h2-unit").createEntityManager();
	}
}