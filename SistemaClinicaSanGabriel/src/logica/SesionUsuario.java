package logica;

public class SesionUsuario {
    private static SesionUsuario instancia;
    private int idUsuario;
    private String username;
    private String rol;

    private SesionUsuario() {}

    public static SesionUsuario getInstance() {
        if (instancia == null) {
            instancia = new SesionUsuario();
        }
        return instancia;
    }

    public void iniciarSesion(int idUsuario, String username, String rol) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.rol = rol;
    }

    public void cerrarSesion() {
        this.idUsuario = 0;
        this.username = null;
        this.rol = null;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public String getRol() {
        return rol;
    }

    public boolean haySesionActiva() {
        return idUsuario > 0 && username != null;
    }
}