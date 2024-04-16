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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByIDusuario", query = "SELECT u FROM Usuario u WHERE u.iDusuario = :iDusuario"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByFechanacimiento", query = "SELECT u FROM Usuario u WHERE u.fechanacimiento = :fechanacimiento"),
    @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
    @NamedQuery(name = "Usuario.findByFechaIngreso", query = "SELECT u FROM Usuario u WHERE u.fechaIngreso = :fechaIngreso"),
    @NamedQuery(name = "Usuario.findByFechamodificacion", query = "SELECT u FROM Usuario u WHERE u.fechamodificacion = :fechamodificacion")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_usuario")
    private Integer iDusuario;
    @Basic(optional = false)
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechanacimiento;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "Fecha_Ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    @Column(name = "Fecha_modificacion")
    @Temporal(TemporalType.DATE)
    private Date fechamodificacion;
    @JoinColumn(name = "Tipo_usuario", referencedColumnName = "Nro_rol")
    @ManyToOne(optional = false)
    private RolTipo tipousuario;

    public Usuario() {
    }

    public Usuario(Integer iDusuario) {
        this.iDusuario = iDusuario;
    }

    public Usuario(Integer iDusuario, String nombre, String password) {
        this.iDusuario = iDusuario;
        this.nombre = nombre;
        this.password = password;
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

    public Date getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public RolTipo getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(RolTipo tipousuario) {
        this.tipousuario = tipousuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iDusuario != null ? iDusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.iDusuario == null && other.iDusuario != null) || (this.iDusuario != null && !this.iDusuario.equals(other.iDusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Clases.Usuario[ iDusuario=" + iDusuario + " ]";
    }
    
}
