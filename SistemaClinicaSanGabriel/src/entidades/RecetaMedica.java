package entidades;

import java.time.LocalDate;

public class RecetaMedica {
    private int idReceta;
    private LocalDate fechaemision;
    private int idPaciente;
    private String descripcion;
    private int idMedicamento;
    private float cantidad;

    public RecetaMedica(int idReceta, LocalDate fechaemision, int idPaciente, String descripcion, int idMedicamento, float cantidad) {
        this.idReceta = idReceta;
        this.fechaemision = fechaemision;
        this.idPaciente = idPaciente;
        this.descripcion = descripcion;
        this.idMedicamento = idMedicamento;
        this.cantidad = cantidad;
    }
    
    public RecetaMedica(){
        idReceta = 0000;
        fechaemision = LocalDate.of(2026,07,01);
        idPaciente = 000;
        descripcion = "NULL";
        idMedicamento = 000;
        cantidad = 1;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public LocalDate getFechaemision() {
        return fechaemision;
    }

    public void setFechaemision(LocalDate fechaemision) {
        this.fechaemision = fechaemision;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
    
    
}
