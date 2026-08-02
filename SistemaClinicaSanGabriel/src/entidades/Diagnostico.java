package entidades;

public class Diagnostico {
    private int idDiagnostico;
    private int idAtencion;
    private String descripcion;
    private String tipo; // "Presuntivo" o "Definitivo"

    public Diagnostico() {
    }

    public Diagnostico(String descripcion, String tipo) {
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(int idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}