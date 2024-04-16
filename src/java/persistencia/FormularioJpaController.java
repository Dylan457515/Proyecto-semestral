/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Clases.Formulario;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.HabitacionTipo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author dylan
 */
public class FormularioJpaController implements Serializable {

    public FormularioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public FormularioJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }

    public void create(Formulario formulario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HabitacionTipo tipohabitacion = formulario.getTipohabitacion();
            if (tipohabitacion != null) {
                tipohabitacion = em.getReference(tipohabitacion.getClass(), tipohabitacion.getNrohabitacion());
                formulario.setTipohabitacion(tipohabitacion);
            }
            em.persist(formulario);
            if (tipohabitacion != null) {
                tipohabitacion.getFormularioCollection().add(formulario);
                tipohabitacion = em.merge(tipohabitacion);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Formulario formulario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Formulario persistentFormulario = em.find(Formulario.class, formulario.getIDformulario());
            HabitacionTipo tipohabitacionOld = persistentFormulario.getTipohabitacion();
            HabitacionTipo tipohabitacionNew = formulario.getTipohabitacion();
            if (tipohabitacionNew != null) {
                tipohabitacionNew = em.getReference(tipohabitacionNew.getClass(), tipohabitacionNew.getNrohabitacion());
                formulario.setTipohabitacion(tipohabitacionNew);
            }
            formulario = em.merge(formulario);
            if (tipohabitacionOld != null && !tipohabitacionOld.equals(tipohabitacionNew)) {
                tipohabitacionOld.getFormularioCollection().remove(formulario);
                tipohabitacionOld = em.merge(tipohabitacionOld);
            }
            if (tipohabitacionNew != null && !tipohabitacionNew.equals(tipohabitacionOld)) {
                tipohabitacionNew.getFormularioCollection().add(formulario);
                tipohabitacionNew = em.merge(tipohabitacionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = formulario.getIDformulario();
                if (findFormulario(id) == null) {
                    throw new NonexistentEntityException("The formulario with id " + id + " no longer exists.");
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
            Formulario formulario;
            try {
                formulario = em.getReference(Formulario.class, id);
                formulario.getIDformulario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The formulario with id " + id + " no longer exists.", enfe);
            }
            HabitacionTipo tipohabitacion = formulario.getTipohabitacion();
            if (tipohabitacion != null) {
                tipohabitacion.getFormularioCollection().remove(formulario);
                tipohabitacion = em.merge(tipohabitacion);
            }
            em.remove(formulario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Formulario> findFormularioEntities() {
        return findFormularioEntities(true, -1, -1);
    }

    public List<Formulario> findFormularioEntities(int maxResults, int firstResult) {
        return findFormularioEntities(false, maxResults, firstResult);
    }

    private List<Formulario> findFormularioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Formulario.class));
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

    public Formulario findFormulario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Formulario.class, id);
        } finally {
            em.close();
        }
    }

    public int getFormularioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Formulario> rt = cq.from(Formulario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
