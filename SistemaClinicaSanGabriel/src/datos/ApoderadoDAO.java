package datos;

import datos.ConexionBD;
import entidades.Apoderado;
import java.sql.*;

public class ApoderadoDAO {

    private Connection con;

    public ApoderadoDAO() {
        this.con = ConexionBD.getInstancia().getConexion();
    }

    public boolean insertar(Apoderado apoderado) {
        String sql = "INSERT INTO apoderado (dni, nombres, apellidos, telefono, parentesco, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, apoderado.getDni());
            ps.setString(2, apoderado.getNombres());
            ps.setString(3, apoderado.getApellidos());
            ps.setString(4, apoderado.getTelefono());
            ps.setString(5, apoderado.getParentesco());
            ps.setBoolean(6, apoderado.isEstado());
            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        apoderado.setIdApoderado(rs.getInt(1));
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

    public boolean actualizar(Apoderado apoderado) {
        String sql = "UPDATE apoderado SET dni=?, nombres=?, apellidos=?, telefono=?, parentesco=? "
                + "WHERE id_apoderado=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, apoderado.getDni());
            ps.setString(2, apoderado.getNombres());
            ps.setString(3, apoderado.getApellidos());
            ps.setString(4, apoderado.getTelefono());
            ps.setString(5, apoderado.getParentesco());
            ps.setInt(6, apoderado.getIdApoderado());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Apoderado buscarPorId(int idApoderado) {
        String sql = "SELECT * FROM apoderado WHERE id_apoderado = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApoderado);
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

    public boolean eliminarLogico(int idApoderado) {
        String sql = "UPDATE apoderado SET estado = false WHERE id_apoderado = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApoderado);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Apoderado mapear(ResultSet rs) throws SQLException {
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
