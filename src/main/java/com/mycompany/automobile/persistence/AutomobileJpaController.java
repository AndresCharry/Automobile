/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automobile.persistence;

import com.mycompany.automobile.logic.Automobile;
import com.mycompany.automobile.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author charry
 */
public class AutomobileJpaController implements Serializable {

	public AutomobileJpaController(EntityManagerFactory emf) {
		this.emf = emf;
	}
	private EntityManagerFactory emf = null;

	public AutomobileJpaController() {
		emf = Persistence.createEntityManagerFactory("automobileJPAPU");
	}

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(Automobile automobile) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(automobile);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(Automobile automobile) throws NonexistentEntityException, Exception {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			automobile = em.merge(automobile);
			em.getTransaction().commit();
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				int id = automobile.getId();
				if (findAutomobile(id) == null) {
					throw new NonexistentEntityException("The automobile with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(int id) throws NonexistentEntityException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Automobile automobile;
			try {
				automobile = em.getReference(Automobile.class, id);
				automobile.getId();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The automobile with id " + id + " no longer exists.", enfe);
			}
			em.remove(automobile);
			em.getTransaction().commit();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<Automobile> findAutomobileEntities() {
		return findAutomobileEntities(true, -1, -1);
	}

	public List<Automobile> findAutomobileEntities(int maxResults, int firstResult) {
		return findAutomobileEntities(false, maxResults, firstResult);
	}

	private List<Automobile> findAutomobileEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Automobile.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public Automobile findAutomobile(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(Automobile.class, id);
		} finally {
			em.close();
		}
	}

	public int getAutomobileCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<Automobile> rt = cq.from(Automobile.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
