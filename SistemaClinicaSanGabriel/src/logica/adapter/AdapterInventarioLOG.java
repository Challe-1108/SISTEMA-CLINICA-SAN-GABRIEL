/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica.adapter;
import datos.MedicamentoDAO;
import entidades.Medicamento;
/**
 *
 * @author LENOVO
 */
public class AdapterInventarioLOG implements IExportable {
    private final MedicamentoDAO medicamentoDAO;

    public AdapterInventarioLOG() {
        this.medicamentoDAO = new MedicamentoDAO();
    }
    @Override
    public String obtenerDatosFormateados(int idMedicamento) {
        try {
            Medicamento med = medicamentoDAO.obtenerPorId(idMedicamento);
            if (med == null) return "{\"error\": \"Medicamento no encontrado\"}";

            return String.format(
                "{\"id\": %d, \"nombre\": \"%s\", \"stock\": %d, \"precio\": %.2f}",
                med.getIdMedicamento(), med.getNombre(), med.getStockActual(), med.getPrecioUnitario()
            );
        } catch (Exception e) {
            return "{\"error\": \"" + e.getMessage() + "\"}";
        }
    }
}
