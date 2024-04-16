/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "parestesco_tipo")
@NamedQueries({
    @NamedQuery(name = "ParestescoTipo.findAll", query = "SELECT p FROM ParestescoTipo p"),
    @NamedQuery(name = "ParestescoTipo.findByIDparentesco", query = "SELECT p FROM ParestescoTipo p WHERE p.iDparentesco = :iDparentesco"),
    @NamedQuery(name = "ParestescoTipo.findByParestescoResponsable", query = "SELECT p FROM ParestescoTipo p WHERE p.parestescoResponsable = :parestescoResponsable")})
public class ParestescoTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_parentesco")
    private Integer iDparentesco;
    @Basic(optional = false)
    @Column(name = "Parestesco_Responsable")
    private String parestescoResponsable;
    @OneToMany(mappedBy = "parestescoResponsable")
    private Collection<Paciente> pacienteCollection;

    public ParestescoTipo() {
    }

    public ParestescoTipo(Integer iDparentesco) {
        this.iDparentesco = iDparentesco;
    }

    public ParestescoTipo(Integer iDparentesco, String parestescoResponsable) {
        this.iDparentesco = iDparentesco;
        this.parestescoResponsable = parestescoResponsable;
    }

    public Integer getIDparentesco() {
        return iDparentesco;
    }

    public void setIDparentesco(Integer iDparentesco) {
        this.iDparentesco = iDparentesco;
    }

    public String getParestescoResponsable() {
        return parestescoResponsable;
    }

    public void setParestescoResponsable(String parestescoResponsable) {
        this.parestescoResponsable = parestescoResponsable;
    }

    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDparentesco != null ? iDparentesco.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParestescoTipo)) {
            return false;
        }
        ParestescoTipo other = (ParestescoTipo) object;
        if ((this.iDparentesco == null && other.iDparentesco != null) || (this.iDparentesco != null && !this.iDparentesco.equals(other.iDparentesco))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.ParestescoTipo[ iDparentesco=" + iDparentesco + " ]";
    }
    
}
