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
@Table(name = "citas_medicas")
@NamedQueries({
    @NamedQuery(name = "CitasMedicas.findAll", query = "SELECT c FROM CitasMedicas c"),
    @NamedQuery(name = "CitasMedicas.findByIDCitas", query = "SELECT c FROM CitasMedicas c WHERE c.iDCitas = :iDCitas"),
    @NamedQuery(name = "CitasMedicas.findByFechaprogramada", query = "SELECT c FROM CitasMedicas c WHERE c.fechaprogramada = :fechaprogramada"),
    @NamedQuery(name = "CitasMedicas.findByHoraprogramada", query = "SELECT c FROM CitasMedicas c WHERE c.horaprogramada = :horaprogramada"),
    @NamedQuery(name = "CitasMedicas.findByFecharegistro", query = "SELECT c FROM CitasMedicas c WHERE c.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "CitasMedicas.findByNumorden", query = "SELECT c FROM CitasMedicas c WHERE c.numorden = :numorden")})
public class CitasMedicas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_Citas")
    private Integer iDCitas;
    @Basic(optional = false)
    @Column(name = "Fecha_programada")
    @Temporal(TemporalType.DATE)
    private Date fechaprogramada;
    @Basic(optional = false)
    @Column(name = "Hora_programada")
    @Temporal(TemporalType.TIME)
    private Date horaprogramada;
    @Basic(optional = false)
    @Column(name = "Fecha_registro")
    @Temporal(TemporalType.DATE)
    private Date fecharegistro;
    @Basic(optional = false)
    @Column(name = "Num_orden")
    private int numorden;
    @JoinColumn(name = "ID_Doctor", referencedColumnName = "ID_Doctor")
    @ManyToOne(optional = false)
    private Doctores iDDoctor;
    @JoinColumn(name = "ID_Paciente", referencedColumnName = "ID_Paciente")
    @ManyToOne(optional = false)
    private Paciente iDPaciente;

    public CitasMedicas() {
    }

    public CitasMedicas(Integer iDCitas) {
        this.iDCitas = iDCitas;
    }

    public CitasMedicas(Integer iDCitas, Date fechaprogramada, Date horaprogramada, Date fecharegistro, int numorden) {
        this.iDCitas = iDCitas;
        this.fechaprogramada = fechaprogramada;
        this.horaprogramada = horaprogramada;
        this.fecharegistro = fecharegistro;
        this.numorden = numorden;
    }

    public Integer getIDCitas() {
        return iDCitas;
    }

    public void setIDCitas(Integer iDCitas) {
        this.iDCitas = iDCitas;
    }

    public Date getFechaprogramada() {
        return fechaprogramada;
    }

    public void setFechaprogramada(Date fechaprogramada) {
        this.fechaprogramada = fechaprogramada;
    }

    public Date getHoraprogramada() {
        return horaprogramada;
    }

    public void setHoraprogramada(Date horaprogramada) {
        this.horaprogramada = horaprogramada;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public int getNumorden() {
        return numorden;
    }

    public void setNumorden(int numorden) {
        this.numorden = numorden;
    }

    public Doctores getIDDoctor() {
        return iDDoctor;
    }

    public void setIDDoctor(Doctores iDDoctor) {
        this.iDDoctor = iDDoctor;
    }

    public Paciente getIDPaciente() {
        return iDPaciente;
    }

    public void setIDPaciente(Paciente iDPaciente) {
        this.iDPaciente = iDPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDCitas != null ? iDCitas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitasMedicas)) {
            return false;
        }
        CitasMedicas other = (CitasMedicas) object;
        if ((this.iDCitas == null && other.iDCitas != null) || (this.iDCitas != null && !this.iDCitas.equals(other.iDCitas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.CitasMedicas[ iDCitas=" + iDCitas + " ]";
    }
    
}
