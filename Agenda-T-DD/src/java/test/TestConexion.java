
package test;

import java.sql.Connection;
import java.sql.SQLException;
import util.ConexionBD;

/**
 *
 * @author Dario
 */
public class TestConexion {
    public static void main(String[] args) {
        // Crear una instancia de la clase ConexionBD
        ConexionBD con = new ConexionBD();
        try (Connection c = con.conexionBD()){
            // Si la conexión es exitosa, imprimir un mensaje en la consola
            System.out.println("Conexión Exitosa");
        } catch (SQLException e) {
            // Si ocurre algún error durante la conexión, imprimir el mensaje de error en la consola
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
