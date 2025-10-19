
package modelo;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import vista.FiltroIncidencia;
import vista.Vista;

/**
 * La clase Modelo se encarga de gestionar la conexión con la base de datos y
 * proporciona métodos para registrar usuarios, iniciar sesión, cambiar
 * contraseñas y gestionar incidencias.
 * 
 * Este modelo sigue el patrón MVC y se conecta a una base de datos MySQL.
 * 
 * @author Alexandru Daniel Costea
 */
public class Modelo {
	private Vista[] misVistas;
	private Connection conexion;
	private String hostname;
	private String port;
	private String usuario;
	private String contrasena;
	private String baseDeDatos;
	private String correoUsuarioActual;

	private List<Object[]> favoritos = new ArrayList<>();
	private List<Object[]> notificaciones = new ArrayList<>();

	/**
	 * Obtiene el correo del usuario actual.
	 * 
	 * @return Correo del usuario actual.
	 */
	public String getCorreoUsuarioActual() {
		return correoUsuarioActual;
	}

	/**
	 * Establece el correo del usuario actual.
	 * 
	 * @param correo Correo a asignar al usuario actual.
	 */
	public void setCorreoUsuarioActual(String correo) {
		this.correoUsuarioActual = correo;
	}

	/**
	 * Añade una incidencia a la lista de favoritos si no existe ya.
	 * 
	 * @param incidencia Incidencia a añadir.
	 * @return true si se añadió, false si ya estaba.
	 */
	public boolean añadirFavorito(Object[] incidencia) {
		if (!favoritos.contains(incidencia)) {
			favoritos.add(incidencia);
			return true;
		}
		return false;
	}

	/**
	 * Añade una incidencia a la lista de notificaciones si no existe ya.
	 * 
	 * @param incidencia Incidencia a añadir.
	 * @return true si se añadió, false si ya estaba.
	 */
	public boolean añadirNotificacion(Object[] incidencia) {
		if (!notificaciones.contains(incidencia)) {
			notificaciones.add(incidencia);
			return true;
		}
		return false;
	}

	/**
	 * Obtiene la lista de incidencias favoritas.
	 * 
	 * @return Lista de favoritos.
	 */
	public List<Object[]> getFavoritos() {
		return favoritos;
	}

	/**
	 * Obtiene la lista de incidencias en notificaciones.
	 * 
	 * @return Lista de notificaciones.
	 */
	public List<Object[]> getNotificaciones() {
		return notificaciones;
	}

	/**
	 * Constructor de la clase Modelo. Intenta establecer la conexión con la base de
	 * datos.
	 */
	public Modelo() {
	}

