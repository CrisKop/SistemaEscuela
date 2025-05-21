/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Managers;

import DB.Conexion;
import Clases.Curso;
import Clases.Usuario;
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
public class CursoManager {
    
    private Connection conexion;
    
    public CursoManager() {
    
        this.conexion = Conexion.getConexion();
}
    
    public boolean insertarCurso (Curso curso) {
    
        String sql = "INSERT INTO cursos (idDepartamento, nombre, horaInicial, horaFinal, maxEstudiantes, creditos) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, curso.getIdDepartamento());
            stmt.setString(2, curso.getNombre());
            stmt.setTime  (3, curso.gethoraInicial());
            stmt.setTime  (4, curso.gethoraFinal());
            stmt.setInt   (5, curso.getmaxEstudiantes());
            stmt.setInt   (6, curso.getcreditos());
            
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
          
            System.err.println("Error al crear curso: " + e.getMessage());
            return false;
        }
}
    
    public Curso obtenerCursoPorID (int id){
        
        String sql = "SELECT * FROM cursos WHERE idCurso = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, id);
            
            try(ResultSet rs = stmt.executeQuery()){
                
                if(rs.next()){
                    
                    return new Curso(
                            
                            rs.getInt("idCurso"),
                            rs.getInt("idDepartamento"),
                            rs.getString("nombre"),
                            rs.getTime("horaInicial"),
                            rs.getTime("horaFinal"),
                            rs.getInt("maxEstudiantes"),
                            rs.getInt("creditos")
                            
                    );
                }
            }
            
            
        }catch (SQLException e){
            
            System.err.println("Error al obtener curso: " + e.getMessage());
                return null;
        }
        return null;
    }
    
    
    public List <Curso> listarCursos (){
        
        String sql = "SELECT * FROM cursos";
        
        List<Curso> lista = new ArrayList<>();
        
        try(Statement stmt = conexion.createStatement()){
            
            try(ResultSet rs = stmt.executeQuery(sql)){
                
                while(rs.next()){
                    
                    Curso curso = new Curso(
                            rs.getInt("idCurso"),
                            rs.getInt("idDepartamento"),
                            rs.getString("nombre"),
                            rs.getTime("horaInicial"),
                            rs.getTime("horaFinal"),
                            rs.getInt("maxEstudiantes"),
                            rs.getInt("creditos")
                            
                    );
                    
                    lista.add(curso);
                }
                
                return lista;
            }
        }catch (SQLException e){
            
            System.err.println("Error al listar cursos: " + e.getMessage());
                return null;
        }
        
    }
    
    
    public boolean actualizarCurso (Curso curso){
        
        String sql = "UPDATE cursos SET   idDepartamento = ? ,nombre = ?, horaInicial = ?, horaFinal = ?, maxEstudiantes = ?, creditos = ? WHERE idCurso = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt   (1, curso.getIdDepartamento());
            stmt.setString(2, curso.getNombre());
            stmt.setTime  (3, curso.gethoraInicial());
            stmt.setTime  (4, curso.gethoraFinal());
            stmt.setInt   (5, curso.getmaxEstudiantes());
            stmt.setInt   (6, curso.getcreditos());
            stmt.setInt   (7, curso.getIdCurso());
            
            return stmt.executeUpdate() > 0;
        
        }catch (SQLException e) {
            
            System.err.println("Error al actualizar curso: " + e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarCurso (int id){
        
        String sql = "DELETE FROM Cursos WHERE idCurso = ?";
        
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            return stmt.executeUpdate() > 0;
            
            
        }catch (SQLException e) {
            
            System.err.println("Error al eliminar Curso: " + e.getMessage());
            return false;
        }
    }
    
    
    public boolean retirarProfesorDeCurso (int profesorId, int cursoId){
        
        String sql = "DELETE FROM cursos_profesores WHERE idProfesor = ? AND idCurso = ?";
        
          try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            stmt.setInt(1, profesorId);
            stmt.setInt(2, cursoId);
            
            
                    // Mejor para depuración
        System.out.println("SQL: " + sql);
        System.out.println("Parámetros -> idProfesor: " + profesorId + ", idCurso: " + cursoId);
        
        
            return stmt.executeUpdate() > 0;
            
            
        }catch (SQLException e) {
            
            System.err.println("Error al retirar profesor de curso: " + e.getMessage());
            return false;
        }
        
    }
    
    
    
      public boolean asignarProfesorACurso (int profesorId, int cursoId){
        
        String sql = "INSERT INTO cursos_profesores (idProfesor, idCurso) VALUES (?, ?)";
        
          try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            
            stmt.setInt(1, profesorId);
            stmt.setInt(2, cursoId);
            
            
                    // Mejor para depuración
        System.out.println("SQL: " + sql);
        System.out.println("Parámetros -> idProfesor: " + profesorId + ", idCurso: " + cursoId);
        
        
            return stmt.executeUpdate() > 0;
            
            
        }catch (SQLException e) {
            
            System.err.println("Error al asignar profesor a curso: " + e.getMessage());
            return false;
        }
        
    }
      
      
      
           /*
    =====================MET 7================================
        MÉTODO PARA LISTAR LOS PROFESORES DENTRO DE UN CURSO
    ==========================================================
    */
    
    
    public List <Curso> listarCursosDeProfesor (int idProfesor){
        
        String sql = """
                     SELECT c.*
                     FROM cursos c
                     JOIN cursos_profesores cp ON c.idCurso = cp.idCurso
                     WHERE cp.idProfesor = ?
                     """;
        
        List<Curso> lista = new ArrayList<>();
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, idProfesor);
            
            try(ResultSet rs = stmt.executeQuery()){
                
                while(rs.next()){
                    
                    Curso curso = new Curso(
                            rs.getInt("idCurso"),
                            rs.getInt("idDepartamento"),
                            rs.getString("nombre"),
                            rs.getTime("horaInicial"),
                            rs.getTime("horaFinal"),
                            rs.getInt("maxEstudiantes"),
                            rs.getInt("creditos")
                            
                    );
                    
                    lista.add(curso);
                }
                
                return lista;
            }
        }catch (SQLException e){
            
            System.err.println("Error al listar cursos: " + e.getMessage());
                return null;
        }
        
    }
    
    
    
           /*
    =====================MET 8================================
        MÉTODO PARA LISTAR LOS ESTUDIANTES DENTRO DE UN CURSO
    ==========================================================
    */
    
    
    public List <Curso> listarCursosDeEstudiante (int idEstudiante){
        
        String sql = """
                     SELECT c.*
                     FROM cursos c
                     JOIN cursos_estudiantes ce ON c.idCurso = ce.idCurso
                     WHERE ce.idEstudiante = ?
                     """;
        
        List<Curso> lista = new ArrayList<>();
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, idEstudiante);
            
            try(ResultSet rs = stmt.executeQuery()){
                
                while(rs.next()){
                    
                    Curso curso = new Curso(
                            rs.getInt("idCurso"),
                            rs.getInt("idDepartamento"),
                            rs.getString("nombre"),
                            rs.getTime("horaInicial"),
                            rs.getTime("horaFinal"),
                            rs.getInt("maxEstudiantes"),
                            rs.getInt("creditos")
                            
                    );
                    
                    lista.add(curso);
                }
                
                return lista;
            }
        }catch (SQLException e){
            
            System.err.println("Error al listar cursos: " + e.getMessage());
                return null;
        }
        
    }
    
    
    
          /*
    =====================MET 8================================
        MÉTODO PARA LISTAR LOS ESTUDIANTES DENTRO DE UN CURSO
    ==========================================================
    */
    
    
    public List <Curso> listarCursosDisponiblesEstudiante (int idEstudiante){
        
        String sql = """
                    SELECT c.*
                     FROM cursos c
                     WHERE NOT EXISTS (
                         SELECT 1
                         FROM cursos_estudiantes ce
                         WHERE ce.idCurso = c.idCurso
                           AND ce.idEstudiante = ?
                     );
                     """;
        
        List<Curso> lista = new ArrayList<>();
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, idEstudiante);
            
            try(ResultSet rs = stmt.executeQuery()){
                
                while(rs.next()){
                    
                    Curso curso = new Curso(
                            rs.getInt("idCurso"),
                            rs.getInt("idDepartamento"),
                            rs.getString("nombre"),
                            rs.getTime("horaInicial"),
                            rs.getTime("horaFinal"),
                            rs.getInt("maxEstudiantes"),
                            rs.getInt("creditos")
                            
                    );
                    
                    lista.add(curso);
                }
                
                return lista;
            }
        }catch (SQLException e){
            
            System.err.println("Error al listar cursos: " + e.getMessage());
                return null;
        }
        
    }
    
    
   public boolean CantidadEstudiantesEnCursoMayorAMax(int idCurso, int maxEstudiantes) {
    String sql = "SELECT COUNT(*) FROM cursos_estudiantes WHERE idCurso = ?";

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, idCurso);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int cantidad = rs.getInt(1);
                System.out.println("Cantidad: " + cantidad);
                return cantidad <= maxEstudiantes;
            }
        }

    } catch (SQLException e) {
        System.err.println("Error al verificar cantidad de estudiantes: " + e.getMessage());
    }

    return false;
}
    
    
     public boolean inscribirEstudiante (int idEstudiante, int idCurso, int maxEstudiantes) {
    
        String sql = "INSERT INTO cursos_estudiantes (idEstudiante, idCurso) VALUES (?, ?)";
        
        boolean verificacionDeMaxEstudiantes = CantidadEstudiantesEnCursoMayorAMax(idCurso, maxEstudiantes);

        if (!verificacionDeMaxEstudiantes) {
            System.out.println("Curso lleno, no se puede inscribir");
            return false;
        }

        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idCurso);
            
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
          
            System.err.println("Error al inscribir estudiante: " + e.getMessage());
            return false;
        }
}
     
     
      public boolean retirarEstudiante (int idEstudiante, int idCurso) {
    
        String sql = "DELETE FROM cursos_estudiantes WHERE idEstudiante = ? AND idCurso = ?";
        
        try(PreparedStatement stmt = conexion.prepareStatement(sql)){
            
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idCurso);
            
            
            return stmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
          
            System.err.println("Error al inscribir estudiante: " + e.getMessage());
            return false;
        }
}
    
    
    
}
