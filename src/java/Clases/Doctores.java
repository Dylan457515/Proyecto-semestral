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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author dylan
 */
@Entity
@Table(name = "doctores")
@NamedQueries({
    @NamedQuery(name = "Doctores.findAll", query = "SELECT d FROM Doctores d"),
    @NamedQuery(name = "Doctores.findByIDDoctor", query = "SELECT d FROM Doctores d WHERE d.iDDoctor = :iDDoctor"),
    @NamedQuery(name = "Doctores.findByIDusuario", query = "SELECT d FROM Doctores d WHERE d.iDusuario = :iDusuario"),
    @NamedQuery(name = "Doctores.findByNombre", query = "SELECT d FROM Doctores d WHERE d.nombre = :nombre"),
    @NamedQuery(name = "Doctores.findByApPaterno", query = "SELECT d FROM Doctores d WHERE d.apPaterno = :apPaterno"),
    @NamedQuery(name = "Doctores.findByApMaterno", query = "SELECT d FROM Doctores d WHERE d.apMaterno = :apMaterno"),
    @NamedQuery(name = "Doctores.findByDireccion", query = "SELECT d FROM Doctores d WHERE d.direccion = :direccion"),
    @NamedQuery(name = "Doctores.findByTelefono", query = "SELECT d FROM Doctores d WHERE d.telefono = :telefono")})
public class Doctores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Doctor")
    private Integer iDDoctor;
    @Basic(optional = false)
    @Column(name = "ID_usuario")
    private int iDusuario;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Ap_Paterno")
    private String apPaterno;
    @Column(name = "Ap_Materno")
    private String apMaterno;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Telefono")
    private Integer telefono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDDoctor")
    private Collection<Formulario> formularioCollection;
    @JoinColumn(name = "Espeialidad", referencedColumnName = "Nro_rol")
    @ManyToOne
    private RolTipo espeialidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDDoctor")
    private Collection<CitasMedicas> citasMedicasCollection;

    public Doctores() {
    }

    public Doctores(Integer iDDoctor) {
        this.iDDoctor = iDDoctor;
    }

    public Doctores(Integer iDDoctor, int iDusuario, String nombre) {
        this.iDDoctor = iDDoctor;
        this.iDusuario = iDusuario;
        this.nombre = nombre;
    }

    public Integer getIDDoctor() {
        return iDDoctor;
    }

    public void setIDDoctor(Integer iDDoctor) {
        this.iDDoctor = iDDoctor;
    }

    public int getIDusuario() {
        return iDusuario;
    }

    public void setIDusuario(int iDusuario) {
        this.iDusuario = iDusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Collection<Formulario> getFormularioCollection() {
        return formularioCollection;
    }

    public void setFormularioCollection(Collection<Formulario> formularioCollection) {
        this.formularioCollection = formularioCollection;
    }

    public RolTipo getEspeialidad() {
        return espeialidad;
    }

    public void setEspeialidad(RolTipo espeialidad) {
        this.espeialidad = espeialidad;
    }

    public Collection<CitasMedicas> getCitasMedicasCollection() {
        return citasMedicasCollection;
    }

    public void setCitasMedicasCollection(Collection<CitasMedicas> citasMedicasCollection) {
        this.citasMedicasCollection = citasMedicasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDDoctor != null ? iDDoctor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Doctores)) {
            return false;
        }
        Doctores other = (Doctores) object;
        if ((this.iDDoctor == null && other.iDDoctor != null) || (this.iDDoctor != null && !this.iDDoctor.equals(other.iDDoctor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Doctores[ iDDoctor=" + iDDoctor + " ]";
    }
    
}
