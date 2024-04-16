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
@Table(name = "habitacion_tipo")
@NamedQueries({
    @NamedQuery(name = "HabitacionTipo.findAll", query = "SELECT h FROM HabitacionTipo h"),
    @NamedQuery(name = "HabitacionTipo.findByNrohabitacion", query = "SELECT h FROM HabitacionTipo h WHERE h.nrohabitacion = :nrohabitacion"),
    @NamedQuery(name = "HabitacionTipo.findByTipodehabitacion", query = "SELECT h FROM HabitacionTipo h WHERE h.tipodehabitacion = :tipodehabitacion")})
public class HabitacionTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Nro_habitacion")
    private Integer nrohabitacion;
    @Basic(optional = false)
    @Column(name = "Tipo_de_habitacion")
    private String tipodehabitacion;
    @OneToMany(mappedBy = "tipohabitacion")
    private Collection<Formulario> formularioCollection;

    public HabitacionTipo() {
    }

    public HabitacionTipo(Integer nrohabitacion) {
        this.nrohabitacion = nrohabitacion;
    }

    public HabitacionTipo(Integer nrohabitacion, String tipodehabitacion) {
        this.nrohabitacion = nrohabitacion;
        this.tipodehabitacion = tipodehabitacion;
    }

    public Integer getNrohabitacion() {
        return nrohabitacion;
    }

    public void setNrohabitacion(Integer nrohabitacion) {
        this.nrohabitacion = nrohabitacion;
    }

    public String getTipodehabitacion() {
        return tipodehabitacion;
    }

    public void setTipodehabitacion(String tipodehabitacion) {
        this.tipodehabitacion = tipodehabitacion;
    }

    public Collection<Formulario> getFormularioCollection() {
        return formularioCollection;
    }

    public void setFormularioCollection(Collection<Formulario> formularioCollection) {
        this.formularioCollection = formularioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrohabitacion != null ? nrohabitacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HabitacionTipo)) {
            return false;
        }
        HabitacionTipo other = (HabitacionTipo) object;
        if ((this.nrohabitacion == null && other.nrohabitacion != null) || (this.nrohabitacion != null && !this.nrohabitacion.equals(other.nrohabitacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.HabitacionTipo[ nrohabitacion=" + nrohabitacion + " ]";
    }
    
}
