/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author sthef
 */
import entidades.Apoderado;
import entidades.Paciente;
import entidades.SeguroMedico;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private Connection con;

    public PacienteDAO() {
        this.con = ConexionBD.getInstancia().getConexion();
    }

    public boolean insertar(Paciente paciente) {
        String sql = "INSERT INTO paciente (dni, nombres, apellidos, fecha_nacimiento, sexo, telefono, "
                + "direccion, numero_historia_clinica, id_seguro, id_apoderado, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, paciente.getDni());
            ps.setString(2, paciente.getNombres());
            ps.setString(3, paciente.getApellidos());
            ps.setDate(4, Date.valueOf(paciente.getFechaNacimiento()));
            ps.setString(5, paciente.getSexo());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getDireccion());
            ps.setString(8, paciente.getNumeroHistoriaClinica());

            if (paciente.getSeguroMedico() != null) {
                ps.setInt(9, paciente.getSeguroMedico().getIdSeguro());
            } else {
                ps.setNull(9, Types.INTEGER);
            }

            if (paciente.getApoderado() != null) {
                ps.setInt(10, paciente.getApoderado().getIdApoderado());
            } else {
                ps.setNull(10, Types.INTEGER);
            }

            ps.setBoolean(11, paciente.isEstado());

            int filas = ps.executeUpdate();
            if (filas > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        paciente.setIdPaciente(rs.getInt(1));
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

    public boolean actualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nombres=?, apellidos=?, telefono=?, direccion=?, "
                + "id_seguro=?, id_apoderado=? WHERE id_paciente=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, paciente.getNombres());
            ps.setString(2, paciente.getApellidos());
            ps.setString(3, paciente.getTelefono());
            ps.setString(4, paciente.getDireccion());

            if (paciente.getSeguroMedico() != null) {
                ps.setInt(5, paciente.getSeguroMedico().getIdSeguro());
            } else {
                ps.setNull(5, Types.INTEGER);
            }

            if (paciente.getApoderado() != null) {
                ps.setInt(6, paciente.getApoderado().getIdApoderado());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.setInt(7, paciente.getIdPaciente());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * RN-11: Eliminación lógica. Nunca se realiza DELETE físico.
     */
    public boolean eliminarLogico(int idPaciente) {
        String sql = "UPDATE paciente SET estado = false WHERE id_paciente = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idPaciente);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Paciente buscarPorDni(String dni) {
        String sql = "SELECT * FROM paciente WHERE dni = ?";
        return buscarUno(sql, dni);
    }

    public Paciente buscarPorHistoriaClinica(String numeroHistoriaClinica) {
        String sql = "SELECT * FROM paciente WHERE numero_historia_clinica = ?";
        return buscarUno(sql, numeroHistoriaClinica);
    }

    private Paciente buscarUno(String sql, String parametro) {
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, parametro);
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

    public List<Paciente> buscarPorNombre(String nombreOApellido) {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente WHERE nombres LIKE ? OR apellidos LIKE ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            String like = "%" + nombreOApellido + "%";
            ps.setString(1, like);
            ps.setString(2, like);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapear(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Paciente> listarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Paciente mapear(ResultSet rs) throws SQLException {
        SeguroDAO seguroDAO = new SeguroDAO();
        SeguroMedico seguro = null;
        int idSeguro = rs.getInt("id_seguro");
        if (!rs.wasNull()) {
            seguro = seguroDAO.buscarPorId(idSeguro);
        }

        Apoderado apoderado = null;
        int idApoderado = rs.getInt("id_apoderado");
        if (!rs.wasNull()) {
            apoderado = buscarApoderadoPorId(idApoderado);
        }

        return new Paciente.Builder()
                .idPaciente(rs.getInt("id_paciente"))
                .dni(rs.getString("dni"))
                .nombres(rs.getString("nombres"))
                .apellidos(rs.getString("apellidos"))
                .fechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate())
                .sexo(rs.getString("sexo"))
                .telefono(rs.getString("telefono"))
                .direccion(rs.getString("direccion"))
                .numeroHistoriaClinica(rs.getString("numero_historia_clinica"))
                .seguroMedico(seguro)
                .apoderado(apoderado)
                .estado(rs.getBoolean("estado"))
                .build();
    }

    private Apoderado buscarApoderadoPorId(int idApoderado) {
        String sql = "SELECT * FROM apoderado WHERE id_apoderado = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idApoderado);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}