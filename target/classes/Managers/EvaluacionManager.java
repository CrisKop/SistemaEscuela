/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import DB.Conexion;
import Clases.Calificacion;
import Clases.Departamento;
import Clases.Evaluacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SENA 21
 */
public class EvaluacionManager {
      private Connection conexion;
      
      public EvaluacionManager(){
           this.conexion = Conexion.getConexion();
      }
      
      
       public boolean insertarEvaluacion(Evaluacion evaluacion){
        
      String sql = "INSERT INTO evaluaciones (idCurso, idProfesor, titulo, fechaInicio, fechaFin, tipo) VALUES (?,?,?,?,?, ?)";
      
      try(PreparedStatement stmt = conexion.prepareStatement(sql)){
          
          
           stmt.setInt (1, evaluacion.getIdCurso());
            stmt.setInt (2, evaluacion.getIdProfesor());
             stmt.setString (3, evaluacion.getTitulo());
          stmt.setTimestamp(4, (Timestamp) evaluacion.getFechaInicio());
          stmt.setTimestamp(5, (Timestamp) evaluacion.getFechaFin());
          stmt.setString (6, evaluacion.getTipo());
          
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            
             System.err.println("Error al crear evaluacion: " + e.getMessage());
            return false;
        }  
      }
       
        public Evaluacion obtenerEvaluacionPorID(int id){
              
        String sql = "SELECT * FROM evaluaciones WHERE idEvaluacion = ?";      
       
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                             
                if(rs.next()){
                    return new Evaluacion(
                            rs.getInt("idEvaluacion"),
                             rs.getInt("idCurso"),
                             rs.getInt("idProfesor"),
                            rs.getString("titulo"),
                            rs.getTimestamp("fechaInicio"),
                            rs.getTimestamp("fechaFin"),
                            rs.getString("tipo")
                           
                    );
                }
            
            }
            } catch (SQLException e){
                
                 System.err.println("Error al obtener evaluacion: " + e.getMessage());
                return null;
            }
            
    return null;
    
    }
        
         public List<Evaluacion> listarEvaluacion (){
        
         String sql = "SELECT * FROM evaluaciones";
       
         List<Evaluacion> lista = new ArrayList<>();
        
        try(Statement stmt = conexion.createStatement()){
     
            try(ResultSet rs = stmt.executeQuery(sql)){
             
                while(rs.next()){
                    Evaluacion evaluacion = new Evaluacion(
                            rs.getInt("idEvaluacion"),
                             rs.getInt("idCurso"),
                             rs.getInt("idProfesor"),
                            rs.getString("titulo"),
                            rs.getTimestamp("fechaInicio"),
                            rs.getTimestamp("fechaFin"),
                            rs.getString("tipo")
                           
                    );
                         System.out.println("fechaInicio lista eval: " + evaluacion.getFechaInicio());
                    lista.add(evaluacion);
                }
               
                return lista;
            
            }} catch (SQLException e){
               
                 System.err.println("Error al listar evaluacion: " + e.getMessage());
                return null;
            }
    }
         
         
         
            public List<Evaluacion> listarEvaluacionesPorEstudiante (){
        
         String sql = "SELECT * FROM evaluaciones";
       
         List<Evaluacion> lista = new ArrayList<>();
        
        try(Statement stmt = conexion.createStatement()){
     
            try(ResultSet rs = stmt.executeQuery(sql)){
             
                while(rs.next()){
                    Evaluacion evaluacion = new Evaluacion(
                            rs.getInt("idEvaluacion"),
                             rs.getInt("idCurso"),
                             rs.getInt("idProfesor"),
                            rs.getString("titulo"),
                            rs.getTimestamp("fechaInicio"),
                            rs.getTimestamp("fechaFin"),
                            rs.getString("tipo")
                           
                    );
                    
                    lista.add(evaluacion);
                }
               
                return lista;
            
            }} catch (SQLException e){
               
                 System.err.println("Error al listar evaluacion: " + e.getMessage());
                return null;
            }
    }
            
            
            
            
               public List<Evaluacion> listarEvaluacionesPorProfesor (int idProfesor){
        
         String sql = "SELECT * FROM evaluaciones WHERE idProfesor = ?";
       
         List<Evaluacion> lista = new ArrayList<>();
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
     
                stmt.setInt(1, idProfesor);
            
            try(ResultSet rs = stmt.executeQuery()){
             
                while(rs.next()){
                    Evaluacion evaluacion = new Evaluacion(
                            rs.getInt("idEvaluacion"),
                             rs.getInt("idCurso"),
                             rs.getInt("idProfesor"),
                            rs.getString("titulo"),
                            rs.getTimestamp("fechaInicio"),
                            rs.getTimestamp("fechaFin"),
                            rs.getString("tipo")
                           
                    );
                    
                    lista.add(evaluacion);
                }
               
                return lista;
            
            }} catch (SQLException e){
               
                 System.err.println("Error al listar evaluacion: " + e.getMessage());
                return null;
            }
    }
               
               
                public List<Evaluacion> listarEvaluacionesPendientesCurso (int idCurso, int idEstudiante){
        
         String sql = """
                      SELECT e.*
                      FROM evaluaciones e
                      LEFT JOIN calificacion c ON e.idEvaluacion = c.idEvaluacion
                      WHERE e.idCurso = ? AND c.idEstudiante IS NULL;
                      """;
       
         List<Evaluacion> lista = new ArrayList<>();
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
     
                stmt.setInt(1, idCurso);
                stmt.setInt(2, idEstudiante);
            
            try(ResultSet rs = stmt.executeQuery()){
             
                while(rs.next()){
                    Evaluacion evaluacion = new Evaluacion(
                            rs.getInt("idEvaluacion"),
                             rs.getInt("idCurso"),
                             rs.getInt("idProfesor"),
                            rs.getString("titulo"),
                            rs.getTimestamp("fechaInicio"),
                            rs.getTimestamp("fechaFin"),
                            rs.getString("tipo")
                           
                    );
                    
                    lista.add(evaluacion);
                }
               
                return lista;
            
            }} catch (SQLException e){
               
                 System.err.println("Error al listar evaluacion: " + e.getMessage());
                return null;
            }
    }
         
          public boolean actualizarEvaluacion(Evaluacion evaluacion){
               String sql = "UPDATE evaluaciones SET idCurso = ?, idProfesor = ?, titulo = ?, fechaInicio = ?,fechaFin = ?, tipo = ? WHERE idEvaluacion = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setInt(1, evaluacion.getIdCurso());
            stmt.setInt(2, evaluacion.getIdProfesor());
             stmt.setString(3, evaluacion.getTitulo());
            stmt.setTimestamp(4, (Timestamp) evaluacion.getFechaInicio());
            stmt.setTimestamp(5, (Timestamp) evaluacion.getFechaFin());
            stmt.setString(6, evaluacion.getTipo());
                    
             stmt.setInt(7, evaluacion.getIdEvaluacion());
             
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al crear evaluacion: " + e.getMessage());
            return false;
        }
    } 
          
           public boolean eliminarEvaluacion(int id) {
        String sql = "DELETE FROM evaluaciones WHERE idEvaluacion = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar evaluacion: " + e.getMessage());
            return false;
        }
    }  
    
         
}
