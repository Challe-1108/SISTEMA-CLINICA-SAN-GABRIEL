/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author sthef
 */
import entidades.SeguroMedico;
import java.sql.*;

public class SeguroDAO {

    private Connection con;

    public SeguroDAO() {
        this.con = ConexionBD.getInstancia().getConexion();
    }

    public boolean insertar(SeguroMedico seguro) {
        String sql = "INSERT INTO seguro_medico (compania, numero_poliza, tipo_cobertura, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, seguro.getCompania());
            ps.setString(2, seguro.getNumeroPoliza());
            ps.setString(3, seguro.getTipoCobertura());
            ps.setBoolean(4, seguro.isEstado());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        seguro.setIdSeguro(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizar(SeguroMedico seguro) {
        String sql = "UPDATE seguro_medico SET compania=?, numero_poliza=?, tipo_cobertura=? WHERE id_seguro=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, seguro.getCompania());
            ps.setString(2, seguro.getNumeroPoliza());
            ps.setString(3, seguro.getTipoCobertura());
            ps.setInt(4, seguro.getIdSeguro());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public SeguroMedico buscarPorId(int idSeguro) {

        String sql = "SELECT * FROM seguro_medico WHERE id_seguro = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idSeguro);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return mapear(rs);

                }

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    // RN-11 (eliminación lógica aplicada de forma consistente en todo el módulo)
    public boolean eliminarLogico(int idSeguro) {
        String sql = "UPDATE seguro_medico SET estado = false WHERE id_seguro = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSeguro);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private SeguroMedico mapear(ResultSet rs) throws SQLException {
        return new SeguroMedico(
                rs.getInt("id_seguro"),
                rs.getString("compania"),
                rs.getString("numero_poliza"),
                rs.getString("tipo_cobertura"),
                rs.getBoolean("estado")
        );
    }
}
