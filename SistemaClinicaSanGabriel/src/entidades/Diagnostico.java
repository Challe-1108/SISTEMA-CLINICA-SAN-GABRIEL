package entidades;

public class Diagnostico {
    private int idDiagnostico;
    private int idAtencion;
    private String codigoCIE10; // RN-26
    private String descripcion;
    private String tipo; // Presuntivo / Definitivo

    public Diagnostico() {}

    public Diagnostico(String codigoCIE10, String descripcion, String tipo) {
        this.codigoCIE10 = codigoCIE10;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getIdDiagnostico() { return idDiagnostico; }
    public void setIdDiagnostico(int idDiagnostico) { this.idDiagnostico = idDiagnostico; }
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public String getCodigoCIE10() { return codigoCIE10; }
    public void setCodigoCIE10(String codigoCIE10) { this.codigoCIE10 = codigoCIE10; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}