package entidades;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AtencionMedica {
    private int idAtencion;
    private int idCita;
    private String motivoConsulta;
    private String antecedentes;
    private String planTratamiento;
    private String observaciones;
    private Timestamp fechaAtencion;

    // Relaciones integradas en la atención
    private SignosVitales signosVitales;
    private List<Diagnostico> diagnosticos;
    private RecetaMedica recetaMedica;

    // Constructor inicializando objetos y listas para evitar NullPointerException
    public AtencionMedica() {
        this.signosVitales = new SignosVitales();
        this.diagnosticos = new ArrayList<>();
        this.recetaMedica = new RecetaMedica();
    }

    // Método helper para agregar diagnósticos fácilmente
    public void agregarDiagnostico(Diagnostico diagnostico) {
        this.diagnosticos.add(diagnostico);
    }

    // --- GETTERS Y SETTERS ---

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
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

    public String getPlanTratamiento() {
        return planTratamiento;
    }

    public void setPlanTratamiento(String planTratamiento) {
        this.planTratamiento = planTratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Timestamp getFechaAtencion() {
        return fechaAtencion;
    }

    public void setFechaAtencion(Timestamp fechaAtencion) {
        this.fechaAtencion = fechaAtencion;
    }

    public SignosVitales getSignosVitales() {
        return signosVitales;
    }

    public void setSignosVitales(SignosVitales signosVitales) {
        this.signosVitales = signosVitales;
    }

    public List<Diagnostico> getDiagnosticos() {
        return diagnosticos;
    }

    public void setDiagnosticos(List<Diagnostico> diagnosticos) {
        this.diagnosticos = diagnosticos;
    }

    public RecetaMedica getRecetaMedica() {
        return recetaMedica;
    }

    public void setRecetaMedica(RecetaMedica recetaMedica) {
        this.recetaMedica = recetaMedica;
    }
}