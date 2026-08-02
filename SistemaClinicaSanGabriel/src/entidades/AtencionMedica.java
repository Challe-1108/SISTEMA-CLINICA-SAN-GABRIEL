package entidades;

import java.util.ArrayList;

public class AtencionMedica {

    private int idAtencion;
    private String codigoCita; // Cambiado de int idCita a String codigoCita
    private String motivoConsulta;
    private String antecedentes;
    private SignosVitales signosVitales;
    private ArrayList<Diagnostico> listaDiagnosticos;
    private RecetaMedica receta;
    private String tratamiento;
    private String observaciones;

    public AtencionMedica() {
        this.listaDiagnosticos = new ArrayList<>();
    }

    public AtencionMedica(String codigoCita, String motivoConsulta, String antecedentes) {
        this();
        this.codigoCita = codigoCita;
        this.motivoConsulta = motivoConsulta;
        this.antecedentes = antecedentes;
    }

    public void agregarDiagnostico(Diagnostico diagnostico) {
        if (diagnostico != null) {
            this.listaDiagnosticos.add(diagnostico);
        }
    }

    public void agregarMedicamentoAReceta(int idMedicamento, String nombreMedicamento, int cantidad, String indicacion) {
        if (this.receta == null) {
            this.receta = new RecetaMedica();
        }
        this.receta.agregarDetalle(new DetalleReceta(idMedicamento, nombreMedicamento, cantidad, indicacion));
    }

    public boolean tieneDiagnosticos() {
        return this.listaDiagnosticos != null && !this.listaDiagnosticos.isEmpty();
    }

    public boolean tieneReceta() {
        return this.receta != null && this.receta.tieneDetalles();
    }

    @Override
    public String toString() {
        return "AtencionMedica{idAtencion=" + idAtencion
                + ", codigoCita='" + codigoCita + '\''
                + ", motivoConsulta='" + motivoConsulta + '\''
                + ", diagnostico(s)=" + (listaDiagnosticos == null ? 0 : listaDiagnosticos.size())
                + ", receta=" + (receta != null ? "Si" : "No") + '}';
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(String codigoCita) {
        this.codigoCita = codigoCita;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(SignosVitales signosVitales) {
        this.signosVitales = signosVitales;
    }

    public ArrayList<Diagnostico> getListaDiagnosticos() {
        return listaDiagnosticos;
    }

    public void setListaDiagnosticos(ArrayList<Diagnostico> listaDiagnosticos) {
        this.listaDiagnosticos = listaDiagnosticos;
    }

    public RecetaMedica getReceta() {
        return receta;
    }

    public void setReceta(RecetaMedica receta) {
        this.receta = receta;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}