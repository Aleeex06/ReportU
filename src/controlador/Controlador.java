//@Autor: Alexandru Daniel Costea
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Modelo;
import vista.*;

/**
 * Clase Controlador que maneja la lógica entre la Vista y el Modelo. Controla
 * las acciones del usuario y coordina los cambios en la vista y el modelo.
 */
public class Controlador implements ActionListener {
	private Modelo miModelo;
	private Vista[] misVistas;
	private String usuarioLogueado;

	/**
	 * Establece las vistas usadas por el controlador.
	 * 
	 * @param misVistas Array de objetos Vista.
	 */
	public void setVista(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

	/**
	 * Establece el usuario que ha iniciado sesión.
	 * 
	 * @param email Correo electrónico del usuario logueado.
	 */
	public void setUsuarioLogueado(String email) {
		this.usuarioLogueado = email;
	}

	/**
	 * Devuelve el correo del usuario logueado.
	 * 
	 * @return Correo electrónico.
	 */
	public String getUsuarioLogueado() {
		return this.usuarioLogueado;
	}

	/**
	 * Establece el modelo que usará el controlador.
	 * 
	 * @param miModelo Objeto Modelo.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	/**
	 * Cambia de una ventana a otra dentro del array de vistas.
	 * 
	 * @param desde Índice de la vista actual.
	 * @param hasta Índice de la vista destino.
	 */
	public void cambiarVentana(int desde, int hasta) {
		if (misVistas[desde] instanceof JFrame && misVistas[hasta] instanceof JFrame) {
			((JFrame) misVistas[desde]).setVisible(false);
			((JFrame) misVistas[hasta]).setVisible(true);
		}
	}

	/**
	 * Intenta iniciar sesión con las credenciales proporcionadas.
	 * 
	 * @param correo     Correo electrónico del usuario.
	 * @param contrasena Contraseña del usuario.
	 */
	public void loginUsuario(String correo, String contrasena) {
		System.out.println("Intentando login con: " + correo + " / " + contrasena);
		if (miModelo.comprobarLogin(correo, contrasena)) {
			this.usuarioLogueado = correo;
			miModelo.setCorreoUsuarioActual(correo); // Asegurarse de que el modelo tenga el correo

			String rol = miModelo.obtenerRol(correo);
			if (rol == null) {
				JOptionPane.showMessageDialog(null, "No se ha podido recuperar el rol del usuario.");
				return;
			}
			String nombreUsuario = miModelo.obtenerNombreUsuario(correo);
			actualizarNombreUsuario(nombreUsuario);

			// Configurar la vista principal según el rol
			_07_PaginaPrincipal vistaPrincipal = (_07_PaginaPrincipal) misVistas[7];
			vistaPrincipal.configurarBotonAdmin("Admin".equals(rol));
			if (rol.equals("Admin")) {
				cambiarVentana(1, 8);
				cargarUsuarios();
			} else {
				cambiarVentana(1, 7);
				cargarIncidencias();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos.");
		}
	}

	/**
	 * Registra un nuevo usuario a través del formulario de registro.
	 */
	public void registrarUsuario() {
		vista._02_Registro registroVista = (vista._02_Registro) misVistas[2];
		String correo = registroVista.getCorreo();
		String contrasena = registroVista.getContrasena();
		String rol = registroVista.getRol();
		String repiteContrasena = registroVista.getRepiteContrasena();

		if (!contrasena.equals(repiteContrasena)) {
			registroVista.mostrarMensaje("Las contraseñas no coinciden.");
			return;
		}

		if (miModelo.usuarioExiste(correo)) {
			registroVista.mostrarMensaje("El usuario ya existe.");
		} else {
			boolean registrado = miModelo.registrarUsuario(correo, contrasena, rol);
			if (registrado) {
				registroVista.mostrarMensaje("Usuario registrado correctamente.");
				cambiarVentana(2, 1);
			} else {
				registroVista.mostrarMensaje("Error al registrar el usuario.");
			}
		}
	}

	/**
	 * Carga todas las incidencias en la vista principal.
	 */
	public void cargarIncidencias() {
		_07_PaginaPrincipal vista7 = (_07_PaginaPrincipal) misVistas[7];
		_08AdminIncidencia vista8 = (_08AdminIncidencia) misVistas[8];

		List<Object[]> listaIncidencias = miModelo.obtenerIncidenciasParaTabla();

		vista7.actualizarTablaIncidencias(listaIncidencias);
		vista8.actualizarTablaIncidencias(listaIncidencias);
	}

	/**
	 * Carga las incidencias filtradas según los parámetros del filtro.
	 * 
	 * @param filtro Objeto FiltroIncidencia con los criterios de búsqueda.
	 */
	public void cargarIncidenciasFiltradas(FiltroIncidencia filtro) {
		_07_PaginaPrincipal vista7 = (_07_PaginaPrincipal) misVistas[7];
		_08AdminIncidencia vista8 = (_08AdminIncidencia) misVistas[8];

		List<Object[]> listaIncidenciasFiltradas = miModelo.obtenerIncidenciasFiltradas(filtro.getCampus(),
				filtro.getEdificio(), filtro.getPiso(), filtro.getAula(), filtro.getEstado(), filtro.getVarios());

		System.out.println("Cantidad de incidencias filtradas: " + listaIncidenciasFiltradas.size());

		vista7.actualizarTablaIncidencias(listaIncidenciasFiltradas);
		vista8.actualizarTablaIncidencias(listaIncidenciasFiltradas);
	}

	/**
	 * Añade la incidencia seleccionada a favoritos.
	 */
	public void añadirAFavoritosDesdeVista() {
		_07_PaginaPrincipal vistaPrincipal = (_07_PaginaPrincipal) misVistas[7];
		Object[] incidencia = vistaPrincipal.getIncidenciaSeleccionada();
		if (incidencia == null) {
			JOptionPane.showMessageDialog(null, "Selecciona una incidencia primero.");
			return;
		}
		if (miModelo.añadirFavorito(incidencia)) {
			JOptionPane.showMessageDialog(null, "Incidencia añadida a Favoritos.");
		} else {
			JOptionPane.showMessageDialog(null, "La incidencia ya está en Favoritos.");
		}
	}

	/**
	 * Añade la incidencia seleccionada a notificaciones.
	 */
	public void añadirANotificacionesDesdeVista() {
		_07_PaginaPrincipal vistaPrincipal = (_07_PaginaPrincipal) misVistas[7];
		Object[] incidencia = vistaPrincipal.getIncidenciaSeleccionada();

		if (incidencia == null) {
			JOptionPane.showMessageDialog(null, "Selecciona una incidencia primero.");
			return;
		}

		boolean exito = miModelo.añadirNotificacion(incidencia);

		if (exito) {
			JOptionPane.showMessageDialog(null, "Incidencia añadida a Notificaciones.");
		} else {
			JOptionPane.showMessageDialog(null, "La incidencia ya está en Notificaciones o hubo un error.");
		}
	}

	/**
	 * Carga las incidencias recientes en la vista principal sin login.
	 */
	public void cargarIncidenciasRecientes() {
		_00_PaginaPrincipalNoLog vistaIncidencias = (_00_PaginaPrincipalNoLog) misVistas[0];
		List<Object[]> listaIncidencias = miModelo.obtenerIncidenciasRecientes();
		vistaIncidencias.actualizarTablaIncidencias(listaIncidencias);
	}

	/**
	 * Carga las incidencias del usuario actualmente logueado.
	 */
	public void cargarMisIncidencias() {
		_13_MisIncidencias vistaIncidencias = (_13_MisIncidencias) misVistas[13];
		List<Object[]> listaIncidencias = miModelo.obtenerIncidenciasUsuarioActual();
		if (listaIncidencias != null && !listaIncidencias.isEmpty()) {
			vistaIncidencias.actualizarTablaIncidencias(listaIncidencias);
		}
	}

	/**
	 * Carga todos los usuarios en la vista de administración.
	 */
	public void cargarUsuarios() {
		_14_VentanaUsuarios vistaUsuarios = (_14_VentanaUsuarios) misVistas[14];
		try {
			Object[][] datos = miModelo.obtenerUsuarios();
			vistaUsuarios.cargarUsuarios(datos);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + e.getMessage());
		}
	}

	/**
	 * Manejador de eventos para los botones de la vista de usuarios.
	 * 
	 * @param e Evento de acción generado por un botón.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_14_VentanaUsuarios vistaUsuarios = (_14_VentanaUsuarios) misVistas[14];
		String comando = e.getActionCommand();

		switch (comando) {
		case "Alta":
			altaUsuario(vistaUsuarios);
			break;
		case "Baja":
			bajaUsuario(vistaUsuarios);
			break;
		case "Modificación":
			modificarUsuario(vistaUsuarios);
			break;
		}
	}

	// Métodos privados auxiliares
	private void altaUsuario(_14_VentanaUsuarios vista) {
		String usr = vista.getNombreUsuario();
		String pwd = vista.getContrasena();
		String rol = vista.getRolSeleccionado();

		if (usr.isEmpty() || pwd.isEmpty() || rol.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Completa todos los campos para dar de alta.");
			return;
		}
		if (miModelo.usuarioExiste(usr)) {
			JOptionPane.showMessageDialog(null, "El usuario ya existe.");
			return;
		}
		boolean exito = miModelo.insertarUsuario(usr, pwd, rol);
		if (exito) {
			JOptionPane.showMessageDialog(null, "Usuario dado de alta correctamente.");
			cargarUsuarios();
			vista.limpiarCampos();
		} else {
			JOptionPane.showMessageDialog(null, "Error al dar de alta el usuario.");
		}
	}

	/**
	 * Elimina el usuario seleccionado tras confirmar la acción. Actualiza la lista
	 * de usuarios y limpia los campos de la vista.
	 * 
	 * @param vista Vista de administración de usuarios.
	 */
	private void bajaUsuario(_14_VentanaUsuarios vista) {
		String usr = vista.getNombreUsuario();

		if (usr.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Selecciona un usuario para dar de baja.");
			return;
		}

		int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar el usuario " + usr + "?",
				"Confirmar baja", JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			boolean exito = miModelo.eliminarUsuario(usr);
			if (exito) {
				JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente.");
				cargarUsuarios();
				vista.limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.");
			}
		}
	}

