package vista;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import controlador.Controlador;
import modelo.Modelo;

/**
 * Vista para la sección de Ayuda accesible sin estar logueado. Muestra
 * información de ayuda en una tabla y permite navegación entre ventanas.
 * 
 * @author Alexandru Daniel Costea
 */
public class _15_AyudaNoLog extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador miControlador;
	private Modelo miModelo;
	private JButton btnVolver;
	private JButton btnPaginaPrincipal;
	private JButton btnMisIncidencias;
	private JButton btnReportarIncidencia;
	private JButton btnNotificaciones;
	private JButton btnUsuario;
	private JTextField txtBuscador;
	private JButton btnSearch;
	private JLabel lblFondo;
	private JTable table;

	public static void main(String[] args) {
		_15_AyudaNoLog frame = new _15_AyudaNoLog();
		frame.setVisible(true);
	}

	public _15_AyudaNoLog() {
		setTitle("Ayuda");
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
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(15, 1));

		// Botones de navegación
		JButton btnPaginaPrincipal = new JButton("Página Principal");
		btnPaginaPrincipal.setBackground(Color.LIGHT_GRAY);
		btnPaginaPrincipal.setBounds(10, 54, 169, 28);
		contentPane.add(btnPaginaPrincipal);
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(15, 1));

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setBounds(224, 54, 169, 28);
		contentPane.add(btnMisIncidencias);
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(15, 1));

		JButton btnReportarIncidencia = new JButton("Reportar Incidencia");
		btnReportarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnReportarIncidencia.setBounds(424, 54, 169, 28);
		contentPane.add(btnReportarIncidencia);
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(15, 1));

		btnUsuario = new JButton("Usuario");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.setBounds(637, 10, 139, 68);
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(15, 1));
		contentPane.add(btnUsuario);

		txtBuscador = new JTextField("Buscador");
		txtBuscador.setBounds(22, 95, 393, 19);
		contentPane.add(txtBuscador);
		txtBuscador.setColumns(10);

		btnSearch = new JButton("Buscar");
		btnSearch.setBackground(Color.LIGHT_GRAY);
		btnSearch.setBounds(423, 94, 85, 21);
		contentPane.add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 214, 640, 202);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(Color.RED));
		table.setRowHeight(100);
		scrollPane.setViewportView(table);

		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_15_AyudaNoLog.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 946, 659);
		contentPane.add(lblFondo);
	}

	/**
	 * Actualiza el texto del botón de usuario para mostrar el nombre del usuario
	 * logueado.
	 * 
	 * @param nombre Nombre del usuario.
	 */
	public void setNombreUsuarioEnBoton(String nombre) {
		btnUsuario.setText(nombre);
	}

	/**
	 * Establece el modelo de datos para la vista. Intenta conectar con la base de
	 * datos y carga los datos de ayuda si la conexión es exitosa.
	 * 
	 * @param miModelo Instancia del modelo.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
		if (miModelo.conectarBD()) {
			cargarDatosDesdeBD();
		} else {
			JOptionPane.showMessageDialog(this, "No se pudo conectar con la base de datos.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Establece el controlador que manejará las acciones del usuario en esta vista.
	 * 
	 * @param miControlador Instancia del controlador.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * Carga los datos de ayuda desde la base de datos y los muestra en la tabla. En
	 * caso de error muestra un mensaje al usuario.
	 */
	private void cargarDatosDesdeBD() {
		try {
			Object[][] datos = miModelo.obtenerDatosAyuda(); // Este método debe existir en Modelo
			String[] columnas = { "Problema", "Solución" };
			DefaultTableModel modeloTabla = new DefaultTableModel(datos, columnas);
			table.setModel(modeloTabla);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al cargar los datos de ayuda.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
