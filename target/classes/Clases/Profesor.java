/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author SENA 22
 */
public class Profesor {
    private int idProfesor;
    private int idUsuario;
    private int idDepartamento;
    private String especialidad;

    public Profesor(int idProfesor, int idUsuario, int idDepartamento, String especialidad) {
        this.idProfesor = idProfesor;
        this.idUsuario = idUsuario;
        this.idDepartamento = idDepartamento;
        this.especialidad = especialidad;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int intDepartamento) {
        this.idDepartamento = intDepartamento;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    
}
