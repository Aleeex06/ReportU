
package vista;

import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controlador.Controlador;
import modelo.Modelo;
import java.awt.Color;

/**
 * Clase `_05_PerfilUsuario` que representa la ventana del perfil de usuario.
 * Contiene elementos de la interfaz gráfica como botones, campos de texto y
 * etiquetas, y permite al usuario visualizar y editar su información personal.
 * También proporciona accesos a otras secciones de la aplicación.
 * 
 * @author Gonzalo Alamo Castro
 */
public class _05_PerfilUsuario extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Controlador miControlador;
	private Modelo miModelo;
	private JTextField txtNombre;
	private JTextField txtCampus;
	private JButton btnVolver;
	private JButton btnPaginaPrincipal;
	private JButton btnMisIncidencias;
	private JButton btnReportarIncidencia;
	private JButton btnCerrrarSesion;
	private JButton btnConfirmar;
	private JButton btnCambioContraseña;
	private JTextField txtRespuesta1;
	private JTextField txtRespuesta2;
	private JComboBox<String> comboBox;
	private JComboBox<String> comboBox_1;
	private JLabel lblFondo;

	/**
	 * Método principal para lanzar la aplicación.
	 *
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		_05_PerfilUsuario frame = new _05_PerfilUsuario();
		frame.setVisible(true);
	}

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes.
	 */
	public _05_PerfilUsuario() {
		setTitle("Perfil de Usuario");
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
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(5, 0));

		// Botones de navegación
		JButton btnPaginaPrincipal = new JButton("Página Principal");
		btnPaginaPrincipal.setBackground(Color.LIGHT_GRAY);
		btnPaginaPrincipal.setBounds(10, 54, 169, 28);
		contentPane.add(btnPaginaPrincipal);
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(5, 7));

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setBounds(224, 54, 169, 28);
		contentPane.add(btnMisIncidencias);
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(5, 13));

		JButton btnReportarIncidencia = new JButton("Reportar Incidencia");
		btnReportarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnReportarIncidencia.setBounds(424, 54, 169, 28);
		contentPane.add(btnReportarIncidencia);
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(5, 11));

		btnCerrrarSesion = new JButton("Cerrar Sesión");
		btnCerrrarSesion.setBackground(new Color(255, 69, 0));
		btnCerrrarSesion.setBounds(641, 10, 135, 72);
		contentPane.add(btnCerrrarSesion);

		// Botón para confirmar cambios
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.LIGHT_GRAY);
		btnConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnConfirmar.setBounds(429, 502, 192, 38);
		contentPane.add(btnConfirmar);

		// Etiqueta para la foto de perfil
		JLabel lblFotoPerfil = new JLabel("");
		lblFotoPerfil.setIcon(new ImageIcon(_05_PerfilUsuario.class.getResource("/img/perfilApp.png")));
		lblFotoPerfil.setBounds(321, 107, 181, 136);
		contentPane.add(lblFotoPerfil);

		// Campos de texto para el nombre y campus
		txtNombre = new JTextField("Nombre de Perfil (correo electrónico)");
		txtNombre.setBounds(269, 269, 278, 19);
		contentPane.add(txtNombre);

		txtCampus = new JTextField("Villaviciosa ");
		txtCampus.setBounds(269, 307, 278, 19);
		contentPane.add(txtCampus);

		// Botón para cambio de contraseña
		btnCambioContraseña = new JButton("Olvido o Cambio de Contraseña");
		btnCambioContraseña.setBackground(Color.LIGHT_GRAY);
		btnCambioContraseña.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnCambioContraseña.setBounds(215, 502, 192, 38);
		contentPane.add(btnCambioContraseña);

		// Comboboxes para preguntas de seguridad
		comboBox = new JComboBox<>(new String[] { "¿Cuál es tu color favorito?", "¿Nombre de tu primera mascota?",
				"¿Número favorito?", "¿Cuántas veces has ido a la playa?", "¿Cuándo aprendiste a escribir?" });
		comboBox.setBounds(269, 368, 278, 21);
		contentPane.add(comboBox);

		comboBox_1 = new JComboBox<>(new String[] { "¿Cuál es tu color favorito?", "¿Nombre de tu primera mascota?",
				"¿Número favorito?", "¿Cuántas veces has ido a la playa?", "¿Cuándo aprendiste a escribir?" });
		comboBox_1.setBounds(269, 428, 278, 21);
		contentPane.add(comboBox_1);

		// Campos de texto para respuestas de seguridad
		txtRespuesta1 = new JTextField("Respuesta");
		txtRespuesta1.setBounds(269, 399, 278, 19);
		contentPane.add(txtRespuesta1);

		txtRespuesta2 = new JTextField("Respuesta");
		txtRespuesta2.setBounds(269, 459, 278, 19);
		contentPane.add(txtRespuesta2);

		// Etiqueta para el título de preguntas de seguridad
		JLabel lblTituloPreguntas = new JLabel("Preguntas de Seguridad");
		lblTituloPreguntas.setForeground(Color.WHITE);
		lblTituloPreguntas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTituloPreguntas.setBounds(321, 336, 181, 22);
		contentPane.add(lblTituloPreguntas);

		JButton btnAyuda = new JButton("Ayuda");
		btnAyuda.setBackground(Color.LIGHT_GRAY);
		btnAyuda.setBounds(641, 89, 133, 28);
		contentPane.add(btnAyuda);

		// Fondo de la ventana
		lblFondo = new JLabel("");
		lblFondo.setBackground(Color.BLACK);
		lblFondo.setIcon(new ImageIcon(_05_PerfilUsuario.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(0, -66, 946, 659);
		contentPane.add(lblFondo);

		// Configuración de los listeners para cambiar de ventana
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(5, 7));
		btnMisIncidencias.addActionListener(e -> {
			miControlador.cargarMisIncidencias(); // Carga las incidencias del usuario actual
			miControlador.cambiarVentana(5, 13); // Cambia a la vista de "Mis incidencias"
		});
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(5, 11));
		btnCerrrarSesion.addActionListener(e -> miControlador.cambiarVentana(5, 1));
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(5, 0));
		btnCambioContraseña.addActionListener(e -> miControlador.cambiarVentana(5, 3));
		btnAyuda.addActionListener(e -> miControlador.cambiarVentana(5, 6));
	}

	/** cambia el campo de texto para que aparezca el nombre del usuario */
	public void setNombreUsuarioEnBoton(String nombre) {
		txtNombre.setText(nombre);
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
