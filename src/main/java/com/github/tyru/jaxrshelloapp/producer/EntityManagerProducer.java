package com.github.tyru.jaxrshelloapp.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@ApplicationScoped
class EntityManagerProducer {

	@Produces
	public EntityManager getEntityManager() {
		return EntityManagerSingleton.INSTANCE.get();
	}

	private static enum EntityManagerSingleton {
		INSTANCE;
		private EntityManager em;
		private EntityManagerSingleton() {
			em = Persistence.createEntityManagerFactory("h2-unit").createEntityManager();
		}
		public EntityManager get() {
			return em;
		}
	}
}