/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.sql.Date;

/**
 *
 * @author SENA 22
 */
public class Calificacion {
    private int idCalificacion;
    private int idEvaluacion;
    private  int idEstudiante;
    private Date fechaEntrega;
    private float nota;
    
    
    public Calificacion (int idCalificacion , int idEvaluacion , int idEstudiante , Date fechaEntrega , float nota){
        this.idCalificacion = idCalificacion;
        this.idEvaluacion = idEvaluacion;
        this.idEstudiante = idEstudiante;
        this.fechaEntrega = fechaEntrega;
        this.nota = nota;
                
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public float getNota() {
        return nota;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
    
    
}


//