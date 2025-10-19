package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import controlador.Controlador;
import modelo.Modelo;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Clase que representa la interfaz gráfica para la administración de
 * incidencias en el sistema. Esta ventana permite al usuario administrador
 * visualizar, filtrar y actualizar el estado de las incidencias reportadas.
 * También proporciona navegación a otras vistas del sistema como gestión de
 * usuarios, estadísticas, reportes y más.
 * 
 * @author Manuel Zaragoza Guijarro
 */
public class _08AdminIncidencia extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Modelo miModelo;
	private Controlador miControlador;
	private JTable table;
	private JButton btnUsuario;
	private JButton btnVolver;
	private JButton btnGestionUsr;
	private JComboBox<String> comboBoxCampus;
	private JComboBox<String> comboBoxEdificio;
	private JComboBox<String> comboBoxPiso;
	private JComboBox<String> comboBoxAula;
	private JComboBox<String> comboBoxvariosTextos;
	private JComboBox<String> comboBoxEstado;

	/**
	 * Constructor de la clase. Configura todos los componentes gráficos, listeners
	 * de eventos y la disposición visual de la ventana.
	 */
	public _08AdminIncidencia() {
		setTitle("Página Principal - Sesión Admin");
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
		btnPaginaPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPaginaPrincipal.setBounds(20, 54, 133, 28);
		getContentPane().add(btnPaginaPrincipal);
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(8, 7));

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMisIncidencias.setBounds(179, 54, 142, 28);
		getContentPane().add(btnMisIncidencias);
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(8, 13));

		JButton btnReportar = new JButton("Reportar Incidencia");
		btnReportar.setBackground(Color.LIGHT_GRAY);
		btnReportar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReportar.setBounds(331, 54, 133, 28);
		getContentPane().add(btnReportar);
		btnReportar.addActionListener(e -> miControlador.cambiarVentana(8, 11));

		JButton btnEstadisticas = new JButton("Estadísticas");
		btnEstadisticas.setBackground(Color.LIGHT_GRAY);
		btnEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEstadisticas.setBounds(474, 54, 131, 28);
		getContentPane().add(btnEstadisticas);
		btnEstadisticas.addActionListener(e -> miControlador.cambiarVentana(8, 9));

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
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(8, 5));

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

		// Botón para gestión de usuarios
		btnGestionUsr = new JButton("Gestión Usuarios");
		btnGestionUsr.setBackground(Color.LIGHT_GRAY);
		btnGestionUsr.setBounds(555, 427, 133, 27);
		getContentPane().add(btnGestionUsr);
		btnGestionUsr.addActionListener(e -> miControlador.cambiarVentana(8, 14));

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

		JButton btnResuelto = new JButton("Resuelto");
		btnResuelto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnResuelto.setBackground(Color.LIGHT_GRAY);
		btnResuelto.setBounds(39, 426, 133, 28);
		contentPane.add(btnResuelto);
		btnResuelto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = table.getSelectedRow();
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecciona una incidencia primero.");
					return;
				}

				String correo = table.getValueAt(filaSeleccionada, 0).toString();
				String estadoActual = table.getValueAt(filaSeleccionada, 4).toString();

				boolean exito = miControlador.marcarIncidenciaComoResuelta(correo, estadoActual);

				if (exito) {
					JOptionPane.showMessageDialog(null, "Incidencia marcada como resuelta.");
					miControlador.cargarIncidencias(); // o cargarIncidenciasFiltradas si estás filtrando
				} else {
					JOptionPane.showMessageDialog(null, "No se pudo actualizar la incidencia.");
				}
			}
		});

		// Fondo de la ventana
		JLabel lblFondo = new JLabel("");
		lblFondo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblFondo.setIcon(new ImageIcon(_08AdminIncidencia.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 946, 659);
		getContentPane().add(lblFondo);

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

	private JComboBox<String> createComboBox(String[] items, int x, int y) {
		JComboBox<String> combo = new JComboBox<>(items);
		combo.setBounds(x, y, 100, 22);
		return combo;
	}

	/**
	 * Método principal para lanzar la aplicación.
	 *
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		_08AdminIncidencia frame = new _08AdminIncidencia();
		frame.setVisible(true);
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