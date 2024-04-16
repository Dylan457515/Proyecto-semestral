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
import Clases.RolTipo;
import Clases.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import persistencia.exceptions.NonexistentEntityException;

/**
 *
 * @author dylan
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public UsuarioJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }
    

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolTipo tipousuario = usuario.getTipousuario();
            if (tipousuario != null) {
                tipousuario = em.getReference(tipousuario.getClass(), tipousuario.getNrorol());
                usuario.setTipousuario(tipousuario);
            }
            em.persist(usuario);
            if (tipousuario != null) {
                tipousuario.getUsuarioCollection().add(usuario);
                tipousuario = em.merge(tipousuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIDusuario());
            RolTipo tipousuarioOld = persistentUsuario.getTipousuario();
            RolTipo tipousuarioNew = usuario.getTipousuario();
            if (tipousuarioNew != null) {
                tipousuarioNew = em.getReference(tipousuarioNew.getClass(), tipousuarioNew.getNrorol());
                usuario.setTipousuario(tipousuarioNew);
            }
            usuario = em.merge(usuario);
            if (tipousuarioOld != null && !tipousuarioOld.equals(tipousuarioNew)) {
                tipousuarioOld.getUsuarioCollection().remove(usuario);
                tipousuarioOld = em.merge(tipousuarioOld);
            }
            if (tipousuarioNew != null && !tipousuarioNew.equals(tipousuarioOld)) {
                tipousuarioNew.getUsuarioCollection().add(usuario);
                tipousuarioNew = em.merge(tipousuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIDusuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIDusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            RolTipo tipousuario = usuario.getTipousuario();
            if (tipousuario != null) {
                tipousuario.getUsuarioCollection().remove(usuario);
                tipousuario = em.merge(tipousuario);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Usuario findLastUsuario() {
    EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT u FROM Usuario u ORDER BY u.iDusuario DESC");
            query.setMaxResults(1);
            return (Usuario) query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
}
