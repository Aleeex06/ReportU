package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import controlador.Controlador;
import modelo.Modelo;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase _10_IncidenciaIndividual que representa la ventana para visualizar una
 * incidencia individual. Contiene elementos de la interfaz gráfica como
 * botones, etiquetas y campos de texto.
 * 
 * @author Ivan Garcia Ledesma
 */
public class _10_IncidenciaIndividual extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Modelo miModelo; // Modelo asociado a la vista
	private Controlador miControlador; // Controlador para manejar eventos
	private JButton btnVolver; // Botón para volver a la ventana anterior

	private int likes = 0; // Contador de likes
	private JLabel lblLikes; // Etiqueta para mostrar el número de likes
	private JButton btnUsuario;

	private JLabel lblCampus, lblEdificio, lblPiso, lblAula, lblEstado;
	private JTextArea txtDescripcion;
	private Object[] datosIncidenciaActual;

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
	public _10_IncidenciaIndividual() {
		setTitle("Incidencia Individual");
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
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(10, 0));

		// Botones de navegación
		JButton btnPaginaPrincipal = new JButton("Página Principal");
		btnPaginaPrincipal.setBackground(Color.LIGHT_GRAY);
		btnPaginaPrincipal.setBounds(10, 54, 169, 28);
		contentPane.add(btnPaginaPrincipal);
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(10, 7));

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setBounds(224, 54, 169, 28);
		contentPane.add(btnMisIncidencias);
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(10, 13));

		JButton btnReportarIncidencia = new JButton("Reportar Incidencia");
		btnReportarIncidencia.setBackground(Color.LIGHT_GRAY);
		btnReportarIncidencia.setBounds(424, 54, 169, 28);
		contentPane.add(btnReportarIncidencia);
		btnReportarIncidencia.addActionListener(e -> miControlador.cambiarVentana(10, 11));

		btnUsuario = new JButton("Usuario");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.setBounds(617, 11, 157, 71);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(10, 5));

		// Imagen de la incidencia
		JLabel lblImagenIncidencia = new JLabel("");
		lblImagenIncidencia.setIcon(new ImageIcon(_10_IncidenciaIndividual.class.getResource("/img/Incidencia.jpg")));
		lblImagenIncidencia.setBounds(153, 128, 462, 316);
		contentPane.add(lblImagenIncidencia);

		// Etiquetas para mostrar información de la incidencia
		lblCampus = new JLabel("CAMPUS");
		lblCampus.setForeground(Color.WHITE);
		lblCampus.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCampus.setBounds(153, 106, 150, 14);
		contentPane.add(lblCampus);

		lblEdificio = new JLabel("EDIFICIO");
		lblEdificio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEdificio.setForeground(Color.WHITE);
		lblEdificio.setBounds(302, 106, 117, 14);
		contentPane.add(lblEdificio);

		lblPiso = new JLabel("PISO");
		lblPiso.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPiso.setForeground(Color.WHITE);
		lblPiso.setBounds(444, 106, 60, 14);
		contentPane.add(lblPiso);

		lblAula = new JLabel("AULA");
		lblAula.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAula.setForeground(Color.WHITE);
		lblAula.setBounds(526, 106, 70, 14);
		contentPane.add(lblAula);

		lblEstado = new JLabel("ESTADO");
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setVerticalAlignment(SwingConstants.TOP);
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setBounds(10, 140, 127, 22);
		contentPane.add(lblEstado);

		lblLikes = new JLabel("Likes: 0");
		lblLikes.setForeground(Color.WHITE);
		lblLikes.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLikes.setBounds(163, 461, 58, 28);
		contentPane.add(lblLikes);

		// Botón para dar "like" a la incidencia
		JButton btnLike = new JButton("LIKE");
		btnLike.setBackground(Color.LIGHT_GRAY);
		btnLike.setBounds(75, 461, 70, 28);
		contentPane.add(btnLike);

		// Acción del botón "like"
		btnLike.addActionListener(e -> {
			likes++;
			lblLikes.setText("Likes: " + likes);
		});

		// Botón para marcar la incidencia como resuelta
		JButton btnResuelto = new JButton("RESUELTO");
		btnResuelto.setBackground(Color.LIGHT_GRAY);
		btnResuelto.setBounds(231, 461, 108, 28);
		contentPane.add(btnResuelto);
		btnResuelto.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Has marcado la incidencia como resuelta, ¡gracias por tu apoyo!",
					"Incidencia resuelta", JOptionPane.INFORMATION_MESSAGE);
		});

		// Botón para enviar una notificación
		JButton btnNotificacion = new JButton("NOTIFICACION");
		btnNotificacion.setBackground(Color.LIGHT_GRAY);
		btnNotificacion.setBounds(347, 461, 142, 28);
		contentPane.add(btnNotificacion);

		btnNotificacion.addActionListener(e -> {
			if (datosIncidenciaActual != null) {
				if (miModelo.añadirNotificacion(datosIncidenciaActual)) {
					JOptionPane.showMessageDialog(this, "Incidencia añadida a Notificaciones");
				} else {
					JOptionPane.showMessageDialog(this, "La incidencia ya está en Notificaciones");
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay datos de incidencia para añadir");
			}
		});

		// Botón para agregar la incidencia a favoritos
		JButton btnFavoritos = new JButton("FAVORITOS");
		btnFavoritos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFavoritos.setBackground(Color.LIGHT_GRAY);
		btnFavoritos.setBounds(497, 461, 108, 28);
		contentPane.add(btnFavoritos);

		btnFavoritos.addActionListener(e -> {
			if (datosIncidenciaActual != null) {
				if (miModelo.añadirFavorito(datosIncidenciaActual)) {
					JOptionPane.showMessageDialog(this, "Incidencia añadida a Favoritos");
				} else {
					JOptionPane.showMessageDialog(this, "La incidencia ya está en Favoritos");
				}
			} else {
				JOptionPane.showMessageDialog(this, "No hay datos de incidencia para añadir");
			}

		});

		// Etiqueta para la justificación
		JLabel lblJustificacion = new JLabel("JUSTIFICACION:");
		lblJustificacion.setVerticalAlignment(SwingConstants.TOP);
		lblJustificacion.setBounds(189, 691, 251, 45);
		contentPane.add(lblJustificacion);

		// ComboBox para cambiar el estado de la incidencia
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Cambio de Estado", "Resuelto", "Reportado" }));
		comboBox.setBounds(189, 659, 251, 21);
		contentPane.add(comboBox);

		// Botón para confirmar el cambio de estado
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(479, 713, 117, 23);
		contentPane.add(btnConfirmar);

		// Área de texto para la descripción de la incidencia
		txtDescripcion = new JTextArea();
		txtDescripcion.setLineWrap(true);
		txtDescripcion.setWrapStyleWord(true);
		txtDescripcion.setEditable(false); // Para que solo sea de lectura
		txtDescripcion.setBounds(153, 507, 452, 45);
		txtDescripcion.setText("DESCRIPCION:");
		contentPane.add(txtDescripcion);

		// Fondo de la ventana
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_10_IncidenciaIndividual.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(0, -32, 843, 585);
		contentPane.add(lblFondo);
	}

	/**
	 * Muestra los datos de una incidencia en los componentes visuales
	 * correspondientes.
	 * 
	 * Se espera que el array 'datos' contenga los campos en el siguiente orden:
	 * USUARIO, DESCRIPCIÓN, FECHA, LIKES, ESTADO, AULA, PISO, EDIFICIO, CAMPUS.
	 * 
	 * @param datos Array de objetos con la información de la incidencia.
	 */
	public void mostrarDatosIncidencia(Object[] datos) {
		this.datosIncidenciaActual = datos; // Guardar los datos de la incidencia

		txtDescripcion.setText(datos[1].toString()); // Descripción
		lblLikes.setText("Likes: " + datos[3].toString());
		lblEstado.setText("Estado: " + datos[4].toString());
		lblAula.setText("Aula: " + datos[5].toString());
		lblPiso.setText("Piso: " + datos[6].toString());
		lblEdificio.setText("Edificio: " + datos[7].toString());
		lblCampus.setText("Campus: " + datos[8].toString());
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
}