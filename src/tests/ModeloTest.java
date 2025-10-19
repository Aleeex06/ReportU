
package tests;

import modelo.Modelo;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

/**
 * Clase de pruebas para la clase Modelo. Contiene tests unitarios para verificar
 * el correcto funcionamiento de los métodos de la clase Modelo.
 */
public class ModeloTest {

    private Modelo modelo;
    private final String correoPrueba = "testuser@example.com";
    private final String contrasenaPrueba = "test1234";
    private final String rolPrueba = "usuario";

    /**
     * Configuración inicial antes de cada prueba. Se crea una instancia de Modelo
     * y se establece la conexión a la base de datos.
     */
    @BeforeEach
    public void setUp() {
        modelo = new Modelo();
        modelo.conectarBD();
    }

    /**
     * Limpieza después de cada prueba. Se elimina el usuario de prueba si existe
     * y se cierra la conexión a la base de datos.
     */
    @AfterEach
    public void tearDown() {
        modelo.eliminarUsuario(correoPrueba); // Limpieza por si quedó registrado
        modelo.cerrarConexion();
    }

    /**
     * Verifica que la conexión a la base de datos se establece correctamente.
     */
    @Test
    public void testConectarBD_DeberiaConectarCorrectamente() {
        Modelo m = new Modelo();
        boolean conectado = m.conectarBD();
        assertTrue(conectado, "La conexión a la BD debería funcionar con config.ini correcto");
        m.cerrarConexion();
    }

    /**
     * Verifica que un usuario inexistente no se encuentra en la base de datos.
     */
    @Test
    public void testUsuarioNoExiste_DeberiaDevolverFalse() {
        boolean existe = modelo.usuarioExiste("inexistente@correo.com");
        assertFalse(existe, "Un usuario que no existe debería devolver false");
    }

    /**
     * Verifica que un usuario puede registrarse y eliminarse correctamente.
     */
    @Test
    public void testRegistrarYEliminarUsuario_DeberiaFuncionar() {
        boolean registrado = modelo.registrarUsuario(correoPrueba, contrasenaPrueba, rolPrueba);
        assertTrue(registrado, "El usuario debería registrarse correctamente");

        boolean existe = modelo.usuarioExiste(correoPrueba);
        assertTrue(existe, "El usuario debería existir tras registrarse");

        boolean eliminado = modelo.eliminarUsuario(correoPrueba);
        assertTrue(eliminado, "El usuario debería eliminarse correctamente");
    }

    /**
     * Verifica que el método hashPassword genera el mismo hash para la misma contraseña.
     * Utiliza reflexión para acceder al método privado.
     *
     * @throws Exception Si ocurre un error al invocar el método.
     */
    @Test
    public void testHashPasswordConReflexion() throws Exception {
        java.lang.reflect.Method metodo = Modelo.class.getDeclaredMethod("hashPassword", String.class);
        metodo.setAccessible(true);
        String hash1 = (String) metodo.invoke(modelo, "claveSegura");
        String hash2 = (String) metodo.invoke(modelo, "claveSegura");
        assertEquals(hash1, hash2);
    }

    /**
     * Verifica que el rol de un usuario registrado se obtiene correctamente.
     */
    @Test
    public void testObtenerRol_DeberiaDevolverRolCorrecto() {
        modelo.registrarUsuario(correoPrueba, contrasenaPrueba, rolPrueba);
        String rol = modelo.obtenerRol(correoPrueba);
        assertEquals(rolPrueba, rol, "El rol devuelto debe coincidir con el del usuario registrado");
    }

    /**
     * Verifica que el login funciona correctamente con credenciales válidas.
     */
    @Test
    public void testComprobarLogin_DeberiaAceptarCredencialesCorrectas() {
        modelo.registrarUsuario(correoPrueba, contrasenaPrueba, rolPrueba);
        boolean loginCorrecto = modelo.comprobarLogin(correoPrueba, contrasenaPrueba);
        assertTrue(loginCorrecto, "El login debe ser exitoso con credenciales válidas");
    }

    /**
     * Verifica que un usuario puede ser modificado correctamente.
     */
    @Test
    public void testModificarUsuario_DeberiaActualizarContraseñaYRol() {
        modelo.registrarUsuario(correoPrueba, contrasenaPrueba, rolPrueba);

        String nuevaPwd = "nuevaClave123";
        String nuevoRol = "admin";

        boolean modificado = modelo.modificarUsuario(correoPrueba, nuevaPwd, nuevoRol);
        assertTrue(modificado, "El usuario debería actualizarse correctamente");

        String rolActual = modelo.obtenerRol(correoPrueba);
        assertEquals(nuevoRol, rolActual, "El rol actualizado debe coincidir");
    }

    /**
     * Verifica que el método obtenerUsuarios no lanza excepciones.
     */
    @Test
    public void testObtenerUsuarios_NoDebeLanzarExcepcion() {
        assertDoesNotThrow(() -> {
            modelo.obtenerUsuarios();
        }, "El método obtenerUsuarios no debe lanzar excepción");
    }
}
