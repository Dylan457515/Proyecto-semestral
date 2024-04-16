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
import javax.persistence.Lob;
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
@Table(name = "formulario")
@NamedQueries({
    @NamedQuery(name = "Formulario.findAll", query = "SELECT f FROM Formulario f"),
    @NamedQuery(name = "Formulario.findByIDformulario", query = "SELECT f FROM Formulario f WHERE f.iDformulario = :iDformulario"),
    @NamedQuery(name = "Formulario.findByNombre", query = "SELECT f FROM Formulario f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "Formulario.findBySexo", query = "SELECT f FROM Formulario f WHERE f.sexo = :sexo"),
    @NamedQuery(name = "Formulario.findByDireccion", query = "SELECT f FROM Formulario f WHERE f.direccion = :direccion"),
    @NamedQuery(name = "Formulario.findByTelefono", query = "SELECT f FROM Formulario f WHERE f.telefono = :telefono"),
    @NamedQuery(name = "Formulario.findByHabitacion", query = "SELECT f FROM Formulario f WHERE f.habitacion = :habitacion"),
    @NamedQuery(name = "Formulario.findByFechaingreso", query = "SELECT f FROM Formulario f WHERE f.fechaingreso = :fechaingreso"),
    @NamedQuery(name = "Formulario.findByHoraingreso", query = "SELECT f FROM Formulario f WHERE f.horaingreso = :horaingreso"),
    @NamedQuery(name = "Formulario.findByFechaalta", query = "SELECT f FROM Formulario f WHERE f.fechaalta = :fechaalta"),
    @NamedQuery(name = "Formulario.findByTotalpagar", query = "SELECT f FROM Formulario f WHERE f.totalpagar = :totalpagar")})
public class Formulario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_formulario")
    private Integer iDformulario;
    @Column(name = "Nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "sexo")
    private String sexo;
    @Column(name = "Direccion")
    private String direccion;
    @Column(name = "Telefono")
    private Integer telefono;
    @Column(name = "habitacion")
    private Integer habitacion;
    @Column(name = "Fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @Column(name = "Hora_ingreso")
    @Temporal(TemporalType.TIME)
    private Date horaingreso;
    @Basic(optional = false)
    @Lob
    @Column(name = "Motivo")
    private String motivo;
    @Basic(optional = false)
    @Lob
    @Column(name = "Diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @Lob
    @Column(name = "Tratamiento")
    private String tratamiento;
    @Lob
    @Column(name = "Notas")
    private String notas;
    @Basic(optional = false)
    @Column(name = "Fecha_alta")
    @Temporal(TemporalType.DATE)
    private Date fechaalta;
    @Basic(optional = false)
    @Column(name = "Total_pagar")
    private int totalpagar;
    @JoinColumn(name = "ID_Paciente", referencedColumnName = "ID_Paciente")
    @ManyToOne(optional = false)
    private Paciente iDPaciente;
    @JoinColumn(name = "ID_Doctor", referencedColumnName = "ID_Doctor")
    @ManyToOne(optional = false)
    private Doctores iDDoctor;
    @JoinColumn(name = "Tipo_habitacion", referencedColumnName = "Nro_habitacion")
    @ManyToOne
    private HabitacionTipo tipohabitacion;

    public Formulario() {
    }

    public Formulario(Integer iDformulario) {
        this.iDformulario = iDformulario;
    }

    public Formulario(Integer iDformulario, String sexo, String motivo, String diagnostico, String tratamiento, Date fechaalta, int totalpagar) {
        this.iDformulario = iDformulario;
        this.sexo = sexo;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fechaalta = fechaalta;
        this.totalpagar = totalpagar;
    }

    public Integer getIDformulario() {
        return iDformulario;
    }

    public void setIDformulario(Integer iDformulario) {
        this.iDformulario = iDformulario;
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

    public Integer getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Integer habitacion) {
        this.habitacion = habitacion;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public Date getHoraingreso() {
        return horaingreso;
    }

    public void setHoraingreso(Date horaingreso) {
        this.horaingreso = horaingreso;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Date getFechaalta() {
        return fechaalta;
    }

    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }

    public int getTotalpagar() {
        return totalpagar;
    }

    public void setTotalpagar(int totalpagar) {
        this.totalpagar = totalpagar;
    }

    public Paciente getIDPaciente() {
        return iDPaciente;
    }

    public void setIDPaciente(Paciente iDPaciente) {
        this.iDPaciente = iDPaciente;
    }

    public Doctores getIDDoctor() {
        return iDDoctor;
    }

    public void setIDDoctor(Doctores iDDoctor) {
        this.iDDoctor = iDDoctor;
    }

    public HabitacionTipo getTipohabitacion() {
        return tipohabitacion;
    }

    public void setTipohabitacion(HabitacionTipo tipohabitacion) {
        this.tipohabitacion = tipohabitacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDformulario != null ? iDformulario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formulario)) {
            return false;
        }
        Formulario other = (Formulario) object;
        if ((this.iDformulario == null && other.iDformulario != null) || (this.iDformulario != null && !this.iDformulario.equals(other.iDformulario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Formulario[ iDformulario=" + iDformulario + " ]";
    }
    
}
