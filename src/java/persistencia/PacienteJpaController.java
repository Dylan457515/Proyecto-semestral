/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Clases.Paciente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.ParestescoTipo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author dylan
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public PacienteJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }

    public void create(Paciente paciente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParestescoTipo parestescoResponsable = paciente.getParestescoResponsable();
            if (parestescoResponsable != null) {
                parestescoResponsable = em.getReference(parestescoResponsable.getClass(), parestescoResponsable.getIDparentesco());
                paciente.setParestescoResponsable(parestescoResponsable);
            }
            em.persist(paciente);
            if (parestescoResponsable != null) {
                parestescoResponsable.getPacienteCollection().add(paciente);
                parestescoResponsable = em.merge(parestescoResponsable);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getIDPaciente());
            ParestescoTipo parestescoResponsableOld = persistentPaciente.getParestescoResponsable();
            ParestescoTipo parestescoResponsableNew = paciente.getParestescoResponsable();
            if (parestescoResponsableNew != null) {
                parestescoResponsableNew = em.getReference(parestescoResponsableNew.getClass(), parestescoResponsableNew.getIDparentesco());
                paciente.setParestescoResponsable(parestescoResponsableNew);
            }
            paciente = em.merge(paciente);
            if (parestescoResponsableOld != null && !parestescoResponsableOld.equals(parestescoResponsableNew)) {
                parestescoResponsableOld.getPacienteCollection().remove(paciente);
                parestescoResponsableOld = em.merge(parestescoResponsableOld);
            }
            if (parestescoResponsableNew != null && !parestescoResponsableNew.equals(parestescoResponsableOld)) {
                parestescoResponsableNew.getPacienteCollection().add(paciente);
                parestescoResponsableNew = em.merge(parestescoResponsableNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paciente.getIDPaciente();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getIDPaciente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            ParestescoTipo parestescoResponsable = paciente.getParestescoResponsable();
            if (parestescoResponsable != null) {
                parestescoResponsable.getPacienteCollection().remove(paciente);
                parestescoResponsable = em.merge(parestescoResponsable);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Paciente findLastPaciente() {
    EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Paciente u ORDER BY u.iDPaciente DESC");
            query.setMaxResults(1);
            return (Paciente) query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
