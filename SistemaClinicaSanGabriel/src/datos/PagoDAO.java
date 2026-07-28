package datos;

/**
 *
 * @author Cristopher
 */

import entidades.Pago;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PagoDAO {
   
    public static boolean registrarPago(Pago pago) {
        String sql = "INSERT INTO pago (id_atencion, fecha_pago, monto, metodo_pago, estado) VALUES (?, ?, ?, ?, ?)";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, pago.getIdAtencion());
            ps.setDate(2, Date.valueOf(pago.getFechaPago()));
            ps.setDouble(3, pago.getMonto());
            ps.setString(4, pago.getMetodoPago());
            ps.setBoolean(5, pago.isEstado());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al registrar pago: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static Pago buscarPago(int idPago) {
        String sql = "SELECT * FROM pago WHERE id_pago = ?";
        Pago pago = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idPago);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdAtencion(rs.getInt("id_atencion"));
                pago.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                pago.setMonto(rs.getDouble("monto"));
                pago.setMetodoPago(rs.getString("metodo_pago"));
                pago.setEstado(rs.getBoolean("estado"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar pago: " + e.getMessage());
        }

        return pago;
    }

    public static Pago buscarPagoPorAtencion(int idAtencion) {
        String sql = "SELECT * FROM pago WHERE id_atencion = ?";
        Pago pago = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idAtencion);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdAtencion(rs.getInt("id_atencion"));
                pago.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                pago.setMonto(rs.getDouble("monto"));
                pago.setMetodoPago(rs.getString("metodo_pago"));
                pago.setEstado(rs.getBoolean("estado"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar pago por atención: " + e.getMessage());
        }

        return pago;
    }

    public static ArrayList<Pago> listarPagos() {
        String sql = "SELECT * FROM pago";
        ArrayList<Pago> lista = new ArrayList<>();

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pago pago = new Pago();
                pago.setIdPago(rs.getInt("id_pago"));
                pago.setIdAtencion(rs.getInt("id_atencion"));
                pago.setFechaPago(rs.getDate("fecha_pago").toLocalDate());
                pago.setMonto(rs.getDouble("monto"));
                pago.setMetodoPago(rs.getString("metodo_pago"));
                pago.setEstado(rs.getBoolean("estado"));

                lista.add(pago);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar pagos: " + e.getMessage());
        }

        return lista;
    }
    
}
