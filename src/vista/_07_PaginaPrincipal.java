
package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Modelo;

/**
 * Clase `_07_PaginaPrincipal` que representa la ventana principal de la
 * aplicación. Contiene elementos de la interfaz gráfica como botones, tablas y
 * filtros.
 * 
 * @author Ivan Garcia Ledesma
 */
public class _07_PaginaPrincipal extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Modelo miModelo;
	private Controlador miControlador;
	private JButton btnVolver;
	private JTable table;
	private JButton btnUsuario;
	private JButton btnVistaAdmin;
	private JComboBox<String> comboBoxCampus;
	private JComboBox<String> comboBoxEdificio;
	private JComboBox<String> comboBoxPiso;
	private JComboBox<String> comboBoxAula;
	private JComboBox<String> comboBoxvariosTextos;
	private JComboBox<String> comboBoxEstado;

	/**
	 * Método principal para lanzar la aplicación.
	 *
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		_07_PaginaPrincipal frame = new _07_PaginaPrincipal();
		frame.setVisible(true);
	}

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes.
	 */
	public _07_PaginaPrincipal() {
		setTitle("Página Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Botón para volver a la ventana anterior
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(10, 10, 100, 21);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(7, 0));

		// Botones de navegación
		JButton btnPaginaPrincipal = new JButton("Página Principal");
		btnPaginaPrincipal.setBackground(Color.LIGHT_GRAY);
		btnPaginaPrincipal.setBounds(10, 54, 169, 28);
		contentPane.add(btnPaginaPrincipal);

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setBounds(224, 54, 169, 28);
		contentPane.add(btnMisIncidencias);

		JButton btnReportarIncidencia = new JButton("Reportar Incidencia");
		btnReportarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnReportarIncidencia.setBounds(424, 54, 169, 28);
		contentPane.add(btnReportarIncidencia);

		// Etiqueta para los filtros
		JLabel lblFiltros = new JLabel("Filtros");
		lblFiltros.setForeground(Color.WHITE);
		lblFiltros.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFiltros.setBounds(29, 129, 70, 39);
		contentPane.add(lblFiltros);

		btnUsuario = new JButton("Usuario");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.setBounds(617, 11, 157, 71);
		contentPane.add(btnUsuario);

		JButton btnVerIncidencia = new JButton("VER");
		btnVerIncidencia.setBackground(Color.LIGHT_GRAY);
		btnVerIncidencia.setForeground(new Color(0, 0, 0));
		btnVerIncidencia.setBounds(591, 431, 96, 28);
		contentPane.add(btnVerIncidencia);

		// Valores visibles en los combos (más amigables)
		String[] campusValores = { "Campus", "Campus 1", "Campus 2", "Campus 3" };

		// Edificio hasta E
		String[] edificioValores = { "Edificio", "Edificio A", "Edificio B", "Edificio C", "Edificio D", "Edificio E" };

		// Piso hasta 6
		String[] pisoValores = { "Piso", "1", "2", "3", "4", "5", "6" };

		// Aula del A1 al C3 (A1, A2, A3, B1, B2, B3, C1, C2, C3)
		String[] aulaValores = { "Aula", "A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3" };

		// Fecha (puedes dejarlo igual o añadir fechas dinámicas)
		// Lo dejo tal cual porque normalmente fecha se selecciona con un DatePicker o
		// similar
		String[] variosTextos = { "Tipo", "Favoritos", "Notificacion" };

		// Estado con las opciones que quieres
		String[] estadoValores = { "Estado", "Abierto", "Resuelto", "En proceso", "Pendiente" };

		// Crear comboboxes con valores "amigables"
		comboBoxCampus = createComboBox(campusValores, 125, 120);
		comboBoxEdificio = createComboBox(edificioValores, 125, 158);
		comboBoxPiso = createComboBox(pisoValores, 239, 120);
		comboBoxAula = createComboBox(aulaValores, 239, 158);
		comboBoxvariosTextos = createComboBox(variosTextos, 348, 120);
		comboBoxEstado = createComboBox(estadoValores, 348, 158);

		contentPane.add(comboBoxCampus);
		contentPane.add(comboBoxEdificio);
		contentPane.add(comboBoxPiso);
		contentPane.add(comboBoxAula);
		contentPane.add(comboBoxvariosTextos);
		contentPane.add(comboBoxEstado);

		// Botón para aplicar filtros
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setBackground(Color.LIGHT_GRAY);
		btnAplicar.setBounds(487, 158, 106, 23);
		contentPane.add(btnAplicar);

		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FiltroIncidencia filtro = new FiltroIncidencia();

				// Mapear valores seleccionados para que coincidan con la BD
				String campusSeleccionado = comboBoxCampus.getSelectedItem().toString();
				switch (campusSeleccionado) {
				case "Campus 1" -> filtro.setCampus("Campus1");
				case "Campus 2" -> filtro.setCampus("Campus2");
				case "Campus 3" -> filtro.setCampus("Campus3");
				default -> filtro.setCampus(""); // o null o cadena vacía para no filtrar
				}

				String edificioSeleccionado = comboBoxEdificio.getSelectedItem().toString();
				switch (edificioSeleccionado) {
				case "Edificio A" -> filtro.setEdificio("Edificio A");
				case "Edificio B" -> filtro.setEdificio("Edificio B");
				case "Edificio C" -> filtro.setEdificio("Edificio C");
				default -> filtro.setEdificio("");
				}

				// Para piso y aula dejamos como están pero sin el texto base
				String pisoSeleccionado = comboBoxPiso.getSelectedItem().toString();
				filtro.setPiso(pisoSeleccionado.equals("Piso") ? "" : pisoSeleccionado);

				String aulaSeleccionada = comboBoxAula.getSelectedItem().toString();
				filtro.setAula(aulaSeleccionada.equals("Aula") ? "" : aulaSeleccionada);

				// Para fecha y estado igual, simplificamos:
				String varios = comboBoxvariosTextos.getSelectedItem().toString();
				filtro.setVarios(varios.equals("Tipo") ? "" : varios);

				String estadoSeleccionado = comboBoxEstado.getSelectedItem().toString();
				filtro.setEstado(estadoSeleccionado.equals("Estado") ? "" : estadoSeleccionado);

				miControlador.cargarIncidenciasFiltradas(filtro);
			}
		});

		// Imagen de estado
		JLabel lblEstadoImg = new JLabel("");
		lblEstadoImg.setIcon(new ImageIcon(
				"C:\\Users\\Usuario\\Downloads\\ProyectoIntegrador1\\ProyectoIntegrador1\\ProyectoIntegrador1\\src\\img\\EstadoVerde.jpg"));
		lblEstadoImg.setBounds(109, 287, 70, 71);
		contentPane.add(lblEstadoImg);

		// Tabla de incidencias
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 214, 658, 202);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(Color.RED));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(new Object[][] {}, // Empieza vacía, luego se rellena con datos reales
				new String[] { "USUARIO", "DESCRIPCIÓN", "FECHA", "LIKES", "ESTADO", "AULA", "PISO", "EDIFICIO",
						"CAMPUS" }));
		scrollPane.setViewportView(table);

		// Botón para dar "like" a una incidencia
		JButton btnLike = new JButton("LIKE");
		btnLike.setBackground(Color.LIGHT_GRAY);
		btnLike.setBounds(29, 431, 100, 28);
		contentPane.add(btnLike);

		// Funcionalidad de "like" por fila seleccionada
		btnLike.addActionListener(e -> {
			int fila = table.getSelectedRow();
			if (fila != -1) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				Object valorActual = modelo.getValueAt(fila, 3); // columna "LIKES"
				try {
					int likes = Integer.parseInt(valorActual.toString());
					likes++;
					modelo.setValueAt(String.valueOf(likes), fila, 3);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(this, "El valor de likes no es válido.");
				}
			} else {
				JOptionPane.showMessageDialog(this, "Selecciona una fila primero.");
			}
		});

		// Botones adicionales
		JButton btnResuelto = new JButton("RESUELTO");
		btnResuelto.setBackground(Color.LIGHT_GRAY);
		btnResuelto.setBounds(137, 431, 138, 28);
		contentPane.add(btnResuelto);
		btnResuelto.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Has marcado la incidencia como resuelta, ¡gracias por tu apoyo!",
					"Incidencia resuelta", JOptionPane.INFORMATION_MESSAGE);
		});

		JButton btnNotificacion = new JButton("NOTIFICACION");
		btnNotificacion.setBackground(Color.LIGHT_GRAY);
		btnNotificacion.setBounds(283, 431, 155, 28);
		contentPane.add(btnNotificacion);
		btnNotificacion.addActionListener(e -> miControlador.añadirANotificacionesDesdeVista());

		JButton btnFavoritos = new JButton("FAVORITOS");
		btnFavoritos.setBackground(Color.LIGHT_GRAY);
		btnFavoritos.setBounds(446, 431, 137, 28);
		contentPane.add(btnFavoritos);
		btnFavoritos.addActionListener(e -> miControlador.añadirAFavoritosDesdeVista());

		// Fondo de la ventana
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_07_PaginaPrincipal.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 946, 659);
		contentPane.add(lblFondo);

		btnVistaAdmin = new JButton("Vista Admin");
		btnVistaAdmin.setBackground(Color.LIGHT_GRAY);
		btnVistaAdmin.setBounds(657, 522, 117, 28);
		btnVistaAdmin.setVisible(false); // Inicialmente invisible
		contentPane.add(btnVistaAdmin);

		// Navegación entre vistas
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(7, 7));
		btnMisIncidencias.addActionListener(e -> {
			miControlador.cargarMisIncidencias(); // Carga las incidencias del usuario actual
			miControlador.cambiarVentana(7, 13); // Cambia a la vista de "Mis incidencias"
		});

		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(7, 11));
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(7, 5));
		btnVerIncidencia.addActionListener(e -> {
			int filaSeleccionada = table.getSelectedRow();
			if (filaSeleccionada != -1) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				Object[] datosFila = new Object[modelo.getColumnCount()];
				for (int i = 0; i < modelo.getColumnCount(); i++) {
					datosFila[i] = modelo.getValueAt(filaSeleccionada, i);
				}
				miControlador.mostrarIncidenciaIndividual(datosFila); // <-- nuevo método que debes crear
				miControlador.cambiarVentana(7, 10);
			} else {
				JOptionPane.showMessageDialog(this, "Por favor selecciona una incidencia.");
			}
		});
	}

	/**
	 * Obtiene los datos completos de la incidencia seleccionada en la tabla.
	 * 
	 * @return un array de objetos con los valores de la fila seleccionada en la
	 *         tabla, o null si no hay ninguna fila seleccionada.
	 */
	public Object[] getIncidenciaSeleccionada() {
		int fila = table.getSelectedRow();
		if (fila != -1) {
			Object[] datos = new Object[table.getColumnCount()];
			for (int i = 0; i < datos.length; i++) {
				datos[i] = table.getValueAt(fila, i);
			}
			return datos;
		}
		return null;
	}

	/**
	 * Actualiza la tabla de incidencias con los datos proporcionados.
	 *
	 * @param datosIncidencias Lista de datos para llenar la tabla.
	 */
	public void actualizarTablaIncidencias(List<Object[]> datosIncidencias) {
		String[] columnas = { "USUARIO", "DESCRIPCIÓN", "FECHA", "LIKES", "ESTADO", "AULA", "PISO", "EDIFICIO",
				"CAMPUS" };
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

		for (Object[] fila : datosIncidencias) {
			modelo.addRow(fila);
		}

		table.setModel(modelo);
	}

	/**
	 * Establece el nombre del usuario en el botón de usuario.
	 * 
	 * @param nombre Nombre del usuario actual.
	 */
	public void setNombreUsuarioEnBoton(String nombre) {
		btnUsuario.setText(nombre);
	}

	/**
	 * Configura la visibilidad y funcionalidad del botón de vista de administrador.
	 * Solo se muestra y habilita si el usuario es admin. Además, asigna el
	 * ActionListener para cambiar a la ventana admin si es necesario.
	 * 
	 * @param esAdmin true si el usuario es administrador, false en caso contrario.
	 */
	public void configurarBotonAdmin(boolean esAdmin) {
		btnVistaAdmin.setVisible(esAdmin);
		btnVistaAdmin.setEnabled(esAdmin);

		// Solo añadir el ActionListener si es admin y no está ya añadido
		if (esAdmin) {
			for (ActionListener al : btnVistaAdmin.getActionListeners()) {
				btnVistaAdmin.removeActionListener(al);
			}
			btnVistaAdmin.addActionListener(e -> miControlador.cambiarVentana(7, 8));
		}
	}

	/**
	 * Crea un JComboBox con los elementos especificados.
	 *
	 * @param items Elementos del JComboBox.
	 * @param x     Posición X del JComboBox.
	 * @param y     Posición Y del JComboBox.
	 * @return JComboBox creado.
	 */
	private JComboBox<String> createComboBox(String[] items, int x, int y) {
		JComboBox<String> combo = new JComboBox<>(items);
		combo.setBounds(x, y, 100, 22);
		return combo;
	}

	/**
	 * Establece el modelo para la ventana.
	 *
	 * @param miModelo Instancia del modelo.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	/**
	 * Establece el controlador para la ventana.
	 *
	 * @param miControlador Instancia del controlador.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}
}
