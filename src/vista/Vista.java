package vista;

import controlador.Controlador;
import modelo.Modelo;

/**
 * Interfaz que define los métodos básicos que debe implementar una vista en la
 * aplicación para poder recibir el modelo y el controlador.
 */
public interface Vista {
	/**
	 * Establece el modelo que usará la vista para interactuar con los datos.
	 * 
	 * @param miModelo instancia del modelo
	 */
	void setModelo(Modelo miModelo);

	/**
	 * Establece el controlador que manejará la lógica y eventos de la vista.
	 * 
	 * @param miControlador instancia del controlador
	 */
	void setControlador(Controlador miControlador);
}
