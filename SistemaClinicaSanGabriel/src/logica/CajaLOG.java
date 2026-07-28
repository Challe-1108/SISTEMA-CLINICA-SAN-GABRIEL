package logica;

/**
 *
 * @author Cristopher
 */

import datos.ComprobanteDAO;
import datos.PagoDAO;
import entidades.Comprobante;
import entidades.Pago;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CajaLOG {
   
    public static boolean registrarPago(Pago pago, String tipoComprobante) {

        if (!validarPago(pago, tipoComprobante)) {
            return false;
        }

        if (PagoDAO.buscarPagoPorAtencion(pago.getIdAtencion()) != null) {
            JOptionPane.showMessageDialog(null,
                    "La atención médica seleccionada ya cuenta con un pago registrado.",
                    "Pago existente",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        boolean exito = PagoDAO.registrarPago(pago);

        if (!exito) {
            JOptionPane.showMessageDialog(null,
                    "No se pudo registrar el pago. Verifique la información e intente nuevamente.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        Pago pagoRegistrado = PagoDAO.buscarPagoPorAtencion(pago.getIdAtencion());

        if (pagoRegistrado != null) {

            Comprobante comprobante = new Comprobante();
            comprobante.setNumeroComprobante(generarNumeroComprobante(tipoComprobante));
            comprobante.setFechaEmision(LocalDate.now());
            comprobante.setTipoComprobante(tipoComprobante);
            comprobante.setTotal(pago.getMonto());
            comprobante.setIdPago(pagoRegistrado.getIdPago());

            ComprobanteDAO.registrarComprobante(comprobante);
        }

        JOptionPane.showMessageDialog(null,
                "El pago se registró correctamente.",
                "Registro exitoso",
                JOptionPane.INFORMATION_MESSAGE);

        return true;
    }

    public static Pago buscarPago(int idPago) {

        Pago pago = PagoDAO.buscarPago(idPago);

        if (pago == null) {
            JOptionPane.showMessageDialog(null,
                    "No se encontró el pago solicitado.",
                    "Sin resultados",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        return pago;
    }

    public static ArrayList<Pago> listarPagos() {
        return PagoDAO.listarPagos();
    }

    private static boolean validarPago(Pago pago, String tipoComprobante) {

        StringBuilder errores = new StringBuilder();

        if (pago.getIdAtencion() <= 0) {
            errores.append("- Debe seleccionar una atención médica.\n");
        }

        if (pago.getFechaPago() == null) {
            errores.append("- La fecha de pago es obligatoria.\n");
        }

        if (pago.getMonto() <= 0) {
            errores.append("- El monto debe ser mayor a cero.\n");
        }

        if (pago.getMetodoPago() == null || pago.getMetodoPago().trim().isEmpty()) {
            errores.append("- Debe seleccionar un método de pago.\n");
        }

        if (tipoComprobante == null || tipoComprobante.trim().isEmpty()) {
            errores.append("- Debe seleccionar un tipo de comprobante.\n");
        }

        if (errores.length() > 0) {
            JOptionPane.showMessageDialog(null,
                    "Corrija los siguientes datos:\n\n" + errores,
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private static String generarNumeroComprobante(String tipoComprobante) {

        int numero = ComprobanteDAO.listarComprobantes().size() + 1;

        if (tipoComprobante.equalsIgnoreCase("Boleta")) {
            return String.format("B001-%06d", numero);
        }

        return String.format("F001-%06d", numero);
    }
    
}
