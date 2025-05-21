/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.Estudiante;

import Clases.Calificacion;
import Clases.Curso;
import Clases.Estudiante;
import Clases.Evaluacion;
import Clases.Usuario;
import GUI.Login;
import Managers.CalificacionManager;
import Managers.CursoManager;
import Managers.EvaluacionManager;
import java.awt.Color;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import middlewares.CurrentSession;



/**
 *
 * @author criskop
 */
public class DetalleCursoEstudiante extends javax.swing.JFrame {
    Curso currentCurso;
    /**
     * Creates new form Principal
     */
    
      CurrentSession currentSession = CurrentSession.getInstance();
    Usuario currentUser = currentSession.getCurrentSessionData();
    Estudiante currentRole = (Estudiante) currentSession.getCurrentRoleData();
    CursoManager cursoManager = new CursoManager();
    EvaluacionManager evaluacionManager = new EvaluacionManager();
    CalificacionManager calificacionManager = new CalificacionManager();
   
    public DetalleCursoEstudiante(Curso curso) {
        initComponents();
        welcomeMessageName.setText(curso.getNombre());
        cargarModeloTablas();
        this.currentCurso = curso;
        cargarTablasNecesarias();

    }
    
    private void cambiarTab(int index){
        try{
            TabbedContainer.setSelectedIndex(index);
        } catch(Exception e){
            System.out.println("Error al cambiar de tab (probablemente index no encontrado)");
            System.out.println(e.getMessage());
        }
    }
    
    private void logout(){
          // Limpia sesión antes que nada
    currentSession.logOut();

    // Cierra esta ventana
    this.dispose();

     Login login = new Login();
  
     login.setVisible(true);
  

    }
    
    
   private void calcularPromedio() {
    List<Calificacion> listaCalificaciones = calificacionManager.listarCalificacionesDeEstudiante(
        currentCurso.getIdCurso(), 
        currentRole.getIdEstudiante()
    );

    float nota1 = 0, nota2 = 0, nota3 = 0, nota4 = 0;

    if (listaCalificaciones != null && !listaCalificaciones.isEmpty()) {
        if (listaCalificaciones.size() > 0) {
            nota1 = listaCalificaciones.get(0).getNota();
        }
        if (listaCalificaciones.size() > 1) {
            nota2 = listaCalificaciones.get(1).getNota();
        }
        if (listaCalificaciones.size() > 2) {
            nota3 = listaCalificaciones.get(2).getNota();
        }
        if (listaCalificaciones.size() > 3) {
            nota4 = listaCalificaciones.get(3).getNota();
        }
    }

    // Opcional: calcular el promedio solo con las notas que existen
    int cantidadNotas = Math.min(listaCalificaciones != null ? listaCalificaciones.size() : 0, 4);
    float suma = ((nota1 + nota2 + nota3) / 3 * 0.7f) + (nota4 * 0.3f);
    float promedio = cantidadNotas > 0 ? suma / cantidadNotas : 0;

    System.out.println("Promedio: " + promedio);
    labelPromedio.setText(String.valueOf(promedio));
}

    
    
      private void cargarModeloTablas(){
            String[] columnasEvals = {"ID de evaluacion", "ID Del curso", "ID del Profesor", "Titulo", "Fecha de Inicio", "Plazo de entrega", "Tipo"};
        Crear_Modelo(columnasEvals, tableCursoEvals);
        
                String[] columnasCalificaciones = {"ID de calificacion", "ID de evaluacion",  "ID del estudiante", "Fecha de entrega", "Nota"};
        Crear_Modelo(columnasCalificaciones, tableCalificaciones);
}
      
        private void cargarTablasNecesarias(){
       cargarTablaCursoEvalsPendientes();
       cargarTablaCalificacionesDeEstudiante();
       calcularPromedio();
     
    }
        
        
          private void cargarTablaCursoEvalsPendientes(){
           List<Evaluacion> listaEvaluaciones = evaluacionManager.listarEvaluacionesPendientesCurso(currentCurso.getIdCurso(), currentRole.getIdEstudiante());
        
        if(listaEvaluaciones != null) {
            cargarTabla(tableCursoEvals, listaEvaluaciones);
        }
    }
          
