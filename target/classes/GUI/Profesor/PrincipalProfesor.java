/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI.Profesor;

import Clases.Curso;
import Clases.Profesor;
import Clases.Usuario;
import static GUI.Administrador.PrincipalAdministrador.cargarTabla;
import GUI.Login;
import Managers.CursoManager;
import Managers.UsuarioManager;
import java.awt.Color;
import java.lang.reflect.Field;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import middlewares.CurrentSession;



/**
 *
 * @author criskop
 */
public class PrincipalProfesor extends javax.swing.JFrame {

    
    
     CurrentSession currentSession = CurrentSession.getInstance();
    Usuario currentUser = currentSession.getCurrentSessionData();
    Profesor currentRole = (Profesor) currentSession.getCurrentRoleData();
    CursoManager cursoManager = new CursoManager();
    
    
    /**
     * Creates new form Principal
     */
    public PrincipalProfesor() {
        initComponents();
          welcomeMessageName.setText(currentUser.getNombre() + "!");
        cargarModeloTablas();
        cargarTablasNecesarias();
        loadCurrentInformation();
    }
    
    
      private void cargarModeloTablas(){ 
           String[] columnasCursos = {"ID de curso", "Departamento", "Nombre", "Hora Inicial", "Hora Final", "Maximo de estudiantes", "Créditos"};
        Crear_Modelo(columnasCursos, tableCursosImpartidos);

    }
      
      private void cargarTablasNecesarias(){
          cargarTablaCursosImpartidos();
     
    }
      
