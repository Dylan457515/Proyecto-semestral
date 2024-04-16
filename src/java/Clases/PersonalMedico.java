/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dylan
 */
@Entity
@Table(name = "personal_medico")
@NamedQueries({
    @NamedQuery(name = "PersonalMedico.findAll", query = "SELECT p FROM PersonalMedico p"),
    @NamedQuery(name = "PersonalMedico.findByIDempleado", query = "SELECT p FROM PersonalMedico p WHERE p.iDempleado = :iDempleado"),
    @NamedQuery(name = "PersonalMedico.findByNombre", query = "SELECT p FROM PersonalMedico p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "PersonalMedico.findByApPaterno", query = "SELECT p FROM PersonalMedico p WHERE p.apPaterno = :apPaterno"),
    @NamedQuery(name = "PersonalMedico.findByApMaterno", query = "SELECT p FROM PersonalMedico p WHERE p.apMaterno = :apMaterno"),
    @NamedQuery(name = "PersonalMedico.findByDireccion", query = "SELECT p FROM PersonalMedico p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "PersonalMedico.findByTelefono", query = "SELECT p FROM PersonalMedico p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "PersonalMedico.findByFechaRegistro", query = "SELECT p FROM PersonalMedico p WHERE p.fechaRegistro = :fechaRegistro"),
    @NamedQuery(name = "PersonalMedico.findByFechaModificasion", query = "SELECT p FROM PersonalMedico p WHERE p.fechaModificasion = :fechaModificasion")})
public class PersonalMedico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_empleado")
    private Integer iDempleado;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "Ap_Paterno")
    private String apPaterno;
    @Basic(optional = false)
    @Column(name = "Ap_Materno")
    private String apMaterno;
    @Basic(optional = false)
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Telefono")
    private Integer telefono;
    @Column(name = "Fecha_Registro")
    @Temporal(TemporalType.DATE)
    private Date fechaRegistro;
    @Column(name = "Fecha_Modificasion")
    @Temporal(TemporalType.DATE)
    private Date fechaModificasion;
    @JoinColumn(name = "rol", referencedColumnName = "Nro_rol")
    @ManyToOne(optional = false)
    private RolTipo rol;

    public PersonalMedico() {
    }

    public PersonalMedico(Integer iDempleado) {
        this.iDempleado = iDempleado;
    }

    public PersonalMedico(Integer iDempleado, String nombre, String apPaterno, String apMaterno, String direccion) {
        this.iDempleado = iDempleado;
        this.nombre = nombre;
        this.apPaterno = apPaterno;
        this.apMaterno = apMaterno;
        this.direccion = direccion;
    }

    public Integer getIDempleado() {
        return iDempleado;
    }

    public void setIDempleado(Integer iDempleado) {
        this.iDempleado = iDempleado;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaModificasion() {
        return fechaModificasion;
    }

    public void setFechaModificasion(Date fechaModificasion) {
        this.fechaModificasion = fechaModificasion;
    }

    public RolTipo getRol() {
        return rol;
    }

    public void setRol(RolTipo rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDempleado != null ? iDempleado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalMedico)) {
            return false;
        }
        PersonalMedico other = (PersonalMedico) object;
        if ((this.iDempleado == null && other.iDempleado != null) || (this.iDempleado != null && !this.iDempleado.equals(other.iDempleado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.PersonalMedico[ iDempleado=" + iDempleado + " ]";
    }
    
}
