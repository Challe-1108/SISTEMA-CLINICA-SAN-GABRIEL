package entidades;

import java.util.ArrayList;
import java.util.List;

public class AtencionMedica {
    private int idAtencion;
    private int idCita; // Relación obligatoria con la cita
    private String codigoCita;
    private String motivoConsulta;
    private String antecedentes;
    private String planTratamiento;
    private String observaciones;

    private SignosVitales signosVitales;
    private List<Diagnostico> listaDiagnosticos;
    private RecetaMedica recetaMedica;

    public AtencionMedica() {
        this.listaDiagnosticos = new ArrayList<>();
    }

    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public int getIdCita() { return idCita; }
    public void setIdCita(int idCita) { this.idCita = idCita; }
    public String getCodigoCita() { return codigoCita; }
    public void setCodigoCita(String codigoCita) { this.codigoCita = codigoCita; }
    public String getMotivoConsulta() { return motivoConsulta; }
    public void setMotivoConsulta(String motivoConsulta) { this.motivoConsulta = motivoConsulta; }
    public String getAntecedentes() { return antecedentes; }
    public void setAntecedentes(String antecedentes) { this.antecedentes = antecedentes; }
    public String getPlanTratamiento() { return planTratamiento; }
    public void setPlanTratamiento(String planTratamiento) { this.planTratamiento = planTratamiento; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    public SignosVitales getSignosVitales() { return signosVitales; }
    public void setSignosVitales(SignosVitales signosVitales) { this.signosVitales = signosVitales; }
    public List<Diagnostico> getListaDiagnosticos() { return listaDiagnosticos; }
    public void setListaDiagnosticos(List<Diagnostico> listaDiagnosticos) { this.listaDiagnosticos = listaDiagnosticos; }
    public RecetaMedica getRecetaMedica() { return recetaMedica; }
    public void setRecetaMedica(RecetaMedica recetaMedica) { this.recetaMedica = recetaMedica; }
}