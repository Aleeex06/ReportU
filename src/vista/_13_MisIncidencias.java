package vista;

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

import controlador.Controlador;
import modelo.Modelo;

import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Clase _13_MisIncidencias que representa la ventana para visualizar las
 * incidencias del usuario. Contiene elementos de la interfaz gráfica como
 * botones, etiquetas, filtros y una tabla.
 * 
 * @author Ivan Garcia Ledesma
 */
public class _13_MisIncidencias extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Panel principal de la ventana
	private Modelo miModelo; // Modelo asociado a la vista
	private Controlador miControlador; // Controlador para manejar eventos
	private JButton btnVolver; // Botón para volver a la ventana anterior
	private JTable table; // Tabla para mostrar las incidencias
	private JButton btnUsuario;
	private JButton btnBorrarIncidencia;

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
	public _13_MisIncidencias() {
		setTitle("Mis incidencias");
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

		// Imagen del estado
		JLabel lblEstadoImg = new JLabel("");
		lblEstadoImg.setIcon(new ImageIcon(
				"C:\\Users\\Usuario\\Downloads\\ProyectoIntegrador1\\ProyectoIntegrador1\\ProyectoIntegrador1\\src\\img\\EstadoVerde.jpg"));
		lblEstadoImg.setBounds(109, 287, 70, 71);
		contentPane.add(lblEstadoImg);

		// Tabla para mostrar las incidencias
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 214, 658, 202);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(Color.RED));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		btnUsuario = new JButton("Usuario");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.setBounds(617, 11, 157, 71);
		contentPane.add(btnUsuario);

		// Boton para borrar incidencia
		btnBorrarIncidencia = new JButton("Borrar Incidencia");
		btnBorrarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnBorrarIncidencia.setBounds(29, 426, 150, 28);
		contentPane.add(btnBorrarIncidencia);
		btnBorrarIncidencia.addActionListener(e -> borrarIncidenciaSeleccionada());

		JButton btnVerIncidencia = new JButton("VER");
		btnVerIncidencia.setBackground(Color.LIGHT_GRAY);
		btnVerIncidencia.setForeground(new Color(0, 0, 0));
		btnVerIncidencia.setBounds(591, 431, 96, 28);
		contentPane.add(btnVerIncidencia);

		// Navegación entre vistas
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(13, 7));
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(13, 13));
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(13, 11));
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(13, 5));
		btnVerIncidencia.addActionListener(e -> {
			int filaSeleccionada = table.getSelectedRow();
			if (filaSeleccionada != -1) {
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				Object[] datosFila = new Object[modelo.getColumnCount()];
				for (int i = 0; i < modelo.getColumnCount(); i++) {
					datosFila[i] = modelo.getValueAt(filaSeleccionada, i);
				}
				miControlador.mostrarIncidenciaIndividual(datosFila); //
				miControlador.cambiarVentana(13, 10);
			} else {
				JOptionPane.showMessageDialog(this, "Por favor selecciona una incidencia.");
			}
		});

		// Fondo de la ventana
		JLabel lblFondo = new JLabel("");
		lblFondo.setBackground(Color.LIGHT_GRAY);
		lblFondo.setIcon(new ImageIcon(_13_MisIncidencias.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 946, 659);
		contentPane.add(lblFondo);

		// Navegación entre vistas
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(13, 7));

		btnMisIncidencias.addActionListener(e -> {
			miControlador.cargarMisIncidencias(); // Carga las incidencias del usuario actual
			miControlador.cambiarVentana(13, 13); // Cambia a la vista de "Mis incidencias"
		});

		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(13, 11));
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(13, 5));

	}

	private void borrarIncidenciaSeleccionada() {
		int filaSeleccionada = table.getSelectedRow();
		if (filaSeleccionada == -1) {
			JOptionPane.showMessageDialog(this, "Por favor, seleccione una incidencia para borrar", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// Obtener datos clave
		String usuarioCorreo = (String) table.getValueAt(filaSeleccionada, 0);
		java.sql.Timestamp fecha = (java.sql.Timestamp) table.getValueAt(filaSeleccionada, 2);

		// Confirmación
		int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea borrar esta incidencia?",
				"Confirmar borrado", JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			// Borrar y actualizar
			if (miControlador.borrarIncidencia(usuarioCorreo, fecha)) {
				// Actualizar el modelo de tabla directamente
				((DefaultTableModel) table.getModel()).removeRow(filaSeleccionada);

				// Forzar repintado
				table.revalidate();
				table.repaint();

				JOptionPane.showMessageDialog(this, "Incidencia borrada correctamente", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Error al borrar la incidencia", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
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
	 * Actualiza la tabla de incidencias con los datos proporcionados.
	 *
	 * @param datosIncidencias Lista de datos de las incidencias.
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
	 * Crea un combo box con los elementos proporcionados.
	 *
	 * @param items Elementos del combo box.
	 * @param x     Posición X del combo box.
	 * @param y     Posición Y del combo box.
	 * @return Combo box creado.
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