	/**
	 * Modifica los datos del usuario seleccionado y actualiza la lista.
	 * 
	 * @param vista Vista de administración de usuarios.
	 */
	private void modificarUsuario(_14_VentanaUsuarios vista) {
		String usrNuevo = vista.getNombreUsuario();
		String pwdNuevo = vista.getContrasena();
		String rolNuevo = vista.getRolSeleccionado();
		String usrAnterior = vista.getUsuarioAnterior();

		if (usrNuevo.isEmpty() || rolNuevo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Completa los campos obligatorios para modificar.");
			return;
		}

		if (usrAnterior.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Selecciona un usuario para modificar.");
			return;
		}

		boolean exito = miModelo.modificarUsuario(usrNuevo, pwdNuevo, rolNuevo);
		if (exito) {
			JOptionPane.showMessageDialog(null, "Usuario modificado correctamente.");
			cargarUsuarios();
			vista.limpiarCampos();
		} else {
			JOptionPane.showMessageDialog(null, "Error al modificar el usuario.");
		}
	}

	/**
	 * Actualiza el nombre del usuario logueado en todas las vistas que lo muestran.
	 * 
	 * @param nombreUsuario Nombre completo del usuario.
	 */
	public void actualizarNombreUsuario(String nombreUsuario) {
		// Actualizar todas las vistas que muestran el nombre de usuario
		((_07_PaginaPrincipal) misVistas[7]).setNombreUsuarioEnBoton(nombreUsuario);
		((_05_PerfilUsuario) misVistas[5]).setNombreUsuarioEnBoton(nombreUsuario);
		((_06_Ayuda) misVistas[6]).setNombreUsuarioEnBoton(nombreUsuario);
		((_08AdminIncidencia) misVistas[8]).setNombreUsuarioEnBoton(nombreUsuario);
		((_09AdminEstadisticas) misVistas[9]).setNombreUsuarioEnBoton(nombreUsuario);
		((_10_IncidenciaIndividual) misVistas[10]).setNombreUsuarioEnBoton(nombreUsuario);
		((_11_ReportarIncidencia) misVistas[11]).setNombreUsuarioEnBoton(nombreUsuario);
		((_12_Notificaciones) misVistas[12]).setNombreUsuarioEnBoton(nombreUsuario);
		((_13_MisIncidencias) misVistas[13]).setNombreUsuarioEnBoton(nombreUsuario);

		// Actualizar el estado del botón de admin según el rol
		String rol = miModelo.obtenerRol(usuarioLogueado);
		((_07_PaginaPrincipal) misVistas[7]).configurarBotonAdmin("Admin".equals(rol));
	}

