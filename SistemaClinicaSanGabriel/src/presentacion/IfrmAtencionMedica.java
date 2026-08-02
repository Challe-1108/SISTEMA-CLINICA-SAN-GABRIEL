package presentacion;

import entidades.*;
import datos.MedicamentoDAO;
import datos.PacienteDAO;
import logica.AtencionMedicaLOG;
import logica.HistorialClinicoBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

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
    private JComboBox<Medicamento> cbxMedicamentos;
    private JTextField txtMedCantidad, txtMedIndicacion;
    private JTable tblReceta;
    private DefaultTableModel modelReceta;
    private List<Medicamento> listaMedicamentos;

    public IfrmAtencionMedica() {
        super("Registro de Atención Médica", true, true, true, true);
        setSize(950, 820);

        iniciarComponentes();
        cargarMedicamentos();
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
        cbxMedicamentos = new JComboBox<>();
        cbxMedicamentos.setPreferredSize(new Dimension(280, 25));
        cbxMedicamentos.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Medicamento) {
                    Medicamento m = (Medicamento) value;
                    setText(m.getNombre() + "  (Stock: " + m.getStockActual() + ")");
                }
                return this;
            }
        });
        txtMedCantidad = new JTextField(4);
        txtMedIndicacion = new JTextField(18);
        JButton btnAgregarMed = new JButton("Agregar");
        JButton btnQuitarMed = new JButton("Quitar");

        pnlRecetaInputs.add(new JLabel("Medicamento:"));
        pnlRecetaInputs.add(cbxMedicamentos);
        pnlRecetaInputs.add(new JLabel("Cant:"));
        pnlRecetaInputs.add(txtMedCantidad);
        pnlRecetaInputs.add(new JLabel("Indicación:"));
        pnlRecetaInputs.add(txtMedIndicacion);
        pnlRecetaInputs.add(btnAgregarMed);
        pnlRecetaInputs.add(btnQuitarMed);

        modelReceta = new DefaultTableModel(new String[]{"ID Med", "Medicamento", "Cantidad", "Indicación"}, 0);
        tblReceta = new JTable(modelReceta);
        tblReceta.setRowHeight(22);
        tblReceta.getColumnModel().getColumn(0).setPreferredWidth(60);
        tblReceta.getColumnModel().getColumn(1).setPreferredWidth(250);
        tblReceta.getColumnModel().getColumn(2).setPreferredWidth(60);
        tblReceta.getColumnModel().getColumn(3).setPreferredWidth(400);

        btnAgregarMed.addActionListener(e -> agregarMedicamentoAReceta());
        btnQuitarMed.addActionListener(e -> quitarMedicamentoDeReceta());

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

    private void cargarMedicamentos() {
        try {
            listaMedicamentos = new MedicamentoDAO().listar();
            actualizarComboMedicamentos();
            if (listaMedicamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay medicamentos activos registrados en el inventario.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los medicamentos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarComboMedicamentos() {
        int idSeleccion = cbxMedicamentos.getSelectedItem() != null
                ? ((Medicamento) cbxMedicamentos.getSelectedItem()).getIdMedicamento()
                : -1;
        cbxMedicamentos.removeAllItems();
        if (listaMedicamentos != null) {
            for (Medicamento m : listaMedicamentos) {
                cbxMedicamentos.addItem(m);
            }
            for (Medicamento m : listaMedicamentos) {
                if (m.getIdMedicamento() == idSeleccion) {
                    cbxMedicamentos.setSelectedItem(m);
                    break;
                }
            }
        }
    }

    private Medicamento buscarMedicamentoEnLista(int idMedicamento) {
        if (listaMedicamentos != null) {
            for (Medicamento m : listaMedicamentos) {
                if (m.getIdMedicamento() == idMedicamento) {
                    return m;
                }
            }
        }
        return null;
    }

    private void agregarMedicamentoAReceta() {
        Medicamento medicamento = (Medicamento) cbxMedicamentos.getSelectedItem();
        if (medicamento == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un medicamento de la lista.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String cantTexto = txtMedCantidad.getText().trim();
        if (cantTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cantidad del medicamento.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantTexto);
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0 (RN-29).", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idMedicamento = medicamento.getIdMedicamento();
            int stockDisponible = medicamento.getStockActual();

            if (cantidad > stockDisponible) {
                JOptionPane.showMessageDialog(this,
                        "Stock insuficiente. Disponible: " + stockDisponible + " (RN-28).",
                        "Sin stock", JOptionPane.WARNING_MESSAGE);
                return;
            }

            modelReceta.addRow(new Object[]{
                    medicamento.getIdMedicamento(),
                    medicamento.getNombre(),
                    cantTexto,
                    txtMedIndicacion.getText().trim()
            });

            Medicamento enMemoria = buscarMedicamentoEnLista(idMedicamento);
            if (enMemoria != null) {
                enMemoria.setStockActual(stockDisponible - cantidad);
            }
            actualizarComboMedicamentos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
            return;
        }

        txtMedCantidad.setText("");
        txtMedIndicacion.setText("");
    }

    private void quitarMedicamentoDeReceta() {
        int fila = tblReceta.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila de la receta para quitar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int idMedicamento = Integer.parseInt(modelReceta.getValueAt(fila, 0).toString());
        int cantidad = Integer.parseInt(modelReceta.getValueAt(fila, 2).toString());
        modelReceta.removeRow(fila);

        Medicamento enMemoria = buscarMedicamentoEnLista(idMedicamento);
        if (enMemoria != null) {
            enMemoria.setStockActual(enMemoria.getStockActual() + cantidad);
        }
        actualizarComboMedicamentos();
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