           private void cargarTablaCalificacionesDeEstudiante(){
            List<Calificacion> listaCalificaciones = calificacionManager.listarCalificacionesDeEstudiante(currentCurso.getIdCurso(), currentRole.getIdEstudiante());
        
        if(listaCalificaciones != null) {
            cargarTabla(tableCalificaciones, listaCalificaciones);
        }
      }
        
       
       
       
        public static void cargarTabla(JTable tabla, List<?> lista) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);  // Limpiar tabla

        if (lista == null || lista.isEmpty()) {
            return;
        }

        Object ejemplo = lista.get(0);
        Field[] campos = ejemplo.getClass().getDeclaredFields();

        for (Object obj : lista) {
            Object[] fila = new Object[campos.length];
            try {
                for (int i = 0; i < campos.length; i++) {
                    campos[i].setAccessible(true);
                    fila[i] = campos[i].get(obj);
                }
                modelo.addRow(fila);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RightContainer = new javax.swing.JPanel();
        TabbedContainer = new javax.swing.JTabbedPane();
        Tab1Container = new javax.swing.JPanel();
        welcomeMessageName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCursoEvals = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        labelPromedio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCalificaciones = new javax.swing.JTable();
        BtnSalirCurso1 = new javax.swing.JButton();
        btnEvaluacionEnviar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(970, 750));
        setResizable(false);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        RightContainer.setBackground(new java.awt.Color(251, 251, 254));
        RightContainer.setMinimumSize(new java.awt.Dimension(970, 700));
        RightContainer.setPreferredSize(new java.awt.Dimension(970, 700));
        RightContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tab1Container.setBackground(new java.awt.Color(251, 251, 254));
        Tab1Container.setMinimumSize(new java.awt.Dimension(940, 700));
        Tab1Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcomeMessageName.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        welcomeMessageName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        welcomeMessageName.setText("!");
        Tab1Container.add(welcomeMessageName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 40, 370, -1));

        jSeparator1.setForeground(new java.awt.Color(62, 255, 59));
        Tab1Container.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 360, 10));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        jLabel5.setText("Detalles del curso");
        Tab1Container.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        tableCursoEvals.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        tableCursoEvals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableCursoEvals.setRowHeight(40);
        tableCursoEvals.setSelectionBackground(new java.awt.Color(4, 205, 4));
        tableCursoEvals.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCursoEvalsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableCursoEvals);

        Tab1Container.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 910, 130));
        Tab1Container.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 910, 10));

        labelPromedio.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        labelPromedio.setText("0");
        Tab1Container.add(labelPromedio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, -1, -1));

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel3.setText("Promedio:");
        Tab1Container.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));
        Tab1Container.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 910, 10));

        jLabel6.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel6.setText("Evaluaciones pendientes");
        Tab1Container.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel7.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel7.setText("Tus calificaciones de este curso");
        Tab1Container.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, -1));

        tableCalificaciones.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        tableCalificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableCalificaciones.setRowHeight(40);
        tableCalificaciones.setSelectionBackground(new java.awt.Color(4, 205, 4));
        jScrollPane2.setViewportView(tableCalificaciones);

        Tab1Container.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 910, 130));

        BtnSalirCurso1.setBackground(new java.awt.Color(174, 197, 177));
        BtnSalirCurso1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        BtnSalirCurso1.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalirCurso1.setText("Retirarse del Curso");
        BtnSalirCurso1.setBorder(null);
        BtnSalirCurso1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirCurso1ActionPerformed(evt);
            }
        });
        Tab1Container.add(BtnSalirCurso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 210, 60));

        btnEvaluacionEnviar.setBackground(new java.awt.Color(242, 242, 242));
        btnEvaluacionEnviar.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        btnEvaluacionEnviar.setForeground(new java.awt.Color(255, 255, 255));
        btnEvaluacionEnviar.setText("Enviar evaluacion");
        btnEvaluacionEnviar.setBorder(null);
        btnEvaluacionEnviar.setEnabled(false);
        btnEvaluacionEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEvaluacionEnviarActionPerformed(evt);
            }
        });
        Tab1Container.add(btnEvaluacionEnviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 910, 60));

        TabbedContainer.addTab("Principal", Tab1Container);

        RightContainer.add(TabbedContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, 970, 790));

        getContentPane().add(RightContainer);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnSalirCurso1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirCurso1ActionPerformed
        retirarEstudianteDeCurso();
    }//GEN-LAST:event_BtnSalirCurso1ActionPerformed

    private void btnEvaluacionEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEvaluacionEnviarActionPerformed
        createNewCalificacion();
    }//GEN-LAST:event_btnEvaluacionEnviarActionPerformed

    private void tableCursoEvalsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCursoEvalsMouseClicked
           JTable tabla = tableCursoEvals;
        int fila = tabla.getSelectedRow();
        if (fila != -1) {
        
        JButton[] buttons = {btnEvaluacionEnviar};
        habilitarBotones(buttons);

        }
    }//GEN-LAST:event_tableCursoEvalsMouseClicked

    
    
     private void desabilitarBotones(JButton[] botones){
        
         for(JButton btn : botones){
             
               if(!btn.getText().equals("Crear nuevo")){
            btn.setBackground(new Color(242,242,242));
            btn.setEnabled(false);
               }
        }
         
    }
    
    private void habilitarBotones(JButton[] botones){
        
        for(JButton btn : botones){
            if(btn.getText().equals("Eliminar")){
                btn.setBackground(new Color(255,51,51));
                btn.setEnabled(true);
            } else {
                btn.setBackground(new Color(4,205,4));
                btn.setEnabled(true);
            }
        }
        
    }
    
    
      private void successText(JLabel statusLabel){
         JOptionPane.showMessageDialog(null, "Accion efectuada con éxito");
   
         if(statusLabel != null){
                    statusLabel.setText("Aplicado con éxito");
        statusLabel.setForeground(new Color(51, 153, 0)); // Verde
         }
    }
      
      
      
      
      
       private void retirarEstudianteDeCurso(){

             boolean accion = actionRetirarEstudianteDeCurso();
        if(accion == false) return;
        
                             this.dispose();
                   successText(null);
             
    }
      
       private boolean actionRetirarEstudianteDeCurso(){
   
                  
                   boolean accion = cursoManager.retirarEstudiante(currentRole.getIdEstudiante() ,(int) currentCurso.getIdCurso());
                   
                if(accion == false){
                    JOptionPane.showMessageDialog(null, "Error al efectuar accion");
                    return false;
                }       
            
            
            cargarTablasNecesarias();
            return true;
    }
       
       
       
       
       
        private void createNewCalificacion(){
        
        JComponent[] inputsList = {};
              boolean[] needed = {};
        JButton[] buttonsList = {btnEvaluacionEnviar};
   
        
        boolean accion = actionCreateCalificacion();
        if(accion == false) return;
        
            cargarTablasNecesarias();
           desabilitarBotones(buttonsList);
           successText(null);
        
    }
    
    
        private boolean actionCreateCalificacion(){

            
              int fila = tableCursoEvals.getSelectedRow();
            if (fila != -1) {
                  Object idEvaluacion = tableCursoEvals.getValueAt(fila, 0);
                  
    try {
        // Obtener las fechas como Strings desde los inputs
        String fechaInicioStr = tableCursoEvals.getValueAt(fila, 4).toString();
        String fechaFinStr = tableCursoEvals.getValueAt(fila, 5).toString();

        
        Timestamp fechaActual = Timestamp.valueOf(LocalDateTime.now());

        Timestamp fechaInicioTimestamp = Timestamp.valueOf(fechaInicioStr);
        Timestamp fechaFinTimestamp = Timestamp.valueOf(fechaFinStr);

        // Verifica si la fecha actual está fuera del rango (antes del inicio o después del fin)
        if (fechaActual.before(fechaInicioTimestamp)) {
            System.out.println("Aún no ha comenzado");
            JOptionPane.showMessageDialog(null, "¡Estás enviando demasiado antes! ¡Todavia no es el inicio de la entrega!");
            return false;
            
        }
        
        if (fechaActual.after(fechaFinTimestamp)) {
            System.out.println("Ya terminó");
            JOptionPane.showMessageDialog(null, "Ya se acabó el plazo de entrega de esta evaluación :(");
            return false;
        }
      

        // Crear el nuevo objeto Evaluacion con los Timestamps
        Calificacion nuevoObjeto = new Calificacion(
            0,
            (int) idEvaluacion,
            currentRole.getIdEstudiante(),
            fechaActual,
            0
        );

          boolean accion = calificacionManager.InsertarCalificacion(nuevoObjeto, currentCurso.getIdCurso());

                if(accion == false){
                    JOptionPane.showMessageDialog(null, "Error al efectuar accion");
                    return false;
                }

                cargarTablasNecesarias();
                return true;
    } catch (IllegalArgumentException e) {
        e.printStackTrace();
        // Manejo de error si el formato de la fecha no es válido
    }

       
        //FIN DEL METODO
        }
             return false;
       }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DetalleCursoEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DetalleCursoEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DetalleCursoEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DetalleCursoEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
   

        
    }
    
    
    
    
    
    DefaultTableModel modelo;
 private void Crear_Modelo(String[] titulos, JTable tablaDestino) {
    try {
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {

            // Tipos de datos por columna (aquí todos String, puedes modificarlo)
            Class[] types = new Class[titulos.length];
            {
                for (int i = 0; i < types.length; i++) {
                    types[i] = String.class;
                }
            }

            // Todas las celdas no editables
            boolean[] canEdit = new boolean[titulos.length];
            {
                for (int i = 0; i < canEdit.length; i++) {
                    canEdit[i] = false;
                }
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        tablaDestino.setModel(modelo);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e.toString() + " - Error al crear modelo de tabla");
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSalirCurso1;
    private javax.swing.JPanel RightContainer;
    private javax.swing.JPanel Tab1Container;
    private javax.swing.JTabbedPane TabbedContainer;
    private javax.swing.JButton btnEvaluacionEnviar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelPromedio;
    private javax.swing.JTable tableCalificaciones;
    private javax.swing.JTable tableCursoEvals;
    private javax.swing.JLabel welcomeMessageName;
    // End of variables declaration//GEN-END:variables
}
