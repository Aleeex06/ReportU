package vista;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import controlador.Controlador;
import modelo.Modelo;

/**
 * Clase _11_ReportarIncidencia que representa la ventana para reportar una
 * incidencia. Contiene campos para ingresar información sobre la incidencia y
 * botones de navegación. Implementa la interfaz Vista para seguir el patrón
 * MVC.
 * 
 * @autor Alexandru Daniel Costea
 */
public class _11_ReportarIncidencia extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Modelo miModelo;
	private Controlador miControlador;
	private JButton btnVolver;
	private JButton btnUsuario;
	private JButton btnCargarImagen;
	private JButton btnAyuda;
	private JButton btnSubirIncidencia;
	private JTextField txtCampus;
	private JTextField txtEdificio;
	private JTextField txtPiso;
	private JTextField txtAula;
	private JTextArea txtrDescripcion;
	private JScrollPane scrollDescripcion;

	public _11_ReportarIncidencia() {
		setTitle("Reportar Incidencia");
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
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(11, 0));

		// Botones de navegación
		JButton btnPaginaPrincipal = new JButton("Página Principal");
		btnPaginaPrincipal.setBackground(Color.LIGHT_GRAY);
		btnPaginaPrincipal.setBounds(10, 54, 169, 28);
		contentPane.add(btnPaginaPrincipal);
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(11, 7));

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setBounds(224, 54, 169, 28);
		contentPane.add(btnMisIncidencias);
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(11, 13));

		JButton btnReportarIncidencia = new JButton("Reportar Incidencia");
		btnReportarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnReportarIncidencia.setBounds(424, 54, 169, 28);
		contentPane.add(btnReportarIncidencia);
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(11, 11));

		btnUsuario = new JButton("Usuario");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.setBounds(617, 11, 157, 71);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(11, 5));

		// Campos para la incidencia con placeholders
		txtCampus = new JTextField("Campus - Obligatorio");
		txtCampus.setHorizontalAlignment(SwingConstants.CENTER);
		txtCampus.setBounds(183, 127, 356, 20);
		contentPane.add(txtCampus);
		txtCampus.setColumns(10);
		agregarPlaceholder(txtCampus, "Campus - Obligatorio");

		txtEdificio = new JTextField("Edificio");
		txtEdificio.setBounds(183, 178, 100, 20);
		contentPane.add(txtEdificio);
		txtEdificio.setColumns(10);
		agregarPlaceholder(txtEdificio, "Edificio");

		txtPiso = new JTextField("Piso");
		txtPiso.setHorizontalAlignment(SwingConstants.CENTER);
		txtPiso.setBounds(314, 178, 100, 20);
		contentPane.add(txtPiso);
		txtPiso.setColumns(10);
		agregarPlaceholder(txtPiso, "Piso");

		txtAula = new JTextField("Aula");
		txtAula.setHorizontalAlignment(SwingConstants.CENTER);
		txtAula.setBounds(439, 178, 100, 20);
		contentPane.add(txtAula);
		txtAula.setColumns(10);
		agregarPlaceholder(txtAula, "Aula");

		txtrDescripcion = new JTextArea("Descripción - Obligatorio");
		txtrDescripcion.setLineWrap(true);
		txtrDescripcion.setWrapStyleWord(true);
		scrollDescripcion = new JScrollPane(txtrDescripcion);
		scrollDescripcion.setBounds(183, 231, 356, 113);
		contentPane.add(scrollDescripcion);
		agregarPlaceholder(txtrDescripcion, "Descripción - Obligatorio");

		// Botón para cargar imagen
		btnCargarImagen = new JButton("Carga la imagen");
		btnCargarImagen.setBackground(Color.LIGHT_GRAY);
		btnCargarImagen.setHorizontalAlignment(SwingConstants.LEFT);
		btnCargarImagen.setVerticalAlignment(SwingConstants.TOP);
		btnCargarImagen.setBounds(314, 387, 225, 23);
		contentPane.add(btnCargarImagen);
		btnCargarImagen.addActionListener(e -> {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setDialogTitle("Seleccionar imagen");

			int resultado = fileChooser.showOpenDialog(this);

			if (resultado == JFileChooser.APPROVE_OPTION) {
				File archivoSeleccionado = fileChooser.getSelectedFile();
				ImageIcon imagen = new ImageIcon(archivoSeleccionado.getAbsolutePath());

			}
		});

		// Imagen (perfilApp)
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(_11_ReportarIncidencia.class.getResource("/img/perfilApp.png")));
		lblImagen.setBounds(183, 391, 117, 89);
		contentPane.add(lblImagen);

		// Botón ayuda
		btnAyuda = new JButton("Ayuda");
		btnAyuda.setBackground(Color.LIGHT_GRAY);
		btnAyuda.setBounds(183, 525, 121, 23);
		contentPane.add(btnAyuda);
		btnAyuda.addActionListener(e -> {
			if (miControlador != null)
				miControlador.cambiarVentana(11, 6);
		});

		// Botón subir incidencia
		btnSubirIncidencia = new JButton("Subir Incidencia");
		btnSubirIncidencia.setBackground(Color.LIGHT_GRAY);
		btnSubirIncidencia.setBounds(408, 502, 131, 46);
		contentPane.add(btnSubirIncidencia);

		/**
		 * Acción al pulsar el botón "Subir Incidencia".
		 * 
		 * Realiza las siguientes operaciones: 1. Verifica que el Modelo y el
		 * Controlador estén inicializados para evitar errores. 2. Obtiene los datos
		 * ingresados por el usuario en los campos del formulario. 3. Valida que los
		 * campos obligatorios (Campus y Descripción) no estén vacíos ni contengan el
		 * texto placeholder. 4. Recupera el correo electrónico del usuario logueado
		 * desde el controlador. 5. Intenta insertar la incidencia en la base de datos
		 * mediante el modelo. 6. Muestra mensajes de confirmación o error según el
		 * resultado de la inserción. 7. En caso de éxito, limpia los campos y redirige
		 * al usuario a la ventana de "Mis Incidencias".
		 */
		btnSubirIncidencia.addActionListener(e -> {
			if (miModelo == null || miControlador == null) {
				JOptionPane.showMessageDialog(null, "Error interno: Modelo o Controlador no están inicializados.");
				return;
			}

			String campus = txtCampus.getText().trim();
			String edificio = txtEdificio.getText().trim();
			String piso = txtPiso.getText().trim();
			String aula = txtAula.getText().trim();
			String descripcion = txtrDescripcion.getText().trim();

			if (campus.isEmpty() || campus.equals("Campus - Obligatorio")) {
				JOptionPane.showMessageDialog(null, "El campo Campus es obligatorio.");
				return;
			}
			if (descripcion.isEmpty() || descripcion.equals("Descripción - Obligatorio")) {
				JOptionPane.showMessageDialog(null, "La descripción es obligatoria.");
				return;
			}

			String correo = miControlador.getUsuarioLogueado();
			boolean exito = miModelo.insertarIncidencia(campus, edificio, piso, aula, descripcion, correo, correo);

			if (exito) {
				JOptionPane.showMessageDialog(null, "Incidencia subida con éxito.");
				limpiarCampos();
				// Redirigir a Mis Incidencias y actualizar la lista
				miControlador.cargarMisIncidencias();
				miControlador.cambiarVentana(11, 13);
			} else {
				JOptionPane.showMessageDialog(null, "Error al subir la incidencia. Inténtalo de nuevo.");
			}
		});

		// Imagen de fondo
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_11_ReportarIncidencia.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 946, 659);
		contentPane.add(lblFondo);
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
	 * Añade un comportamiento de placeholder a un JTextField. Cuando el campo gana
	 * el foco y contiene el texto placeholder, se borra. Cuando el campo pierde el
	 * foco y está vacío, se vuelve a mostrar el placeholder.
	 * 
	 * @param campo            Campo de texto al que se le añade el placeholder.
	 * @param textoPlaceholder Texto que se mostrará como placeholder.
	 */
	private void agregarPlaceholder(JTextField campo, String textoPlaceholder) {
		campo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (campo.getText().equals(textoPlaceholder)) {
					campo.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (campo.getText().isEmpty()) {
					campo.setText(textoPlaceholder);
				}
			}
		});
	}

	/**
	 * Añade un comportamiento de placeholder a un JTextArea. Cuando el área gana el
	 * foco y contiene el texto placeholder, se borra. Cuando el área pierde el foco
	 * y está vacía, se vuelve a mostrar el placeholder.
	 * 
	 * @param area             Área de texto a la que se le añade el placeholder.
	 * @param textoPlaceholder Texto que se mostrará como placeholder.
	 */
	private void agregarPlaceholder(JTextArea area, String textoPlaceholder) {
		area.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (area.getText().equals(textoPlaceholder)) {
					area.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (area.getText().isEmpty()) {
					area.setText(textoPlaceholder);
				}
			}
		});
	}

	/**
	 * Limpia los campos del formulario de incidencia y vuelve a colocar los textos
	 * placeholder por defecto.
	 */
	private void limpiarCampos() {
		txtCampus.setText("Campus - Obligatorio");
		txtEdificio.setText("Edificio");
		txtPiso.setText("Piso");
		txtAula.setText("Aula");
		txtrDescripcion.setText("Descripción - Obligatorio");
	}

	/**
	 * Establece la instancia del modelo que usará la vista para interactuar con la
	 * base de datos y lógica.
	 * 
	 * @param miModelo Instancia del modelo.
	 */
	public void setModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	/**
	 * Establece la instancia del controlador que manejará las acciones y la lógica
	 * de la aplicación.
	 * 
	 * @param miControlador Instancia del controlador.
	 */
	public void setControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}
}