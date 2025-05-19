package Managers;

import Clases.Usuario;
import DB.Conexion;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
   public Usuario insertarUsuario(Usuario usuario) {
    // Consulta SQL con los parámetros para insertar el usuario
    String sql = "INSERT INTO usuarios (identificacion, nombre, apellido, email, telefono, rol, password, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
    try (PreparedStatement stmt = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        // Reemplazar los parámetros con los valores del objeto Usuario
        stmt.setInt(1, usuario.getIdentificacion());
        stmt.setString(2, usuario.getNombre());
        stmt.setString(3, usuario.getApellido());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getTelefono());
        stmt.setString(6, usuario.getRol());
        stmt.setString(7, usuario.getPassword());
        stmt.setInt(8, usuario.getEstado());
        
        // Ejecutar la consulta de inserción
        int rowsAffected = stmt.executeUpdate();
        
        // Si la inserción fue exitosa (al menos una fila afectada)
        if (rowsAffected > 0) {
            // Obtener las claves generadas automáticamente
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    // Recuperar el ID generado (se supone que el campo 'idUsuario' es AUTO_INCREMENT)
                    int idUsuario = generatedKeys.getInt(1);  // El primer valor es el ID generado
                    usuario.setIdUsuario(idUsuario);  // Establecer el ID generado en el objeto Usuario
                }
            }
        }
        
        // Devolver el objeto Usuario con el id generado
        return usuario;
        
    } catch (SQLException e) {
        System.err.println("Error al crear usuario: " + e.getMessage());
        return null;  // Si hay un error, devolver null
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
                            rs.getInt("estado")
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
                            rs.getInt("estado")
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
    public boolean actualizarUsuario(Usuario usuario) {
    // Base de la consulta SQL
    String sql = "UPDATE Usuarios SET identificacion = ?, nombre = ?, apellido = ?, email = ?, telefono = ?, estado = ? WHERE idUsuario = ?";
    
    // Si el password no está vacío, añadimos el campo de password a la consulta SQL
    if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
        sql = "UPDATE Usuarios SET identificacion = ?, nombre = ?, apellido = ?, email = ?, telefono = ?, estado = ?, password = ? WHERE idUsuario = ?";
    }

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        // Establecer los parámetros comunes
        stmt.setInt(1, usuario.getIdentificacion());
        stmt.setString(2, usuario.getNombre());
        stmt.setString(3, usuario.getApellido());
        stmt.setString(4, usuario.getEmail());
        stmt.setString(5, usuario.getTelefono());
        stmt.setInt(6, usuario.getEstado());
        
        // Si el password no está vacío, añadimos el parámetro de password
        if (usuario.getPassword() != null && !usuario.getPassword().isEmpty()) {
            stmt.setString(7, usuario.getPassword()); // password en el índice 7
            stmt.setInt(8, usuario.getIdUsuario());   // El idUsuario en el índice 8
        } else {
            stmt.setInt(7, usuario.getIdUsuario());   // El idUsuario en el índice 7 (sin password)
        }
        
        // Ejecutar la actualización
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        System.err.println("Error al actualizar usuario: " + e.getMessage());
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
        
        
        
          /*
    =====================MET 6================================
       MÉTODO PARA LISTAR USUARIOS FILTRADOS EN BASE A OTRA LISTA (ESTUDIANTES, PROFESORES Y ADMINISTRADORES)
        (EXTRAE EL idUuario DE CADA OBJETO PARA BUSCARLO EN USUARIOS)
    ==========================================================
    */
    public List<Usuario> listarUsuariosEnBaseAOtraLista(List<?> lista){
        
           if (lista == null || lista.isEmpty()) {
               System.out.println("la lista secundaria está vacia");
        return null;
    }

    // Obtener los ID de usuario usando reflexión
    List<Integer> ids = new ArrayList<>();

    try{
            for (Object obj : lista) {
        Method metodo = obj.getClass().getMethod("getIdUsuario");  // usa reflexión
        int id = (int) metodo.invoke(obj);
        ids.add(id);
    }
    } catch (IllegalAccessException | NoSuchMethodException | SecurityException | InvocationTargetException e){
        System.out.println("Error en el método que tiene try-catch que obtiene el método getIdUsuario (listarUsuariosEnBaseAOtraLista method)");
        System.out.println(e.getMessage());
    }

    // Construir los placeholders (?, ?, ?, ...)
    String placeholders = ids.stream()
            .map(id -> "?")
            .collect(Collectors.joining(", "));

    String sql = "SELECT * FROM usuarios WHERE idUsuario IN (" + placeholders + ")";
    
        System.out.println(sql);
        System.out.println(ids);
         
         //LISTA VACIA QUE ALMACENARÁ LOS RESULTADOS
         List<Usuario> listaTemporal = new ArrayList<>();
        
              //ZONA DE REEMPLAZO DE ? EN EL QUERY PREPARADO
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
                // Insertar los valores
    for (int i = 0; i < ids.size(); i++) {
        stmt.setInt(i + 1, ids.get(i));
    }
           
            //ZONA DE OBTENCIÓN DE RESULTADO DE QUERY
            try(ResultSet rs = stmt.executeQuery()){
                
                /*
                YA QUE HAY VARIOS RESULTADOS, UN WHILE POR CADA RESULTADO
                CADA RESULTADO SE CONVIERTE EN UN OBJETO (.clases/Usuario.java)
                Y SE AGREGA A 'listaTemporal'
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
                            rs.getInt("estado")
                    );
                    
                    listaTemporal.add(usuario);
                }
                
                //EL MÉTODO DEVUELVE EL LISTADO
                return listaTemporal;
            
            }} catch (SQLException e){
                /*
                SI HAY ERROR DEVOLVER NULL
                (ALTERNATIVA A UN ARRAYLIST PARA ERROR)
                */
                 System.err.println("Error al listar usuarios: " + e.getMessage());
                return null;
            }
    }
}
