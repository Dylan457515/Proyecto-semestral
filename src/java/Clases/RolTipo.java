/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dylan
 */
@Entity
@Table(name = "rol_tipo")
@NamedQueries({
    @NamedQuery(name = "RolTipo.findAll", query = "SELECT r FROM RolTipo r"),
    @NamedQuery(name = "RolTipo.findByNrorol", query = "SELECT r FROM RolTipo r WHERE r.nrorol = :nrorol"),
    @NamedQuery(name = "RolTipo.findByTipoderol", query = "SELECT r FROM RolTipo r WHERE r.tipoderol = :tipoderol")})
public class RolTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Nro_rol")
    private Integer nrorol;
    @Basic(optional = false)
    @Column(name = "Tipo_de_rol")
    private String tipoderol;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rol")
    private Collection<PersonalMedico> personalMedicoCollection;
    @OneToMany(mappedBy = "espeialidad")
    private Collection<Doctores> doctoresCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipousuario")
    private Collection<Usuario> usuarioCollection;

    public RolTipo() {
    }

    public RolTipo(Integer nrorol) {
        this.nrorol = nrorol;
    }

    public RolTipo(Integer nrorol, String tipoderol) {
        this.nrorol = nrorol;
        this.tipoderol = tipoderol;
    }

    public Integer getNrorol() {
        return nrorol;
    }

    public void setNrorol(Integer nrorol) {
        this.nrorol = nrorol;
    }

    public String getTipoderol() {
        return tipoderol;
    }

    public void setTipoderol(String tipoderol) {
        this.tipoderol = tipoderol;
    }

    public Collection<PersonalMedico> getPersonalMedicoCollection() {
        return personalMedicoCollection;
    }

    public void setPersonalMedicoCollection(Collection<PersonalMedico> personalMedicoCollection) {
        this.personalMedicoCollection = personalMedicoCollection;
    }

    public Collection<Doctores> getDoctoresCollection() {
        return doctoresCollection;
    }

    public void setDoctoresCollection(Collection<Doctores> doctoresCollection) {
        this.doctoresCollection = doctoresCollection;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrorol != null ? nrorol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolTipo)) {
            return false;
        }
        RolTipo other = (RolTipo) object;
        if ((this.nrorol == null && other.nrorol != null) || (this.nrorol != null && !this.nrorol.equals(other.nrorol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.RolTipo[ nrorol=" + nrorol + " ]";
    }
    
}