	/**
	 * Establece la conexión con la base de datos.
	 *
	 * @return true si la conexión fue exitosa, false en caso contrario.
	 */
	public boolean conectarBD() {
		if (!leerConfiguracionBD()) {
			System.err.println("No se pudo leer el archivo de configuración.");
			return false;
		}

		try {
			String url = "jdbc:mysql://" + hostname + ":" + port + "/" + baseDeDatos + "?useSSL=false";
			conexion = DriverManager.getConnection(url, usuario, contrasena);
			System.out.println("Conexión establecida correctamente.");
			return true;
		} catch (SQLException e) {
			System.err.println("Error conectando a la base de datos:");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Lee la configuración de la base de datos desde un archivo.
	 *
	 * @return true si la configuración fue leída correctamente, false en caso
	 *         contrario.
	 */
	private boolean leerConfiguracionBD() {
		Properties props = new Properties();
		File archivo = new File("src/config.ini");

		if (!archivo.exists()) {
			System.err.println("El archivo config.ini no existe.");
			return false;
		}

		try (FileReader reader = new FileReader(archivo)) {
			props.load(reader);

			usuario = props.getProperty("usr");
			contrasena = props.getProperty("pwd");

			String urlCompleta = props.getProperty("url");
			if (urlCompleta != null && urlCompleta.startsWith("jdbc:mysql://")) {
				String sinPrefijo = urlCompleta.substring("jdbc:mysql://".length());
				String[] partesUrl = sinPrefijo.split("/", 2);
				if (partesUrl.length == 2) {
					String hostPort = partesUrl[0];
					baseDeDatos = partesUrl[1].split("\\?")[0];
					if (hostPort.contains(":")) {
						String[] hp = hostPort.split(":");
						hostname = hp[0];
						port = hp[1];
					} else {
						hostname = hostPort;
						port = "3306";
					}
				}
			}

			return usuario != null && contrasena != null && hostname != null && port != null && baseDeDatos != null;
		} catch (IOException e) {
			System.err.println("Error leyendo el archivo config.ini:");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Prueba la conexión a la base de datos ejecutando una consulta simple.
	 */
	public void probarConexion() {
		try {
			String query = "SELECT usuario_correo FROM incidencias LIMIT 1";
			PreparedStatement stmt = conexion.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				System.out.println("Conexión exitosa, primer usuario: " + rs.getString("usuario_correo"));
			} else {
				System.out.println("No se encontraron incidencias.");
			}
		} catch (SQLException e) {
			System.err.println("Error probando la conexión a la base de datos:");
			e.printStackTrace();
		}
	}

	/**
	 * Verifica si las credenciales proporcionadas coinciden con algún usuario en la
	 * base de datos.
	 * 
	 * @param usuario    Nombre de usuario a verificar.
	 * @param contrasena Contraseña correspondiente al usuario.
	 * @return true si el usuario y la contraseña coinciden; false en caso
	 *         contrario.
	 */
	public boolean comprobarLogin(String correo, String contrasena) {
		if (!conectarBD()) {
			return false;
		}
		String query = "SELECT pwd FROM users WHERE usr = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, correo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String pwdBD = rs.getString("pwd");
				String pwdIngresadaHash = hashPassword(contrasena);
				if (pwdBD.equals(pwdIngresadaHash)) {
					this.correoUsuarioActual = correo; // Guardamos el correo del usuario logueado
					return true;
				}
			}
		} catch (SQLException e) {
			System.err.println("Error en comprobarLogin:");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Obtiene el rol de un usuario a partir de su correo electrónico.
	 *
	 * @param correo Correo electrónico del usuario.
	 * @return El rol del usuario o null si no se encuentra.
	 */
	public String obtenerRol(String correo) {
		String query = "SELECT rol FROM users WHERE usr = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, correo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("rol");
			}
		} catch (SQLException e) {
			System.err.println("Error en obtenerRol:");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Inserta una nueva incidencia en la base de datos con información detallada
	 * sobre el campus, ubicación, descripción, usuario y estado inicial.
	 * 
	 * @param campus        Nombre del campus donde ocurre la incidencia.
	 * @param edificio      Nombre o número del edificio afectado.
	 * @param piso          Piso o planta donde se encuentra el aula.
	 * @param aula          Aula específica donde se ha detectado la incidencia.
	 * @param descripcion   Descripción del problema o incidencia detectada.
	 * @param correo        Correo electrónico del usuario que reporta la
	 *                      incidencia.
	 * @param usuarioCorreo Nombre de usuario que reporta la incidencia (se puede
	 *                      usar para seguimiento interno).
	 * @return true si la incidencia se inserta correctamente; false si ocurre un
	 *         error.
	 */
	public boolean insertarIncidencia(String campus, String edificio, String piso, String aula, String descripcion,
			String correo, String usuarioCorreo) {
		try {
			String sql = "INSERT INTO incidencias (campus, edificio, piso, aula, descripcion, usuario_correo, correo, fecha, estado, ranking, resuelto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement ps = conexion.prepareStatement(sql);
			ps.setString(1, campus);
			ps.setString(2, edificio);
			ps.setString(3, piso);
			ps.setString(4, aula);
			ps.setString(5, descripcion);
			ps.setString(6, usuarioCorreo); // usuario_correo
			ps.setString(7, correo); // correo
			ps.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			ps.setString(9, "Pendiente");
			ps.setInt(10, 0);
			ps.setInt(11, 0);

			int filas = ps.executeUpdate();

			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Comprueba si un usuario existe en la base de datos.
	 *
	 * @param correo Correo electrónico del usuario.
	 * @return true si el usuario existe, false en caso contrario.
	 */
	public boolean usuarioExiste(String correo) {
		String query = "SELECT COUNT(*) FROM users WHERE usr = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, correo);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0;
			}
		} catch (SQLException e) {
			System.err.println("Error en usuarioExiste:");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Registra un nuevo usuario en la base de datos.
	 *
	 * @param correo     Correo electrónico del usuario.
	 * @param contrasena Contraseña del usuario.
	 * @param rol        Rol del usuario.
	 * @return true si el registro fue exitoso, false en caso contrario.
	 */
	public boolean registrarUsuario(String correo, String contrasena, String rol) {
		String queryInsertUsers = "INSERT INTO users (usr, pwd, rol) VALUES (?, ?, ?)";
		// Incluye nickname porque es obligatorio
		String queryInsertUsuario = "INSERT INTO usuario (correo, nickname, admin, contraseña) VALUES (?, ?, ?, ?)";
		try {
			conexion.setAutoCommit(false); // Ambas inserciones atómicas

			// Insertar en users
			String pwdHasheada = hashPassword(contrasena);
			try (PreparedStatement stmtUsers = conexion.prepareStatement(queryInsertUsers)) {
				stmtUsers.setString(1, correo);
				stmtUsers.setString(2, pwdHasheada);
				stmtUsers.setString(3, rol);
				stmtUsers.executeUpdate();
			}

			// Insertar en usuario con nickname igual a correo
			try (PreparedStatement stmtUsuario = conexion.prepareStatement(queryInsertUsuario)) {
				stmtUsuario.setString(1, correo);
				stmtUsuario.setString(2, correo); // nickname
				stmtUsuario.setInt(3, 0); // admin como usuario normal
				stmtUsuario.setString(4, pwdHasheada); // contraseña
				stmtUsuario.executeUpdate();
			}

			conexion.commit();
			conexion.setAutoCommit(true);
			return true;

		} catch (SQLException e) {
			try {
				conexion.rollback();
				conexion.setAutoCommit(true);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			System.err.println("Error insertando nuevo usuario:");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Cambia la contraseña de un usuario.
	 *
	 * @param correo   Correo del usuario.
	 * @param nuevaPwd Nueva contraseña sin hash.
	 * @return true si se actualizó correctamente, false si hubo error.
	 */
	public boolean cambiarContrasena(String correo, String nuevaPwd) {
		String query = "UPDATE users SET pwd = ? WHERE usr = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			String nuevaPwdHasheada = hashPassword(nuevaPwd);
			stmt.setString(1, nuevaPwdHasheada);
			stmt.setString(2, correo);
			int filasAfectadas = stmt.executeUpdate();
			return filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error cambiando la contraseña:");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Genera un hash SHA-256 para una contraseña.
	 *
	 * @param password Contraseña a hashear.
	 * @return El hash de la contraseña.
	 */
	private String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error al hashear la contraseña", e);
		}
	}

	/**
	 * Cierra la conexión con la base de datos.
	 */
	public void cerrarConexion() {
		try {
			if (conexion != null) {
				conexion.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene las incidencias para mostrarlas en una tabla.
	 *
	 * @return Una lista de objetos que representan las incidencias.
	 */
	public List<Object[]> obtenerIncidenciasParaTabla() {
		List<Object[]> lista = new ArrayList<>();
		String query = "SELECT usuario_correo, descripcion, fecha, ranking, estado, aula, piso, edificio, campus FROM incidencias";

		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Object[] fila = new Object[9];
				fila[0] = rs.getString("usuario_correo");
				fila[1] = rs.getString("descripcion");
				fila[2] = rs.getDate("fecha");
				fila[3] = rs.getInt("ranking");
				fila[4] = rs.getString("estado");
				fila[5] = rs.getString("aula");
				fila[6] = rs.getInt("piso");
				fila[7] = rs.getString("edificio");
				fila[8] = rs.getString("campus");
				lista.add(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Obtiene las incidencias más recientes.
	 *
	 * @return Una lista de objetos que representan las incidencias recientes.
	 */
	public List<Object[]> obtenerIncidenciasRecientes() {
		List<Object[]> incidencias = new ArrayList<>();
		String query = "SELECT usuario_correo, descripcion, fecha, ranking, estado, aula, piso, edificio, campus "
				+ "FROM incidencias ORDER BY fecha DESC LIMIT 2";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				incidencias.add(new Object[] { rs.getString("usuario_correo"), rs.getString("descripcion"),
						rs.getDate("fecha"), rs.getInt("ranking"), rs.getString("estado"), rs.getString("aula"),
						rs.getInt("piso"), rs.getString("edificio"), rs.getString("campus") });
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return incidencias;
	}

	/**
	 * Obtiene una única incidencia.
	 *
	 * @return Un arreglo de objetos que representa una incidencia o null si no se
	 *         encuentra.
	 */
	public List<Object[]> obtenerIncidenciasUsuarioActual() {
		List<Object[]> lista = new ArrayList<>();
		String correoActual = getCorreoUsuarioActual();

		String query = "SELECT usuario_correo, descripcion, fecha, ranking, estado, aula, piso, edificio, campus "
				+ "FROM incidencias WHERE usuario_correo = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, correoActual);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Object[] fila = new Object[] { rs.getString("usuario_correo"), rs.getString("descripcion"),
						rs.getTimestamp("fecha"), // Cambiado a getTimestamp
						rs.getInt("ranking"), rs.getString("estado"), rs.getString("aula"), rs.getInt("piso"),
						rs.getString("edificio"), rs.getString("campus") };
				lista.add(fila);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	/**
	 * Elimina un usuario de la base de datos.
	 *
	 * @param correo Correo electrónico del usuario.
	 * @return true si la eliminación fue exitosa, false en caso contrario.
	 */
	public boolean eliminarUsuario(String correo) {
		String query = "DELETE FROM users WHERE usr = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, correo);
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			System.err.println("Error eliminando usuario:");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtiene el nombre del usuario dado su correo.
	 * 
	 * @param correo Correo del usuario.
	 * @return Nombre del usuario (en este caso, devuelve el correo).
	 */
	public String obtenerNombreUsuario(String correo) {
		return correo;
	}

	/**
	 * Obtiene todos los usuarios de la base de datos.
	 *
	 * @return Una matriz de objetos que representa los usuarios.
	 * @throws SQLException Si ocurre un error al consultar la base de datos.
	 */
	public Object[][] obtenerUsuarios() throws SQLException {
		String sql = "SELECT USR, PWD, ROL FROM USERS";
		List<Object[]> listaUsuarios = new ArrayList<>();

		try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String usr = rs.getString("USR");
				String pwd = rs.getString("PWD");
				String rol = rs.getString("ROL");
				listaUsuarios.add(new Object[] { usr, pwd, rol });
			}
		}

		Object[][] datos = new Object[listaUsuarios.size()][3];
		for (int i = 0; i < listaUsuarios.size(); i++) {
			datos[i] = listaUsuarios.get(i);
		}
		return datos;
	}

	/**
	 * Recupera todos los datos de la tabla de ayuda, incluyendo los títulos y
	 * contenidos, y los devuelve como una matriz bidimensional de objetos para ser
	 * usados, por ejemplo, en una tabla.
	 * 
	 * @return Una matriz de objetos Object[][] donde cada fila contiene el título y
	 *         contenido de una entrada de ayuda.
	 * @throws SQLException Si ocurre un error al acceder a la base de datos.
	 */
	public Object[][] obtenerDatosAyuda() throws SQLException {
		String sql = "SELECT titulo, contenido FROM ayuda";
		PreparedStatement ps = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = ps.executeQuery();

		// Mover el cursor al final para contar filas
		rs.last();
		int totalFilas = rs.getRow();
		rs.beforeFirst(); // Volver al inicio

		Object[][] datos = new Object[totalFilas][2];
		int fila = 0;

		while (rs.next()) {
			datos[fila][0] = rs.getString("titulo");
			datos[fila][1] = rs.getString("contenido");
			fila++;
		}

		rs.close();
		ps.close();
		return datos;
	}

	/**
	 * Inserta un nuevo usuario en la base de datos.
	 *
	 * @param correo     Correo electrónico del usuario.
	 * @param contrasena Contraseña del usuario.
	 * @param rol        Rol del usuario.
	 * @return true si la inserción fue exitosa, false en caso contrario.
	 */
	public boolean insertarUsuario(String correo, String contrasena, String rol) {
		if (usuarioExiste(correo))
			return false;

		String query = "INSERT INTO users (usr, pwd, rol) VALUES (?, ?, ?)";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			String pwdHasheada = hashPassword(contrasena);
			stmt.setString(1, correo);
			stmt.setString(2, pwdHasheada);
			stmt.setString(3, rol);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Error insertando usuario:");
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtiene una lista de incidencias filtradas según los parámetros
	 * especificados. También puede devolver listas predefinidas si se indica
	 * "Favoritos" o "Notificaciones".
	 * 
	 * @param campus   El campus a filtrar (opcional).
	 * @param edificio El edificio a filtrar (opcional).
	 * @param piso     El piso a filtrar (opcional).
	 * @param aula     El aula a filtrar (opcional).
	 * @param estado   El estado de la incidencia a filtrar (opcional).
	 * @param varios   Si contiene "Favoritos" o "Notificaciones", devuelve esas
	 *                 listas directamente.
	 * @return Una lista de arrays de objetos, donde cada array representa una fila
	 *         con los datos de una incidencia.
	 */
	public List<Object[]> obtenerIncidenciasFiltradas(String campus, String edificio, String piso, String aula,
			String estado, String varios) {

		// Casos especiales
		if ("Favoritos".equalsIgnoreCase(varios)) {
			return favoritos;
		} else if ("Notificacion".equalsIgnoreCase(varios)) {
			return notificaciones;
		}

		List<Object[]> lista = new ArrayList<>();
		StringBuilder sql = new StringBuilder(
				"SELECT usuario_correo, descripcion, fecha, ranking, estado, aula, piso, edificio, campus FROM incidencias WHERE 1=1");

		List<Object> parametros = new ArrayList<>();

		if (campus != null && !campus.isEmpty() && !campus.equalsIgnoreCase("Campus")) {
			sql.append(" AND campus = ?");
			parametros.add(campus);
		}
		if (edificio != null && !edificio.isEmpty() && !edificio.equalsIgnoreCase("Edificio")) {
			sql.append(" AND edificio = ?");
			parametros.add(edificio);
		}
		if (piso != null && !piso.isEmpty() && !piso.equalsIgnoreCase("Piso")) {
			sql.append(" AND piso = ?");
			parametros.add(piso);
		}
		if (aula != null && !aula.isEmpty() && !aula.equalsIgnoreCase("Aula")) {
			sql.append(" AND aula = ?");
			parametros.add(aula);
		}
		if (estado != null && !estado.isEmpty() && !estado.equalsIgnoreCase("Estado")) {
			sql.append(" AND estado = ?");
			parametros.add(estado);
		}

		try (PreparedStatement stmt = conexion.prepareStatement(sql.toString())) {
			for (int i = 0; i < parametros.size(); i++) {
				stmt.setObject(i + 1, parametros.get(i));
			}

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Object[] fila = new Object[] { rs.getString("usuario_correo"), rs.getString("descripcion"),
						rs.getDate("fecha"), rs.getInt("ranking"), rs.getString("estado"), rs.getString("aula"),
						rs.getInt("piso"), rs.getString("edificio"), rs.getString("campus") };
				lista.add(fila);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return lista;
	}

	/**
	 * Modifica un usuario existente en la base de datos.
	 *
	 * @param correo   Correo electrónico del usuario.
	 * @param nuevaPwd Nueva contraseña del usuario.
	 * @param nuevoRol Nuevo rol del usuario.
	 * @return true si la modificación fue exitosa, false en caso contrario.
	 */
	public boolean modificarUsuario(String correo, String nuevaPwd, String nuevoRol) {
		String query = "UPDATE users SET pwd = ?, rol = ? WHERE usr = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(query)) {
			stmt.setString(1, hashPassword(nuevaPwd));
			stmt.setString(2, nuevoRol);
			stmt.setString(3, correo);
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			System.err.println("Error modificando usuario:");
			e.printStackTrace();
			return false;
		}
	}

	public boolean marcarIncidenciaComoResuelta(String correoUsuario, String estadoActual) {
		String sql = "UPDATE incidencias SET estado = 'Resuelto' WHERE usuario_correo = ? AND estado = ?";
		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, correoUsuario);
			stmt.setString(2, estadoActual);
			int filas = stmt.executeUpdate();
			return filas > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean borrarIncidencia(String usuarioCorreo, java.sql.Timestamp fecha) {
		String sql = "DELETE FROM incidencias WHERE usuario_correo = ? AND fecha = ?";

		try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
			stmt.setString(1, usuarioCorreo);
			stmt.setTimestamp(2, fecha);

			int filas = stmt.executeUpdate();

			// Verificar que realmente se borró
			if (filas > 0) {
				// Confirmar con una consulta adicional
				String verifySql = "SELECT COUNT(*) FROM incidencias WHERE usuario_correo = ? AND fecha = ?";
				try (PreparedStatement verifyStmt = conexion.prepareStatement(verifySql)) {
					verifyStmt.setString(1, usuarioCorreo);
					verifyStmt.setTimestamp(2, fecha);
					ResultSet rs = verifyStmt.executeQuery();
					rs.next();
					return rs.getInt(1) == 0; // True si ya no existe
				}
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Establece las vistas que serán gestionadas por el modelo.
	 *
	 * @param misVistas Arreglo de vistas.
	 */
	public void setVistas(Vista[] misVistas) {
		this.misVistas = misVistas;
	}

}
