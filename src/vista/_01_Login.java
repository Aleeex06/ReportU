
package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controlador.Controlador;
import modelo.Modelo;
import java.awt.Font;

/**
 * Constructor de la clase `_01_Login`.
 * 
 * Configura la interfaz gráfica del formulario de inicio de sesión, incluyendo
 * etiquetas, campos de entrada, botones y acciones asociadas.
 * 
 * @author Gonzalo Alamo Castro
 */
public class _01_Login extends JFrame implements Vista {

	private Modelo miModelo;
	private Controlador miControlador;
	private boolean verContrasena = false;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private JLabel lblPass;
	private JPasswordField txtPass;
	private JButton btnVer;
	private JButton btnLogin;
	private JButton btnVolver;
	private JLabel lblOlvido;
	private JLabel lblRegistro;
	private JButton btnAyuda;
	private int intentosFallidos = 0;
	private JLabel lblFondo;
	private JButton btnConfig;

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes.
	 */
	public _01_Login() {
		setTitle("Inicio de Sesión");
		setSize(450, 300);
		setBounds(550, 250, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Etiqueta para el correo electrónico
		lblCorreo = new JLabel("Correo electrónico:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setBounds(37, 49, 150, 25);
		getContentPane().add(lblCorreo);

		// Campo de texto para el correo electrónico
		txtCorreo = new JTextField();
		txtCorreo.setBounds(163, 50, 180, 25);
		getContentPane().add(txtCorreo);

		// Etiqueta para la contraseña
		lblPass = new JLabel("Contraseña:");
		lblPass.setForeground(Color.WHITE);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPass.setBounds(50, 86, 150, 25);
		getContentPane().add(lblPass);

		// Campo de texto para la contraseña
		txtPass = new JPasswordField();
		txtPass.setBounds(163, 86, 104, 25);
		getContentPane().add(txtPass);

		// Botón para mostrar/ocultar la contraseña
		btnVer = new JButton("👁");
		btnVer.setBackground(Color.LIGHT_GRAY);
		btnVer.setBounds(277, 86, 66, 25);
		getContentPane().add(btnVer);
		btnVer.addActionListener(e -> {
			verContrasena = !verContrasena;
			txtPass.setEchoChar(verContrasena ? (char) 0 : '•');
		});

		// Botón para iniciar sesión
		btnLogin = new JButton("Iniciar sesión");
		btnLogin.setBackground(Color.LIGHT_GRAY);
		btnLogin.setBounds(163, 129, 180, 30);
		btnLogin.setEnabled(false);
		getContentPane().add(btnLogin);

		// Listeners para habilitar el botón de inicio de sesión
		txtCorreo.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				checkFields();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				checkFields();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				checkFields();
			}
		});

		txtPass.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				checkFields();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				checkFields();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				checkFields();
			}
		});

		// Acción del botón de inicio de sesión
		btnLogin.addActionListener(e -> {
			String usuario = txtCorreo.getText();
			String contrasena = new String(txtPass.getPassword());

			if (!miModelo.conectarBD()) {
				JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos.");
				return;
			}

			if (miModelo.comprobarLogin(usuario, contrasena)) {
				String rol = miModelo.obtenerRol(usuario);
				System.out.println("Rol recuperado de BD: " + rol);

				if (rol == null) {
					JOptionPane.showMessageDialog(null, "No se ha podido recuperar el rol del usuario.");
					return;
				}
				miControlador.setUsuarioLogueado(usuario);
				intentosFallidos = 0; // Reinicia los intentos tras login correcto

				String nombreUsuario = miModelo.obtenerNombreUsuario(usuario);
				miControlador.actualizarNombreUsuario(nombreUsuario);

				if (rol.equalsIgnoreCase("Admin")) {
					miControlador.cambiarVentana(1, 8);
				} else {
					miControlador.cambiarVentana(1, 7);
				}
			} else {
				intentosFallidos++;
				JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Intento " + intentosFallidos + " de 3.");

				txtCorreo.setText("");
				txtPass.setText("");
				txtCorreo.requestFocus();

				if (intentosFallidos >= 3) {
					JOptionPane.showMessageDialog(null,
							"Has alcanzado el límite de intentos. La aplicación se cerrará.");
					System.exit(0);
				}
			}
		});

		// Botón para volver a la ventana anterior
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(10, 10, 90, 25);
		getContentPane().add(btnVolver);
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(1, 0));

		// Etiqueta para recuperar contraseña
		lblOlvido = new JLabel("¿Olvidó su contraseña?");
		lblOlvido.setForeground(Color.WHITE);
		lblOlvido.setBounds(10, 225, 150, 25);
		lblOlvido.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblOlvido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				miControlador.cambiarVentana(1, 3);
			}
		});
		getContentPane().add(lblOlvido);

		// Etiqueta para registrarse
		lblRegistro = new JLabel("Registrarse");
		lblRegistro.setForeground(Color.WHITE);
		lblRegistro.setBounds(353, 225, 74, 25);
		lblRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				miControlador.cambiarVentana(0, 2);
			}
		});
		getContentPane().add(lblRegistro);

		// Botón de ayuda
		btnAyuda = new JButton("Ayuda");
		btnAyuda.setBackground(Color.LIGHT_GRAY);
		btnAyuda.setBounds(342, 12, 85, 21);
		getContentPane().add(btnAyuda);

		// Botón para configurar
		btnConfig = new JButton("Configurar ");
		btnConfig.setBackground(Color.LIGHT_GRAY);
		btnConfig.setBounds(163, 181, 180, 25);
		getContentPane().add(btnConfig);

		btnConfig.addActionListener(e -> {
			new VistaConfiguracion().setVisible(true);
		});

		// Fondo de la ventana
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_01_Login.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-69, 0, 503, 468);
		getContentPane().add(lblFondo);
		btnAyuda.addActionListener(e -> miControlador.cambiarVentana(1, 15));
	}

	/**
	 * Verifica si los campos de texto están llenos para habilitar el botón de
	 * inicio de sesión.
	 */
	private void checkFields() {
		String usuario = txtCorreo.getText();
		String contrasena = new String(txtPass.getPassword());
		btnLogin.setEnabled(!usuario.isEmpty() && !contrasena.isEmpty());
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
