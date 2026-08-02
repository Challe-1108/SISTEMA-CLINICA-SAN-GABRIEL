/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;
import datos.SeguroDAO;
import entidades.Apoderado;
import entidades.Paciente;
import entidades.SeguroMedico;
import logica.PacienteLOG;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import java.time.LocalDate;

/**
 *
 * @author sthef
 */
public class IfrmEditarPaciente extends javax.swing.JInternalFrame {
    private Paciente pacienteActual;
    
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtSexo;
    private javax.swing.JTextField txtNumeroHistoriaClinica;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblNumeroHistoriaClinica;
    private javax.swing.JTextField txtCompaniaSeguro;
    private javax.swing.JTextField txtNumeroPoliza;
    private javax.swing.JTextField txtTipoCobertura;
    private javax.swing.JLabel lblCompaniaSeguro;
    private javax.swing.JLabel lblNumeroPoliza;
    private javax.swing.JLabel lblTipoCobertura;

    public IfrmEditarPaciente(Paciente paciente) {
        

        txtDni = new javax.swing.JTextField();
        txtFechaNacimiento = new javax.swing.JTextField();
        txtSexo = new javax.swing.JTextField();
        txtNumeroHistoriaClinica = new javax.swing.JTextField();
        txtCompaniaSeguro = new javax.swing.JTextField();
        txtNumeroPoliza = new javax.swing.JTextField();
        txtTipoCobertura = new javax.swing.JTextField();

        lblDni = new javax.swing.JLabel("DNI");
        lblFechaNacimiento = new javax.swing.JLabel("Fecha de Nacimiento (AAAA-MM-DD)");
        lblSexo = new javax.swing.JLabel("Sexo (M/F)");
        lblNumeroHistoriaClinica = new javax.swing.JLabel("N° Historia Clínica");
        lblCompaniaSeguro = new javax.swing.JLabel("Compañía Seguro");
        lblNumeroPoliza = new javax.swing.JLabel("N° Póliza");
        lblTipoCobertura = new javax.swing.JLabel("Cobertura (T/P)");

        initComponents();
        this.pacienteActual = paciente;
        configurarSegunEstado();
        cargarDatosEnFormulario();
        agregarCamposSeguro();
    }

    private void configurarSegunEstado() {
        boolean activo = pacienteActual.isEstado();
        
        // Habilitar/deshabilitar campos editables según estado
        txtDni.setEnabled(activo);
        txtNombres.setEnabled(activo);
        txtApellidos.setEnabled(activo);
        txtFechaNacimiento.setEnabled(activo);
        txtSexo.setEnabled(activo);
        txtTelefono.setEnabled(activo);
        txtDireccion.setEnabled(activo);
        txtNumeroHistoriaClinica.setEnabled(activo);
        txtDniApoderado.setEnabled(activo);
        txtNombresApoderado.setEnabled(activo);
        txtApellidosApoderado.setEnabled(activo);
        txtTelefonoApoderado.setEnabled(activo);
        txtParentesco.setEnabled(activo);
        
        btnGuardarCambios.setEnabled(activo);
        
        if (activo) {
            btnInactivar.setText("Inactivar");
            btnInactivar.setToolTipText("Marcar paciente como inactivo");
        } else {
            btnInactivar.setText("Activar");
            btnInactivar.setToolTipText("Reactivar paciente");
        }
    }
    private void cargarDatosEnFormulario() {
            txtDni.setText(pacienteActual.getDni());
            txtNombres.setText(pacienteActual.getNombres());
            txtApellidos.setText(pacienteActual.getApellidos());
            if (pacienteActual.getFechaNacimiento() != null) {
                txtFechaNacimiento.setText(pacienteActual.getFechaNacimiento().toString());
            }
            txtSexo.setText(pacienteActual.getSexo());
            txtTelefono.setText(pacienteActual.getTelefono());
            txtDireccion.setText(pacienteActual.getDireccion());
            txtNumeroHistoriaClinica.setText(pacienteActual.getNumeroHistoriaClinica());

            // Cargar datos de seguro si existe
            SeguroMedico seguro = pacienteActual.getSeguroMedico();
            if (seguro != null) {
                txtCompaniaSeguro.setText(seguro.getCompania());
                txtNumeroPoliza.setText(seguro.getNumeroPoliza());
                txtTipoCobertura.setText(seguro.getTipoCobertura());
            }
        }

