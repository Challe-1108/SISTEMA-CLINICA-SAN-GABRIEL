package presentacion;

import entidades.*;
import logica.AtencionMedicaLOG;
import logica.HistorialClinicoBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class FrmAtencionMedica extends JFrame {

    // Componentes de Identificación del Paciente
    private JTextField txtNumHistoria, txtNombrePaciente, txtDniPaciente, txtIdCita;
    private JButton btnBuscarHistoria;

    // Componentes Anamnesis y Vitales
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

    /**
     * Constructor vacío: Permite abrir la pantalla directamente sin parámetros.
     */
    public FrmAtencionMedica() {
        setTitle("Registro de Atención Médica");
        setSize(920, 780);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        iniciarComponentes();
    }

    private void iniciarComponentes() {
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 0. Búsqueda e Identificación por Historia Clínica
        JPanel pnlIdentificacion = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlIdentificacion.setBorder(BorderFactory.createTitledBorder("0. Identificación por Historia Clínica"));

        txtNumHistoria = new JTextField(8);
        btnBuscarHistoria = new JButton("🔍 Buscar HC");
        txtNombrePaciente = new JTextField(18);
        txtNombrePaciente.setEditable(false);
        txtDniPaciente = new JTextField(8);
        txtDniPaciente.setEditable(false);
        txtIdCita = new JTextField(5); // Opción de vincular Cita ID

        pnlIdentificacion.add(new JLabel("N° Hist. Clínica:"));
        pnlIdentificacion.add(txtNumHistoria);
        pnlIdentificacion.add(btnBuscarHistoria);
        pnlIdentificacion.add(new JLabel("Paciente:"));
        pnlIdentificacion.add(txtNombrePaciente);
        pnlIdentificacion.add(new JLabel("DNI:"));
        pnlIdentificacion.add(txtDniPaciente);
        pnlIdentificacion.add(new JLabel("ID Cita:"));
        pnlIdentificacion.add(txtIdCita);

        // Evento para cargar datos al presionar Buscar
        btnBuscarHistoria.addActionListener(e -> buscarPacientePorHistoria());

        panelContenido.add(pnlIdentificacion);

        // 1. Anamnesis
        JPanel pnlAnamnesis = new JPanel(new GridLayout(2, 2, 5, 5));
        pnlAnamnesis.setBorder(BorderFactory.createTitledBorder("1. Anamnesis"));
        pnlAnamnesis.add(new JLabel("Motivo Consulta:"));
        txtMotivo = new JTextField();
        pnlAnamnesis.add(txtMotivo);
        pnlAnamnesis.add(new JLabel("Antecedentes:"));
        txtAntecedentes = new JTextArea(2, 20);
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

        // 3. Diagnósticos
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
        pnlDiagnosticos.setPreferredSize(new Dimension(850, 120));
        panelContenido.add(pnlDiagnosticos);

        // 4. Receta Médica
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
        pnlReceta.setPreferredSize(new Dimension(850, 120));
        panelContenido.add(pnlReceta);

        // 5. Tratamiento y Observaciones
        JPanel pnlTratamiento = new JPanel(new GridLayout(1, 4, 5, 5));
        pnlTratamiento.setBorder(BorderFactory.createTitledBorder("5. Plan y Observaciones"));
        txtTratamiento = new JTextArea(2, 10);
        txtObservaciones = new JTextArea(2, 10);
        pnlTratamiento.add(new JLabel("Tratamiento:")); pnlTratamiento.add(new JScrollPane(txtTratamiento));
        pnlTratamiento.add(new JLabel("Observaciones:")); pnlTratamiento.add(new JScrollPane(txtObservaciones));
        panelContenido.add(pnlTratamiento);

        // Scroll Principal
        JScrollPane scrollMain = new JScrollPane(panelContenido);
        add(scrollMain, BorderLayout.CENTER);

        // Botón Guardar
        JButton btnGuardar = new JButton("💾 GUARDAR ATENCIÓN MÉDICA");
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnGuardar.addActionListener(e -> guardarAtencion());
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void buscarPacientePorHistoria() {
        String numHc = txtNumHistoria.getText().trim();
        if (numHc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el número de Historia Clínica para buscar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // TODO: Conectar con tu capa LOG/DAO para obtener el paciente por N° de HC
        // Ejemplo de consulta a tu BD (descomentar según tu implementación):
        /*
        PacienteLOG pacienteLog = new PacienteLOG();
        Paciente p = pacienteLog.obtenerPorHistoriaClinica(numHc);
        if (p != null) {
            txtNombrePaciente.setText(p.getNombreCompleto());
            txtDniPaciente.setText(p.getDni());
            txtAntecedentes.setText(p.getAntecedentes());
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        */
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
        try {
            int idCita = 0;
            if (!txtIdCita.getText().trim().isEmpty()) {
                idCita = Integer.parseInt(txtIdCita.getText().trim());
            }

            // 1. Instanciar Signos Vitales
            SignosVitales sv = new SignosVitales();
            sv.setPeso(Double.parseDouble(txtPeso.getText().trim()));
            sv.setTalla(Double.parseDouble(txtTalla.getText().trim()));
            sv.setPas(Double.parseDouble(txtPas.getText().trim()));
            sv.setPad(Double.parseDouble(txtPad.getText().trim()));
            sv.setFc(Integer.parseInt(txtFc.getText().trim()));
            sv.setFr(Integer.parseInt(txtFr.getText().trim()));
            sv.setTemperatura(Double.parseDouble(txtTemp.getText().trim()));
            sv.setImc(Double.parseDouble(txtImc.getText().trim()));

            // 2. Builder para la Atención Médica
            HistorialClinicoBuilder builder = new HistorialClinicoBuilder(idCita);
            builder.conAnamnesis(txtMotivo.getText().trim(), txtAntecedentes.getText().trim())
                    .conSignosVitales(sv)
                    .conTratamientoYObservaciones(txtTratamiento.getText().trim(), txtObservaciones.getText().trim());

            // 3. Obtener Diagnósticos
            for (int i = 0; i < modelDiagnosticos.getRowCount(); i++) {
                String desc = modelDiagnosticos.getValueAt(i, 0).toString();
                String tipo = modelDiagnosticos.getValueAt(i, 1).toString();
                builder.agregarDiagnostico(new Diagnostico(desc, tipo));
            }

            // 4. Obtener Receta Médica (DetalleReceta con 4 parámetros)
            RecetaMedica receta = new RecetaMedica();
            for (int i = 0; i < modelReceta.getRowCount(); i++) {
                int idMed = Integer.parseInt(modelReceta.getValueAt(i, 0).toString());
                String nomMed = modelReceta.getValueAt(i, 1).toString();
                int cant = Integer.parseInt(modelReceta.getValueAt(i, 2).toString());
                String ind = modelReceta.getValueAt(i, 3).toString();

                receta.agregarDetalle(new DetalleReceta(idMed, nomMed, cant, ind));
            }
            builder.conRecetaMedica(receta);

            // 5. Enviar a la capa de lógica
            AtencionMedica atencion = builder.build();
            AtencionMedicaLOG logica = new AtencionMedicaLOG();

            boolean exito = logica.registrarAtencion(atencion);
            if (exito) {
                this.dispose();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor revise que los valores numéricos ingresados sean válidos.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
        }
    }
}