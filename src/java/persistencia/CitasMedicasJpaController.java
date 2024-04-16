/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Clases.CitasMedicas;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author dylan
 */
public class CitasMedicasJpaController implements Serializable {

    public CitasMedicasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CitasMedicasJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }

    public void create(CitasMedicas citasMedicas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(citasMedicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CitasMedicas citasMedicas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            citasMedicas = em.merge(citasMedicas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = citasMedicas.getIDCitas();
                if (findCitasMedicas(id) == null) {
                    throw new NonexistentEntityException("The citasMedicas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CitasMedicas citasMedicas;
            try {
                citasMedicas = em.getReference(CitasMedicas.class, id);
                citasMedicas.getIDCitas();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citasMedicas with id " + id + " no longer exists.", enfe);
            }
            em.remove(citasMedicas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CitasMedicas> findCitasMedicasEntities() {
        return findCitasMedicasEntities(true, -1, -1);
    }

    public List<CitasMedicas> findCitasMedicasEntities(int maxResults, int firstResult) {
        return findCitasMedicasEntities(false, maxResults, firstResult);
    }

    private List<CitasMedicas> findCitasMedicasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CitasMedicas.class));
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

    public CitasMedicas findCitasMedicas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CitasMedicas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitasMedicasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CitasMedicas> rt = cq.from(CitasMedicas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