	/**
	 * Muestra los detalles de una incidencia específica en la vista individual.
	 * 
	 * @param datosIncidencia Array de datos de la incidencia seleccionada.
	 */
	public void mostrarIncidenciaIndividual(Object[] datosIncidencia) {
		_10_IncidenciaIndividual vista = (_10_IncidenciaIndividual) misVistas[10];
		vista.mostrarDatosIncidencia(datosIncidencia);
	}

	/**
	 * Marca una incidencia como resuelta.
	 * 
	 * @param correoUsuario Correo del usuario dueño de la incidencia.
	 * @param estadoActual  Estado actual de la incidencia.
	 * @return true si se marcó correctamente, false en caso contrario.
	 */
	public boolean marcarIncidenciaComoResuelta(String correoUsuario, String estadoActual) {
		return miModelo.marcarIncidenciaComoResuelta(correoUsuario, estadoActual);
	}

	/**
	 * Elimina una incidencia específica y actualiza la vista.
	 * 
	 * @param usuarioCorreo Correo del usuario.
	 * @param fecha         Fecha/timestamp de la incidencia.
	 * @return true si se eliminó correctamente, false en caso contrario.
	 */
	public boolean borrarIncidencia(String usuarioCorreo, java.sql.Timestamp fecha) {
		boolean resultado = miModelo.borrarIncidencia(usuarioCorreo, fecha);
		if (resultado) {
			cargarMisIncidencias();
		}
		return resultado;
	}

