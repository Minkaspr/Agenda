package test;

import dao.DaoUsuario;
import dao.impl.DaoUsuarioImpl;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dario
 */
public class TestDaoUsuario {

    public static void main(String[] args) {
        TestDaoUsuario tester = new TestDaoUsuario();

        //tester.testUsuarioSel();
        //tester.testUsuarioGet();
        //tester.testUsuarioIns();
        //tester.testUsuarioUpd();
        tester.testUsuarioDel();
        
    }

    public void testUsuarioSel() {
        // Lista para almacenar los usuarios obtenidos de la base de datos
        List<Usuario> lista = null;
        
        // Instancia del DAO de Usuario
        DaoUsuario dao = new DaoUsuarioImpl();

        try {
            // Obtener la lista de usuarios desde la base de datos
            lista = dao.UsuarioSel();
            
            // Verificar si la lista está vacía
            if (lista.isEmpty()) {
                // Mostrar un mensaje si la tabla 'usuario' no tiene registros
                System.out.println("La tabla 'usuario' no tiene registros.");
            } else {
                // Recorrer la lista y mostrar el nombre de cada usuario
                lista.forEach(usu -> {
                    System.out.println("Nombre del usuario: " + usu.getNombre());
                });
            }
        } catch (Exception e) {
            // Mostrar un mensaje de error si ocurre una excepción durante la operación
            System.out.println("Error: " + dao.getMensaje() + "\n" + e.getMessage());
        }
    }

    public void testUsuarioGet() {
        // Usuario para almacenar el resultado obtenido del método usuarioGet()
        Usuario usu = null;
        
        // Instancia del DAO de Usuario
        DaoUsuario dao = new DaoUsuarioImpl();

        try {
            // Llamar al método usuarioGet() para obtener un objeto Usuario según el idUsuario dado
            usu = dao.usuarioGet(1);
            
            // Mostrar el nombre del usuario obtenido por pantalla
            System.out.println("Nombre del usuario: " + usu.getNombre());
        } catch (Exception e) {
            // Mostrar un mensaje de error si ocurre una excepción durante la operación
            System.out.println("Error: " + dao.getMensaje() + "\n" + e.getMessage());
        }
    }

    public void testUsuarioIns() {
        // Crear un objeto Usuario con datos de prueba
        Usuario usu = new Usuario();
        usu.setNombre("Itai");
        usu.setApellido("itai");
        usu.setCorreo("itai@gmail.com");
        usu.setClave("0123*Pe");

        // Instancia del DAO de Usuario
        DaoUsuario dao = new DaoUsuarioImpl();

        try {
            // Llamar al método usuarioIns() para insertar el nuevo usuario en la base de datos
            String result = dao.usuarioIns(usu);
            
            // Verificar si la inserción se realizó con éxito y mostrar el mensaje
            if (result == null) {
                System.out.println("Se insertó el nuevo usuario");
            }
        } catch (Exception e) {
            // Mostrar un mensaje de error si ocurre una excepción durante la operación
            System.out.println("Error: " + dao.getMensaje() + "\n" + e.getMessage());
        }
    }

    public void testUsuarioUpd() {
        // Crear un objeto Usuario con datos de prueba para la actualización
        Usuario usu = new Usuario();
        usu.setIdUsuario(1);
        usu.setNombre("MinKa");
        usu.setApellido("Kaspr");
        usu.setCorreo("minkaspr@gmail.com");
        usu.setClave("0123*Pe");
        
        // Instancia del DAO de Usuario
        DaoUsuario dao = new DaoUsuarioImpl();
        
        try {
            // Llamar al método usuarioUpd() para actualizar el usuario en la base de datos
            String result = dao.usuarioUpd(usu);
            
            // Verificar si la inserción se realizó con éxito y mostrar el mensaje
            if (result == null) {
                System.out.println("Se actualizó el nuevo usuario");
            }
        } catch (Exception e) {
            // Mostrar un mensaje de error si ocurre una excepción durante la operación
            System.out.println("Error: " + dao.getMensaje() + "\n" + e.getMessage());
        }
    }
    
    // Se elimina solo si todos los ids existen, caso contrario no se elimina ninguno
    public void testUsuarioDel(){
        // Se crea una instancia del DAO de Usuario
        DaoUsuario dao = new DaoUsuarioImpl();
        // Verificar si la eliminación fue exitosa (result == null) o si hubo algún problema
        List<Integer> del = new ArrayList<>();
        del.add(2);
        //del.add(3);
        try {
            String result = dao.usuarioDel(del);
            // Verificar si la eliminación fue exitosa o si hubo algún problema
            if (result == null) {
                System.out.println("Se eliminó correctamente el/los usuario(s)");
            } else {
                System.out.println("No se puede eliminar\n"+dao.usuarioDel(del));
            }
        } catch (Exception e) {
            // Verificar si la eliminación fue exitosa (result == null) o si hubo algún problema
            System.out.println("Error: " + dao.getMensaje() + "\n" + e.getMessage());
        }
    }
}
