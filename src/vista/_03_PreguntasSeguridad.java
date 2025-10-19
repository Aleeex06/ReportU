
package vista;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.Modelo;
import java.awt.Font;

/**
 * Clase `_03_PreguntasSeguridad` que representa la ventana para verificar las
 * preguntas de seguridad. Contiene elementos de la interfaz gráfica como campos
 * de texto, botones y etiquetas.
 * 
 * @author Gonzalo Alamo Castro
 */
public class _03_PreguntasSeguridad extends JFrame implements Vista {

	private Modelo miModelo;
	private Controlador miControlador;
	private JTextField txtRespuesta1;
	private JTextField txtRespuesta2;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private JLabel lblPregunta1;
	private JComboBox<String> comboPregunta1;
	private JLabel lblPregunta2;
	private JComboBox<String> comboPregunta2;
	private JButton btnVolver;
	private JButton btnComprobar_1;
	private JLabel lblRespuesta1;
	private JLabel lblRespuesta2;
	private JLabel lblFondo;

	/**
	 * Constructor de la clase. Configura la ventana, los componentes de la interfaz
	 * y sus comportamientos.
	 */
	public _03_PreguntasSeguridad() {
		setTitle("Olvio de Contraseña");
		setSize(690, 450);
		setBounds(350, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Preguntas de seguridad disponibles
		String[] preguntas = { "¿Cuál es tu color favorito?", "¿Nombre de tu primera mascota?", "¿Número favorito?",
				"¿Cuántas veces has ido a la playa?", "¿Cuándo aprendiste a escribir?" };

		// Etiqueta para el correo electrónico
		lblCorreo = new JLabel("Correo electrónico:");
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setBounds(120, 141, 150, 25);
		getContentPane().add(lblCorreo);

		// Campo de texto para el correo electrónico
		txtCorreo = new JTextField();
		txtCorreo.setBounds(280, 141, 288, 25);
		getContentPane().add(txtCorreo);

		// Etiqueta para la primera pregunta
		lblPregunta1 = new JLabel("Pregunta 1  :");
		lblPregunta1.setForeground(Color.WHITE);
		lblPregunta1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPregunta1.setBounds(120, 193, 150, 25);
		getContentPane().add(lblPregunta1);

		// ComboBox para seleccionar la primera pregunta
		comboPregunta1 = new JComboBox<>(preguntas);
		comboPregunta1.setBackground(Color.LIGHT_GRAY);
		comboPregunta1.setBounds(280, 193, 288, 25);
		getContentPane().add(comboPregunta1);

		// Etiqueta para la respuesta a la primera pregunta
		lblRespuesta1 = new JLabel("Respuesta 1 :");
		lblRespuesta1.setForeground(Color.WHITE);
		lblRespuesta1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRespuesta1.setBounds(120, 228, 150, 25);
		getContentPane().add(lblRespuesta1);

		// Campo de texto para la respuesta a la primera pregunta
		txtRespuesta1 = new JTextField();
		txtRespuesta1.setBounds(280, 228, 288, 25);
		getContentPane().add(txtRespuesta1);

		// Etiqueta para la segunda pregunta
		lblPregunta2 = new JLabel("Pregunta 2  :");
		lblPregunta2.setForeground(Color.WHITE);
		lblPregunta2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPregunta2.setBounds(120, 275, 150, 25);
		getContentPane().add(lblPregunta2);

		// ComboBox para seleccionar la segunda pregunta
		comboPregunta2 = new JComboBox<>(preguntas);
		comboPregunta2.setBackground(Color.LIGHT_GRAY);
		comboPregunta2.setBounds(280, 275, 288, 25);
		getContentPane().add(comboPregunta2);

		// Etiqueta para la respuesta a la segunda pregunta
		lblRespuesta2 = new JLabel("Respuesta 2 :");
		lblRespuesta2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRespuesta2.setForeground(Color.WHITE);
		lblRespuesta2.setBounds(120, 310, 150, 25);
		getContentPane().add(lblRespuesta2);

		// Campo de texto para la respuesta a la segunda pregunta
		txtRespuesta2 = new JTextField();
		txtRespuesta2.setBounds(280, 310, 288, 25);
		getContentPane().add(txtRespuesta2);

		// Botón para comprobar las respuestas
		btnComprobar_1 = new JButton("Comprobar");
		btnComprobar_1.setBackground(Color.LIGHT_GRAY);
		btnComprobar_1.setBounds(448, 345, 120, 30);
		getContentPane().add(btnComprobar_1);

		// Botón para volver a la ventana anterior
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(10, 11, 90, 25);
		getContentPane().add(btnVolver);

		// Fondo de la ventana
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_03_PreguntasSeguridad.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(0, -64, 953, 625);
		getContentPane().add(lblFondo);

		// Acción del botón "Volver"
		btnVolver.addActionListener(e -> {
			setVisible(false);
			miControlador.cambiarVentana(3, 1);
		});

		// Acción del botón "Comprobar respuestas"
		btnComprobar_1.addActionListener(e -> {
			String correo = txtCorreo.getText().trim();

			if (correo.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, introduce un correo.");
				return;
			}

			if (miModelo.usuarioExiste(correo)) {
				miControlador.setUsuarioLogueado(correo); // Guarda el correo para usarlo en la siguiente ventana
				setVisible(false);
				miControlador.cambiarVentana(3, 4); // Ir a ventana nueva contraseña
			} else {
				JOptionPane.showMessageDialog(null, "El correo no está registrado.");
			}
		});

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
