package metodos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import objetos.Viaje;

/**
 * Clase que contendra el listado de viajes disponibles con sus respectivos
 * datos.
 */
public class ArrayViajes {

	/**
	 * Atributo que almacena la lista de los viajes
	 */
	public static List<Viaje> listaViajes = new ArrayList<>();

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

	public static boolean modificaViaje(String lugar) {
		// Variable donde se almacena si se ha modificado el viaje
		boolean modificado = false;

		// Lista donde se almacenará los viajes con ese destino
		List<Viaje> listaViajes = new ArrayList<>();
		
		// Variable donde se almacenará el viaje selecionado
		int opcion;

		// Llamamos a la función que busca los viajes por lugar
		listaViajes = buscaViajesLugar(lugar);

		// Comprobamos que la lista no esté vacía
		if (!listaViajes.isEmpty()) {
			// Mostramos cuantos viajes se han encontrado
			int numViajes = listaViajes.size();

			System.out.println("Hay " + numViajes + (numViajes > 0 ? " viajes" : " viaje") + " con ese lugar");
			
			// Llamamos a la funcion que muestra los viajes encontrados
			muestraViajes(listaViajes);
			
			do {
				opcion = leeNumero("¿Qué viaje deseas modificar?");
			} while (opcion <= 0 || opcion >= numViajes);
			
			// Llama a la que modifica
		}

		// Devolvemos si se ha modificado el viaje
		return modificado;
	}
	
	/**
	 * Función que muestra un mensaje y lee un número
	 * 
	 * @param mensaje Mensaje a mostrar al usuario
	 * @return Devuelve el número leído
	 */
	private static int leeNumero(String mensaje) {
		// Variable donde se almacena el número a leer
		int num = -1;
		
		boolean valorCorrecto = false;
		
		// Creamos un Scanner 
		Scanner sc = new Scanner(System.in);
		
		do {
			// Mostramos el mensaje
			try {
				System.out.println(mensaje);
				num = sc.nextInt();
				valorCorrecto = true;
			} catch(InputMismatchException e) {
				valorCorrecto = false;
				System.out.println("Valor incorrecto");
			}
		} while (!valorCorrecto);
		
		// Limpiamos y cerramos el Scanner
		sc.nextLine();
		sc.close();
		
		// Devolvemos el número
		return num;
	}

	/**
	 * Función que muestra los viajes encontrados por pantalla
	 * 
	 * @param listaViajes Lista que contiene los viajes encontrados
	 */
	private static void muestraViajes(List<Viaje> listaViajes) {
		Viaje viaje;
		
		for (int i = 0; i < listaViajes.size(); i++) {
			viaje = listaViajes.get(i);
			System.out.println("Viaje " + (i+1));
			System.out.println(viaje);
		}
		
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