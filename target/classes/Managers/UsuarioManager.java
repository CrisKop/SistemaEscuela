package Managers;

import Clases.Usuario;
import DB.Conexion;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UsuarioManager {
    
    private Connection conexion;

    public UsuarioManager() {
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
    public boolean insertarUsuario(Usuario usuario){
        
        //QUERY QUE SE EJECUTARÁ, CON ? PARA REEMPLAZAR
        String sql = "INSERT INTO usuarios (identificacion, nombre, apellido, email, telefono, rol, password, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        //ZONA DE REEMPLAZO DE ? EN EL QUERY PREPARADO
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            //OBTENER Y REEMPLAZAR CADA DATO EN ORDEN DEL OBJETO BASE
            stmt.setInt(1, usuario.getIdentificacion());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getApellido());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefono());
            stmt.setString(6, usuario.getRol());
            stmt.setString(7, usuario.getPassword());
            stmt.setBoolean(8, usuario.getEstado());
                    
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
    MÉTODO PARA OBTENER UN USUARIO POR LA ID (NECESITA INT ID)
    ==========================================================
    */
    public Usuario obtenerUsuarioPorID(int id){
        
        //QUERY QUE SE EJECUTARÁ, CON ? PARA REEMPLAZAR
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        
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
                    return new Usuario(
                            rs.getInt("idUsuario"),
                            rs.getInt("identificacion"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("rol"),
                            rs.getString("password"),
                            rs.getBoolean("estado")
                    );
                }
            
            }
            } catch (SQLException e){
                /*
                SI HAY ERROR DEVOLVER NULL
                (ALTERNATIVA A UN OBJETO PARA DECIR QUE NO
                SE ENCONTRÓ)
                */
                 System.err.println("Error al obtener usuario: " + e.getMessage());
                return null;
            }
            
    return null;
    
    }
    
    
    /*
    =====================MET 3================================
            MÉTODO PARA LISTAR TODOS LOS USUARIOS
    ==========================================================
    */
    public List<Usuario> listarUsuarios(){
        
        //QUERY QUE SE EJECUTARÁ
         String sql = "SELECT * FROM usuarios";
         
         //LISTA VACIA QUE ALMACENARÁ LOS RESULTADOS
         List<Usuario> lista = new ArrayList<>();
        
         //(CREATESTATEMENT PORQUE NO HUBO UN PREPARESTATEMENT)
        try(Statement stmt = conexion.createStatement()){
           
            //ZONA DE OBTENCIÓN DE RESULTADO DE QUERY
            try(ResultSet rs = stmt.executeQuery(sql)){
                
                /*
                YA QUE HAY VARIOS RESULTADOS, UN WHILE POR CADA RESULTADO
                CADA RESULTADO SE CONVIERTE EN UN OBJETO (.clases/Usuario.java)
                Y SE AGREGA A 'lista'
                */
                while(rs.next()){
                    Usuario usuario = new Usuario(
                             rs.getInt("idUsuario"),
                            rs.getInt("identificacion"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("rol"),
                            rs.getString("password"),
                            rs.getBoolean("estado")
                    );
                    
                    lista.add(usuario);
                }
                
                //EL MÉTODO DEVUELVE EL LISTADO
                return lista;
            
            }} catch (SQLException e){
                /*
                SI HAY ERROR DEVOLVER NULL
                (ALTERNATIVA A UN ARRAYLIST PARA ERROR)
                */
                 System.err.println("Error al listar usuarios: " + e.getMessage());
                return null;
            }
    }
    
    
   
    /*
    =====================MET 4================================
            MÉTODO PARA ACTUALIZAR UN USUARIO
    ==========================================================
    */
      public boolean actualizarUsuario(Usuario usuario){
               String sql = "UPDATE Usuarios SET nombre = ?, apellido = ?, email = ?, telefono = ?, rol = ?, password = ?, estado = ? WHERE idUsuario = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getTelefono());
            stmt.setString(5, usuario.getRol());
            stmt.setString(6, usuario.getPassword());
            stmt.setBoolean(7, usuario.getEstado());
            
            stmt.setInt(8, usuario.getIdusuario()); // Este es el idUsuario del WHERE
                    
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
             System.err.println("Error al crear usuario: " + e.getMessage());
            return false;
        }
    }
      
      
          /*
    =====================MET 5================================
            MÉTODO PARA ELIMINAR UN USUARIO POR ID
    ==========================================================
    */
        public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM Usuarios WHERE idUsuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }
}
