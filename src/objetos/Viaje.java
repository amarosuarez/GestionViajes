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
	private int precio;
	
	/**
	 * Constructor con parámetros lugar, fecha y precio
	 * 
	 * @param lugar Cadena que almacena el lugar del viaje
	 * @param fecha Cadena que almacena la fecha del viaje
	 * @param precio Entero que almacena el precio del viaje
	 */
	public Viaje(String lugar, String fecha, int precio) {
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
	
}
