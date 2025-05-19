
package middlewares;

import Clases.Administrador;
import Clases.Estudiante;
import Clases.Profesor;
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
    private Object currentRoleData;
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

    public Object getCurrentRoleData() {
        return currentRoleData;
    }

    public void setCurrentRoleData(Object currentRoleData) {
        this.currentRoleData = currentRoleData;
    }
    
    
    
    public boolean login(int id, String password) {
    Usuario user = autenticarUsuario(id, password);
    if (user == null) return false;

    Object datosRol = obtenerDatosRol(user);
    if (datosRol == null) return false;

    cargarDatosSesion(user, datosRol);
    return true;
}

    
    private Usuario autenticarUsuario(int id, String password) {
    String sql = "SELECT * FROM usuarios WHERE identificacion = ? AND password = ?";
    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.setString(2, password);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
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
    } catch (SQLException e) {
        System.err.println("Error al autenticar usuario: " + e.getMessage());
    }
    return null;
}

    private Object obtenerDatosRol(Usuario user) {
    String tabla = user.getRol();
    String sql = "SELECT * FROM " + tabla + " WHERE idUsuario = ?";

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, user.getIdUsuario());

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                switch (tabla) {
                    case "Estudiantes":
                        return new Estudiante(
                            rs.getInt("idEstudiante"),
                            rs.getInt("idUsuario"),
                            rs.getInt("grado")
                        );
                    case "Profesores":
                        return new Profesor(
                            rs.getInt("idProfesor"),
                            rs.getInt("idUsuario"),
                            rs.getInt("idDepartamento"),
                            rs.getString("especialidad")
                        );
                    case "Administradores":
                        return new Administrador(
                            rs.getInt("idAdministrador"),
                            rs.getInt("idUsuario")
                        );
                }
            }
        }
    } catch (SQLException e) {
        System.err.println("Error al obtener datos del rol: " + e.getMessage());
    }
    return null;
}

    
    private void cargarDatosSesion(Usuario user, Object datosRol) {
    setCurrentSessionData(user);
    setCurrentRoleData(datosRol);
}

    
    
    //Método para cerrar sesión
    public boolean logOut(){
        this.currentSessionData = null;
        this.currentRoleData = null;
        return true;
    }
}
