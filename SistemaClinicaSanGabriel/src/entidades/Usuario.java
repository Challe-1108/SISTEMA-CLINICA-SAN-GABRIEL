package entidades;

public class Usuario {

    private int idUsuario;
    private String username;
    private String password;
    private Rol rol;
    private boolean estado;

    public Usuario() {

    }

    public Usuario(String username, String password, Rol rol, boolean estado) {
        this.idUsuario = -1;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
    }

    public Usuario(int idUsuario, String username, String password, Rol rol, boolean estado) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", rol='" + rol + '\'' +
                ", estado=" + estado +
                '}';
    }
}
