package controlador;

import modelo.Modelo;
import vista.*;

/**
 * Clase principal que inicia la aplicación UniFix. Crea las instancias del
 * modelo, las vistas y el controlador, y configura sus relaciones.
 */
public class Main {

	/**
	 * Método principal de la aplicación.
	 * 
	 * @param args Argumentos de línea de comandos (no utilizados)
	 */
	public static void main(String[] args) {
		// Crear instancia del modelo y conectar a la base de datos
		Modelo miModelo = new Modelo();
		miModelo.conectarBD();

		// Crear instancia del controlador
		Controlador miControlador = new Controlador();

		// Crear arreglo de vistas
		Vista[] misVistas = new Vista[16];
		misVistas[0] = new _00_PaginaPrincipalNoLog();
		misVistas[1] = new _01_Login();
		misVistas[2] = new _02_Registro();
		misVistas[3] = new _03_PreguntasSeguridad();
		misVistas[4] = new _04_NuevaContrasena();
		misVistas[5] = new _05_PerfilUsuario();
		misVistas[6] = new _06_Ayuda();
		misVistas[7] = new _07_PaginaPrincipal();
		misVistas[8] = new _08AdminIncidencia();
		misVistas[9] = new _09AdminEstadisticas();
		misVistas[10] = new _10_IncidenciaIndividual();
		misVistas[11] = new _11_ReportarIncidencia();
		misVistas[12] = new _12_Notificaciones();
		misVistas[13] = new _13_MisIncidencias();
		misVistas[14] = new _14_VentanaUsuarios();
		misVistas[15] = new _15_AyudaNoLog();

		// Asignar las vistas al modelo
		miModelo.setVistas(misVistas);

		// Asignar vistas y modelo al controlador
		miControlador.setVista(misVistas);
		miControlador.setModelo(miModelo);

		// Cargar datos iniciales desde la base de datos
		miControlador.cargarIncidencias();
		miControlador.cargarMisIncidencias();
		miControlador.cargarIncidenciasRecientes();
		miControlador.cargarUsuarios();

		// Configurar el modelo y controlador en todas las vistas
		for (Vista vista : misVistas) {
			vista.setModelo(miModelo);
			vista.setControlador(miControlador);
		}

		// Mostrar la primera vista (pantalla inicial sin login)
		((javax.swing.JFrame) misVistas[0]).setVisible(true);
	}
}