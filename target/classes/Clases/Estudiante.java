/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author SENA 22
 */
public class Estudiante {
    
    private int idEstudiante;
    private int idUsuario;
    private int Grado;


    public Estudiante(int idEstudiante, int idUsuario, int Grado) {
        this.idEstudiante = idEstudiante;
        this.idUsuario = idUsuario;
        this.Grado = Grado;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getGrado() {
        return Grado;
    }

    public void setGrado(int Grado) {
        this.Grado = Grado;
    }
    
    
    
}
