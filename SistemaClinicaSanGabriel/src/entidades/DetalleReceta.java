package entidades;

public class DetalleReceta {
    private int idDetalle;
    private int idReceta;
    private int idMedicamento;
    private String nombreMedicamento; // Auxiliar para mostrar en la tabla de la interfaz
    private int cantidad;
    private String indicacion;

    // Constructor vacío
    public DetalleReceta() {
    }

    // Constructor útil para agregar desde la interfaz
    public DetalleReceta(int idMedicamento, String nombreMedicamento, int cantidad, String indicacion) {
        this.idMedicamento = idMedicamento;
        this.nombreMedicamento = nombreMedicamento;
        this.cantidad = cantidad;
        this.indicacion = indicacion;
    }

    // --- GETTERS Y SETTERS ---

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getIndicacion() {
        return indicacion;
    }

    public void setIndicacion(String indicacion) {
        this.indicacion = indicacion;
    }
}