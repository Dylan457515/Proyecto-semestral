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
import Clases.PersonalMedico;
import Clases.RolTipo;
import java.util.ArrayList;
import java.util.Collection;
import Clases.Usuario;
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
public class RolTipoJpaController implements Serializable {

    public RolTipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public RolTipoJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }

    public void create(RolTipo rolTipo) {
        if (rolTipo.getPersonalMedicoCollection() == null) {
            rolTipo.setPersonalMedicoCollection(new ArrayList<PersonalMedico>());
        }
        if (rolTipo.getUsuarioCollection() == null) {
            rolTipo.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<PersonalMedico> attachedPersonalMedicoCollection = new ArrayList<PersonalMedico>();
            for (PersonalMedico personalMedicoCollectionPersonalMedicoToAttach : rolTipo.getPersonalMedicoCollection()) {
                personalMedicoCollectionPersonalMedicoToAttach = em.getReference(personalMedicoCollectionPersonalMedicoToAttach.getClass(), personalMedicoCollectionPersonalMedicoToAttach.getIDempleado());
                attachedPersonalMedicoCollection.add(personalMedicoCollectionPersonalMedicoToAttach);
            }
            rolTipo.setPersonalMedicoCollection(attachedPersonalMedicoCollection);
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : rolTipo.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getIDusuario());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            rolTipo.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(rolTipo);
            for (PersonalMedico personalMedicoCollectionPersonalMedico : rolTipo.getPersonalMedicoCollection()) {
                RolTipo oldRolOfPersonalMedicoCollectionPersonalMedico = personalMedicoCollectionPersonalMedico.getRol();
                personalMedicoCollectionPersonalMedico.setRol(rolTipo);
                personalMedicoCollectionPersonalMedico = em.merge(personalMedicoCollectionPersonalMedico);
                if (oldRolOfPersonalMedicoCollectionPersonalMedico != null) {
                    oldRolOfPersonalMedicoCollectionPersonalMedico.getPersonalMedicoCollection().remove(personalMedicoCollectionPersonalMedico);
                    oldRolOfPersonalMedicoCollectionPersonalMedico = em.merge(oldRolOfPersonalMedicoCollectionPersonalMedico);
                }
            }
            for (Usuario usuarioCollectionUsuario : rolTipo.getUsuarioCollection()) {
                RolTipo oldTipousuarioOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getTipousuario();
                usuarioCollectionUsuario.setTipousuario(rolTipo);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldTipousuarioOfUsuarioCollectionUsuario != null) {
                    oldTipousuarioOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldTipousuarioOfUsuarioCollectionUsuario = em.merge(oldTipousuarioOfUsuarioCollectionUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(RolTipo rolTipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolTipo persistentRolTipo = em.find(RolTipo.class, rolTipo.getNrorol());
            Collection<PersonalMedico> personalMedicoCollectionOld = persistentRolTipo.getPersonalMedicoCollection();
            Collection<PersonalMedico> personalMedicoCollectionNew = rolTipo.getPersonalMedicoCollection();
            Collection<Usuario> usuarioCollectionOld = persistentRolTipo.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = rolTipo.getUsuarioCollection();
            List<String> illegalOrphanMessages = null;
            for (PersonalMedico personalMedicoCollectionOldPersonalMedico : personalMedicoCollectionOld) {
                if (!personalMedicoCollectionNew.contains(personalMedicoCollectionOldPersonalMedico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PersonalMedico " + personalMedicoCollectionOldPersonalMedico + " since its rol field is not nullable.");
                }
            }
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioCollectionOldUsuario + " since its tipousuario field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<PersonalMedico> attachedPersonalMedicoCollectionNew = new ArrayList<PersonalMedico>();
            for (PersonalMedico personalMedicoCollectionNewPersonalMedicoToAttach : personalMedicoCollectionNew) {
                personalMedicoCollectionNewPersonalMedicoToAttach = em.getReference(personalMedicoCollectionNewPersonalMedicoToAttach.getClass(), personalMedicoCollectionNewPersonalMedicoToAttach.getIDempleado());
                attachedPersonalMedicoCollectionNew.add(personalMedicoCollectionNewPersonalMedicoToAttach);
            }
            personalMedicoCollectionNew = attachedPersonalMedicoCollectionNew;
            rolTipo.setPersonalMedicoCollection(personalMedicoCollectionNew);
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getIDusuario());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            rolTipo.setUsuarioCollection(usuarioCollectionNew);
            rolTipo = em.merge(rolTipo);
            for (PersonalMedico personalMedicoCollectionNewPersonalMedico : personalMedicoCollectionNew) {
                if (!personalMedicoCollectionOld.contains(personalMedicoCollectionNewPersonalMedico)) {
                    RolTipo oldRolOfPersonalMedicoCollectionNewPersonalMedico = personalMedicoCollectionNewPersonalMedico.getRol();
                    personalMedicoCollectionNewPersonalMedico.setRol(rolTipo);
                    personalMedicoCollectionNewPersonalMedico = em.merge(personalMedicoCollectionNewPersonalMedico);
                    if (oldRolOfPersonalMedicoCollectionNewPersonalMedico != null && !oldRolOfPersonalMedicoCollectionNewPersonalMedico.equals(rolTipo)) {
                        oldRolOfPersonalMedicoCollectionNewPersonalMedico.getPersonalMedicoCollection().remove(personalMedicoCollectionNewPersonalMedico);
                        oldRolOfPersonalMedicoCollectionNewPersonalMedico = em.merge(oldRolOfPersonalMedicoCollectionNewPersonalMedico);
                    }
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    RolTipo oldTipousuarioOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getTipousuario();
                    usuarioCollectionNewUsuario.setTipousuario(rolTipo);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldTipousuarioOfUsuarioCollectionNewUsuario != null && !oldTipousuarioOfUsuarioCollectionNewUsuario.equals(rolTipo)) {
                        oldTipousuarioOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldTipousuarioOfUsuarioCollectionNewUsuario = em.merge(oldTipousuarioOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rolTipo.getNrorol();
                if (findRolTipo(id) == null) {
                    throw new NonexistentEntityException("The rolTipo with id " + id + " no longer exists.");
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
            RolTipo rolTipo;
            try {
                rolTipo = em.getReference(RolTipo.class, id);
                rolTipo.getNrorol();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rolTipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<PersonalMedico> personalMedicoCollectionOrphanCheck = rolTipo.getPersonalMedicoCollection();
            for (PersonalMedico personalMedicoCollectionOrphanCheckPersonalMedico : personalMedicoCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RolTipo (" + rolTipo + ") cannot be destroyed since the PersonalMedico " + personalMedicoCollectionOrphanCheckPersonalMedico + " in its personalMedicoCollection field has a non-nullable rol field.");
            }
            Collection<Usuario> usuarioCollectionOrphanCheck = rolTipo.getUsuarioCollection();
            for (Usuario usuarioCollectionOrphanCheckUsuario : usuarioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This RolTipo (" + rolTipo + ") cannot be destroyed since the Usuario " + usuarioCollectionOrphanCheckUsuario + " in its usuarioCollection field has a non-nullable tipousuario field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rolTipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<RolTipo> findRolTipoEntities() {
        return findRolTipoEntities(true, -1, -1);
    }

    public List<RolTipo> findRolTipoEntities(int maxResults, int firstResult) {
        return findRolTipoEntities(false, maxResults, firstResult);
    }

    private List<RolTipo> findRolTipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(RolTipo.class));
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

    public RolTipo findRolTipo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(RolTipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolTipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<RolTipo> rt = cq.from(RolTipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
