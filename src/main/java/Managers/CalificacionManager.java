package Managers;


import DB.Conexion;
import Clases.Calificacion;
import Clases.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class CalificacionManager {
    private Connection conexion;
    
    
    public CalificacionManager(){
        this.conexion = Conexion.getConexion();
    }
    
    
    
    
    public boolean InsertarCalificacion(Calificacion calificacion){
        
    String sql ="INSERT INTO calificacion (idEvaluacion, idEstudiante, fechaEntrega , nota ) VALUES (?,?,?,?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            
            stmt.setInt  (1, calificacion.getIdEvaluacion());
            stmt.setInt  (2, calificacion.getIdEstudiante());
            stmt.setDate (3, calificacion.getFechaEntrega());
            stmt.setFloat(4, calificacion.getNota());
          
            return stmt.executeUpdate() > 0 ;
     } catch (SQLException e) {
    
     System.err.println("Error al crear calificacion: " + e.getMessage());
            return false;
    }    
  }
    
    
    
    
    public Calificacion obtenerCalificacionPorID(int id){
     
        String sql ="SELECT * FROM calificacion WHERE idCalificacion = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()){
                
                if(rs.next()){
                    
                    return new Calificacion(
                            rs.getInt ("idCalificacion"),
                            rs.getInt ("idEvaluacion"),
                            rs.getInt ("idEstudiante"),
                            rs.getDate ("fechaEntrega"),
                            rs.getFloat ("nota")
                );
                    
                }          
             }
             } catch (SQLException e){
                System.err.println("Error al obtener calificacion: " + e.getMessage());
                return null;
             }
     return null;
    }
    
    
    public List <Calificacion> listarCalificaciones (){
        
        String sql = "SELECT * FROM calificacion";
        
        List <Calificacion> lista = new ArrayList<>();
        
        try (Statement stmt = conexion.createStatement()){
            
            try (ResultSet rs = stmt.executeQuery(sql)){
                
                
                while (rs.next()) {
                    
                    Calificacion calificacion = new Calificacion (
                    
                            rs.getInt ("idCalificacion"),
                            rs.getInt ("idEvaluacion"),
                            rs.getInt ("idEstudiante"),
                            rs.getDate ("fechaEntrega"),
                            rs.getFloat ("nota")
                    
                    
                    );
                    
                    lista.add(calificacion);
                }
                return lista;
            
            }} catch (SQLException e){
                
            System.err.println("Error al listar calificaciones: " + e.getMessage());
            return null;
        }
    }
    
    
    
    public boolean actualizarCalificaciones(Calificacion calificacion){
        
        String sql = "UPDATE Calificacion SET  idEvaluacion = ?, idEstudiante = ?, fechaentrega = ?, nota = ? WHERE idCalificacion = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt  (1, calificacion.getIdEvaluacion());
            stmt.setInt  (2, calificacion.getIdEstudiante());
            stmt.setDate (3, calificacion.getFechaEntrega());
            stmt.setFloat(4,calificacion.getNota());
            stmt.setInt(5, calificacion.getIdCalificacion());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al actualizar calificacion: " + e.getMessage());
            return false;
        }
    }
    
      public boolean actualizarNotaDeCalificacion(int idCalificacion, int nota){
        
        String sql = "UPDATE Calificacion SET nota = ? WHERE idCalificacion = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, nota);
            stmt.setInt(2, idCalificacion);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al actualizar calificacion: " + e.getMessage());
            return false;
        }
    }
    
    
    public boolean eliminarCalificacion (int id){
        
        String sql = "DELETE FROM Calificacion WHERE idCalificacion = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
            } catch (SQLException e) {
                
            System.err.println("Error al eliminar calificacion: " + e.getMessage());
            return false;
        }
        
        
    }
    
    
      public List <Calificacion> listarCalificacionesPorEvaluacionDeProfesor (int idProfesor){
        
        String sql = """
                     SELECT c.*
                     FROM calificacion c
                     JOIN evaluaciones e ON c.idEvaluacion = e.idEvaluacion
                     WHERE e.idProfesor = ? ORDER BY c.fechaEntrega DESC;
                     """;
        
        List <Calificacion> lista = new ArrayList<>();
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, idProfesor);
            
            try (ResultSet rs = stmt.executeQuery()){
                
                while (rs.next()) {
                    
                    Calificacion calificacion = new Calificacion (
                    
                            rs.getInt ("idCalificacion"),
                            rs.getInt ("idEvaluacion"),
                            rs.getInt ("idEstudiante"),
                            rs.getDate ("fechaEntrega"),
                            rs.getFloat ("nota")
                    
                    
                    );
                    
                    lista.add(calificacion);
                }
                return lista;
            
            }} catch (SQLException e){
                
            System.err.println("Error al listar calificaciones: " + e.getMessage());
            return null;
        }
    }
      
      
      
      
}