    private void agregarCamposSeguro() {
        javax.swing.GroupLayout jPanel1Layout = (javax.swing.GroupLayout) jPanel1.getLayout();
        javax.swing.GroupLayout layout = (javax.swing.GroupLayout) getContentPane().getLayout();

        // Agregar campos al jPanel1 (después de Dirección)
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDni)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(lblSexo)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(lblNumeroHistoriaClinica)
                    .addComponent(lblCompaniaSeguro)
                    .addComponent(lblNumeroPoliza)
                    .addComponent(lblTipoCobertura))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtApellidos)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCompaniaSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroPoliza, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDni)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaNacimiento)
                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSexo)
                    .addComponent(txtSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroHistoriaClinica)
                    .addComponent(txtNumeroHistoriaClinica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCompaniaSeguro)
                    .addComponent(txtCompaniaSeguro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroPoliza)
                    .addComponent(txtNumeroPoliza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoCobertura)
                    .addComponent(txtTipoCobertura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Habilitar/deshabilitar campos de seguro según estado del paciente
        boolean activo = pacienteActual.isEstado();
        txtCompaniaSeguro.setEnabled(activo);
        txtNumeroPoliza.setEnabled(activo);
        txtTipoCobertura.setEnabled(activo);

        pack();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombresApoderado = new javax.swing.JTextField();
        txtApellidosApoderado = new javax.swing.JTextField();
        txtDniApoderado = new javax.swing.JTextField();
        txtTelefonoApoderado = new javax.swing.JTextField();
        txtParentesco = new javax.swing.JTextField();
        btnGuardarCambios = new javax.swing.JButton();
        btnInactivar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Nombres");

        jLabel2.setText("Apellidos");

        jLabel3.setText("Direccion");

        jLabel4.setText("Telefono");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Datos del apoderado(Solo si es menor de edad)");

        jLabel6.setText("Nombres");

        jLabel7.setText("Apellidos");

        jLabel8.setText("DNI");

        jLabel9.setText("Telefono");

        jLabel10.setText("Parentesco");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtApellidosApoderado, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(txtDniApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefonoApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtParentesco)
                            .addComponent(txtNombresApoderado)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtNombresApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtApellidosApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDniApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTelefonoApoderado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtParentesco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtApellidos)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        btnGuardarCambios.setText("Guardar");
        btnGuardarCambios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCambiosActionPerformed(evt);
            }
        });

        btnInactivar.setText("Inactivar");
        btnInactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInactivarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel11.setText("Editar Información de Paciente");

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(btnGuardarCambios)
                        .addGap(129, 129, 129)
                        .addComponent(btnInactivar)
                        .addGap(100, 100, 100)
                        .addComponent(btnSalir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCambios)
                    .addComponent(btnInactivar)
                    .addComponent(btnSalir))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String validarFormulario() {
        StringBuilder errores = new StringBuilder();

        String dni = txtDni.getText().trim();
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String fechaStr = txtFechaNacimiento.getText().trim();
        String sexo = txtSexo.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String direccion = txtDireccion.getText().trim();
        String historia = txtNumeroHistoriaClinica.getText().trim();

        if (dni.isEmpty()) {
            errores.append("- El DNI es obligatorio.\n");
        } else if (!dni.matches("\\d{8}")) {
            errores.append("- El DNI debe tener exactamente 8 dígitos numéricos.\n");
        }

        if (nombres.isEmpty()) {
            errores.append("- Los nombres son obligatorios.\n");
        } else if (!nombres.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
            errores.append("- Los nombres solo pueden contener letras y espacios.\n");
        }

        if (apellidos.isEmpty()) {
            errores.append("- Los apellidos son obligatorios.\n");
        } else if (!apellidos.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
            errores.append("- Los apellidos solo pueden contener letras y espacios.\n");
        }

        LocalDate fechaNacimiento = null;
        if (fechaStr.isEmpty()) {
            errores.append("- La fecha de nacimiento es obligatoria.\n");
        } else {
            try {
                fechaNacimiento = LocalDate.parse(fechaStr);
                if (fechaNacimiento.isAfter(LocalDate.now())) {
                    errores.append("- La fecha de nacimiento no puede ser futura.\n");
                }
            } catch (Exception ex) {
                errores.append("- La fecha de nacimiento debe usar el formato AAAA-MM-DD (ejemplo: 1995-03-21).\n");
            }
        }

        if (sexo.isEmpty()) {
            errores.append("- El sexo es obligatorio.\n");
        } else if (!sexo.toUpperCase().matches("^[MF]$")) {
            errores.append("- El sexo debe ser 'M' o 'F'.\n");
        }

        if (telefono.isEmpty()) {
            errores.append("- El teléfono es obligatorio.\n");
        } else if (!telefono.matches("\\d{9}")) {
            errores.append("- El teléfono debe tener exactamente 9 dígitos numéricos.\n");
        }

        if (direccion.isEmpty()) {
            errores.append("- La dirección es obligatoria.\n");
        }

        if (historia.isEmpty()) {
            errores.append("- El número de historia clínica es obligatorio.\n");
        } else if (!historia.matches("\\d{8}")) {
            errores.append("- El número de historia clínica debe tener exactamente 8 dígitos numéricos.\n");
        }

        // Seguro médico (opcional): si se llena algún campo, todos los obligatorios
        String compania = txtCompaniaSeguro.getText().trim();
        String poliza = txtNumeroPoliza.getText().trim();
        String cobertura = txtTipoCobertura.getText().trim().toUpperCase();

        if (!compania.isEmpty() || !poliza.isEmpty() || !cobertura.isEmpty()) {
            if (compania.isEmpty()) {
                errores.append("- La compañía del seguro es obligatoria.\n");
            }
            if (poliza.isEmpty()) {
                errores.append("- El número de póliza es obligatorio.\n");
            } else if (!poliza.matches("\\d{10}")) {
                errores.append("- El número de póliza debe tener exactamente 10 dígitos numéricos.\n");
            }
            if (cobertura.isEmpty()) {
                errores.append("- El tipo de cobertura es obligatorio.\n");
            } else if (!cobertura.matches("^[TP]$")) {
                errores.append("- La cobertura debe ser 'T' (Total) o 'P' (Parcial).\n");
            }
        }

        // Apoderado (opcional): si se llena algún campo, todos los obligatorios
        String dniApoderado = txtDniApoderado.getText().trim();
        String nombresApoderado = txtNombresApoderado.getText().trim();
        String apellidosApoderado = txtApellidosApoderado.getText().trim();
        String telefonoApoderado = txtTelefonoApoderado.getText().trim();
        String parentesco = txtParentesco.getText().trim();

        if (!dniApoderado.isEmpty() || !nombresApoderado.isEmpty() || !apellidosApoderado.isEmpty()
                || !telefonoApoderado.isEmpty() || !parentesco.isEmpty()) {
            if (dniApoderado.isEmpty()) {
                errores.append("- El DNI del apoderado es obligatorio.\n");
            } else if (!dniApoderado.matches("\\d{8}")) {
                errores.append("- El DNI del apoderado debe tener exactamente 8 dígitos numéricos.\n");
            }
            if (nombresApoderado.isEmpty()) {
                errores.append("- Los nombres del apoderado son obligatorios.\n");
            } else if (!nombresApoderado.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
                errores.append("- Los nombres del apoderado solo pueden contener letras y espacios.\n");
            }
            if (apellidosApoderado.isEmpty()) {
                errores.append("- Los apellidos del apoderado son obligatorios.\n");
            } else if (!apellidosApoderado.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
                errores.append("- Los apellidos del apoderado solo pueden contener letras y espacios.\n");
            }
            if (telefonoApoderado.isEmpty()) {
                errores.append("- El teléfono del apoderado es obligatorio.\n");
            } else if (!telefonoApoderado.matches("\\d{9}")) {
                errores.append("- El teléfono del apoderado debe tener exactamente 9 dígitos numéricos.\n");
            }
            if (parentesco.isEmpty()) {
                errores.append("- El parentesco es obligatorio.\n");
            } else if (!parentesco.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$")) {
                errores.append("- El parentesco solo puede contener letras y espacios.\n");
            }
        }

        return errores.length() == 0 ? null : errores.toString();
    }

    private void btnGuardarCambiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCambiosActionPerformed
        try {
            String errores = validarFormulario();
            if (errores != null) {
                JOptionPane.showMessageDialog(this,
                        "Los siguientes campos son obligatorios o tienen formato inválido:\n\n" + errores,
                        "Validación de datos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String fechaStr = txtFechaNacimiento.getText().trim();
            LocalDate fechaNacimiento = LocalDate.parse(fechaStr);

            pacienteActual.setEstado(pacienteActual.isEstado());

            // Reflejar cambios editables (RN-09: nombres, apellidos, teléfono, dirección son actualizables)
            Paciente.Builder builder = new Paciente.Builder()
                    .idPaciente(pacienteActual.getIdPaciente())
                    .dni(txtDni.getText().trim())
                    .nombres(txtNombres.getText().trim())
                    .apellidos(txtApellidos.getText().trim())
                    .fechaNacimiento(fechaNacimiento)
                    .sexo(txtSexo.getText().trim())
                    .telefono(txtTelefono.getText().trim())
                    .direccion(txtDireccion.getText().trim())
                    .numeroHistoriaClinica(txtNumeroHistoriaClinica.getText().trim())
                    .estado(pacienteActual.isEstado());

            // Seguro médico (opcional, editable)
            if (!txtCompaniaSeguro.getText().trim().isEmpty()) {
                SeguroMedico seguro = new SeguroMedico();
                seguro.setCompania(txtCompaniaSeguro.getText().trim());
                seguro.setNumeroPoliza(txtNumeroPoliza.getText().trim());
                seguro.setTipoCobertura(txtTipoCobertura.getText().trim().toUpperCase());
                seguro.setEstado(true);
                SeguroDAO.insertar(seguro);
                builder.seguroMedico(seguro);
            } else if (pacienteActual.getSeguroMedico() != null) {
                builder.seguroMedico(pacienteActual.getSeguroMedico());
            }

            if (!txtDniApoderado.getText().trim().isEmpty()) {
                Apoderado apoderado = new Apoderado();
                apoderado.setDni(txtDniApoderado.getText().trim());
                apoderado.setNombres(txtNombresApoderado.getText().trim());
                apoderado.setApellidos(txtApellidosApoderado.getText().trim());
                apoderado.setTelefono(txtTelefonoApoderado.getText().trim());
                apoderado.setParentesco(txtParentesco.getText().trim());
                apoderado.setEstado(true);
                builder.apoderado(apoderado);
            } else if (pacienteActual.getApoderado() != null) {
                builder.apoderado(pacienteActual.getApoderado());
            }

            Paciente pacienteActualizado = builder.build();
        boolean exito = PacienteLOG.actualizarPaciente(pacienteActualizado);

        if (exito) {
            this.dispose(); // cierra el JInternalFrame solo si la actualización fue exitosa
        }
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Ocurrio un error inesperado al actualizar el paciente.\nDetalle: " + e.getMessage(),
                    "Error del sistema", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarCambiosActionPerformed

    private void btnInactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInactivarActionPerformed
        boolean activo = pacienteActual.isEstado();
        
        if (activo) {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de marcar este paciente como inactivo?\nEl paciente no aparecerá en búsquedas futuras.",
                    "Confirmar inactivación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = PacienteLOG.inactivarPaciente(pacienteActual.getIdPaciente());
                if (exito) {
                    this.dispose();
                }
            }
        } else {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Está seguro de reactivar este paciente?\nVolverá a aparecer en búsquedas activas.",
                    "Confirmar activación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = PacienteLOG.activarPaciente(pacienteActual.getIdPaciente());
                if (exito) {
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_btnInactivarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarCambios;
    private javax.swing.JButton btnInactivar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtApellidosApoderado;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDniApoderado;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtNombresApoderado;
    private javax.swing.JTextField txtParentesco;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoApoderado;
    // End of variables declaration//GEN-END:variables
}
