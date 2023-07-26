
package dao;

import entidades.Usuario;
import java.util.List;

/**
 *
 * @author Dario
 */
public interface DaoUsuario {
    // Muestra todos los registros de la tabla usuario
    List <Usuario> UsuarioSel();
    // Muestra un solo registro de la tabla usuario
    Usuario usuarioGet(Integer id);
    // Inserta un solo registro a la tabla usuario
    String usuarioIns(Usuario usuario);
    // Actualiza un solo registro de la tabla usuario
    String usuarioUpd(Usuario usuario);
    // Elimina uno o mas registros de la tabla usuario
    String usuarioDel(List<Integer>ids);
    String getMensaje();
}
