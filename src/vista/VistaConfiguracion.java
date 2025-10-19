package vista;

import java.io.IOException;
import java.util.Map;
import modelo.ClaseFichero;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Clase `VistaConfiguracion` que representa la ventana para configurar los
 * datos de conexión. Contiene elementos de la interfaz gráfica como campos de
 * texto y botones.
 * 
 * @author Alexandru Daniel Costea
 */
public class VistaConfiguracion extends JFrame {
	private JTextField txtUsr; // Campo de texto para el usuario
	private JTextField txtPwd; // Campo de texto para la contraseña
	private JTextField txtUrl; // Campo de texto para la URL

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes.
	 */
	public VistaConfiguracion() {
		setTitle("Configuración ");
		setSize(400, 250);
		getContentPane().setLayout(null);

		// Etiqueta para el usuario
		JLabel lblUsr = new JLabel("Usuario:");
		lblUsr.setBounds(30, 30, 80, 25);
		getContentPane().add(lblUsr);

		// Campo de texto para el usuario
		txtUsr = new JTextField();
		txtUsr.setBounds(120, 30, 200, 25);
		getContentPane().add(txtUsr);

		// Etiqueta para la contraseña
		JLabel lblPwd = new JLabel("Contraseña:");
		lblPwd.setBounds(30, 70, 80, 25);
		getContentPane().add(lblPwd);

		// Campo de texto para la contraseña
		txtPwd = new JTextField();
		txtPwd.setBounds(120, 70, 200, 25);
		getContentPane().add(txtPwd);

		// Etiqueta para la URL
		JLabel lblUrl = new JLabel("URL:");
		lblUrl.setBounds(30, 110, 80, 25);
		getContentPane().add(lblUrl);

		// Campo de texto para la URL
		txtUrl = new JTextField();
		txtUrl.setBounds(120, 110, 200, 25);
		getContentPane().add(txtUrl);

		// Botón para guardar la configuración
		JButton btnGuardar = new JButton("Modificar");
		btnGuardar.setBounds(120, 145, 100, 30);
		getContentPane().add(btnGuardar);

		// Botón para cancelar y cerrar la ventana
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(276, 173, 100, 30);
		getContentPane().add(btnCancelar);

		// Eventos de los botones
		btnGuardar.addActionListener(e -> {
			try {
				ClaseFichero.guardarConfiguracion(txtUsr.getText(), txtPwd.getText(), txtUrl.getText());
				JOptionPane.showMessageDialog(this, "Configuración guardada.");
				dispose();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Error al guardar configuración.");
			}
		});

		btnCancelar.addActionListener(e -> dispose());

		// Cargar la configuración existente
		cargarConfiguracion();
	}

	/**
	 * Método para cargar la configuración desde el archivo.
	 */
	private void cargarConfiguracion() {
		try {
			Map<String, String> config = ClaseFichero.leerConfiguracion();
			txtUsr.setText(config.get("usr"));
			txtPwd.setText(config.get("pwd"));
			txtUrl.setText(config.get("url"));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Error al leer la configuración:\n" + ex.getMessage());
		}
	}
}
