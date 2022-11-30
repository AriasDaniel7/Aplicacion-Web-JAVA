package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Daniel Arias
 */
public class conexion {
    Connection conexion = null;
    String url = "jdbc:postgresql://localhost:5432/almacen";
    String usuario = "postgres";
    String clave = "arix07";
    Statement sentencia = null;
    
    public Connection getConexion(){
        try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url,usuario,clave);
            return conexion;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Conexion fallida"+e,"conexion",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
