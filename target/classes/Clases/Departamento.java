/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author SENA 21
 */
public class Departamento {
    private int idDepartamento;
    private int idEscuela;
    private String Nombre;
    private int Jefe;

    public Departamento(int idDepartamento, int idEscuela, String Nombre, int Jefe) {
        this.idDepartamento = idDepartamento;
        this.idEscuela = idEscuela;
        this.Nombre = Nombre;
        if(Jefe != 0){
            this.Jefe = Jefe;
        }
    }

    public int getIdDepartamentos() {
        return idDepartamento;
    }

    public void setIdDepartamentos(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(int idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getJefe() {
        return Jefe;
    }

    public void setJefe(int Jefe) {
        this.Jefe = Jefe;
    }
    
            @Override
    public String toString() {
        return idDepartamento + " - " + Nombre;
    }
    
    
}
