
package vista;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controlador.Controlador;
import modelo.Modelo;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

/**
 * Clase `_02_Registro` que representa la ventana de registro de usuario.
 * Contiene elementos de la interfaz gráfica como campos de texto, botones y
 * etiquetas.
 * 
 * @author Gonzalo Alamo Castro
 */
public class _02_Registro extends JFrame implements Vista {

	private Modelo miModelo;
	private Controlador miControlador;
	private boolean verContrasena = false;
	private JLabel lblCorreo;
	private JTextField txtCorreo;
	private JLabel lblPass;
	private JPasswordField txtPass;
	private JButton btnVer;
	private JButton btnVer_2;
	private JLabel lblRepitePass;
	private JPasswordField txtRepitePass;
	private JLabel lblPregunta1;
	private JComboBox<String> comboPregunta1;
	private JLabel lblPregunta2;
	private JComboBox<String> comboPregunta2;
	private JLabel lblRespuesta1;
	private JTextField txtRespuesta1;
	private JLabel lblRespuesta2;
	private JTextField txtRespuesta2;
	private JCheckBox chkMayorEdad;
	private JLabel lblPassAdmin;
	private JPasswordField txtPassAdmin;
	private JButton btnRegistrar;
	private JButton btnVolver;
	private JRadioButton rdbtnUsuario;
	private JRadioButton rdbtnAdministrador;
	private JCheckBox chkCaptcha;
	private JButton btnAyuda;
	private JLabel lblInfo;
	private JLabel lblFondo;

