/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import DB.Conexion;
import Clases.Estudiante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SENA 21
 */
public class EstudiantesManager {
    
    private Connection conexion;
    
    public EstudiantesManager() {
        this.conexion = Conexion.getConexion();
    }
    
    public boolean insertarEstudiantes(Estudiante estudiantes){
        
      String sql = "INSERT INTO estudiantes (idUsuario, grado) VALUES (?,?)";
      
      try(PreparedStatement stmt = conexion.prepareStatement(sql)){
          
          stmt.setInt(1, estudiantes.getIdUsuario());
          stmt.setInt(2, estudiantes.getGrado());
          
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
           
             System.err.println("Error al crear estudiante: " + e.getMessage());
            return false;
        }  
      }
    
     public Estudiante obtenerEstudiantePorID(int id){
              
        String sql = "SELECT * FROM estudiantes WHERE idEstudiante = ?";      
       
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                             
                if(rs.next()){
                    return new Estudiante(
                            rs.getInt("idEstudiante"),
                            rs.getInt("idUsuario"),
                            rs.getInt("grado")   
                    );
                }
            
            }
            } catch (SQLException e){
                
                 System.err.println("Error al obtener estudiantes: " + e.getMessage());
                return null;
            }
            
    return null;
    
    }
     
      public List<Estudiante> listarEstudiante(){
        
         String sql = "SELECT * FROM estudiantes";
       
         List<Estudiante> lista = new ArrayList<>();
        
        try(Statement stmt = conexion.createStatement()){
     
            try(ResultSet rs = stmt.executeQuery(sql)){
             
                while(rs.next()){
                   Estudiante estudiante = new Estudiante(
                            rs.getInt("idEstudiante"),
                            rs.getInt("idUsuario"),
                            rs.getInt("grado")
                    );
                    
                    lista.add(estudiante);
                }
               
                return lista;
            
            }} catch (SQLException e){
               
                 System.err.println("Error al listar estudiante: " + e.getMessage());
                return null;
            }
    }

       public boolean actualizarEstudiante(Estudiante estudiante){
               String sql = "UPDATE estudiantes SET grado = ? WHERE idUsuario = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
             stmt.setInt(1, estudiante.getGrado());
             stmt.setInt(2, estudiante.getIdUsuario());
            
                    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al crear estudiante: " + e.getMessage());
            return false;
        }
    }
        public boolean eliminarEstudiante(int id) {
        String sql = "DELETE FROM estudiantes WHERE idUsuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar Estudiante: " + e.getMessage());
            return false;
        }
    }  
      
}
