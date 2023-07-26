package dao.impl;

import dao.DaoUsuario;
import entidades.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.ConexionBD;

/**
 *
 * @author Dario
 */
public class DaoUsuarioImpl implements DaoUsuario {

    private final ConexionBD conexion;
    private String mensaje;

    // Constructor
    public DaoUsuarioImpl() {
        // Inicializamos la conexión
        conexion = new ConexionBD();
    }

    /**
     * Obtiene una lista de todos los usuarios desde la base de datos.
     *
     * @return Una lista de objetos Usuario que representa todos los registros
     * de la tabla 'usuario'. Si no se encuentran registros, la lista estará
     * vacía.
     * @throws - SQLException Si ocurre algún error durante la consulta a la
     * base de datos.
     */
    @Override
    public List<Usuario> UsuarioSel() {
        List<Usuario> list = null;

        // Construir la consulta SQL para seleccionar todos los campos de la tabla "usuario"
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("idUsuario, ")
                .append("nombre, ")
                .append("apellido, ")
                .append("correo, ")
                .append("clave ")
                .append("FROM usuario");

        try (Connection cn = conexion.conexionBD()) {
            // Establecer la conexión con la base de datos
            // y preparar la consulta SQL utilizando PreparedStatement
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ResultSet rs = ps.executeQuery();

            // Inicializar la lista para almacenar los usuarios recuperados
            list = new ArrayList<>();

            // Recorrer los resultados del ResultSet y construir objetos Usuario
            // a partir de los datos obtenidos de la base de datos
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setCorreo(rs.getString(4));
                usuario.setClave(rs.getString(5));

                // Agregar el objeto Usuario a la lista
                list.add(usuario);
            }
        } catch (SQLException e) {
            // Si ocurre alguna excepción durante la operación, almacenar el mensaje de error
            // en la variable "mensaje" para su posterior uso o manejo
            mensaje = e.getMessage();
        }

