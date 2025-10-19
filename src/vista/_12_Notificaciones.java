
package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Controlador;
import modelo.Modelo;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.JLayeredPane;

/**
 * Clase `_12_Notificaciones` que representa la ventana para gestionar
 * notificaciones. Contiene elementos de la interfaz gráfica como botones,
 * etiquetas y áreas de texto.
 * 
 * @author Ivan Garcia Ledesma
 */
public class _12_Notificaciones extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Panel principal de la ventana
	private Modelo miModelo; // Modelo asociado a la vista
	private Controlador miControlador; // Controlador para manejar eventos
	private JButton btnVolver; // Botón para volver a la ventana anterior
	private JButton btnUsuario;

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
	public _12_Notificaciones() {
		setTitle("Notificaciones");
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
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(9, 7));

		// Botón para ir a la página principal
		JButton btnNewButton = new JButton("Página Principal");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(10, 54, 138, 28);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(e -> miControlador.cambiarVentana(12, 7));

		// Botón para ir a "Mis Incidencias"
		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setBounds(153, 54, 151, 28);
		contentPane.add(btnMisIncidencias);
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(12, 13));

		// Botón para reportar una incidencia
		JButton btnReportarIncidencia = new JButton("Reportar Incidencia");
		btnReportarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnReportarIncidencia.setBounds(314, 54, 155, 28);
		contentPane.add(btnReportarIncidencia);
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(12, 11));

		// Botón para ir a notificaciones
		JButton btnNotificaciones = new JButton("Notificaciones");
		btnNotificaciones.setBackground(Color.LIGHT_GRAY);
		btnNotificaciones.setBounds(479, 54, 117, 28);
		contentPane.add(btnNotificaciones);
		btnNotificaciones.addActionListener(e -> miControlador.cambiarVentana(12, 12));

		// Botón para mostrar información del usuario
		btnUsuario = new JButton("Nombre de Usuario");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.setBounds(617, 10, 157, 71);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(12, 5));

		// Imagen de la notificación
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(_12_Notificaciones.class.getResource("/img/Incidencia.jpg")));
		lblNewLabel_1.setBounds(197, 151, 372, 284);
		contentPane.add(lblNewLabel_1);

		// Etiquetas para mostrar información de la notificación
		JLabel lblNewLabel_2 = new JLabel("CAMPUS");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2.setBounds(197, 126, 56, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("EDIFICIO");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_1.setBounds(263, 126, 70, 14);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("PISO");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_2.setBounds(343, 126, 46, 14);
		contentPane.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_3 = new JLabel("AULA");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_2_3.setForeground(Color.WHITE);
		lblNewLabel_2_3.setBounds(399, 126, 46, 14);
		contentPane.add(lblNewLabel_2_3);

		JLabel lblNewLabel_3 = new JLabel("ESTADO");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(107, 151, 80, 22);
		contentPane.add(lblNewLabel_3);

		// Botones para acciones relacionadas con la notificación
		JButton btnNewButton_3 = new JButton("LIKE");
		btnNewButton_3.setBounds(189, 565, 80, 28);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_3_1 = new JButton("RESUELTO");
		btnNewButton_3_1.setBounds(279, 565, 93, 28);
		contentPane.add(btnNewButton_3_1);

		JButton btnNewButton_3_2 = new JButton("NOTIFICACION");
		btnNewButton_3_2.setBounds(382, 565, 117, 28);
		contentPane.add(btnNewButton_3_2);

		JButton btnNewButton_3_3 = new JButton("FAVORITOS");
		btnNewButton_3_3.setBounds(507, 565, 100, 28);
		contentPane.add(btnNewButton_3_3);

		// Etiqueta para la descripción de la notificación
		JLabel lblNewLabel_5 = new JLabel("DESCRIPCION:\r\n");
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setBounds(189, 604, 418, 145);
		contentPane.add(lblNewLabel_5);

		// Área de texto para la descripción
		JTextArea txtrDescripcion = new JTextArea();
		txtrDescripcion.setText("DESCRIPCION:");
		txtrDescripcion.setBounds(197, 446, 372, 44);
		contentPane.add(txtrDescripcion);

		// Área de texto para la justificación
		JTextArea txtrJustificacion = new JTextArea();
		txtrJustificacion.setText("JUSTIFICACION:");
		txtrJustificacion.setBounds(197, 501, 372, 21);
		contentPane.add(txtrJustificacion);

		// Fondo de la ventana
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_12_Notificaciones.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 946, 659);
		contentPane.add(lblFondo);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(235, 69, 1, 1);
		contentPane.add(layeredPane);
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

	/**
	 * Clase interna para manejar acciones específicas.
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
