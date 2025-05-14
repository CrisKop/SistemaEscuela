/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ing. Yesica
 */
// Definición de la clase pública Conexion
public class Conexion {

    // Método estático que retorna un objeto de tipo Connection
    public static Connection getConexion(){
        // Se declara e inicializa la variable de conexión
        Connection conexion = null;
        
        Dotenv dotenv = Dotenv.load();
        
        String dbName = dotenv.get("DB_NAME");
        String dbUser = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");
    
     

        // Nombre de la base de datos
        var baseDatos = dbName;
        // URL de conexión al servidor MySQL con el nombre de la base de datos
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        // Usuario de la base de datos
        var usuario = dbUser;
        // Contraseña del usuario
        var password = dbPassword;

        // Intentamos cargar el driver de MySQL y realizar la conexión
        try {
            // Cargamos la clase del driver de MySQL en Memoria
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecimiento de la conexión con la base de datos
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch(ClassNotFoundException | SQLException e){
            // Captura y muestra cualquier error que ocurra al cargar el driver o conectarse
            System.out.println("Ocurrió un error en la conexión: " + e.getMessage());
        }

        // Retorna la conexión (puede ser null si falló)
        return conexion;
    }

    // Método principal para probar la conexión
    public static void main(String[] args) {
        // Llama al método de conexión y almacena el resultado
        var conexion = Conexion.getConexion();

        // Verifica si la conexión fue exitosa
        if(conexion != null)
            System.out.println("Conexión exitosa: " + conexion);
        else
            System.out.println("Error al conectarse");
    }
}

