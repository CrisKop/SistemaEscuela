/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author SENA 22
 */
public class Curso {
    
    private int idCurso;
    private int idDepartamento;
    private String nombre;
    private Time horaInicial;
    private Time horaFinal;
    private int maxEstudiantes;
    private int creditos;
    
    public Curso (int idCurso, int idDepartamento, String nombre ,Time horaInicial , Time horaFinal , int maxEstudiantes , int creditos){
        this.idCurso= idCurso;
        this.idDepartamento = idDepartamento;
        this.nombre= nombre;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
        this.maxEstudiantes = maxEstudiantes;
        this.creditos = creditos;
        
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    
    public int getIdCurso() {
        return idCurso;
    }

    public String getNombre() {
        return nombre;
    }
    public Time gethoraInicial() {
        return horaInicial;
    }
    
    public Time gethoraFinal() {
        return horaFinal;
    }
    
    public int getmaxEstudiantes() {
        return maxEstudiantes;
    }
    public int getcreditos() {
        return creditos;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void sethoraInicial(Time horaInicial) {
        this.horaInicial = horaInicial;
    }
    public void sethoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    public void setmaxEstudiantes(int maxEstudiantes) {
        this.maxEstudiantes = maxEstudiantes;
    }
    
    public void setcreditos(int creditos) {
        this.creditos = creditos;
    }
    
    
}//
