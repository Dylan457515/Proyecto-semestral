/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Clases.Formulario;
import Clases.HabitacionTipo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.IllegalOrphanException;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author dylan
 */
public class HabitacionTipoJpaController implements Serializable {

    public HabitacionTipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public HabitacionTipoJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }

    public void create(HabitacionTipo habitacionTipo) {
        if (habitacionTipo.getFormularioCollection() == null) {
            habitacionTipo.setFormularioCollection(new ArrayList<Formulario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Formulario> attachedFormularioCollection = new ArrayList<Formulario>();
            for (Formulario formularioCollectionFormularioToAttach : habitacionTipo.getFormularioCollection()) {
                formularioCollectionFormularioToAttach = em.getReference(formularioCollectionFormularioToAttach.getClass(), formularioCollectionFormularioToAttach.getIDformulario());
                attachedFormularioCollection.add(formularioCollectionFormularioToAttach);
            }
            habitacionTipo.setFormularioCollection(attachedFormularioCollection);
            em.persist(habitacionTipo);
            for (Formulario formularioCollectionFormulario : habitacionTipo.getFormularioCollection()) {
                HabitacionTipo oldTipohabitacionOfFormularioCollectionFormulario = formularioCollectionFormulario.getTipohabitacion();
                formularioCollectionFormulario.setTipohabitacion(habitacionTipo);
                formularioCollectionFormulario = em.merge(formularioCollectionFormulario);
                if (oldTipohabitacionOfFormularioCollectionFormulario != null) {
                    oldTipohabitacionOfFormularioCollectionFormulario.getFormularioCollection().remove(formularioCollectionFormulario);
                    oldTipohabitacionOfFormularioCollectionFormulario = em.merge(oldTipohabitacionOfFormularioCollectionFormulario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HabitacionTipo habitacionTipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HabitacionTipo persistentHabitacionTipo = em.find(HabitacionTipo.class, habitacionTipo.getNrohabitacion());
            Collection<Formulario> formularioCollectionOld = persistentHabitacionTipo.getFormularioCollection();
            Collection<Formulario> formularioCollectionNew = habitacionTipo.getFormularioCollection();
            List<String> illegalOrphanMessages = null;
            for (Formulario formularioCollectionOldFormulario : formularioCollectionOld) {
                if (!formularioCollectionNew.contains(formularioCollectionOldFormulario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Formulario " + formularioCollectionOldFormulario + " since its tipohabitacion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Formulario> attachedFormularioCollectionNew = new ArrayList<Formulario>();
            for (Formulario formularioCollectionNewFormularioToAttach : formularioCollectionNew) {
                formularioCollectionNewFormularioToAttach = em.getReference(formularioCollectionNewFormularioToAttach.getClass(), formularioCollectionNewFormularioToAttach.getIDformulario());
                attachedFormularioCollectionNew.add(formularioCollectionNewFormularioToAttach);
            }
            formularioCollectionNew = attachedFormularioCollectionNew;
            habitacionTipo.setFormularioCollection(formularioCollectionNew);
            habitacionTipo = em.merge(habitacionTipo);
            for (Formulario formularioCollectionNewFormulario : formularioCollectionNew) {
                if (!formularioCollectionOld.contains(formularioCollectionNewFormulario)) {
                    HabitacionTipo oldTipohabitacionOfFormularioCollectionNewFormulario = formularioCollectionNewFormulario.getTipohabitacion();
                    formularioCollectionNewFormulario.setTipohabitacion(habitacionTipo);
                    formularioCollectionNewFormulario = em.merge(formularioCollectionNewFormulario);
                    if (oldTipohabitacionOfFormularioCollectionNewFormulario != null && !oldTipohabitacionOfFormularioCollectionNewFormulario.equals(habitacionTipo)) {
                        oldTipohabitacionOfFormularioCollectionNewFormulario.getFormularioCollection().remove(formularioCollectionNewFormulario);
                        oldTipohabitacionOfFormularioCollectionNewFormulario = em.merge(oldTipohabitacionOfFormularioCollectionNewFormulario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = habitacionTipo.getNrohabitacion();
                if (findHabitacionTipo(id) == null) {
                    throw new NonexistentEntityException("The habitacionTipo with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HabitacionTipo habitacionTipo;
            try {
                habitacionTipo = em.getReference(HabitacionTipo.class, id);
                habitacionTipo.getNrohabitacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habitacionTipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Formulario> formularioCollectionOrphanCheck = habitacionTipo.getFormularioCollection();
            for (Formulario formularioCollectionOrphanCheckFormulario : formularioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This HabitacionTipo (" + habitacionTipo + ") cannot be destroyed since the Formulario " + formularioCollectionOrphanCheckFormulario + " in its formularioCollection field has a non-nullable tipohabitacion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(habitacionTipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HabitacionTipo> findHabitacionTipoEntities() {
        return findHabitacionTipoEntities(true, -1, -1);
    }

    public List<HabitacionTipo> findHabitacionTipoEntities(int maxResults, int firstResult) {
        return findHabitacionTipoEntities(false, maxResults, firstResult);
    }

    private List<HabitacionTipo> findHabitacionTipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HabitacionTipo.class));
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

    public HabitacionTipo findHabitacionTipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HabitacionTipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getHabitacionTipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HabitacionTipo> rt = cq.from(HabitacionTipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
