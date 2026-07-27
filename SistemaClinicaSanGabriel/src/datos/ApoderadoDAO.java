package datos;

import entidades.Apoderado;
import java.sql.*;

public class ApoderadoDAO {

    public static boolean insertar(Apoderado apoderado) {
        String sql = "INSERT INTO apoderado (dni, nombres, apellidos, telefono, parentesco, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, apoderado.getDni());
            ps.setString(2, apoderado.getNombres());
            ps.setString(3, apoderado.getApellidos());
            ps.setString(4, apoderado.getTelefono());
            ps.setString(5, apoderado.getParentesco());
            ps.setBoolean(6, apoderado.isEstado());
            filasAfectadas = ps.executeUpdate();

            if (filasAfectadas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        apoderado.setIdApoderado(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar apoderado: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static boolean actualizar(Apoderado apoderado) {
        String sql = "UPDATE apoderado SET dni=?, nombres=?, apellidos=?, telefono=?, parentesco=? "
                + "WHERE id_apoderado=?";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, apoderado.getDni());
            ps.setString(2, apoderado.getNombres());
            ps.setString(3, apoderado.getApellidos());
            ps.setString(4, apoderado.getTelefono());
            ps.setString(5, apoderado.getParentesco());
            ps.setInt(6, apoderado.getIdApoderado());
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar apoderado: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    public static Apoderado buscarPorId(int idApoderado) {
        String sql = "SELECT * FROM apoderado WHERE id_apoderado = ?";
        Apoderado apoderado = null;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idApoderado);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    apoderado = mapear(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar apoderado: " + e.getMessage());
        }

        return apoderado;
    }

    public static boolean eliminarLogico(int idApoderado) {
        String sql = "UPDATE apoderado SET estado = false WHERE id_apoderado = ?";
        int filasAfectadas = 0;

        try (Connection cn = ConexionBD.getInstancia().getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idApoderado);
            filasAfectadas = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al inactivar apoderado: " + e.getMessage());
        }

        return filasAfectadas > 0;
    }

    private static Apoderado mapear(ResultSet rs) throws SQLException {
        return new Apoderado(
                rs.getInt("id_apoderado"),
                rs.getString("dni"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("telefono"),
                rs.getString("parentesco"),
                rs.getBoolean("estado")
        );
    }
}
