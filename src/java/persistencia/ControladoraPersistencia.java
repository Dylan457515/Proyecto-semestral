/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Clases.CitasMedicas;
import Clases.Usuario;
import Clases.Paciente;
import Clases.Doctores;
import Clases.Formulario;
import Clases.RolTipo;
import java.util.List;

/**
 *
 * @author dylan
 */

public class ControladoraPersistencia{
    
    // Crear nuevas instancias de los controladores JPA
    UsuarioJpaController usuJpa = new UsuarioJpaController();
    PacienteJpaController paciJpa = new PacienteJpaController();
    DoctoresJpaController docJpa = new DoctoresJpaController();
    FormularioJpaController forJpa = new FormularioJpaController();
    RolTipoJpaController tipRJpa = new RolTipoJpaController();
    CitasMedicasJpaController citJpa = new CitasMedicasJpaController();

    
    // Método para crear un nuevo usuario
    public void crearUsuario(Usuario usu){
        usuJpa.create(usu);
    }
    
    // Método para traer todos los usuarios
    public List<Usuario> traerUsuario(){
        return usuJpa.findUsuarioEntities();
    }
    
    // Método para traer un usuario específico por ID
    public Usuario traerUsuario(int id ){
        return usuJpa.findUsuario(id);
    }
    
    // Método para crear un nuevo paciente
    public void crearPaciente(Paciente paci){
        paciJpa.create(paci);
    }
    
    // Método para traer un paciente específico por ID
    public Paciente traerPaciente(int id){
        return paciJpa.findPaciente(id);
    }
    
    // Método para crear un nuevo doctor
    public void crearDoctor(Doctores doc){
        docJpa.create(doc);
    }
    
    // Método para traer un doctor específico por ID
    public Doctores traerDoctor(int id){
        return docJpa.findDoctores(id);
    }
    
    // Método para crear un nuevo formulario
    public void crearFormulario(Formulario form){
        forJpa.create(form);
    }
    
    // Método para traer el último usuario creado
    public Usuario traerUltimoUsuario(){
        return usuJpa.findLastUsuario();
    }

    // Método para traer el último paciente creado
    public Paciente traerUltimoPaciente(){
        return paciJpa.findLastPaciente();
    }
    
    // Método para traer todos los tipos de roles
    public List<RolTipo> traerRolTipo(){
        return tipRJpa.findRolTipoEntities();
    }
    
    // Método para traer un tipo de rol específico por ID
    public RolTipo traerUnRolTipo(int id){
        return tipRJpa.findRolTipo(id);
    }
    
    // Método para crear una nueva cita médica
    public void crearCitasMedicas(CitasMedicas cit){
        citJpa.create(cit);
    }
    
    public List<Paciente> traerListaPacientes(){
        return paciJpa.findPacienteEntities();
    }
    
    public List<Formulario> traerListaFormularios(){
        return forJpa.findFormularioEntities();
    }
    
    public List<Doctores> traerListaDoctor(){
        return docJpa.findDoctoresEntities();
    }
    
}