	/**
	 * Constructor que inicializa la ventana y todos sus componentes gráficos,
	 * además de configurar los listeners para validación y acciones.
	 */
	public _02_Registro() {
		setTitle("Registro de Usuario");

		setBounds(350, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Etiqueta para el correo electrónico
		lblCorreo = new JLabel("Correo electrónico:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCorreo.setForeground(Color.WHITE);
		lblCorreo.setBackground(Color.WHITE);
		lblCorreo.setBounds(189, 51, 150, 25);
		getContentPane().add(lblCorreo);

		// Campo de texto para el correo electrónico
		txtCorreo = new JTextField();
		txtCorreo.setBounds(323, 49, 204, 25);
		getContentPane().add(txtCorreo);

		// Etiqueta para la contraseña
		lblPass = new JLabel("Contraseña:");
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPass.setForeground(Color.WHITE);
		lblPass.setBounds(189, 84, 150, 25);
		getContentPane().add(lblPass);

		// Campo de texto para la contraseña
		txtPass = new JPasswordField();
		txtPass.setBounds(323, 82, 135, 25);
		getContentPane().add(txtPass);

		// Botón para mostrar/ocultar la contraseña
		btnVer = new JButton("👁");
		btnVer.setBackground(Color.LIGHT_GRAY);
		btnVer.setBounds(468, 82, 59, 25);
		getContentPane().add(btnVer);
		btnVer.addActionListener(e -> {
			verContrasena = !verContrasena;
			txtPass.setEchoChar(verContrasena ? (char) 0 : '•');
		});

		// Botón para mostrar/ocultar la repetición de la contraseña
		btnVer_2 = new JButton("👁");
		btnVer_2.setBackground(Color.LIGHT_GRAY);
		btnVer_2.setBounds(468, 117, 59, 25);
		getContentPane().add(btnVer_2);
		btnVer_2.addActionListener(e -> {
			verContrasena = !verContrasena;
			txtRepitePass.setEchoChar(verContrasena ? (char) 0 : '•');
		});

		// Etiqueta para repetir la contraseña
		lblRepitePass = new JLabel("Repetir contraseña:");
		lblRepitePass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRepitePass.setForeground(Color.WHITE);
		lblRepitePass.setBackground(Color.WHITE);
		lblRepitePass.setBounds(189, 116, 150, 25);
		getContentPane().add(lblRepitePass);

		// Campo de texto para repetir la contraseña
		txtRepitePass = new JPasswordField();
		txtRepitePass.setBounds(323, 117, 135, 25);
		getContentPane().add(txtRepitePass);

		// Etiqueta para la primera pregunta de seguridad
		lblPregunta1 = new JLabel("Pregunta 1:");
		lblPregunta1.setForeground(new Color(255, 255, 255));
		lblPregunta1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPregunta1.setBounds(189, 163, 150, 25);
		getContentPane().add(lblPregunta1);

		// ComboBox para seleccionar la primera pregunta de seguridad
		comboPregunta1 = new JComboBox<>(new String[] { "¿Cuál es tu color favorito?", "¿Nombre de tu primera mascota?",
				"¿Número favorito?", "¿Cuántas veces has ido a la playa?", "¿Cuándo aprendiste a escribir?" });
		comboPregunta1.setBackground(Color.LIGHT_GRAY);
		comboPregunta1.setBounds(323, 164, 204, 25);
		getContentPane().add(comboPregunta1);

		// Etiqueta para la segunda pregunta de seguridad
		lblPregunta2 = new JLabel("Pregunta 2:");
		lblPregunta2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPregunta2.setForeground(Color.WHITE);
		lblPregunta2.setBackground(Color.LIGHT_GRAY);
		lblPregunta2.setBounds(189, 234, 150, 25);
		getContentPane().add(lblPregunta2);

		// ComboBox para seleccionar la segunda pregunta de seguridad
		comboPregunta2 = new JComboBox<>(new String[] { "¿Cuál es tu color favorito?", "¿Nombre de tu primera mascota?",
				"¿Número favorito?", "¿Cuántas veces has ido a la playa?", "¿Cuándo aprendiste a escribir?" });
		comboPregunta2.setBackground(Color.LIGHT_GRAY);
		comboPregunta2.setBounds(323, 235, 204, 25);
		getContentPane().add(comboPregunta2);

		// Etiqueta para la respuesta a la primera pregunta
		lblRespuesta1 = new JLabel("Respuesta 1:");
		lblRespuesta1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRespuesta1.setForeground(Color.WHITE);
		lblRespuesta1.setBounds(189, 200, 150, 25);
		getContentPane().add(lblRespuesta1);

		// Campo de texto para la respuesta a la primera pregunta
		txtRespuesta1 = new JTextField();
		txtRespuesta1.setBounds(323, 199, 204, 25);
		getContentPane().add(txtRespuesta1);

		// Etiqueta para la respuesta a la segunda pregunta
		lblRespuesta2 = new JLabel("Respuesta 2:");
		lblRespuesta2.setBackground(Color.BLACK);
		lblRespuesta2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRespuesta2.setForeground(Color.WHITE);
		lblRespuesta2.setBounds(189, 269, 150, 25);
		getContentPane().add(lblRespuesta2);

		// Campo de texto para la respuesta a la segunda pregunta
		txtRespuesta2 = new JTextField();
		txtRespuesta2.setBounds(323, 270, 204, 25);
		getContentPane().add(txtRespuesta2);

		// Checkbox para confirmar que el usuario es mayor de edad
		chkMayorEdad = new JCheckBox("Mayor de 16 años");
		chkMayorEdad.setBackground(Color.LIGHT_GRAY);
		chkMayorEdad.setBounds(455, 327, 150, 15);
		getContentPane().add(chkMayorEdad);

		// Etiqueta para la contraseña de administrador
		lblPassAdmin = new JLabel("Contraseña Admin:");
		lblPassAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassAdmin.setBackground(Color.LIGHT_GRAY);
		lblPassAdmin.setForeground(new Color(255, 255, 255));
		lblPassAdmin.setBounds(204, 403, 124, 25);
		getContentPane().add(lblPassAdmin);

		// Campo de texto para la contraseña de administrador
		txtPassAdmin = new JPasswordField();
		txtPassAdmin.setBounds(323, 403, 124, 25);
		getContentPane().add(txtPassAdmin);

		// Botón para registrar al usuario
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(Color.LIGHT_GRAY);
		btnRegistrar.setBounds(465, 400, 120, 30);
		btnRegistrar.setEnabled(false);
		getContentPane().add(btnRegistrar);

		// Botón para volver a la ventana anterior
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(38, 27, 90, 25);
		getContentPane().add(btnVolver);

		// RadioButton para seleccionar el rol de usuario
		rdbtnUsuario = new JRadioButton("Usuario");
		rdbtnUsuario.setBackground(Color.LIGHT_GRAY);
		rdbtnUsuario.setBounds(323, 323, 109, 23);
		getContentPane().add(rdbtnUsuario);

		// RadioButton para seleccionar el rol de administrador
		rdbtnAdministrador = new JRadioButton("Administrador");
		rdbtnAdministrador.setBackground(Color.LIGHT_GRAY);
		rdbtnAdministrador.setBounds(323, 348, 109, 23);
		getContentPane().add(rdbtnAdministrador);

		// Agrupación de los RadioButtons
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnUsuario);
		group.add(rdbtnAdministrador);

		// Checkbox para confirmar el CAPTCHA
		chkCaptcha = new JCheckBox("CAPTCHA");
		chkCaptcha.setBackground(Color.LIGHT_GRAY);
		chkCaptcha.setBounds(455, 352, 150, 15);
		getContentPane().add(chkCaptcha);

		// Etiqueta para mostrar información o mensajes
		lblInfo = new JLabel("");
		lblInfo.setForeground(Color.RED);
		lblInfo.setBounds(323, 439, 270, 25);
		getContentPane().add(lblInfo);

		// Acción del botón "Volver"
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(2, 0));

		// Acción del botón "Registrar"
		btnRegistrar.addActionListener(e -> miControlador.registrarUsuario());

		// Botón de ayuda
		btnAyuda = new JButton("Ayuda");
		btnAyuda.setBackground(Color.LIGHT_GRAY);
		btnAyuda.setBounds(43, 486, 85, 21);
		getContentPane().add(btnAyuda);

		// Fondo de la ventana
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_02_Registro.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 795, 625);
		getContentPane().add(lblFondo);

