package logica;

import datos.ReportesDAO;
import entidades.ReporteAtencion;
import entidades.ReporteIngresos;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ReporteLOG {

    public static ArrayList<ReporteAtencion> obtenerReportePacientesPorEspecialidad() {

        ArrayList<ReporteAtencion> lista = ReportesDAO.reportePacientesPorEspecialidad();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No existen datos para generar el reporte de pacientes por especialidad.",
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        return lista;
    }

    public static ArrayList<ReporteIngresos> obtenerReporteIngresosDiarios() {

        ArrayList<ReporteIngresos> lista = ReportesDAO.reporteIngresosDiarios();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No existen pagos registrados para generar el reporte de ingresos diarios.",
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        return lista;
    }

    public static double obtenerTotalIngresos() {
        return ReportesDAO.obtenerTotalIngresos();
    }

    public static int obtenerCantidadPagos() {
        return ReportesDAO.obtenerCantidadPagos();
    }

    public static int obtenerCantidadBoletas() {
        return ReportesDAO.obtenerCantidadBoletas();
    }

    public static int obtenerCantidadFacturas() {
        return ReportesDAO.obtenerCantidadFacturas();
    }
}
