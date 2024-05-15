package metodos;

import java.util.ArrayList;
import java.util.List;

import objetos.Viaje;

/**
 * Clase que contendra el listado de viajes disponibles con sus respectivos
 * datos.
 */
public class ArrayViajes {

	static List<Viaje> listaViajes = new ArrayList<>();

// Funcion que muestra los viajes
	public static void mostrarViajes() {
		for (Viaje viaje : listaViajes) {
			System.out.println(viaje);
		}
	}

	/**
	 * Función que añade un nuevo viaje, y devuelve si se ha podido añadir
	 * 
	 * @param viaje Objeto viaje a añadir
	 * @return Devuelve si se ha podido añadir el viaje
	 */
	public static boolean nuevoViaje(Viaje viaje) {
		// Variable donde se almacenará si se ha añadido el viaje
		boolean anyadido = false;

		// Comprobamos que no haya un viaje igual
		if (!buscaViajeIgual(viaje)) {
			// Añadimos el viaje
			listaViajes.add(viaje);

			// Cambiamos el boolean+
			anyadido = true;
		}

		// Devuelve si se ha podido añadir
		return anyadido;
	}

	/**
	 * Función que comprueba si hay un viaje igual
	 * 
	 * @param viaje Objeto viaje a comparar
	 * @return Devuelve si se ha encontrado un viaje igual
	 */
	private static boolean buscaViajeIgual(Viaje viaje) {
		// Variable que almacena si ha encontrado un viaje igual
		boolean encontrado = false;

		// Entero donde se almacena el indice
		int indice = 0;

		// Hacemos un while mientras no se encuentre y no se salga de la longitud
		while (indice < listaViajes.size() && !encontrado) {
			if (viaje.equals(listaViajes.get(indice))) {
				encontrado = true;
			}
			indice++;
		}

		// Devuelve si se ha encontrado un viaje
		return encontrado;
	}

}