/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import DB.Conexion;
import Clases.Departamentos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author SENA 22
 */
public class DepartamentosManager {
    
    private Connection conexion;
    
    public DepartamentosManager(){
       this.conexion = Conexion.getConexion();
    }
    public boolean insertarDepartamentos(Departamentos departamentos){
        
      String sql = "INSERT INTO departamentos (idEscuela, nombre, jefe) VALUES (?,?,?)";
      
      try(PreparedStatement stmt = conexion.prepareStatement(sql)){
          
          stmt.setInt(1, departamentos.getidEscuela());
          stmt.setString(2, departamentos.getNombre());
          stmt.setInt(3, departamentos.getJefe());
           
          
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            
             System.err.println("Error al crear departamentos: " + e.getMessage());
            return false;
        }  
      }
     
    
    
     public Departamentos obtenerDepartamentoPorID(int id){
              
        String sql = "SELECT * FROM departamentos WHERE idDepartamento = ?";      
       
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                             
                if(rs.next()){
                    return new Departamentos(
                             rs.getInt("idDepartamento"),
                             rs.getInt("idEscuela"),
                            rs.getString("nombre"),
                            rs.getInt("jefe")
                           
                    );
                }
            
            }
            } catch (SQLException e){
                
                 System.err.println("Error al obtener departamentos: " + e.getMessage());
                return null;
            }
            
    return null;
    
    }
     
      public List<Departamentos> listarDepartamentos(){
        
         String sql = "SELECT * FROM departamentos";
       
         List<Departamentos> lista = new ArrayList<>();
        
        try(Statement stmt = conexion.createStatement()){
     
            try(ResultSet rs = stmt.executeQuery(sql)){
             
                while(rs.next()){
                    Departamentos departamentos = new Departamentos(
                             rs.getInt("idDepartamento"),
                             rs.getInt("idEscuela"),
                            rs.getString("nombre"),
                            rs.getInt("jefe")
                           
                    );
                    
                    lista.add(departamentos);
                }
               
                return lista;
            
            }} catch (SQLException e){
               
                 System.err.println("Error al listar departamentos: " + e.getMessage());
                return null;
            }
    }
    
    
      
      public boolean actualizarDepartamento(Departamentos departamentos){
               String sql = "UPDATE Departamento SET idEscuela = ?, nombre = ?, jefe = ? WHERE idDepartamento = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
               stmt.setInt(1, departamentos.getidEscuela());
            stmt.setString(2, departamentos.getNombre());
            stmt.setInt(3, departamentos.getJefe());
          
               stmt.setInt(4, departamentos.getIdDepartamentos());
           
                    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al crear Departamento: " + e.getMessage());
            return false;
        }
    }
      
      
     public boolean eliminarDepartamento(int id) {
        String sql = "DELETE FROM Departametos WHERE idDepartamento = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar departamento: " + e.getMessage());
            return false;
        }
    }  
      
}
    

    
    