	/**
	 * Añade una incidencia a favoritos desde la vista individual.
	 * 
	 * @param incidencia Datos de la incidencia.
	 */
	public void añadirAFavoritosDesdeVistaIndividual(Object[] incidencia) {
		if (incidencia == null) {
			JOptionPane.showMessageDialog(null, "No hay datos de incidencia para añadir.");
			return;
		}

		if (miModelo.añadirFavorito(incidencia)) {
			JOptionPane.showMessageDialog(null, "Incidencia añadida a Favoritos.");
		} else {
			JOptionPane.showMessageDialog(null, "La incidencia ya está en Favoritos.");
		}
	}

	/**
	 * Añade una incidencia a notificaciones desde la vista individual.
	 * 
	 * @param incidencia Datos de la incidencia.
	 */
	public void añadirANotificacionesDesdeVistaIndividual(Object[] incidencia) {
		if (incidencia == null) {
			JOptionPane.showMessageDialog(null, "No hay datos de incidencia para añadir.");
			return;
		}

		boolean exito = miModelo.añadirNotificacion(incidencia);

		if (exito) {
			JOptionPane.showMessageDialog(null, "Incidencia añadida a Notificaciones.");
		} else {
			JOptionPane.showMessageDialog(null, "La incidencia ya está en Notificaciones o hubo un error.");
		}
	}

}
