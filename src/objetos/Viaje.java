package objetos;

/**
 * Clase que contiene los métodos y atributos de un viaje
 * 
 * @author Amaro Suárez
 * @version 1.0
 */
public class Viaje {

	/**
	 * Atributo que almacena el lugar del viaje
	 */
	private String lugar = "";

	/**
	 * Atributo que almacena la fecha del viaje
	 */
	private String fecha = "";

	/**
	 * Atributo que almacena el precio del viaje
	 */
	private double precio;

	/**
	 * Constructor con parámetros lugar, fecha y precio
	 * 
	 * @param lugar  Cadena que almacena el lugar del viaje
	 * @param fecha  Cadena que almacena la fecha del viaje
	 * @param precio Doble que almacena el precio del viaje
	 */
	public Viaje(String lugar, String fecha, double precio) {
		// Comprobamos que el lugar no sea null ni vacío
		if (lugar != null && !lugar.isEmpty()) {
			this.lugar = lugar;
		}

		// Comprobamos que la fecha no sea null ni vacía
		if (fecha != null && !fecha.isEmpty()) {
			this.fecha = fecha;
		}

		// Comprobamos que el precio no sea menor que 0
		if (precio >= 0) {
			this.precio = precio;
		}
	}

	/**
	 * Función que obtiene el lugar
	 * 
	 * @return Devuelve el lugar
	 */
	public String getLugar() {
		return this.lugar;
	}

	/**
	 * Función que almacena el lugar
	 * 
	 * @param lugar Cadena que almacena el lugar
	 */
	public void setLugar(String lugar) {
		// Comprobamos que el lugar no sea null ni vacío
		if (lugar != null && !lugar.isEmpty()) {
			this.lugar = lugar;
		}
	}

	/**
	 * Función que obtiene la fecha
	 * 
	 * @return Devuelve la fecha
	 */
	public String getFecha() {
		return this.fecha;
	}

	/**
	 * Función que almacena la fecha
	 * 
	 * @param fecha Cadena que almacena la fecha
	 */
	public void setFecha(String fecha) {
		// Comprobamos que la fecha no sea null ni vacía
		if (fecha != null && !fecha.isEmpty()) {
			this.fecha = fecha;
		}
	}

	/**
	 * Función que obtiene el precio
	 * 
	 * @return Devuelve el precio
	 */
	public double getPrecio() {
		return this.precio;
	}

	/**
	 * Función que almacena el precio
	 * 
	 * @param precio Double que almacena el precio
	 */
	public void setPrecio(double precio) {
		// Comprobamos que el precio no sea menor que 0
		if (precio >= 0) {
			this.precio = precio;
		}
	}
	
	/**
	 * Función que devuelve la información del viaje
	 * 
	 * @return Devuelve la información del viaje
	 */
	@Override
	public String toString() {
		// Variable que almacena la información del viaje
		String info = "";
		
		// Almacenamos la información
		info += "\nLugar: " + this.lugar;
		info += "\nFecha: " + this.fecha;
		info += "\nPrecio: " + this.precio;
		
		// Devuelve la información
		return info;
	}
}
