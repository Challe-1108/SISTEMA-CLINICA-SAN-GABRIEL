package entidades;

import java.util.ArrayList;
import java.util.List;

public class RecetaMedica {
    private int idReceta;
    private int idAtencion;
    private List<DetalleReceta> detalles;

    // Constructor vacío inicializando la lista de detalles
    public RecetaMedica() {
        this.detalles = new ArrayList<>();
    }

    // Método helper para agregar un renglón de medicamento
    public void agregarDetalle(DetalleReceta detalle) {
        this.detalles.add(detalle);
    }

    // --- GETTERS Y SETTERS ---

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public int getIdAtencion() {
        return idAtencion;
    }

    public void setIdAtencion(int idAtencion) {
        this.idAtencion = idAtencion;
    }

    public List<DetalleReceta> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleReceta> detalles) {
        this.detalles = detalles;
    }
}