		// Acción del botón "Ayuda"
		btnAyuda.addActionListener(e -> miControlador.cambiarVentana(2, 15));

		// Listener para validar los campos del formulario
		DocumentListener docListener = new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				validarCampos();
			}

			public void removeUpdate(DocumentEvent e) {
				validarCampos();
			}

			public void insertUpdate(DocumentEvent e) {
				validarCampos();
			}
		};

		// Asignación del listener a los campos relevantes
		txtCorreo.getDocument().addDocumentListener(docListener);
		txtPass.getDocument().addDocumentListener(docListener);
		txtRepitePass.getDocument().addDocumentListener(docListener);
		txtRespuesta1.getDocument().addDocumentListener(docListener);
		txtRespuesta2.getDocument().addDocumentListener(docListener);
		txtPassAdmin.getDocument().addDocumentListener(docListener);

		// Listeners adicionales para validar campos
		chkMayorEdad.addItemListener(e -> validarCampos());
		chkCaptcha.addItemListener(e -> validarCampos());
		rdbtnUsuario.addItemListener(e -> validarCampos());
		rdbtnAdministrador.addItemListener(e -> validarCampos());
		comboPregunta1.addActionListener(e -> validarCampos());
		comboPregunta2.addActionListener(e -> validarCampos());
	}

	/**
	 * Valida los campos del formulario y habilita o deshabilita el botón de
	 * registro según si todos los campos son válidos.
	 */

	private void validarCampos() {
		boolean correo = !txtCorreo.getText().trim().isEmpty();
		boolean pass = txtPass.getPassword().length > 0;
		boolean repitePass = txtRepitePass.getPassword().length > 0;
		boolean respuesta1 = !txtRespuesta1.getText().trim().isEmpty();
		boolean respuesta2 = !txtRespuesta2.getText().trim().isEmpty();
		boolean mayorEdad = chkMayorEdad.isSelected();
		boolean captcha = chkCaptcha.isSelected();
		boolean pregunta1Seleccionada = comboPregunta1.getSelectedIndex() >= 0;
		boolean pregunta2Seleccionada = comboPregunta2.getSelectedIndex() >= 0;
		boolean rolSeleccionado = rdbtnUsuario.isSelected() || rdbtnAdministrador.isSelected();
		boolean adminPassOK = true;
		if (rdbtnAdministrador.isSelected()) {
			adminPassOK = txtPassAdmin.getPassword().length > 0;
		}
		boolean todoOK = correo && pass && repitePass && respuesta1 && respuesta2 && mayorEdad && captcha
				&& pregunta1Seleccionada && pregunta2Seleccionada && rolSeleccionado && adminPassOK;
		btnRegistrar.setEnabled(todoOK);
	}

	/**
	 * Devuelve la contraseña repetida ingresada por el usuario.
	 *
	 * @return Contraseña repetida.
	 */
	public String getRepiteContrasena() {
		return new String(txtRepitePass.getPassword());
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
	 * Devuelve el correo ingresado por el usuario.
	 *
	 * @return Correo electrónico.
	 */
	public String getCorreo() {
		return txtCorreo.getText().trim();
	}

	/**
	 * Devuelve la contraseña ingresada por el usuario.
	 *
	 * @return Contraseña.
	 */
	public String getContrasena() {
		return new String(txtPass.getPassword());
	}

	/**
	 * Devuelve el rol seleccionado por el usuario.
	 *
	 * @return Rol del usuario ("Admin" o "Usuario").
	 */
	public String getRol() {
		return rdbtnAdministrador.isSelected() ? "Admin" : "Usuario";
	}

	/**
	 * Muestra un mensaje en la etiqueta de información.
	 *
	 * @param mensaje Mensaje a mostrar.
	 */
	public void mostrarMensaje(String mensaje) {
		lblInfo.setText(mensaje);
	}
}
