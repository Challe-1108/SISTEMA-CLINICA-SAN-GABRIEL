package entidades;

import java.util.ArrayList;
import java.util.List;

public class RecetaMedica {
    private int idReceta;
    private int idAtencion;
    private List<DetalleReceta> detalles; // Soporta 1 a N medicamentos (RN-27 y RN-28)

    public RecetaMedica() {
        this.detalles = new ArrayList<>();
    }

    public static class DetalleReceta {
        private int idMedicamento;
        private String nombreMedicamento;
        private int cantidad; // RN-29: debe ser > 0
        private String indicacion;

        public DetalleReceta(int idMedicamento, String nombreMedicamento, int cantidad, String indicacion) {
            this.idMedicamento = idMedicamento;
            this.nombreMedicamento = nombreMedicamento;
            this.cantidad = cantidad;
            this.indicacion = indicacion;
        }

        public int getIdMedicamento() { return idMedicamento; }
        public String getNombreMedicamento() { return nombreMedicamento; }
        public int getCantidad() { return cantidad; }
        public String getIndicacion() { return indicacion; }
    }

    public void agregarMedicamento(int idMedicamento, String nombre, int cantidad, String indicacion) {
        this.detalles.add(new DetalleReceta(idMedicamento, nombre, cantidad, indicacion));
    }

    public int getIdReceta() { return idReceta; }
    public void setIdReceta(int idReceta) { this.idReceta = idReceta; }
    public int getIdAtencion() { return idAtencion; }
    public void setIdAtencion(int idAtencion) { this.idAtencion = idAtencion; }
    public List<DetalleReceta> getDetalles() { return detalles; }
}