/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import DB.Conexion;
import Clases.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author KEVYN
 */
public class ProfesorManager {
    private Connection conexion;
    
    public ProfesorManager(){
    
        this.conexion = Conexion.getConexion();
    }
    
    //stmt.executeQuery() cuando es un select y hay resultado
    //osea, try (prepareStatement) y dentro try de executeQuery
    
    //Y para lista el executeQuery tiene rs.next
    //Es un bucle que se itera por cada resultado del query
    //con rs.next();

    /*
    ====================================================================
    =======MÉTODOS DE CRUD + LISTAR DE USUARIO==========================
    ====================================================================
    =======PUEDES BUSCAR MÉTODOS POR ORDEN CON==========================
    =======CTRL + F Y "MET #", EJEMPLO "MET 2"==========================
    ===============O POR SU NOMBRE======================================
    ====================================================================
    */
    
    /*
     =====================MET 1================================
     MÉTODO INSERTAR USUARIO (NECESITA OBJETO USUARIO DE .clases/Usuario.java)
     ==========================================================
    */
    public boolean insertarProfesor(Profesor profesor){
        
        //QUERY QUE SE EJECUTARÁ, CON ? PARA REEMPLAZAR
        String sql = "INSERT INTO profesores (idUsuario, idDepartamento, especialidad) VALUES (?)";
        
        //ZONA DE REEMPLAZO DE ? EN EL QUERY PREPARADO
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            //OBTENER Y REEMPLAZAR CADA DATO EN ORDEN DEL OBJETO BASE
            stmt.setInt(1, profesor.getIdUsuario());
            stmt.setInt(2, profesor.getIdProfesor());
            stmt.setString(3, profesor.getEspecialidad());
            
                    
            //TRUE SI SE EJECUTÓ EL QUERY SIN PROBLEMA
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            //FALSE SI HUBO ERROR
             System.err.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }
    
    
    /*
    =====================MET 2================================
    MÉTODO PARA OBTENER UN PROFESOR POR LA ID (NECESITA INT ID)
    ==========================================================
    */
    public Profesor obtenerProfesorPorID(int id){
        
        //QUERY QUE SE EJECUTARÁ, CON ? PARA REEMPLAZAR
        String sql = "SELECT * FROM profesores WHERE idProfesor = ?";
        
        //ZONA DE REEMPLAZO DE ? EN EL QUERY PREPARADO
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            //REEMPLAZAR ? POR EL ARGUMENTO DEL MÉTODO
            stmt.setInt(1, id);
            
            //ZONA DE OBTENCIÓN DE RESULTADO DE QUERY
            try(ResultSet rs = stmt.executeQuery()){
                
                /*SI HAY 1 RESULTADO, CREAR Y DEVOLVER EL OBJETO
                     (.clases/Usuario.java)
                */
                if(rs.next()){
                    return new Profesor(
                           rs.getInt("idProfesor"),
                            rs.getInt("idUsuario"),
                            rs.getInt("idDepartamento"),
                            rs.getString("especialidad")
                    );
                }
            
            }
            } catch (SQLException e){
                /*
                SI HAY ERROR DEVOLVER NULL
                (ALTERNATIVA A UN OBJETO PARA DECIR QUE NO
                SE ENCONTRÓ)
                */
                 System.err.println("Error al obtener profesor: " + e.getMessage());
                return null;
            }
            
    return null;
    
    }
    
    
    /*
    =====================MET 3================================
            MÉTODO PARA LISTAR TODOS LOS PROFESORES
    ==========================================================
    */
    public List<Profesor> listarProfesor(){
        
        //QUERY QUE SE EJECUTARÁ
         String sql = "SELECT * FROM profesores";
         
         //LISTA VACIA QUE ALMACENARÁ LOS RESULTADOS
         List<Profesor> lista = new ArrayList<>();
        
         //(CREATESTATEMENT PORQUE NO HUBO UN PREPARESTATEMENT)
        try(Statement stmt = conexion.createStatement()){
           
            //ZONA DE OBTENCIÓN DE RESULTADO DE QUERY
            try(ResultSet rs = stmt.executeQuery(sql)){
                
                /*
                YA QUE HAY VARIOS RESULTADOS, UN WHILE POR CADA RESULTADO
                CADA RESULTADO SE CONVIERTE EN UN OBJETO (.clases/Profesor.java)
                Y SE AGREGA A 'lista'
                */
                while(rs.next()){
                    Profesor profesor = new Profesor(
                            rs.getInt("idProfesor"),
                            rs.getInt("idUsuario"),
                            rs.getInt("idDepartamento"),
                            rs.getString("especialidad")
                    );
                    
                    lista.add(profesor);
                }
                
                //EL MÉTODO DEVUELVE EL LISTADO
                return lista;
            
            }} catch (SQLException e){
                /*
                SI HAY ERROR DEVOLVER NULL
                (ALTERNATIVA A UN ARRAYLIST PARA ERROR)
                */
                 System.err.println("Error al listar profesores: " + e.getMessage());
                return null;
            }
    }
    
    
   
    /*
    =====================MET 4================================
            MÉTODO PARA ACTUALIZAR UN PROFESOR
    ==========================================================
    */
      public boolean actualizarProfesor(Profesor profesor){
               String sql = "UPDATE profesores SET especialidad = ? WHERE idProfesor = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setString(1, profesor.getEspecialidad());
            
            stmt.setInt(2, profesor.getIdProfesor()); // Este es el idProfesor del WHERE
            
                    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al crear profesor: " + e.getMessage());
            return false;
        }
    }
      
      
          /*
    =====================MET 5================================
            MÉTODO PARA ELIMINAR UN PROFESOR POR ID
    ==========================================================
    */
        public boolean eliminarProfesor(int id) {
        String sql = "DELETE FROM profesores WHERE idProfesor = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar profesor: " + e.getMessage());
            return false;
        }
    }
    
    
}
