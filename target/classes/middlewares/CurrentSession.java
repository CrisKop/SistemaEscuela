
package middlewares;

import Clases.Usuario;
import DB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
=========================================================================
Linea de codigo para recuperar la clase instancia:

CurrentSession currentUser = CurrentSession.getInstance();
=========================================================================
*/

public class CurrentSession {
    
    private static CurrentSession instance;
    private Usuario currentSessionData;
    private Connection conexion;

    public CurrentSession() {
        this.conexion = Conexion.getConexion();
    }
    
    public static CurrentSession getInstance() {
    if (instance == null) {
        instance = new CurrentSession();
    }
    return instance;
}
    
    

    //Metodo para obtener los datos del usuario de la sesión actual
    public Usuario getCurrentSessionData() {
        return currentSessionData;
    }

    //Método para definir los datos del usuario de la sesión actual (preferiblemente solo usado por el login o métodos de la misma clase)
    private void setCurrentSessionData(Usuario currentSessionData) {
        this.currentSessionData = currentSessionData;
    }
    
    public Usuario login(int id, String password){
        //QUERY QUE SE EJECUTARÁ, CON ? PARA REEMPLAZAR
        String sql = "SELECT * FROM usuarios WHERE identificacion = ? AND password = ?";
        
        //ZONA DE REEMPLAZO DE ? EN EL QUERY PREPARADO
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            //REEMPLAZAR ? POR EL ARGUMENTO DEL MÉTODO
            stmt.setInt(1, id);
            stmt.setString(2, password);
            
            //ZONA DE OBTENCIÓN DE RESULTADO DE QUERY
            try(ResultSet rs = stmt.executeQuery()){
                
                /*SI HAY 1 RESULTADO, CREAR Y DEVOLVER EL OBJETO
                     (.clases/Usuario.java)
                */
                if(rs.next()){
                    Usuario userFound = new Usuario(
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
                    setCurrentSessionData(userFound);
                    return userFound;
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
    
    
    //Método para cerrar sesión
    public boolean logOut(){
        this.currentSessionData = null;
        return true;
    }
}
