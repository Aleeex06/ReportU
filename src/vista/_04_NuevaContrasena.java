package vista;

import javax.swing.*;
import controlador.Controlador;
import modelo.Modelo;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

/**
 * Clase `_04_NuevaContrasena` que representa la ventana para establecer una
 * nueva contraseña. Contiene elementos de la interfaz gráfica como campos de
 * texto, botones y etiquetas, y gestiona la lógica para confirmar y actualizar
 * la contraseña del usuario.
 * 
 * @author Alexandru Daniel Costea
 */
public class _04_NuevaContrasena extends JFrame implements Vista {

	private Modelo miModelo;
	private Controlador miControlador;
	private JLabel lblNuevaPass;
	private JPasswordField txtNuevaPass;
	private JLabel lblConfirmarPass;
	private JPasswordField txtConfirmarPass;
	private JButton btnConfirmar;
	private JButton btnVolver;
	private JButton btnVer;
	private JButton btnVer_1;
	private boolean verContrasena = false;
	private boolean verConfirmarContrasena = false;
	private JLabel lblFondo;

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes.
	 */
	public _04_NuevaContrasena() {
		setTitle("Nueva Contraseña");
		setSize(400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// Etiqueta para la nueva contraseña
		lblNuevaPass = new JLabel("Nueva contraseña:");
		lblNuevaPass.setForeground(Color.WHITE);
		lblNuevaPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNuevaPass.setBounds(30, 40, 150, 25);
		getContentPane().add(lblNuevaPass);

		// Campo de texto para la nueva contraseña
		txtNuevaPass = new JPasswordField();
		txtNuevaPass.setBounds(180, 40, 120, 25);
		getContentPane().add(txtNuevaPass);

		// Botón para mostrar/ocultar la contraseña
		btnVer = new JButton("👁");
		btnVer.setBackground(Color.LIGHT_GRAY);
		btnVer.setBounds(310, 40, 59, 25);
		getContentPane().add(btnVer);
		btnVer.addActionListener(e -> {
			verContrasena = !verContrasena;
			txtNuevaPass.setEchoChar(verContrasena ? (char) 0 : '•');
		});

		// Etiqueta para confirmar la contraseña
		lblConfirmarPass = new JLabel("Confirmar contraseña:");
		lblConfirmarPass.setForeground(Color.WHITE);
		lblConfirmarPass.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirmarPass.setBounds(30, 91, 150, 25);
		getContentPane().add(lblConfirmarPass);

		// Campo de texto para confirmar la contraseña
		txtConfirmarPass = new JPasswordField();
		txtConfirmarPass.setBounds(180, 92, 120, 25);
		getContentPane().add(txtConfirmarPass);

		// Botón adicional para mostrar/ocultar la confirmación de contraseña
		btnVer_1 = new JButton("👁");
		btnVer_1.setBackground(Color.LIGHT_GRAY);
		btnVer_1.setBounds(310, 92, 59, 25);
		getContentPane().add(btnVer_1);
		btnVer_1.addActionListener(e -> {
			verConfirmarContrasena = !verConfirmarContrasena;
			txtConfirmarPass.setEchoChar(verConfirmarContrasena ? (char) 0 : '•');
		});

		// Botón para confirmar la nueva contraseña
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBackground(Color.LIGHT_GRAY);
		btnConfirmar.setBounds(249, 170, 120, 30);
		getContentPane().add(btnConfirmar);
		btnConfirmar.addActionListener(e -> {
			String nuevaPass = new String(txtNuevaPass.getPassword());
			String confirmarPass = new String(txtConfirmarPass.getPassword());

			if (nuevaPass.isEmpty() || confirmarPass.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Por favor, completa ambos campos.");
				return;
			}

			if (!nuevaPass.equals(confirmarPass)) {
				JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
				return;
			}

			String correo = miControlador.getUsuarioLogueado(); // Asegúrate que esté bien configurado en controlador

			if (miModelo.cambiarContrasena(correo, nuevaPass)) {
				JOptionPane.showMessageDialog(null, "Contraseña actualizada con éxito.");
				setVisible(false);
				miControlador.cambiarVentana(4, 7); // Ir al login u otra ventana
			} else {
				JOptionPane.showMessageDialog(null, "Error al cambiar la contraseña.");
			}
		});

		// Botón para volver a la ventana anterior
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.LIGHT_GRAY);
		btnVolver.setBounds(10, 10, 90, 25);
		getContentPane().add(btnVolver);
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(4, 3));

		// Fondo de la ventana
		lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_04_NuevaContrasena.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-170, 0, 598, 431);
		getContentPane().add(lblFondo);
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
