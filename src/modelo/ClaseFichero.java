package modelo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Clase ClaseFichero que gestiona la lectura y escritura de configuraciones en
 * un archivo de texto.
 * 
 * @author Alexandru Daniel Costea
 */
public class ClaseFichero {
	private static final String CONFIG_PATH = "src/config.ini";

	/**
	 * Lee la configuración desde un archivo y la devuelve como un mapa clave-valor.
	 *
	 * @return Un mapa que contiene las configuraciones leídas del archivo.
	 * @throws IOException Si ocurre un error al leer el archivo.
	 */
	public static Map<String, String> leerConfiguracion() throws IOException {
		Map<String, String> config = new HashMap<>();
		Properties props = new Properties();

		try (FileReader reader = new FileReader(CONFIG_PATH)) {
			props.load(reader);
			config.put("usr", props.getProperty("usr"));
			config.put("pwd", props.getProperty("pwd"));
			config.put("url", props.getProperty("url"));
		}

		return config;
	}

	/**
	 * Guarda la configuración proporcionada en un archivo.
	 *
	 * @param usr El nombre de usuario a guardar.
	 * @param pwd La contraseña a guardar.
	 * @param url La URL a guardar.
	 * @throws IOException Si ocurre un error al escribir en el archivo.
	 */
	public static void guardarConfiguracion(String usr, String pwd, String url) throws IOException {
		try (FileWriter writer = new FileWriter(CONFIG_PATH)) {
			writer.write("usr: " + usr + "\n");
			writer.write("pwd: " + pwd + "\n");
			writer.write("url: " + url + "\n");
		}
	}
}
