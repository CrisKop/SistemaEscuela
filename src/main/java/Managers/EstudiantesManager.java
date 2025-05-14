/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import DB.Conexion;
import Clases.Departamentos;
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
        
      String sql = "INSERT INTO estudiantes (grado, promedio, idUsuario) VALUES (?,?,?)";
      
      try(PreparedStatement stmt = conexion.prepareStatement(sql)){
          
          stmt.setInt(1, estudiantes.getGrado());
          stmt.setFloat(2, estudiantes.getPromedio());
          stmt.setFloat(3, estudiantes.getidUsuario());
          
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
                            (int) rs.getFloat("promedio"),
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
        
         String sql = "SELECT * FROM Estudiantes";
       
         List<Estudiante> lista = new ArrayList<>();
        
        try(Statement stmt = conexion.createStatement()){
     
            try(ResultSet rs = stmt.executeQuery(sql)){
             
                while(rs.next()){
                   Estudiante estudiante = new Estudiante(
                            rs.getInt("idEstudiante"),
                            rs.getInt("idUsuario"),
                            rs.getInt("grado"),
                            rs.getFloat("promedio")
                           
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
               String sql = "UPDATE Estudiantes SET idUsuario = ?, grado = ?, promedio = ?,  WHERE idEstudiante = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
             stmt.setInt(1, (int) estudiante.getidUsuario());
             stmt.setInt(2, estudiante.getGrado());
             stmt.setFloat(3, estudiante.getPromedio());
             stmt.setInt(4, estudiante.getidEstudiante());
            
                    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al crear estudiante: " + e.getMessage());
            return false;
        }
    }
        public boolean eliminarEstudiante(int id) {
        String sql = "DELETE FROM Estudiantes WHERE idEstudiante = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar Estudiante: " + e.getMessage());
            return false;
        }
    }  
      
}
