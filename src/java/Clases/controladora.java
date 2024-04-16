/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.List;
import persistencia.ControladoraPersistencia;

/**
 *
 * @author dylan
 */

public class controladora{

    // Crear una nueva instancia de ControladoraPersistencia
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    
    // Método para crear un nuevo usuario
    public void crearUsuario(Usuario usu){
        controlPersis.crearUsuario(usu);
    }
    
    // Método para traer todos los usuarios
    public List<Usuario> traerUsuario(){
        return controlPersis.traerUsuario();
    }
    
    // Método para traer un usuario específico por ID
    public Usuario traerUsuario(int id ){ 
        return controlPersis.traerUsuario(id);
    }
    
    // Método para crear un nuevo paciente
    public void crearPaciente(Paciente paci){
        controlPersis.crearPaciente(paci);
    }
    
    // Método para traer un paciente específico por ID
    public Paciente traerPaciente(int id){
        return controlPersis.traerPaciente(id);
    }
    
    // Método para crear un nuevo doctor
    public void crearDoctor(Doctores doc){
        controlPersis.crearDoctor(doc);
    }
    
    // Método para traer un doctor específico por ID
    public Doctores traerDoctor(int id){
        return controlPersis.traerDoctor(id);
    }
    
    // Método para crear un nuevo formulario
    public void crearFormulario(Formulario form){
        controlPersis.crearFormulario(form);
    }
    
    // Método para traer el último usuario creado
    public Usuario traerUltimoUsuario(){
        return controlPersis.traerUltimoUsuario();
    }
    
    // Método para traer el último paciente creado
    public Paciente traerUltimoPaciente(){
        return controlPersis.traerUltimoPaciente();
    }
    
    // Método para traer todos los tipos de roles
    public List<RolTipo> traerRolTipo(){
        return controlPersis.traerRolTipo();
    }
    
    // Método para traer un tipo de rol específico por ID
    public RolTipo traerUnRolTipo(int id){
        return controlPersis.traerUnRolTipo(id);
    }
    
    // Método para crear una nueva cita médica
    public void crearCitasMedicas(CitasMedicas form){
        controlPersis.crearCitasMedicas(form);
    }
    
    public List<Paciente> traerListaPacientes(){
        return controlPersis.traerListaPacientes();
    }
    
    public List<Formulario> traerListaFormularios(){
        return controlPersis.traerListaFormularios();
    }
    
    public List<Doctores> traerListaDoctor(){
        return controlPersis.traerListaDoctor();
    }
}
