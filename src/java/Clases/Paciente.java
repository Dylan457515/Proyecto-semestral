/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dylan
 */
@Entity
@Table(name = "paciente")
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p"),
    @NamedQuery(name = "Paciente.findByIDPaciente", query = "SELECT p FROM Paciente p WHERE p.iDPaciente = :iDPaciente"),
    @NamedQuery(name = "Paciente.findByIDusuario", query = "SELECT p FROM Paciente p WHERE p.iDusuario = :iDusuario"),
    @NamedQuery(name = "Paciente.findByNombre", query = "SELECT p FROM Paciente p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Paciente.findBySexo", query = "SELECT p FROM Paciente p WHERE p.sexo = :sexo"),
    @NamedQuery(name = "Paciente.findByApPaterno", query = "SELECT p FROM Paciente p WHERE p.apPaterno = :apPaterno"),
    @NamedQuery(name = "Paciente.findByApMaterno", query = "SELECT p FROM Paciente p WHERE p.apMaterno = :apMaterno"),
    @NamedQuery(name = "Paciente.findByFechadenacimiento", query = "SELECT p FROM Paciente p WHERE p.fechadenacimiento = :fechadenacimiento"),
    @NamedQuery(name = "Paciente.findByDireccion", query = "SELECT p FROM Paciente p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Paciente.findByTelefono", query = "SELECT p FROM Paciente p WHERE p.telefono = :telefono"),
    @NamedQuery(name = "Paciente.findByReponsable", query = "SELECT p FROM Paciente p WHERE p.reponsable = :reponsable"),
    @NamedQuery(name = "Paciente.findByTelefonoreponsable", query = "SELECT p FROM Paciente p WHERE p.telefonoreponsable = :telefonoreponsable")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Paciente")
    private Integer iDPaciente;
    @Column(name = "ID_usuario")
    private Integer iDusuario;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "Ap_Paterno")
    private String apPaterno;
    @Column(name = "Ap_Materno")
    private String apMaterno;
    @Column(name = "Fecha_de_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechadenacimiento;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Telefono")
    private Integer telefono;
    @Column(name = "Reponsable")
    private String reponsable;
    @Column(name = "Telefono_reponsable")
    private Integer telefonoreponsable;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDPaciente")
    private Collection<Formulario> formularioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "iDPaciente")
    private Collection<CitasMedicas> citasMedicasCollection;
    @JoinColumn(name = "Parestesco_Responsable", referencedColumnName = "ID_parentesco")
    @ManyToOne
    private ParestescoTipo parestescoResponsable;

    public Paciente() {
    }

    public Paciente(Integer iDPaciente) {
        this.iDPaciente = iDPaciente;
    }

    public Paciente(Integer iDPaciente, String nombre) {
        this.iDPaciente = iDPaciente;
        this.nombre = nombre;
    }

    public Integer getIDPaciente() {
        return iDPaciente;
    }

    public void setIDPaciente(Integer iDPaciente) {
        this.iDPaciente = iDPaciente;
    }

    public Integer getIDusuario() {
        return iDusuario;
    }

    public void setIDusuario(Integer iDusuario) {
        this.iDusuario = iDusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public Date getFechadenacimiento() {
        return fechadenacimiento;
    }

    public void setFechadenacimiento(Date fechadenacimiento) {
        this.fechadenacimiento = fechadenacimiento;
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

    public String getReponsable() {
        return reponsable;
    }

    public void setReponsable(String reponsable) {
        this.reponsable = reponsable;
    }

    public Integer getTelefonoreponsable() {
        return telefonoreponsable;
    }

    public void setTelefonoreponsable(Integer telefonoreponsable) {
        this.telefonoreponsable = telefonoreponsable;
    }

    public Collection<Formulario> getFormularioCollection() {
        return formularioCollection;
    }

    public void setFormularioCollection(Collection<Formulario> formularioCollection) {
        this.formularioCollection = formularioCollection;
    }

    public Collection<CitasMedicas> getCitasMedicasCollection() {
        return citasMedicasCollection;
    }

    public void setCitasMedicasCollection(Collection<CitasMedicas> citasMedicasCollection) {
        this.citasMedicasCollection = citasMedicasCollection;
    }

    public ParestescoTipo getParestescoResponsable() {
        return parestescoResponsable;
    }

    public void setParestescoResponsable(ParestescoTipo parestescoResponsable) {
        this.parestescoResponsable = parestescoResponsable;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDPaciente != null ? iDPaciente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.iDPaciente == null && other.iDPaciente != null) || (this.iDPaciente != null && !this.iDPaciente.equals(other.iDPaciente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Paciente[ iDPaciente=" + iDPaciente + " ]";
    }
    
}