        // Devolver la lista de usuarios obtenida de la base de datos
        return list;
    }

    /**
     * Obtiene un objeto Usuario de la base de datos según el identificador
     * proporcionado.
     *
     * @param id El identificador del usuario a buscar en la base de datos.
     * @return Un objeto Usuario que representa el registro con el idUsuario
     * especificado, o null si no se encuentra un registro con el identificador
     * dado.
     * @throws - SQLException Si ocurre algún error durante la consulta a la
     * base de datos.
     */
    @Override
    public Usuario usuarioGet(Integer id) {
        // Crear un nuevo objeto Usuario para almacenar el resultado
        Usuario usuario = new Usuario();

        // Construir la consulta SQL para seleccionar el registro correspondiente al idUsuario
        StringBuilder query = new StringBuilder();
        query.append("SELECT ")
                .append("idUsuario, ")
                .append("nombre, ")
                .append("apellido, ")
                .append("correo, ")
                .append("clave ")
                .append("FROM usuario WHERE idUsuario = ?");

        try (Connection cn = conexion.conexionBD()) {
            // Establecer la conexión con la base de datos y preparar la consulta SQL
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                // Ejecutar la consulta y verificar si se encontró un registro
                if (rs.next()) {
                    // Construir el objeto Usuario con los datos obtenidos de la base de datos
                    usuario.setIdUsuario(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setApellido(rs.getString(3));
                    usuario.setCorreo(rs.getString(4));
                    usuario.setClave(rs.getString(5));
                } else {
                    // Si no se encontró un registro con el idUsuario dado, establecer usuario como null
                    usuario = null;
                }
            } catch (Exception e) {
                // Capturar excepciones relacionadas con la consulta y almacenar el mensaje de error
                mensaje = e.getMessage();
            }
        } catch (Exception e) {
            // Capturar excepciones relacionadas con la conexión y almacenar el mensaje de error
            mensaje = "Error: " + e.getMessage();
        }
        // Devolver el objeto Usuario encontrado o null si no se encontró ninguno
        return usuario;
    }

    /**
     * Inserta un nuevo registro de usuario en la base de datos.
     *
     * @param usuario El objeto Usuario con los datos del usuario a insertar.
     * @return Un mensaje indicando el resultado de la operación de inserción.
     * @throws - SQLException Si ocurre un error durante la consulta de
     * inserción.
     */
    @Override
    public String usuarioIns(Usuario usuario) {
        // Construir la consulta SQL para insertar un nuevo registro en la tabla 'usuario'
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO usuario (")
                .append("nombre, ")
                .append("apellido, ")
                .append("correo, ")
                .append("clave ")
                .append(") VALUES (?,?,?,?)");

        try (Connection cn = conexion.conexionBD()) {
            // Establecer la conexión con la base de datos y preparar la consulta SQL
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getClave());
            // Ejecutar la consulta de inserción y obtener el número de filas afectadas
            int ctos = ps.executeUpdate();
            // Verificar si no se insertó ninguna fila y, si es así, mostrar el mensaje de advertencia
            if (ctos == 0) {
                mensaje = "Cero filas insertadas";
            }
        } catch (SQLException e) {
            // Capturar excepciones relacionadas con la conexión y almacenar el mensaje de error
            mensaje = "Error: " + e.getMessage();
        }
        // Devolver el mensaje que indica el resultado de la operación de inserción
        return mensaje;
    }

    /**
     * Actualiza un registro de usuario en la base de datos.
     *
     * @param usuario El objeto Usuario con los nuevos datos a actualizar.
     * @return Un mensaje indicando el resultado de la operación de
     * actualización.
     * @throws - SQLException Si ocurre un error durante la conexión o consulta
     * de actualización.
     */
    @Override
    public String usuarioUpd(Usuario usuario) {
        // Construir la consulta SQL para actualizar el registro de usuario en la base de datos
        StringBuilder query = new StringBuilder();
        query.append("UPDATE usuario SET ")
                .append("nombre = ?, ")
                .append("apellido = ?, ")
                .append("correo = ?, ")
                .append("clave = ? ")
                .append("WHERE idUsuario = ?");
        try (Connection cn = conexion.conexionBD()) {
            // Establecer la conexión con la base de datos y preparar la consulta SQL
            PreparedStatement ps = cn.prepareStatement(query.toString());
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getIdUsuario());

            // Ejecutar la consulta de actualización y obtener el número de filas afectadas
            int ctos = ps.executeUpdate();
            // Verificar si no se actualizó ninguna fila y, si es así, mostrar el mensaje de advertencia
            if (ctos == 0) {
                mensaje = "Cero filas actualizadas";
            }
        } catch (SQLException e) {
            // Capturar excepciones relacionadas con la conexión y almacenar el mensaje de error
            mensaje = "Error: " + e.getMessage();
        }
        // Devolver el mensaje que indica el resultado de la operación de inserción
        return mensaje;
    }

    /**
     * Elimina uno o más registros de usuario de la base de datos según los
     * identificadores proporcionados.
     *
     * @param ids Lista de identificadores (idUsuario) de los usuarios a
     * eliminar.
     * @return Un mensaje indicando el resultado de la operación de eliminación.
     * @throws - SQLException Si ocurre un error durante la conexión o consulta de
     * eliminación.
     */
    @Override
    public String usuarioDel(List<Integer> ids) {
        // Construir la consulta SQL para eliminar uno o más registros de usuario de la base de datos
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM usuario WHERE ")
                .append("idUsuario = ?");

        try (Connection cn = conexion.conexionBD()) {
            // Establecer la conexión con la base de datos y preparar la consulta SQL
            PreparedStatement ps = cn.prepareStatement(query.toString());
            // Establecer el modo de "confirmación manual"
            cn.setAutoCommit(false);
            boolean ok = true;

            // Iterar sobre la lista de identificadores (ids) y ejecutar la consulta de eliminación
            for (int id = 0; id < ids.size(); id++) {
                ps.setInt(1, ids.get(id));
                // Ejecutar la consulta de eliminación y obtener el número de filas afectadas
                int ctos = ps.executeUpdate();
                // Verificar si no se eliminó ningún registro y, si es así, mostrar el mensaje de advertencia
                if (ctos == 0) {
                    ok = false;
                    mensaje = "ID: " + ids.get(id) + " no existe";
                }
            }
            // Confirmar o deshacer los cambios en la base de datos según el resultado de la operación
            if (ok) {
                cn.commit();
            } else {
                cn.rollback();
            }
            // Restaurar el modo de "confirmación automática"
            cn.setAutoCommit(true);
        } catch (SQLException e) {
            mensaje = e.getMessage();
        }
        return mensaje;
    }

    @Override
    public String getMensaje() {
        return mensaje;
    }

}
