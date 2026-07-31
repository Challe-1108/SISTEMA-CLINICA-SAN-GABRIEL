package datos;

/**
 *
 * @author Cristopher
 */

import entidades.ReporteAtencion;
import entidades.ReporteIngresos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReportesDAO {

    public static ArrayList<ReporteAtencion> reportePacientesPorEspecialidad() {

        String sql = "SELECT e.nombre AS especialidad, COUNT(*) AS cantidad "
                + "FROM atencion_medica a "
                + "INNER JOIN especialidad e ON a.id_especialidad = e.id_especialidad "
                + "GROUP BY e.nombre";

        ArrayList<ReporteAtencion> lista = new ArrayList<>();

        try (Connection cn = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                ReporteAtencion reporte = new ReporteAtencion();

                reporte.setEspecialidad(rs.getString("especialidad"));
                reporte.setCantidadPacientes(rs.getInt("cantidad"));

                lista.add(reporte);
            }

        } catch (SQLException e) {
            System.err.println("Error al generar reporte de pacientes por especialidad: " + e.getMessage());
        }
        return lista;
    }

    public static ArrayList<ReporteIngresos> reporteIngresosDiarios() {

        String sql = "SELECT fecha_pago, COUNT(*) AS cantidad_pagos, SUM(monto) AS total_ingresos "
                + "FROM pago "
                + "GROUP BY fecha_pago "
                + "ORDER BY fecha_pago";

        ArrayList<ReporteIngresos> lista = new ArrayList<>();

        try (Connection cn = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                ReporteIngresos reporte = new ReporteIngresos();

                Date fecha = rs.getDate("fecha_pago");
                reporte.setFecha(fecha.toLocalDate());
                reporte.setCantidadPagos(rs.getInt("cantidad_pagos"));
                reporte.setTotalIngresos(rs.getDouble("total_ingresos"));
                lista.add(reporte);
            }
        } catch (SQLException e) {
            System.err.println("Error al generar reporte de ingresos diarios: " + e.getMessage());
        }
        return lista;
    }

    public static double obtenerTotalIngresos() {

        String sql = "SELECT IFNULL(SUM(monto),0) AS total FROM pago";
        double total = 0;
        try (Connection cn = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el total de ingresos: " + e.getMessage());
        }
        return total;
    }

    public static int obtenerCantidadPagos() {

        String sql = "SELECT COUNT(*) AS total FROM pago";
        int cantidad = 0;
        try (Connection cn = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                cantidad = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener la cantidad de pagos: " + e.getMessage());
        }
        return cantidad;
    }

    public static int obtenerCantidadBoletas() {

        String sql = "SELECT COUNT(*) AS total FROM comprobante WHERE tipo_comprobante='Boleta'";
        int cantidad = 0;
        try (Connection cn = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                cantidad = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las boletas: " + e.getMessage());
        }
        return cantidad;
    }

    public static int obtenerCantidadFacturas() {

        String sql = "SELECT COUNT(*) AS total FROM comprobante WHERE tipo_comprobante='Factura'";

        int cantidad = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                cantidad = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener las facturas: " + e.getMessage());
        }
        return cantidad;
    }
}

//corregir atencion medica
