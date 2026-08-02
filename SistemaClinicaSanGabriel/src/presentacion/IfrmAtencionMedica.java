package presentacion;

import entidades.*;
import datos.PacienteDAO;
import logica.AtencionMedicaLOG;
import logica.HistorialClinicoBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class IfrmAtencionMedica extends JInternalFrame {

    // Componentes de Búsqueda por Historia Clínica
    private JTextField txtNumHistoria, txtNombrePaciente, txtDniPaciente, txtCodigoCita;
    private JButton btnBuscarPaciente;

    // Componentes Anamnesis y Signos Vitales
    private JTextField txtMotivo, txtPeso, txtTalla, txtPas, txtPad, txtFc, txtFr, txtTemp, txtImc;
    private JTextArea txtAntecedentes, txtTratamiento, txtObservaciones;

    // Componentes Diagnóstico
    private JTextField txtDiagDescripcion;
    private JComboBox<String> cbxDiagTipo;
    private JTable tblDiagnosticos;
    private DefaultTableModel modelDiagnosticos;

    // Componentes Receta Médica
    private JTextField txtMedId, txtMedNombre, txtMedCantidad, txtMedIndicacion;
    private JTable tblReceta;
    private DefaultTableModel modelReceta;

    public IfrmAtencionMedica() {
        super("Registro de Atención Médica", true, true, true, true);
        setSize(950, 820);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 0. Identificación del Paciente por Historia Clínica
        JPanel pnlPaciente = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlPaciente.setBorder(BorderFactory.createTitledBorder("0. Identificación del Paciente (Por Historia Clínica)"));

        txtNumHistoria = new JTextField(8);
        btnBuscarPaciente = new JButton("🔍 Buscar Paciente");
        txtNombrePaciente = new JTextField(18);
        txtNombrePaciente.setEditable(false);
        txtDniPaciente = new JTextField(8);
        txtDniPaciente.setEditable(false);
        txtCodigoCita = new JTextField(8);

        pnlPaciente.add(new JLabel("N° HC:"));
        pnlPaciente.add(txtNumHistoria);
        pnlPaciente.add(btnBuscarPaciente);
        pnlPaciente.add(new JLabel("Paciente:"));
        pnlPaciente.add(txtNombrePaciente);
        pnlPaciente.add(new JLabel("DNI:"));
        pnlPaciente.add(txtDniPaciente);
        pnlPaciente.add(new JLabel("Cód. Cita:"));
        pnlPaciente.add(txtCodigoCita);

        btnBuscarPaciente.addActionListener(e -> buscarPacientePorHistoriaClinica());

        panelContenido.add(pnlPaciente);

        // 1. Anamnesis
        JPanel pnlAnamnesis = new JPanel(new GridLayout(2, 2, 5, 5));
        pnlAnamnesis.setBorder(BorderFactory.createTitledBorder("1. Anamnesis"));
        pnlAnamnesis.add(new JLabel("Motivo Consulta:"));
        txtMotivo = new JTextField();
        pnlAnamnesis.add(txtMotivo);
        pnlAnamnesis.add(new JLabel("Antecedentes:"));
        txtAntecedentes = new JTextArea(1, 20);
        pnlAnamnesis.add(new JScrollPane(txtAntecedentes));
        panelContenido.add(pnlAnamnesis);

        // 2. Signos Vitales
        JPanel pnlSignos = new JPanel(new GridLayout(3, 6, 5, 5));
        pnlSignos.setBorder(BorderFactory.createTitledBorder("2. Signos Vitales"));
        txtPeso = new JTextField("0");
        txtTalla = new JTextField("0");
        txtPas = new JTextField("0");
        txtPad = new JTextField("0");
        txtFc = new JTextField("0");
        txtFr = new JTextField("0");
        txtTemp = new JTextField("0");
        txtImc = new JTextField("0.0");
        txtImc.setEditable(false);

        FocusAdapter calcularImcListener = new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                calcularIMC();
            }
        };
        txtPeso.addFocusListener(calcularImcListener);
        txtTalla.addFocusListener(calcularImcListener);

        pnlSignos.add(new JLabel("Peso (kg):")); pnlSignos.add(txtPeso);
        pnlSignos.add(new JLabel("Talla (cm):")); pnlSignos.add(txtTalla);
        pnlSignos.add(new JLabel("IMC:")); pnlSignos.add(txtImc);
        pnlSignos.add(new JLabel("PAS:")); pnlSignos.add(txtPas);
        pnlSignos.add(new JLabel("PAD:")); pnlSignos.add(txtPad);
        pnlSignos.add(new JLabel("Temp (°C):")); pnlSignos.add(txtTemp);
        pnlSignos.add(new JLabel("FC (bpm):")); pnlSignos.add(txtFc);
        pnlSignos.add(new JLabel("FR (rpm):")); pnlSignos.add(txtFr);
        panelContenido.add(pnlSignos);

        // 3. Diagnósticos (Tabla Grande)
        JPanel pnlDiagnosticos = new JPanel(new BorderLayout(5, 5));
        pnlDiagnosticos.setBorder(BorderFactory.createTitledBorder("3. Diagnósticos"));

        JPanel pnlDiagInputs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtDiagDescripcion = new JTextField(25);
        cbxDiagTipo = new JComboBox<>(new String[]{"Presuntivo", "Definitivo"});
        JButton btnAgregarDiag = new JButton("Agregar");

        pnlDiagInputs.add(new JLabel("Enfermedad/Descripción:"));
        pnlDiagInputs.add(txtDiagDescripcion);
        pnlDiagInputs.add(new JLabel("Tipo:"));
        pnlDiagInputs.add(cbxDiagTipo);
        pnlDiagInputs.add(btnAgregarDiag);

        modelDiagnosticos = new DefaultTableModel(new String[]{"Descripción", "Tipo"}, 0);
        tblDiagnosticos = new JTable(modelDiagnosticos);
        tblDiagnosticos.setRowHeight(22);
        tblDiagnosticos.getColumnModel().getColumn(0).setPreferredWidth(600);
        tblDiagnosticos.getColumnModel().getColumn(1).setPreferredWidth(150);

        btnAgregarDiag.addActionListener(e -> {
            if (!txtDiagDescripcion.getText().trim().isEmpty()) {
                modelDiagnosticos.addRow(new Object[]{
                        txtDiagDescripcion.getText().trim(),
                        cbxDiagTipo.getSelectedItem().toString()
                });
                txtDiagDescripcion.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese la descripción de la enfermedad.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        pnlDiagnosticos.add(pnlDiagInputs, BorderLayout.NORTH);
        pnlDiagnosticos.add(new JScrollPane(tblDiagnosticos), BorderLayout.CENTER);
        pnlDiagnosticos.setPreferredSize(new Dimension(880, 200));
        panelContenido.add(pnlDiagnosticos);

        // 4. Receta Médica (Tabla Grande)
        JPanel pnlReceta = new JPanel(new BorderLayout(5, 5));
        pnlReceta.setBorder(BorderFactory.createTitledBorder("4. Receta Médica"));

        JPanel pnlRecetaInputs = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtMedId = new JTextField(4);
        txtMedNombre = new JTextField(15);
        txtMedCantidad = new JTextField(4);
        txtMedIndicacion = new JTextField(15);
        JButton btnAgregarMed = new JButton("Agregar");

        pnlRecetaInputs.add(new JLabel("ID Med:")); pnlRecetaInputs.add(txtMedId);
        pnlRecetaInputs.add(new JLabel("Medicamento:")); pnlRecetaInputs.add(txtMedNombre);
        pnlRecetaInputs.add(new JLabel("Cant:")); pnlRecetaInputs.add(txtMedCantidad);
        pnlRecetaInputs.add(new JLabel("Indicación:")); pnlRecetaInputs.add(txtMedIndicacion);
        pnlRecetaInputs.add(btnAgregarMed);

        modelReceta = new DefaultTableModel(new String[]{"ID Med", "Medicamento", "Cantidad", "Indicación"}, 0);
        tblReceta = new JTable(modelReceta);
        tblReceta.setRowHeight(22);
        tblReceta.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblReceta.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblReceta.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblReceta.getColumnModel().getColumn(3).setPreferredWidth(400);

        btnAgregarMed.addActionListener(e -> {
            if (!txtMedId.getText().trim().isEmpty() && !txtMedCantidad.getText().trim().isEmpty()) {
                modelReceta.addRow(new Object[]{
                        txtMedId.getText().trim(),
                        txtMedNombre.getText().trim(),
                        txtMedCantidad.getText().trim(),
                        txtMedIndicacion.getText().trim()
                });
                txtMedId.setText("");
                txtMedNombre.setText("");
                txtMedCantidad.setText("");
                txtMedIndicacion.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese el ID y la Cantidad del medicamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        pnlReceta.add(pnlRecetaInputs, BorderLayout.NORTH);
        pnlReceta.add(new JScrollPane(tblReceta), BorderLayout.CENTER);
        pnlReceta.setPreferredSize(new Dimension(880, 200));
        panelContenido.add(pnlReceta);

        // 5. Tratamiento y Observaciones
        JPanel pnlTratamiento = new JPanel(new GridLayout(1, 4, 5, 5));
        pnlTratamiento.setBorder(BorderFactory.createTitledBorder("5. Plan y Observaciones"));
        txtTratamiento = new JTextArea(1, 10);
        txtObservaciones = new JTextArea(1, 10);
        pnlTratamiento.add(new JLabel("Tratamiento:")); pnlTratamiento.add(new JScrollPane(txtTratamiento));
        pnlTratamiento.add(new JLabel("Observaciones:")); pnlTratamiento.add(new JScrollPane(txtObservaciones));
        panelContenido.add(pnlTratamiento);

        JScrollPane scrollMain = new JScrollPane(panelContenido);
        add(scrollMain, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("💾 GUARDAR ATENCIÓN MÉDICA");
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnGuardar.addActionListener(e -> guardarAtencion());
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void buscarPacientePorHistoriaClinica() {
        String numHistoria = txtNumHistoria.getText().trim();
        if (numHistoria.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de Historia Clínica.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Paciente paciente = PacienteDAO.buscarPorHistoriaClinica(numHistoria);
        if (paciente != null) {
            txtNombrePaciente.setText(paciente.getNombres());
            txtDniPaciente.setText(paciente.getDni());
            JOptionPane.showMessageDialog(this, "Paciente encontrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún paciente registrado con la Historia Clínica: " + numHistoria, "Atención", JOptionPane.ERROR_MESSAGE);
            txtNombrePaciente.setText("");
            txtDniPaciente.setText("");
        }
    }

    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(txtPeso.getText().trim());
            double tallaCm = Double.parseDouble(txtTalla.getText().trim());
            if (peso > 0 && tallaCm > 0) {
                double tallaM = tallaCm / 100.0;
                double imc = peso / (tallaM * tallaM);
                txtImc.setText(String.format("%.2f", imc).replace(",", "."));
            }
        } catch (NumberFormatException ignored) {}
    }

    private void guardarAtencion() {
        if (txtNombrePaciente.getText().trim().isEmpty() || txtNombrePaciente.getText().equals("Paciente no hallado")) {
            JOptionPane.showMessageDialog(this, "Debe buscar y cargar un paciente válido mediante su Historia Clínica antes de guardar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String codigoCita = txtCodigoCita.getText().trim();
        if (codigoCita.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el código de la cita para continuar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            SignosVitales sv = new SignosVitales();
            sv.setPeso(Double.parseDouble(txtPeso.getText().trim()));
            sv.setTalla(Double.parseDouble(txtTalla.getText().trim()));
            sv.setPas(Double.parseDouble(txtPas.getText().trim()));
            sv.setPad(Double.parseDouble(txtPad.getText().trim()));
            sv.setFc(Integer.parseInt(txtFc.getText().trim()));
            sv.setFr(Integer.parseInt(txtFr.getText().trim()));
            sv.setTemperatura(Double.parseDouble(txtTemp.getText().trim()));
            sv.setImc(Double.parseDouble(txtImc.getText().trim()));

            HistorialClinicoBuilder builder = new HistorialClinicoBuilder(codigoCita);
            builder.conAnamnesis(txtMotivo.getText().trim(), txtAntecedentes.getText().trim())
                    .conSignosVitales(sv)
                    .conTratamientoYObservaciones(txtTratamiento.getText().trim(), txtObservaciones.getText().trim());

            for (int i = 0; i < modelDiagnosticos.getRowCount(); i++) {
                String desc = modelDiagnosticos.getValueAt(i, 0).toString();
                String tipo = modelDiagnosticos.getValueAt(i, 1).toString();
                builder.agregarDiagnostico(new Diagnostico(desc, tipo));
            }

            RecetaMedica receta = new RecetaMedica();
            for (int i = 0; i < modelReceta.getRowCount(); i++) {
                int idMed = Integer.parseInt(modelReceta.getValueAt(i, 0).toString());
                String nomMed = modelReceta.getValueAt(i, 1).toString();
                int cant = Integer.parseInt(modelReceta.getValueAt(i, 2).toString());
                String ind = modelReceta.getValueAt(i, 3).toString();

                receta.agregarDetalle(new DetalleReceta(idMed, nomMed, cant, ind));
            }
            builder.conRecetaMedica(receta);

            AtencionMedica atencion = builder.build();
            AtencionMedicaLOG logica = new AtencionMedicaLOG();

            // Lógica realiza la validación real del código de cita en la BD
            boolean exito = logica.registrarAtencion(atencion);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Atención médica registrada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor verifique que los datos numéricos tengan el formato adecuado.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
        }
    }
}