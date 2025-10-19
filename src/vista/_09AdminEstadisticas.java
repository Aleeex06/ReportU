
package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import controlador.Controlador;
import modelo.Modelo;

/**
 * Clase `_09AdminEstadisticas` que representa la ventana de estadísticas para
 * el administrador. Contiene elementos de la interfaz gráfica como gráficos,
 * filtros y botones de navegación.
 * 
 * @author Manuel Zaragoza Guijarro
 */
public class _09AdminEstadisticas extends JFrame implements Vista {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Modelo miModelo;
	private Controlador miControlador;
	private JButton btnUsuario;
	private JButton btnVolver;

	/**
	 * Constructor de la clase. Configura la ventana y sus componentes.
	 */
	public _09AdminEstadisticas() {
		setTitle("Página Principal - Admin Estadísticas");
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
		btnVolver.addActionListener(e -> miControlador.cambiarVentana(7, 0));

		// Botones de navegación
		JButton btnPaginaPrincipal = new JButton("Página Principal");
		btnPaginaPrincipal.setBackground(Color.LIGHT_GRAY);
		btnPaginaPrincipal.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnPaginaPrincipal.setBounds(20, 54, 133, 28);
		getContentPane().add(btnPaginaPrincipal);
		btnPaginaPrincipal.addActionListener(e -> miControlador.cambiarVentana(8, 7));

		JButton btnMisIncidencias = new JButton("Mis Incidencias");
		btnMisIncidencias.setBackground(Color.LIGHT_GRAY);
		btnMisIncidencias.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnMisIncidencias.setBounds(179, 54, 142, 28);
		getContentPane().add(btnMisIncidencias);
		btnMisIncidencias.addActionListener(e -> miControlador.cambiarVentana(8, 13));

		JButton btnReportar = new JButton("Reportar Incidencia");
		btnReportar.setBackground(Color.LIGHT_GRAY);
		btnReportar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnReportar.setBounds(331, 54, 133, 28);
		getContentPane().add(btnReportar);
		btnReportar.addActionListener(e -> miControlador.cambiarVentana(8, 11));

		JButton btnEstadisticas = new JButton("Estadísticas");
		btnEstadisticas.setBackground(Color.LIGHT_GRAY);
		btnEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEstadisticas.setBounds(474, 54, 131, 28);
		getContentPane().add(btnEstadisticas);
		btnEstadisticas.addActionListener(e -> miControlador.cambiarVentana(8, 9));

		btnUsuario = new JButton("Usuario");
		btnUsuario.setBackground(Color.LIGHT_GRAY);
		btnUsuario.setBounds(617, 11, 157, 71);
		contentPane.add(btnUsuario);
		btnUsuario.addActionListener(e -> miControlador.cambiarVentana(9, 5));

		// Gráficos de estadísticas
		JLabel grafico1 = new JLabel("Gráfico de pastel");
		grafico1.setOpaque(true);
		grafico1.setBackground(Color.LIGHT_GRAY);
		grafico1.setHorizontalAlignment(SwingConstants.CENTER);
		grafico1.setBounds(179, 110, 200, 184);
		getContentPane().add(grafico1);

		JLabel lbl1 = new JLabel("Incidencias por mes resueltas");
		lbl1.setForeground(Color.WHITE);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setBounds(179, 305, 200, 20);
		getContentPane().add(lbl1);

		JLabel grafico2 = new JLabel("Gráfico de línea");
		grafico2.setOpaque(true);
		grafico2.setBackground(Color.LIGHT_GRAY);
		grafico2.setHorizontalAlignment(SwingConstants.CENTER);
		grafico2.setBounds(446, 110, 200, 184);
		getContentPane().add(grafico2);

		JLabel lbl2 = new JLabel("Incidencias resueltas");
		lbl2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl2.setForeground(Color.WHITE);
		lbl2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl2.setBounds(456, 305, 200, 20);
		getContentPane().add(lbl2);

		JLabel grafico3 = new JLabel("Gráfico de barras");
		grafico3.setOpaque(true);
		grafico3.setBackground(Color.LIGHT_GRAY);
		grafico3.setHorizontalAlignment(SwingConstants.CENTER);
		grafico3.setBounds(179, 336, 200, 184);
		getContentPane().add(grafico3);

		JLabel lbl3 = new JLabel("Gráfico de barras");
		lbl3.setForeground(Color.WHITE);
		lbl3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl3.setBounds(179, 530, 200, 20);
		getContentPane().add(lbl3);

		JLabel grafico4 = new JLabel("Gráfico de columnas");
		grafico4.setOpaque(true);
		grafico4.setBackground(Color.LIGHT_GRAY);
		grafico4.setHorizontalAlignment(SwingConstants.CENTER);
		grafico4.setBounds(446, 336, 200, 184);
		getContentPane().add(grafico4);

		JLabel lbl4 = new JLabel("Incidencias al mes");
		lbl4.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl4.setForeground(Color.WHITE);
		lbl4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl4.setBounds(446, 530, 200, 20);
		getContentPane().add(lbl4);

		// Fondo de la ventana
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(_09AdminEstadisticas.class.getResource("/img/Fondo.jpg")));
		lblFondo.setBounds(-11, -64, 964, 659);
		getContentPane().add(lblFondo);
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
	 * Método principal para lanzar la aplicación.
	 *
	 * @param args Argumentos de la línea de comandos.
	 */
	public static void main(String[] args) {
		_09AdminEstadisticas frame = new _09AdminEstadisticas();
		frame.setVisible(true);
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