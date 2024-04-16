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
import Clases.Formulario;
import java.util.ArrayList;
import java.util.Collection;
import Clases.CitasMedicas;
import Clases.Doctores;
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
public class DoctoresJpaController implements Serializable {

    public DoctoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public DoctoresJpaController() {
        emf = Persistence.createEntityManagerFactory("poyecto");
    }

    public void create(Doctores doctores) {
        if (doctores.getFormularioCollection() == null) {
            doctores.setFormularioCollection(new ArrayList<Formulario>());
        }
        if (doctores.getCitasMedicasCollection() == null) {
            doctores.setCitasMedicasCollection(new ArrayList<CitasMedicas>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            RolTipo espeialidad = doctores.getEspeialidad();
            if (espeialidad != null) {
                espeialidad = em.getReference(espeialidad.getClass(), espeialidad.getNrorol());
                doctores.setEspeialidad(espeialidad);
            }
            Collection<Formulario> attachedFormularioCollection = new ArrayList<Formulario>();
            for (Formulario formularioCollectionFormularioToAttach : doctores.getFormularioCollection()) {
                formularioCollectionFormularioToAttach = em.getReference(formularioCollectionFormularioToAttach.getClass(), formularioCollectionFormularioToAttach.getIDformulario());
                attachedFormularioCollection.add(formularioCollectionFormularioToAttach);
            }
            doctores.setFormularioCollection(attachedFormularioCollection);
            Collection<CitasMedicas> attachedCitasMedicasCollection = new ArrayList<CitasMedicas>();
            for (CitasMedicas citasMedicasCollectionCitasMedicasToAttach : doctores.getCitasMedicasCollection()) {
                citasMedicasCollectionCitasMedicasToAttach = em.getReference(citasMedicasCollectionCitasMedicasToAttach.getClass(), citasMedicasCollectionCitasMedicasToAttach.getIDCitas());
                attachedCitasMedicasCollection.add(citasMedicasCollectionCitasMedicasToAttach);
            }
            doctores.setCitasMedicasCollection(attachedCitasMedicasCollection);
            em.persist(doctores);
            if (espeialidad != null) {
                espeialidad.getDoctoresCollection().add(doctores);
                espeialidad = em.merge(espeialidad);
            }
            for (Formulario formularioCollectionFormulario : doctores.getFormularioCollection()) {
                Doctores oldIDDoctorOfFormularioCollectionFormulario = formularioCollectionFormulario.getIDDoctor();
                formularioCollectionFormulario.setIDDoctor(doctores);
                formularioCollectionFormulario = em.merge(formularioCollectionFormulario);
                if (oldIDDoctorOfFormularioCollectionFormulario != null) {
                    oldIDDoctorOfFormularioCollectionFormulario.getFormularioCollection().remove(formularioCollectionFormulario);
                    oldIDDoctorOfFormularioCollectionFormulario = em.merge(oldIDDoctorOfFormularioCollectionFormulario);
                }
            }
            for (CitasMedicas citasMedicasCollectionCitasMedicas : doctores.getCitasMedicasCollection()) {
                Doctores oldIDDoctorOfCitasMedicasCollectionCitasMedicas = citasMedicasCollectionCitasMedicas.getIDDoctor();
                citasMedicasCollectionCitasMedicas.setIDDoctor(doctores);
                citasMedicasCollectionCitasMedicas = em.merge(citasMedicasCollectionCitasMedicas);
                if (oldIDDoctorOfCitasMedicasCollectionCitasMedicas != null) {
                    oldIDDoctorOfCitasMedicasCollectionCitasMedicas.getCitasMedicasCollection().remove(citasMedicasCollectionCitasMedicas);
                    oldIDDoctorOfCitasMedicasCollectionCitasMedicas = em.merge(oldIDDoctorOfCitasMedicasCollectionCitasMedicas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Doctores doctores) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Doctores persistentDoctores = em.find(Doctores.class, doctores.getIDDoctor());
            RolTipo espeialidadOld = persistentDoctores.getEspeialidad();
            RolTipo espeialidadNew = doctores.getEspeialidad();
            Collection<Formulario> formularioCollectionOld = persistentDoctores.getFormularioCollection();
            Collection<Formulario> formularioCollectionNew = doctores.getFormularioCollection();
            Collection<CitasMedicas> citasMedicasCollectionOld = persistentDoctores.getCitasMedicasCollection();
            Collection<CitasMedicas> citasMedicasCollectionNew = doctores.getCitasMedicasCollection();
            List<String> illegalOrphanMessages = null;
            for (Formulario formularioCollectionOldFormulario : formularioCollectionOld) {
                if (!formularioCollectionNew.contains(formularioCollectionOldFormulario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Formulario " + formularioCollectionOldFormulario + " since its IDDoctor field is not nullable.");
                }
            }
            for (CitasMedicas citasMedicasCollectionOldCitasMedicas : citasMedicasCollectionOld) {
                if (!citasMedicasCollectionNew.contains(citasMedicasCollectionOldCitasMedicas)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CitasMedicas " + citasMedicasCollectionOldCitasMedicas + " since its IDDoctor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (espeialidadNew != null) {
                espeialidadNew = em.getReference(espeialidadNew.getClass(), espeialidadNew.getNrorol());
                doctores.setEspeialidad(espeialidadNew);
            }
            Collection<Formulario> attachedFormularioCollectionNew = new ArrayList<Formulario>();
            for (Formulario formularioCollectionNewFormularioToAttach : formularioCollectionNew) {
                formularioCollectionNewFormularioToAttach = em.getReference(formularioCollectionNewFormularioToAttach.getClass(), formularioCollectionNewFormularioToAttach.getIDformulario());
                attachedFormularioCollectionNew.add(formularioCollectionNewFormularioToAttach);
            }
            formularioCollectionNew = attachedFormularioCollectionNew;
            doctores.setFormularioCollection(formularioCollectionNew);
            Collection<CitasMedicas> attachedCitasMedicasCollectionNew = new ArrayList<CitasMedicas>();
            for (CitasMedicas citasMedicasCollectionNewCitasMedicasToAttach : citasMedicasCollectionNew) {
                citasMedicasCollectionNewCitasMedicasToAttach = em.getReference(citasMedicasCollectionNewCitasMedicasToAttach.getClass(), citasMedicasCollectionNewCitasMedicasToAttach.getIDCitas());
                attachedCitasMedicasCollectionNew.add(citasMedicasCollectionNewCitasMedicasToAttach);
            }
            citasMedicasCollectionNew = attachedCitasMedicasCollectionNew;
            doctores.setCitasMedicasCollection(citasMedicasCollectionNew);
            doctores = em.merge(doctores);
            if (espeialidadOld != null && !espeialidadOld.equals(espeialidadNew)) {
                espeialidadOld.getDoctoresCollection().remove(doctores);
                espeialidadOld = em.merge(espeialidadOld);
            }
            if (espeialidadNew != null && !espeialidadNew.equals(espeialidadOld)) {
                espeialidadNew.getDoctoresCollection().add(doctores);
                espeialidadNew = em.merge(espeialidadNew);
            }
            for (Formulario formularioCollectionNewFormulario : formularioCollectionNew) {
                if (!formularioCollectionOld.contains(formularioCollectionNewFormulario)) {
                    Doctores oldIDDoctorOfFormularioCollectionNewFormulario = formularioCollectionNewFormulario.getIDDoctor();
                    formularioCollectionNewFormulario.setIDDoctor(doctores);
                    formularioCollectionNewFormulario = em.merge(formularioCollectionNewFormulario);
                    if (oldIDDoctorOfFormularioCollectionNewFormulario != null && !oldIDDoctorOfFormularioCollectionNewFormulario.equals(doctores)) {
                        oldIDDoctorOfFormularioCollectionNewFormulario.getFormularioCollection().remove(formularioCollectionNewFormulario);
                        oldIDDoctorOfFormularioCollectionNewFormulario = em.merge(oldIDDoctorOfFormularioCollectionNewFormulario);
                    }
                }
            }
            for (CitasMedicas citasMedicasCollectionNewCitasMedicas : citasMedicasCollectionNew) {
                if (!citasMedicasCollectionOld.contains(citasMedicasCollectionNewCitasMedicas)) {
                    Doctores oldIDDoctorOfCitasMedicasCollectionNewCitasMedicas = citasMedicasCollectionNewCitasMedicas.getIDDoctor();
                    citasMedicasCollectionNewCitasMedicas.setIDDoctor(doctores);
                    citasMedicasCollectionNewCitasMedicas = em.merge(citasMedicasCollectionNewCitasMedicas);
                    if (oldIDDoctorOfCitasMedicasCollectionNewCitasMedicas != null && !oldIDDoctorOfCitasMedicasCollectionNewCitasMedicas.equals(doctores)) {
                        oldIDDoctorOfCitasMedicasCollectionNewCitasMedicas.getCitasMedicasCollection().remove(citasMedicasCollectionNewCitasMedicas);
                        oldIDDoctorOfCitasMedicasCollectionNewCitasMedicas = em.merge(oldIDDoctorOfCitasMedicasCollectionNewCitasMedicas);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = doctores.getIDDoctor();
                if (findDoctores(id) == null) {
                    throw new NonexistentEntityException("The doctores with id " + id + " no longer exists.");
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
            Doctores doctores;
            try {
                doctores = em.getReference(Doctores.class, id);
                doctores.getIDDoctor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The doctores with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Formulario> formularioCollectionOrphanCheck = doctores.getFormularioCollection();
            for (Formulario formularioCollectionOrphanCheckFormulario : formularioCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Doctores (" + doctores + ") cannot be destroyed since the Formulario " + formularioCollectionOrphanCheckFormulario + " in its formularioCollection field has a non-nullable IDDoctor field.");
            }
            Collection<CitasMedicas> citasMedicasCollectionOrphanCheck = doctores.getCitasMedicasCollection();
            for (CitasMedicas citasMedicasCollectionOrphanCheckCitasMedicas : citasMedicasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Doctores (" + doctores + ") cannot be destroyed since the CitasMedicas " + citasMedicasCollectionOrphanCheckCitasMedicas + " in its citasMedicasCollection field has a non-nullable IDDoctor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            RolTipo espeialidad = doctores.getEspeialidad();
            if (espeialidad != null) {
                espeialidad.getDoctoresCollection().remove(doctores);
                espeialidad = em.merge(espeialidad);
            }
            em.remove(doctores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Doctores> findDoctoresEntities() {
        return findDoctoresEntities(true, -1, -1);
    }

    public List<Doctores> findDoctoresEntities(int maxResults, int firstResult) {
        return findDoctoresEntities(false, maxResults, firstResult);
    }

    private List<Doctores> findDoctoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Doctores.class));
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

    public Doctores findDoctores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Doctores.class, id);
        } finally {
            em.close();
        }
    }

    public int getDoctoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Doctores> rt = cq.from(Doctores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
