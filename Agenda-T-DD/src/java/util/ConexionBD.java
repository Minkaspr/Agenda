
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dario
 */
public class ConexionBD {
    // Variables de instancia para almacenar los detalles de la conexión a la base de datos
    private final String HOST = "localhost";
    private final int PORT = 3306;
    private final String DATABASE = "agenda";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
    private final String USER = "root";
    private final String PASS = "";

    /**
     * Método para establecer una conexión con la base de datos.
     * @return Una conexión a la base de datos.
     * @throws SQLException Si ocurre algún error durante la conexión.
     */
    public Connection conexionBD() throws SQLException {
        Connection conexion = null;
        try {
            // Cargar el driver de la base de datos
            Class.forName(DRIVER).newInstance();
            // Establecer la conexión con la base de datos
            conexion = DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException
                | IllegalAccessException
                | InstantiationException
                | SQLException e) {
            // Lanzar una excepción si ocurre algún error durante la conexión
            throw new SQLException(e.getMessage());
        }
        return conexion;
    }
}
