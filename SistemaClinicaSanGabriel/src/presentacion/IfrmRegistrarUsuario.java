/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import entidades.Rol;
import entidades.Usuario;
import logica.UsuarioLOG;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author yordi
 */
public class IfrmRegistrarUsuario extends javax.swing.JInternalFrame {

    private String username;
    private String password;
    private Rol rol;
    private boolean estado;
    private Usuario usuario;

    public IfrmRegistrarUsuario() {
        initComponents();

        cargarRoles();

        cbxRol.setSelectedIndex(-1);
        cbxEstado.setSelectedIndex(-1);
    }

    private void cargarRoles() {
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Rol rol : Rol.values()) {
            model.addElement(rol.name());
        }
        cbxRol.setModel(model);
    }

    private void obtenerDatosFormulario() {
        username = txtUsername.getText().trim();
        password = txtPassword.getText().trim();
        String rolStr = (String) cbxRol.getSelectedItem();
        rol = Rol.valueOf(rolStr);
        String estadoStr = (String) cbxEstado.getSelectedItem();
        estado = estadoStr.equals("ACTIVO");
    }

    private boolean validarCampos() {
        if (txtUsername.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo USERNAME esta vacio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            txtUsername.requestFocus();
            return false;
        }
        if (txtPassword.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo CONTRASEÑA esta vacio.", "Validacion", JOptionPane.WARNING_MESSAGE);
            txtPassword.requestFocus();
            return false;
        }
        if (cbxRol.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un ROL.", "Validacion", JOptionPane.WARNING_MESSAGE);
            cbxRol.requestFocus();
            return false;
        }
        if (cbxEstado.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un ESTADO.", "Validacion", JOptionPane.WARNING_MESSAGE);
            cbxEstado.requestFocus();
            return false;
        }
        return true;
    }

    private void limpiarFormulario() {
        txtUsername.setText("");
        txtPassword.setText("");
        cbxRol.setSelectedIndex(-1);
        cbxEstado.setSelectedIndex(-1);
        username = "";
        password = "";
        rol = null;
        estado = false;
        usuario = null;

        txtUsername.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        cbxEstado = new javax.swing.JComboBox<>();
        cbxRol = new javax.swing.JComboBox<>();
        btnRegistrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jLabel1.setText("Registrar Usuario");

        jLabel2.setText("USERNAME");

        jLabel3.setText("CONTRASEÑA");

        jLabel4.setText("ROL");

        jLabel5.setText("ESTADO");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVO", "INACTIVO" }));

        cbxRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMINISTRADOR", "MEDICO" }));

        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(this::btnRegistrarActionPerformed);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(this::btnSalirActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(348, 348, 348)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addContainerGap(397, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(501, 501, 501))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnSalir)
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(69, 69, 69))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxRol, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnSalir)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        
        if(!validarCampos()) return;

        obtenerDatosFormulario();

        UsuarioLOG.registrarUsuario(username, password, rol, estado);

        limpiarFormulario();
        
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox<String> cbxEstado;
    private javax.swing.JComboBox<String> cbxRol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
