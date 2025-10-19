package vista;

/**
 * Clase que representa un filtro para buscar incidencias. Contiene campos para
 * filtrar por campus, edificio, piso, aula, texto libre y estado.
 */
public class FiltroIncidencia {
	private String campus;
	private String edificio;
	private String piso;
	private String aula;
	private String varios;
	private String estado;

	/**
	 * Constructor vacío para crear un filtro sin valores iniciales.
	 */
	public FiltroIncidencia() {
		// Constructor vacío
	}

	/**
	 * Constructor con todos los parámetros para inicializar el filtro.
	 * 
	 * @param campus   Nombre del campus.
	 * @param edificio Nombre del edificio.
	 * @param piso     Piso dentro del edificio.
	 * @param aula     Aula específica.
	 * @param varios   Texto libre para búsqueda variada.
	 * @param estado   Estado de la incidencia.
	 */
	public FiltroIncidencia(String Campus, String edificio, String piso, String aula, String varios, String estado) {
		this.campus = Campus;
		this.edificio = edificio;
		this.piso = piso;
		this.aula = aula;
		this.varios = varios;
		this.estado = estado;
	}

	/** @return el campus del filtro */
	public String getCampus() {
		return campus;
	}

	/** @param campus el campus a establecer */
	public void setCampus(String campus) {
		this.campus = campus;
	}

	/** @return el edificio del filtro */
	public String getEdificio() {
		return edificio;
	}

	/** @param edificio el edificio a establecer */
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}

	/** @return el piso del filtro */
	public String getPiso() {
		return piso;
	}

	/** @param piso el piso a establecer */
	public void setPiso(String piso) {
		this.piso = piso;
	}

	/** @return el aula del filtro */
	public String getAula() {
		return aula;
	}

	/** @param aula el aula a establecer */
	public void setAula(String aula) {
		this.aula = aula;
	}

	/** @return el texto libre para búsqueda */
	public String getVarios() {
		return varios;
	}

	/** @param varios el texto libre a establecer */
	public void setVarios(String varios) {
		this.varios = varios;
	}

	/** @return el estado de la incidencia */
	public String getEstado() {
		return estado;
	}

	/** @param estado el estado a establecer */
	public void setEstado(String estado) {
		this.estado = estado;
	}
}