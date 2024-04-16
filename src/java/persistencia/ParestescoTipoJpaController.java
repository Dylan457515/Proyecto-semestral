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
import Clases.Paciente;
import Clases.ParestescoTipo;
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
public class ParestescoTipoJpaController implements Serializable {

    public ParestescoTipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public ParestescoTipoJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }
    
    public void create(ParestescoTipo parestescoTipo) {
        if (parestescoTipo.getPacienteCollection() == null) {
            parestescoTipo.setPacienteCollection(new ArrayList<Paciente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Paciente> attachedPacienteCollection = new ArrayList<Paciente>();
            for (Paciente pacienteCollectionPacienteToAttach : parestescoTipo.getPacienteCollection()) {
                pacienteCollectionPacienteToAttach = em.getReference(pacienteCollectionPacienteToAttach.getClass(), pacienteCollectionPacienteToAttach.getIDPaciente());
                attachedPacienteCollection.add(pacienteCollectionPacienteToAttach);
            }
            parestescoTipo.setPacienteCollection(attachedPacienteCollection);
            em.persist(parestescoTipo);
            for (Paciente pacienteCollectionPaciente : parestescoTipo.getPacienteCollection()) {
                ParestescoTipo oldParestescoResponsableOfPacienteCollectionPaciente = pacienteCollectionPaciente.getParestescoResponsable();
                pacienteCollectionPaciente.setParestescoResponsable(parestescoTipo);
                pacienteCollectionPaciente = em.merge(pacienteCollectionPaciente);
                if (oldParestescoResponsableOfPacienteCollectionPaciente != null) {
                    oldParestescoResponsableOfPacienteCollectionPaciente.getPacienteCollection().remove(pacienteCollectionPaciente);
                    oldParestescoResponsableOfPacienteCollectionPaciente = em.merge(oldParestescoResponsableOfPacienteCollectionPaciente);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ParestescoTipo parestescoTipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ParestescoTipo persistentParestescoTipo = em.find(ParestescoTipo.class, parestescoTipo.getIDparentesco());
            Collection<Paciente> pacienteCollectionOld = persistentParestescoTipo.getPacienteCollection();
            Collection<Paciente> pacienteCollectionNew = parestescoTipo.getPacienteCollection();
            List<String> illegalOrphanMessages = null;
            for (Paciente pacienteCollectionOldPaciente : pacienteCollectionOld) {
                if (!pacienteCollectionNew.contains(pacienteCollectionOldPaciente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Paciente " + pacienteCollectionOldPaciente + " since its parestescoResponsable field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Paciente> attachedPacienteCollectionNew = new ArrayList<Paciente>();
            for (Paciente pacienteCollectionNewPacienteToAttach : pacienteCollectionNew) {
                pacienteCollectionNewPacienteToAttach = em.getReference(pacienteCollectionNewPacienteToAttach.getClass(), pacienteCollectionNewPacienteToAttach.getIDPaciente());
                attachedPacienteCollectionNew.add(pacienteCollectionNewPacienteToAttach);
            }
            pacienteCollectionNew = attachedPacienteCollectionNew;
            parestescoTipo.setPacienteCollection(pacienteCollectionNew);
            parestescoTipo = em.merge(parestescoTipo);
            for (Paciente pacienteCollectionNewPaciente : pacienteCollectionNew) {
                if (!pacienteCollectionOld.contains(pacienteCollectionNewPaciente)) {
                    ParestescoTipo oldParestescoResponsableOfPacienteCollectionNewPaciente = pacienteCollectionNewPaciente.getParestescoResponsable();
                    pacienteCollectionNewPaciente.setParestescoResponsable(parestescoTipo);
                    pacienteCollectionNewPaciente = em.merge(pacienteCollectionNewPaciente);
                    if (oldParestescoResponsableOfPacienteCollectionNewPaciente != null && !oldParestescoResponsableOfPacienteCollectionNewPaciente.equals(parestescoTipo)) {
                        oldParestescoResponsableOfPacienteCollectionNewPaciente.getPacienteCollection().remove(pacienteCollectionNewPaciente);
                        oldParestescoResponsableOfPacienteCollectionNewPaciente = em.merge(oldParestescoResponsableOfPacienteCollectionNewPaciente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = parestescoTipo.getIDparentesco();
                if (findParestescoTipo(id) == null) {
                    throw new NonexistentEntityException("The parestescoTipo with id " + id + " no longer exists.");
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
            ParestescoTipo parestescoTipo;
            try {
                parestescoTipo = em.getReference(ParestescoTipo.class, id);
                parestescoTipo.getIDparentesco();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The parestescoTipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Paciente> pacienteCollectionOrphanCheck = parestescoTipo.getPacienteCollection();
            for (Paciente pacienteCollectionOrphanCheckPaciente : pacienteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ParestescoTipo (" + parestescoTipo + ") cannot be destroyed since the Paciente " + pacienteCollectionOrphanCheckPaciente + " in its pacienteCollection field has a non-nullable parestescoResponsable field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(parestescoTipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ParestescoTipo> findParestescoTipoEntities() {
        return findParestescoTipoEntities(true, -1, -1);
    }

    public List<ParestescoTipo> findParestescoTipoEntities(int maxResults, int firstResult) {
        return findParestescoTipoEntities(false, maxResults, firstResult);
    }

    private List<ParestescoTipo> findParestescoTipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ParestescoTipo.class));
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

    public ParestescoTipo findParestescoTipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ParestescoTipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getParestescoTipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ParestescoTipo> rt = cq.from(ParestescoTipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