       private void cargarTablaCursosImpartidos(){
           List<Curso> listaCursos = cursoManager.listarCursosDeProfesor(currentRole.getIdProfesor());
        
        if(listaCursos != null) {
            cargarTabla(tableCursosImpartidos, listaCursos);
            counterCursosImpartidos.setText(String.valueOf(listaCursos.size()));
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
    
    
      private void cambiarTab(int index) {
    try {
        TabbedContainer.setSelectedIndex(index);

        JButton[] tabsButtons = {TabBtn1, TabBtn2};

        for (int i = 0; i < tabsButtons.length; i++) {
            if (i == index) {
                // Botón activo
                tabsButtons[i].setBackground(new java.awt.Color(255, 255, 255));
                 tabsButtons[i].setForeground(new java.awt.Color(0,0,0));
                
            } else {
                // Botones inactivos
                tabsButtons[i].setBackground(new java.awt.Color(4, 205, 4));
                tabsButtons[i].setForeground(new java.awt.Color(255, 255, 255));
            }
        }

    } catch (Exception e) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LeftBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TabBtnLogOut = new javax.swing.JButton();
        TabBtn1 = new javax.swing.JButton();
        TabBtn2 = new javax.swing.JButton();
        RightContainer = new javax.swing.JPanel();
        TabbedContainer = new javax.swing.JTabbedPane();
        Tab1Container = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        counterCursosImpartidos = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableCursosImpartidos = new javax.swing.JTable();
        btnVerDetallesCursoImpartido = new javax.swing.JButton();
        welcomeMessageName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Tab2Container = new javax.swing.JPanel();
        statusText = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        settingsInputNombres = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        settingsInputApellidos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        settingsInputEmail = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        settingsInputTelefono = new javax.swing.JTextField();
        settingsActualPasswordInput = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        settingsNewPasswordInput = new javax.swing.JPasswordField();
        jLabel15 = new javax.swing.JLabel();
        settingsBtnApplyChanges = new javax.swing.JButton();
        changePasswordCheckBox = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 750));

        LeftBar.setBackground(new java.awt.Color(4, 206, 4));
        LeftBar.setMinimumSize(new java.awt.Dimension(230, 750));
        LeftBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        LeftBar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 230, -1));

        TabBtnLogOut.setBackground(new java.awt.Color(174, 197, 177));
        TabBtnLogOut.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        TabBtnLogOut.setForeground(new java.awt.Color(255, 255, 255));
        TabBtnLogOut.setText("Cerrar Sesion");
        TabBtnLogOut.setBorder(null);
        TabBtnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TabBtnLogOutActionPerformed(evt);
            }
        });
        LeftBar.add(TabBtnLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 210, 60));

        TabBtn1.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        TabBtn1.setText("Principal");
        TabBtn1.setBorder(null);
        TabBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TabBtn1ActionPerformed(evt);
            }
        });
        LeftBar.add(TabBtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 166, 210, 60));

        TabBtn2.setBackground(new java.awt.Color(4, 205, 4));
        TabBtn2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        TabBtn2.setForeground(new java.awt.Color(255, 255, 255));
        TabBtn2.setText("Configuracion");
        TabBtn2.setBorder(null);
        TabBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TabBtn2ActionPerformed(evt);
            }
        });
        LeftBar.add(TabBtn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 210, 60));

        getContentPane().add(LeftBar, java.awt.BorderLayout.LINE_START);

        RightContainer.setBackground(new java.awt.Color(251, 251, 254));
        RightContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tab1Container.setBackground(new java.awt.Color(251, 251, 254));
        Tab1Container.setMinimumSize(new java.awt.Dimension(970, 750));
        Tab1Container.setPreferredSize(new java.awt.Dimension(970, 750));
        Tab1Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setForeground(new java.awt.Color(62, 255, 59));
        Tab1Container.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 360, 10));

        counterCursosImpartidos.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        counterCursosImpartidos.setText("0");
        Tab1Container.add(counterCursosImpartidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        tableCursosImpartidos.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        tableCursosImpartidos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tableCursosImpartidos.setRowHeight(40);
        tableCursosImpartidos.setSelectionBackground(new java.awt.Color(0, 204, 51));
        tableCursosImpartidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCursosImpartidosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tableCursosImpartidos);

        Tab1Container.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 910, 470));

        btnVerDetallesCursoImpartido.setBackground(new java.awt.Color(242, 242, 242));
        btnVerDetallesCursoImpartido.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        btnVerDetallesCursoImpartido.setForeground(new java.awt.Color(255, 255, 255));
        btnVerDetallesCursoImpartido.setText("Ver Detalles");
        btnVerDetallesCursoImpartido.setBorder(null);
        btnVerDetallesCursoImpartido.setEnabled(false);
        btnVerDetallesCursoImpartido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetallesCursoImpartidoActionPerformed(evt);
            }
        });
        Tab1Container.add(btnVerDetallesCursoImpartido, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 910, 60));

        welcomeMessageName.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        welcomeMessageName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        welcomeMessageName.setText("!");
        Tab1Container.add(welcomeMessageName, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 720, -1));

        jLabel5.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        jLabel5.setText("Bienvenido");
        Tab1Container.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jLabel9.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel9.setText("Cursos Impartidos:");
        Tab1Container.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        TabbedContainer.addTab("Cursos disponibles", Tab1Container);

        Tab2Container.setBackground(new java.awt.Color(251, 251, 254));
        Tab2Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        statusText.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        statusText.setForeground(new java.awt.Color(204, 0, 51));
        statusText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Tab2Container.add(statusText, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 680, 40));

        jSeparator4.setForeground(new java.awt.Color(62, 255, 59));
        Tab2Container.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 360, 10));

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 32)); // NOI18N
        jLabel10.setText("Actualizar Información");
        Tab2Container.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        settingsInputNombres.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        Tab2Container.add(settingsInputNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 290, 40));

        jLabel11.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel11.setText("Apellidos");
        Tab2Container.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, -1, -1));

        settingsInputApellidos.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        Tab2Container.add(settingsInputApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 290, 40));

        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel12.setText("Email");
        Tab2Container.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 400, -1, -1));

        settingsInputEmail.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        Tab2Container.add(settingsInputEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 290, 40));

        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel13.setText("Contraseña actual");
        Tab2Container.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 310, -1, -1));

        settingsInputTelefono.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        Tab2Container.add(settingsInputTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 290, 40));

        settingsActualPasswordInput.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        Tab2Container.add(settingsActualPasswordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 290, 40));

        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel14.setText("Telefono");
        Tab2Container.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, -1, -1));

        settingsNewPasswordInput.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        settingsNewPasswordInput.setEnabled(false);
        Tab2Container.add(settingsNewPasswordInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 430, 290, 40));

        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel15.setText("Contraseña nueva");
        Tab2Container.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, -1, -1));

        settingsBtnApplyChanges.setBackground(new java.awt.Color(4, 205, 4));
        settingsBtnApplyChanges.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        settingsBtnApplyChanges.setForeground(new java.awt.Color(255, 255, 255));
        settingsBtnApplyChanges.setText("Aplicar Cambios");
        settingsBtnApplyChanges.setBorder(null);
        settingsBtnApplyChanges.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBtnApplyChangesActionPerformed(evt);
            }
        });
        Tab2Container.add(settingsBtnApplyChanges, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 520, 680, 60));

        changePasswordCheckBox.setText("Cambiar contraseña");
        changePasswordCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                changePasswordCheckBoxStateChanged(evt);
            }
        });
        Tab2Container.add(changePasswordCheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 480, -1, -1));

        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel16.setText("Nombres");
        Tab2Container.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, -1, -1));

        TabbedContainer.addTab("Configuracion", Tab2Container);

        RightContainer.add(TabbedContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -40, -1, 790));

        getContentPane().add(RightContainer, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabBtnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TabBtnLogOutActionPerformed
        logout();
    }//GEN-LAST:event_TabBtnLogOutActionPerformed

    private void TabBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TabBtn1ActionPerformed
        cambiarTab(0);
    }//GEN-LAST:event_TabBtn1ActionPerformed

    private void TabBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TabBtn2ActionPerformed
        cambiarTab(1);
    }//GEN-LAST:event_TabBtn2ActionPerformed

    private void btnVerDetallesCursoImpartidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetallesCursoImpartidoActionPerformed
           JTable tabla = tableCursosImpartidos;
        int fila = tabla.getSelectedRow();
        if (fila != -1) {

          Curso cursoADetallar = cursoManager.obtenerCursoPorID((int) tabla.getValueAt(fila, 0));
          
          if(cursoADetallar != null){
           DetalleCursoProfesor ventana = new DetalleCursoProfesor(cursoADetallar);
            ventana.setLocationRelativeTo(this);
            ventana.setVisible(true);
          } else {
                JOptionPane.showMessageDialog(null, "Error en la acción");
          }
         
        }
          
    }//GEN-LAST:event_btnVerDetallesCursoImpartidoActionPerformed

    private void settingsBtnApplyChangesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBtnApplyChangesActionPerformed
        updateUserInformation();
    }//GEN-LAST:event_settingsBtnApplyChangesActionPerformed

    private void changePasswordCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_changePasswordCheckBoxStateChanged
        settingsNewPasswordInput.setEnabled(!settingsNewPasswordInput.isEnabled());
    }//GEN-LAST:event_changePasswordCheckBoxStateChanged

    private void tableCursosImpartidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCursosImpartidosMouseClicked
          JTable tabla = tableCursosImpartidos;
        int fila = tabla.getSelectedRow();
        if (fila != -1) {

            JButton[] buttons = {btnVerDetallesCursoImpartido};
            habilitarBotones(buttons);

        }
    }//GEN-LAST:event_tableCursosImpartidosMouseClicked

    
    
    
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
    
    
     UsuarioManager usuarioManager = new UsuarioManager();
    
    private void loadCurrentInformation(){
          settingsInputNombres.setText(currentUser.getNombre());
    settingsInputApellidos.setText(currentUser.getApellido());
     settingsInputEmail.setText(currentUser.getEmail());
     settingsInputTelefono.setText(currentUser.getTelefono());
    }
    
    private boolean updateUserInformation(){
        String nombres = settingsInputNombres.getText().trim();
    String apellidos = settingsInputApellidos.getText().trim();
    String email = settingsInputEmail.getText().trim();
    String telefono = settingsInputTelefono.getText().trim();
    String currentPassword = String.valueOf(settingsActualPasswordInput.getPassword()).trim();
    boolean changingPassword = changePasswordCheckBox.isSelected();
    String newPassword = String.valueOf(settingsNewPasswordInput.getPassword()).trim(); // Corrige esto si usaste otro campo
    
        boolean validateInputs = validateInputs();
        if(validateInputs == false) return false;
        
        Usuario nuevoObjeto = applyChangesLocally(nombres, apellidos, email, telefono, currentPassword, changingPassword, newPassword);
        if(nuevoObjeto == null) return false;
        
        boolean changesAppliedOnDB = applyChangesWithManager(nuevoObjeto);
        if(changesAppliedOnDB == false) return false;
        
        
        statusText.setText("Aplicado con éxito");
        statusText.setForeground(new Color(51, 153, 0)); // Verde
        return true;
        
    }
    
    private boolean applyChangesWithManager(Usuario nuevoObjeto){
        return usuarioManager.actualizarUsuario(nuevoObjeto);
    }
    
    private Usuario applyChangesLocally(String nombre, String apellido, String email, String telefono, String currentPassword, Boolean isChangingPassword, String newPassword){
        try{
               currentUser.setNombre(nombre);
        currentUser.setApellido(apellido);
        currentUser.setEmail(email);
        currentUser.setTelefono(telefono);
        
        if(isChangingPassword == true && currentUser.getPassword().equals(currentPassword)){
               currentUser.setPassword(newPassword);
        }
        
        return currentUser;
        }catch (Exception e){
            System.out.println("Error en aplicar cambios localmente con el middleware");
            System.out.println(e.getMessage());
            return null;
        }
     
    }
    
   private boolean validateInputs() {
    String nombres = settingsInputNombres.getText().trim();
    String apellidos = settingsInputApellidos.getText().trim();
    String email = settingsInputEmail.getText().trim();
    String telefono = settingsInputTelefono.getText().trim();
    String currentPassword = String.valueOf(settingsActualPasswordInput.getPassword()).trim();
    boolean changingPassword = changePasswordCheckBox.isSelected();
    String newPassword = String.valueOf(settingsNewPasswordInput.getPassword()).trim(); // Corrige esto si usaste otro campo

    // Verificar campos obligatorios
    if (nombres.isEmpty() || apellidos.isEmpty() || email.isEmpty() || telefono.isEmpty() || currentPassword.isEmpty() ||
        (changingPassword && newPassword.isEmpty())) {

        statusText.setText("Campos faltantes");
        statusText.setForeground(new Color(204, 0, 51)); // Rojo
        return false;

    } else {
        return true;
    }
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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrincipalProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LeftBar;
    private javax.swing.JPanel RightContainer;
    private javax.swing.JPanel Tab1Container;
    private javax.swing.JPanel Tab2Container;
    private javax.swing.JButton TabBtn1;
    private javax.swing.JButton TabBtn2;
    private javax.swing.JButton TabBtnLogOut;
    private javax.swing.JTabbedPane TabbedContainer;
    private javax.swing.JButton btnVerDetallesCursoImpartido;
    private javax.swing.JCheckBox changePasswordCheckBox;
    private javax.swing.JLabel counterCursosImpartidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPasswordField settingsActualPasswordInput;
    private javax.swing.JButton settingsBtnApplyChanges;
    private javax.swing.JTextField settingsInputApellidos;
    private javax.swing.JTextField settingsInputEmail;
    private javax.swing.JTextField settingsInputNombres;
    private javax.swing.JTextField settingsInputTelefono;
    private javax.swing.JPasswordField settingsNewPasswordInput;
    private javax.swing.JLabel statusText;
    private javax.swing.JTable tableCursosImpartidos;
    private javax.swing.JLabel welcomeMessageName;
    // End of variables declaration//GEN-END:variables
}
