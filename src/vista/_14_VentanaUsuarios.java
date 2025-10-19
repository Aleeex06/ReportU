
package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import modelo.Modelo;

/**
 * Clase `_14_VentanaUsuarios` que representa la ventana para la gestión de
 * usuarios. Contiene elementos de la interfaz gráfica como campos de texto,
 * botones y una tabla.
 * 
 * @author Ivan Garcia Ledesma
 */
public class _14_VentanaUsuarios extends JFrame implements Vista {
	private JTextField txtUsr, txtRol, txtPwd; // Campos de texto para usuario, rol y contraseña
	private JButton btnAlta, btnBaja, btnModificacion; // Botones para las acciones de alta, baja y modificación
	private JTable tablaUsuarios; // Tabla para mostrar los usuarios
	private DefaultTableModel modeloTabla; // Modelo de la tabla
	private String usuarioAnterior = ""; // Guarda el usuario original para modificación
	private Controlador miControlador;
	private JButton btnVolver;
	private JButton btnUsuario;

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes.
	 */
	public _14_VentanaUsuarios() {

		setTitle("Gestión de Usuarios");
		setBounds(350, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Panel principal del formulario con 3 filas: etiquetas, campos y botones
		JPanel panelFormulario = new JPanel(new GridLayout(3, 1, 10, 10));
		panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Fila 1: etiquetas
		JPanel panelEtiquetas = new JPanel(new GridLayout(1, 3, 10, 10));
		panelEtiquetas.add(new JLabel("Usuario (USR):", JLabel.CENTER));
		panelEtiquetas.add(new JLabel("Contraseña (PWD):", JLabel.CENTER));
		panelEtiquetas.add(new JLabel("Rol (ROL):", JLabel.CENTER));

		// Fila 2: campos de texto
		JPanel panelCampos = new JPanel(new GridLayout(1, 3, 10, 10));
		txtUsr = new JTextField();
		txtPwd = new JTextField();
		txtRol = new JTextField();
		panelCampos.add(txtUsr);
		panelCampos.add(txtPwd);
		panelCampos.add(txtRol);

		// Fila 3: botones
		JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 10));
		btnAlta = new JButton("Alta");
		btnBaja = new JButton("Baja");
		btnModificacion = new JButton("Modificación");
		panelBotones.add(btnAlta);
		panelBotones.add(btnBaja);
		panelBotones.add(btnModificacion);

		// Botón para volver
		btnVolver = new JButton("Volver");
		btnVolver.setBounds(20, 507, 100, 30);
		getContentPane().add(btnVolver);

		// Añadir subpaneles al panel principal
		panelFormulario.add(panelEtiquetas);
		panelFormulario.add(panelCampos);
		panelFormulario.add(panelBotones);

		// Añadir el panelFormulario a la parte superior de la ventana
		getContentPane().add(panelFormulario, BorderLayout.NORTH);

		// Tabla de usuarios
		modeloTabla = new DefaultTableModel(new String[] { "USR", "PWD", "ROL" }, 0);
		tablaUsuarios = new JTable(modeloTabla);
		tablaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		getContentPane().add(new JScrollPane(tablaUsuarios), BorderLayout.CENTER);

		// Listener para actualizar campos al seleccionar fila
		tablaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaUsuarios.getSelectedRow();
				if (fila >= 0) {
					String usr = modeloTabla.getValueAt(fila, 0).toString();
					// No mostramos la contraseña en el campo para mayor seguridad
					String rol = modeloTabla.getValueAt(fila, 2).toString();
					txtUsr.setText(usr);
					txtPwd.setText(""); // Vaciar el campo contraseña para no mostrar hash
					txtRol.setText(rol);
					usuarioAnterior = usr; // Guardar usuario original para modificación
				}
			}
		});

		// Listener para habilitar botones según campos
		DocumentListener listener = new DocumentListener() {
			private void verificarCampos() {
				boolean habilitar = !txtUsr.getText().trim().isEmpty() && !txtRol.getText().trim().isEmpty();
				btnAlta.setEnabled(habilitar);
				btnBaja.setEnabled(!txtUsr.getText().trim().isEmpty());
				btnModificacion.setEnabled(habilitar);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				verificarCampos();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				verificarCampos();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				verificarCampos();
			}
		};

		// Asociar listener a todos los campos
		txtUsr.getDocument().addDocumentListener(listener);
		txtPwd.getDocument().addDocumentListener(listener);
		txtRol.getDocument().addDocumentListener(listener);

		// Inicializar botones deshabilitados
		btnAlta.setEnabled(false);
		btnBaja.setEnabled(false);
		btnModificacion.setEnabled(false);
	}

	/**
	 * Obtiene el nombre del usuario ingresado.
	 *
	 * @return Nombre del usuario.
	 */
	public String getNombreUsuario() {
		return txtUsr.getText().trim();
	}

	/**
	 * Obtiene la contraseña ingresada.
	 *
	 * @return Contraseña ingresada.
	 */
	public String getContrasena() {
		return new String(txtPwd.getText()).trim();
	}

	/**
	 * Obtiene el rol seleccionado.
	 *
	 * @return Rol seleccionado.
	 */
	public String getRolSeleccionado() {
		return txtRol.getText().trim();
	}

	/**
	 * Obtiene el usuario anterior seleccionado en la tabla.
	 *
	 * @return Usuario anterior.
	 */
	public String getUsuarioAnterior() {
		return usuarioAnterior;
	}

	/**
	 * Limpia los campos de texto y la selección de la tabla.
	 */
	public void limpiarCampos() {
		txtUsr.setText("");
		txtPwd.setText("");
		txtRol.setText("");
		usuarioAnterior = "";
		tablaUsuarios.clearSelection();
	}

	/**
	 * Carga los usuarios en la tabla.
	 *
	 * @param datos Datos de los usuarios.
	 */
	public void cargarUsuarios(Object[][] datos) {
		modeloTabla.setRowCount(0);
		for (Object[] fila : datos) {
			modeloTabla.addRow(fila);
		}
	}

	@Override
	public void setModelo(Modelo miModelo) {
		// Puedes implementar si necesitas usar el modelo aquí
	}

	@Override
	public void setControlador(Controlador miControlador) {
		btnAlta.addActionListener((ActionListener) miControlador);
		btnBaja.addActionListener((ActionListener) miControlador);
		btnModificacion.addActionListener((ActionListener) miControlador);
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(14, 8));
	}
}
