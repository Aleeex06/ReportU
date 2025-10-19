package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
 * Clase _00_PaginaPrincipalNoLog que representa la ventana principal de la
 * aplicación cuando el usuario no ha iniciado sesión. Contiene elementos de la
 * interfaz gráfica como botones, tablas y filtros.
 * 
 * @author Gonzalo Alamo Castro
 */
public class _00_PaginaPrincipalNoLog extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;

	/** Panel principal de contenido de la ventana. */
	private JPanel contentPane;

	/** Instancia del modelo para manejar datos. */
	private Modelo miModelo;

	/** Instancia del controlador para manejar la lógica. */
	private Controlador miControlador;

	/** Botón para volver a la vista anterior. */
	private JButton btnVolver;

	/** Tabla para mostrar las incidencias. */
	private JTable table;

	/**
	 * Método principal para lanzar la aplicación (modo test).
	 * 
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		_07_PaginaPrincipal frame = new _07_PaginaPrincipal();
		frame.setVisible(true);
	}

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes gráficos,
	 * incluyendo botones, etiquetas, filtros y tabla.
	 */
	public _00_PaginaPrincipalNoLog() {
		setTitle("Página Principal - Sin Login");
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
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(7, 0)); // Navega a la vista principal sin login

		// Botones de navegación
		JButton btnNewButton = new JButton("Página Principal");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(10, 54, 169, 28);
		contentPane.add(btnNewButton);

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setBounds(224, 54, 169, 28);
		contentPane.add(btnMisIncidencias);

		JButton btnReportarIncidencia = new JButton("Reportar Incidencia");
		btnReportarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnReportarIncidencia.setBounds(424, 54, 169, 28);
		contentPane.add(btnReportarIncidencia);

		JButton btnNewButton_1 = new JButton("Nombre de Usuario");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(617, 10, 157, 71);
		contentPane.add(btnNewButton_1);

		// Etiqueta para los filtros
		JLabel lblNewLabel = new JLabel("Filtros");
		lblNewLabel.setForeground(new Color(255, 250, 240));
		lblNewLabel.setBackground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(29, 129, 70, 39);
		contentPane.add(lblNewLabel);

		// Comboboxes para los filtros
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(Color.WHITE);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Campus", "Campus 1", "Campus 2", "Campus 3" }));
		comboBox.setBounds(125, 120, 89, 22);
		contentPane.add(comboBox);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setModel(
				new DefaultComboBoxModel(new String[] { "Edificio ", "Edificio 1", "Edificio 2", "Edificio 3" }));
		comboBox_1.setBounds(125, 158, 89, 22);
		contentPane.add(comboBox_1);

		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBackground(Color.WHITE);
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "Piso", "Piso 1", "Piso 2", "Piso 3" }));
		comboBox_2.setBounds(239, 120, 89, 22);
		contentPane.add(comboBox_2);

		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] { "Aula", "Aula 1", "Aula 2", "Aula 3" }));
		comboBox_3.setBounds(239, 158, 89, 22);
		contentPane.add(comboBox_3);

		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBackground(Color.WHITE);
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] { "Fecha" }));
		comboBox_4.setBounds(348, 120, 89, 22);
		contentPane.add(comboBox_4);

		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBackground(Color.WHITE);
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] { "Estado", "Resuelto", "Reportado" }));
		comboBox_5.setBounds(348, 158, 89, 22);
		contentPane.add(comboBox_5);

		// Botón para aplicar filtros
		JButton btnNewButton_2 = new JButton("Aplicar");
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(487, 158, 106, 23);
		contentPane.add(btnNewButton_2);

		// Botones de acciones sobre incidencias
		JButton btnNewButton_3 = new JButton("LIKE");
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setBounds(73, 427, 106, 28);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_3_1 = new JButton("RESUELTO");
		btnNewButton_3_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3_1.setBounds(189, 427, 130, 28);
		contentPane.add(btnNewButton_3_1);

		JButton btnNewButton_3_2 = new JButton("NOTIFICACION");
		btnNewButton_3_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3_2.setBounds(329, 427, 151, 28);
		contentPane.add(btnNewButton_3_2);

		JButton btnNewButton_3_3 = new JButton("FAVORITOS");
		btnNewButton_3_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3_3.setBounds(490, 427, 141, 28);
		contentPane.add(btnNewButton_3_3);

		// Tabla de incidencias
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(29, 214, 658, 202);
		contentPane.add(scrollPane);

		table = new JTable();
		table.setBorder(new LineBorder(Color.RED));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);

		// Fondo
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_00_PaginaPrincipalNoLog.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 946, 659);
		contentPane.add(lblFondo);

		// Listeners para cambiar de ventana (placeholder)
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnNewButton.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnNewButton_1.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnNewButton_3.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnNewButton_3_1.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnNewButton_3_2.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnNewButton_3_3.addActionListener(e -> miControlador.cambiarVentana(0, 1));
		btnNewButton_2.addActionListener(e -> miControlador.cambiarVentana(0, 1));
	}

	/**
	 * Actualiza la tabla de incidencias con los datos proporcionados por el modelo.
	 * 
	 * @param datosIncidencias Lista con objetos (arrays) representando cada fila.
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
	 * Establece el modelo de datos para esta vista.
	 * 
	 * @param miModelo Instancia del modelo.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	/**
	 * Establece el controlador que gestionará la lógica de la vista.
	 * 
	 * @param miControlador Instancia del controlador.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	/**
	 * Clase interna reservada para acciones personalizadas (no utilizada).
	 */
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
		}
	}
}
