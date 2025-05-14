/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import DB.Conexion;
import Clases.Escuela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SENA 22
 */
public class EscuelaManager {
    
     private Connection conexion;
     
     public EscuelaManager(){
         this.conexion = Conexion.getConexion();
     }
     
     /*
     =====================MET 1================================
     MÉTODO INSERTAR ESCUELA (NECESITA OBJETO ESCUELA DE .clases/Escuela.java)
     ==========================================================
    */
    public boolean insertarEscuela(Escuela escuela){
        
        //QUERY QUE SE EJECUTARÁ, CON ? PARA REEMPLAZAR
        String sql = "INSERT INTO escuelas (nombre) VALUES (?)";
        
        //ZONA DE REEMPLAZO DE ? EN EL QUERY PREPARADO
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            //OBTENER Y REEMPLAZAR CADA DATO EN ORDEN DEL OBJETO BASE
            stmt.setString(1, escuela.getNombre());
          
                    
            //TRUE SI SE EJECUTÓ EL QUERY SIN PROBLEMA
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            //FALSE SI HUBO ERROR
             System.err.println("Error al crear Escuela: " + e.getMessage());
            return false;
        }
    }
     /*
    =====================MET 2================================
    MÉTODO PARA OBTENER UN ESCUELA POR LA ID (NECESITA INT ID)
    ==========================================================
    */
    public Escuela obtenerEscuelaPorID(int id){
        //QUERY QUE SE EJECUTARÁ, CON ? PARA REEMPLAZAR
        String sql = "SELECT * FROM escuelas WHERE idEscuela = ?";
        //ZONA DE REEMPLAZO DE ? EN EL QUERY PREPARADO
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            //REEMPLAZAR ? POR EL ARGUMENTO DEL MÉTODO
            stmt.setInt(1, id);
            //ZONA DE OBTENCIÓN DE RESULTADO DE QUERY
            try(ResultSet rs = stmt.executeQuery()){
                /*SI HAY 1 RESULTADO, CREAR Y DEVOLVER EL OBJETO
                     (.clases/Escuela.java)
                */
                if(rs.next()){
                    return new Escuela(
                            rs.getInt("idEscuela"),
                            rs.getString("nombre")
                    );
                }
            }
            } catch (SQLException e){
                /*
                SI HAY ERROR DEVOLVER NULL
                (ALTERNATIVA A UN OBJETO PARA DECIR QUE NO
                SE ENCONTRÓ)
                */
                 System.err.println("Error al obtener escuela: " + e.getMessage());
                return null;
            }
    return null;
    }
    
    /*
    =====================MET 3================================
            MÉTODO PARA LISTAR TODAS LAS ESCUELAS
    ==========================================================
    */
    public List<Escuela> listtarEscuela(){
        
        //QUERY QUE SE EJECUTARÁ
         String sql = "SELECT * FROM escuelas";
         
         //LISTA VACIA QUE ALMACENARÁ LOS RESULTADOS
         List<Escuela> lista = new ArrayList<>();
        
         //(CREATESTATEMENT PORQUE NO HUBO UN PREPARESTATEMENT)
        try(Statement stmt = conexion.createStatement()){
           
            //ZONA DE OBTENCIÓN DE RESULTADO DE QUERY
            try(ResultSet rs = stmt.executeQuery(sql)){
                
                /*
                YA QUE HAY VARIOS RESULTADOS, UN WHILE POR CADA RESULTADO
                CADA RESULTADO SE CONVIERTE EN UN OBJETO (.clases/Escuela.java)
                Y SE AGREGA A 'lista'
                */
                while(rs.next()){
                    Escuela escuela = new Escuela(
                            rs.getInt("idEscuela"),
                            rs.getString("nombre")
                    );
                    
                    lista.add(escuela);
                }
                
                //EL MÉTODO DEVUELVE EL LISTADO
                return lista;
            
            }} catch (SQLException e){
                /*
                SI HAY ERROR DEVOLVER NULL
                (ALTERNATIVA A UN ARRAYLIST PARA ERROR)
                */
                 System.err.println("Error al listar escuela: " + e.getMessage());
                return null;
            }
    }
    
    /*
    =====================MET 4================================
            MÉTODO PARA ACTUALIZAR UNA ESCUELA
    ==========================================================
    */
      public boolean actualizarEscuela(Escuela escuela){
               String sql = "UPDATE escuelas SET nombre = ? WHERE idEscuela = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setString(1, escuela.getNombre());
            
            stmt.setInt(2, escuela.getIdEscuela()); // Este es el idEscuela del WHERE
            
                    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al actualizar escuela: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarEscuela(int id) {
        String sql = "DELETE FROM escuelas WHERE idEscuela = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar escuela: " + e.getMessage());
            return false;
        }
    }
}
