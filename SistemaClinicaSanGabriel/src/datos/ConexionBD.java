package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instancia;
    private Connection conexion;

    private String url = "jdbc:mysql://localhost:3306/Sistema_Clinica_San_Gabriel";
    private String user = "root";
    private String contraseña = "admin";

    private ConexionBD(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            this.conexion = DriverManager.getConnection(url, user, contraseña);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Drive Manager de MySQL no encontrado");
            System.out.println(e.getMessage());
        } catch (SQLException e){
            System.err.println("Error al conectarse a la abse de datos");
            System.out.println(e.getMessage());
        }
    }

    public static ConexionBD getInstancia(){
        try{
            if(instancia == null || instancia.getConexion().isClosed()){
                instancia = new ConexionBD();
            }
        } catch (SQLException e){
            System.err.println("Error en la base de datos");
            System.out.println(e.getMessage());
        }
        return instancia;
    }

    public Connection getConexion(){
        return conexion;
    }
}
