/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Clases.PersonalMedico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.RolTipo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author dylan
 */
public class PersonalMedicoJpaController implements Serializable {

    public PersonalMedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public PersonalMedicoJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }
    
    public void create(PersonalMedico personalMedico) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolTipo rol = personalMedico.getRol();
            if (rol != null) {
                rol = em.getReference(rol.getClass(), rol.getNrorol());
                personalMedico.setRol(rol);
            }
            em.persist(personalMedico);
            if (rol != null) {
                rol.getPersonalMedicoCollection().add(personalMedico);
                rol = em.merge(rol);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PersonalMedico personalMedico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PersonalMedico persistentPersonalMedico = em.find(PersonalMedico.class, personalMedico.getIDempleado());
            RolTipo rolOld = persistentPersonalMedico.getRol();
            RolTipo rolNew = personalMedico.getRol();
            if (rolNew != null) {
                rolNew = em.getReference(rolNew.getClass(), rolNew.getNrorol());
                personalMedico.setRol(rolNew);
            }
            personalMedico = em.merge(personalMedico);
            if (rolOld != null && !rolOld.equals(rolNew)) {
                rolOld.getPersonalMedicoCollection().remove(personalMedico);
                rolOld = em.merge(rolOld);
            }
            if (rolNew != null && !rolNew.equals(rolOld)) {
                rolNew.getPersonalMedicoCollection().add(personalMedico);
                rolNew = em.merge(rolNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = personalMedico.getIDempleado();
                if (findPersonalMedico(id) == null) {
                    throw new NonexistentEntityException("The personalMedico with id " + id + " no longer exists.");
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
            PersonalMedico personalMedico;
            try {
                personalMedico = em.getReference(PersonalMedico.class, id);
                personalMedico.getIDempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The personalMedico with id " + id + " no longer exists.", enfe);
            }
            RolTipo rol = personalMedico.getRol();
            if (rol != null) {
                rol.getPersonalMedicoCollection().remove(personalMedico);
                rol = em.merge(rol);
            }
            em.remove(personalMedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PersonalMedico> findPersonalMedicoEntities() {
        return findPersonalMedicoEntities(true, -1, -1);
    }

    public List<PersonalMedico> findPersonalMedicoEntities(int maxResults, int firstResult) {
        return findPersonalMedicoEntities(false, maxResults, firstResult);
    }

    private List<PersonalMedico> findPersonalMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PersonalMedico.class));
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

    public PersonalMedico findPersonalMedico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PersonalMedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonalMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PersonalMedico> rt = cq.from(PersonalMedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
