package com.github.tyru.jaxrshelloapp.producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@ApplicationScoped
class EntityManagerProducer {

	@Inject
	private EMHolder holder;

	/**
	 * @see https://developer.jboss.org/message/656319#656319
	 */
	@Produces
	public EntityManager getEntityManager() {
		if (holder.getEM() == null || !holder.getEM().isOpen()) {
			EntityManager em = Persistence.createEntityManagerFactory("h2-unit").createEntityManager();
			holder.setEM(em);
		}
		return holder.getEM();
	}

	/**
	 * @see http://kencharos.hatenablog.com/entry/2015_adv_cal_4_jbatch
	 */
	@ApplicationScoped
	static class EMHolder {

		private ThreadLocal<EntityManager> holder = new ThreadLocal<>();

		public void setEM(EntityManager em) {
			holder.set(em);
		}
		public EntityManager getEM() {
			return holder.get();
		}
		public void removeEM() {
			holder.remove();
		}
	}
}