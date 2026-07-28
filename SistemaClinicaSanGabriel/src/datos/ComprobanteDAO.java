package datos;

/**
 *
 * @author Cristopher
 */

import entidades.Comprobante;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ComprobanteDAO {
   
    public static boolean registrarComprobante(Comprobante comprobante) {
        String sql = "INSERT INTO comprobante (numero_comprobante, fecha_emision, tipo_comprobante, total, id_pago) VALUES (?, ?, ?, ?, ?)";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, comprobante.getNumeroComprobante());
            ps.setDate(2, Date.valueOf(comprobante.getFechaEmision()));
            ps.setString(3, comprobante.getTipoComprobante());
            ps.setDouble(4, comprobante.getTotal());
            ps.setInt(5, comprobante.getIdPago());

            filasAfectadas = ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al registrar comprobante: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static Comprobante buscarComprobante(int idComprobante) {
        String sql = "SELECT * FROM comprobante WHERE id_comprobante = ?";
        Comprobante comprobante = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idComprobante);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comprobante = new Comprobante();
                comprobante.setIdComprobante(rs.getInt("id_comprobante"));
                comprobante.setNumeroComprobante(rs.getString("numero_comprobante"));
                comprobante.setFechaEmision(rs.getDate("fecha_emision").toLocalDate());
                comprobante.setTipoComprobante(rs.getString("tipo_comprobante"));
                comprobante.setTotal(rs.getDouble("total"));
                comprobante.setIdPago(rs.getInt("id_pago"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar comprobante: " + e.getMessage());
        }

        return comprobante;
    }

    public static Comprobante buscarComprobantePorNumero(String numeroComprobante) {
        String sql = "SELECT * FROM comprobante WHERE numero_comprobante = ?";
        Comprobante comprobante = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, numeroComprobante);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                comprobante = new Comprobante();
                comprobante.setIdComprobante(rs.getInt("id_comprobante"));
                comprobante.setNumeroComprobante(rs.getString("numero_comprobante"));
                comprobante.setFechaEmision(rs.getDate("fecha_emision").toLocalDate());
                comprobante.setTipoComprobante(rs.getString("tipo_comprobante"));
                comprobante.setTotal(rs.getDouble("total"));
                comprobante.setIdPago(rs.getInt("id_pago"));
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar comprobante por número: " + e.getMessage());
        }

        return comprobante;
    }

    public static ArrayList<Comprobante> listarComprobantes() {
        String sql = "SELECT * FROM comprobante";
        ArrayList<Comprobante> lista = new ArrayList<>();

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Comprobante comprobante = new Comprobante();
                comprobante.setIdComprobante(rs.getInt("id_comprobante"));
                comprobante.setNumeroComprobante(rs.getString("numero_comprobante"));
                comprobante.setFechaEmision(rs.getDate("fecha_emision").toLocalDate());
                comprobante.setTipoComprobante(rs.getString("tipo_comprobante"));
                comprobante.setTotal(rs.getDouble("total"));
                comprobante.setIdPago(rs.getInt("id_pago"));

                lista.add(comprobante);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar comprobantes: " + e.getMessage());
        }

        return lista;
    }
    
}
