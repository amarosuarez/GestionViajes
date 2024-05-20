package metodos;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

	/**
	 * Función que modifica un viaje según su lugar
	 * 
	 * @param lugar Lugar al que buscar el viaje
	 * @return Devuelve si se ha modificado el viaje
	 */
	public static boolean modificaViaje(String lugar) {
		// Variable donde se almacena si se ha modificado el viaje
		boolean modificado = false;

		// Objeto donde se almacena el viaje a modificar
		Viaje viaje;

		// Lista donde se almacenará los viajes con ese destino
		List<Viaje> listaViajes = new ArrayList<>();

		// Variable donde se almacenará el viaje selecionado
		int viajeSeleccionado;

		// Variable donde se almacenará la opción a modificar
		int opcion;

		// Llamamos a la función que busca los viajes por lugar
		listaViajes = buscaViajeLugar(lugar);

		// Comprobamos que la lista no esté vacía
		if (!listaViajes.isEmpty()) {
			// Mostramos cuantos viajes se han encontrado
			int numViajes = listaViajes.size();

			System.out.println("Hay " + numViajes + (numViajes > 0 ? " viajes" : " viaje") + " con ese lugar");

			// Llamamos a la funcion que muestra los viajes encontrados
			muestraViajes(listaViajes);

			do {
				viajeSeleccionado = Utiles.leeNumero("¿Qué viaje deseas modificar? (0 para salir)");
			} while (viajeSeleccionado < 0 || viajeSeleccionado > numViajes);

			// Comprobamos que no desee salir
			if (viajeSeleccionado != 0) {
				// Inicializamos el objeto viaje al viaje a modificar
				viaje = listaViajes.get(viajeSeleccionado - 1);

				do {

					// Mostramos el menú de opciones modificables y leemos la opción
					opcion = Utiles.leeNumero(
							"¿Qué deseas modificar?\n1. Precio\n2. Fecha\nPara salir pulse cualquier otro número");

					switch (opcion) {
					case 1: // Modifica el precio
						modificaPrecio(viaje);
						break;
					case 2: // Modifica la fecha
						modificaFecha(viaje);
						break;
					default: // Sale del programa
						System.out.println("Saliendo...");
						break;
					}

				} while (opcion == 1 || opcion == 2);

				modificado = true;
			} else {
				System.out.println("Saliendo...");
			}

		} else {
			System.out.println("No se han encontrado viajes con ese lugar");
		}

		// Devolvemos si se ha modificado el viaje
		return modificado;
	}

	/**
	 * Función que modifica la fecha de un viaje
	 * 
	 * @param viaje Viaje a modificar la fecha
	 */
	private static void modificaFecha(Viaje viaje) {
		int dia, mes, año;

		LocalDateTime hoy = LocalDateTime.now();
		int actualDay = hoy.getDayOfMonth();
		int actualMonth = hoy.getMonthValue();
		int actualYear = hoy.getYear();

		do {
			año = Utiles.leeNumero("Año del viaje (Debe ser mayor o igual que " + actualYear + ")");
		} while (año < actualYear);

		do {
			mes = Utiles.leeNumero("Mes del viaje (Entre 1 y 12)");
		} while (!Utiles.mesDentroRango(mes, actualMonth, año, actualYear));

		do {
			dia = Utiles.leeNumero("Día del viaje");
		} while (dia < 1 || dia >= Utiles.diaMaxMes(mes, año)
				|| (año == actualYear && mes == actualMonth && dia < actualDay));

		String diaS = (dia < 10 ? "0" : "") + dia;
		String mesS = (mes < 10 ? "0" : "") + mes;

		String fecha = diaS + "/" + mesS + "/" + año;

		viaje.setFecha(fecha);

		// Mostramos el mensaje
		System.out.println("Fecha modificada");
	}

	/**
	 * Función que modifica el precio de un viaje
	 * 
	 * @param viaje Viaje al que se le modificará el precio
	 */
	private static void modificaPrecio(Viaje viaje) {
		// Variable donde se almacenará el nuevo precio
		double precio;

		do {
			// Le pedimos el nuevo precio
			precio = Utiles.leeDoble("Introduzca el nuevo precio (Debe ser mayor que 0)");
		} while (precio <= 0);

		// Cambiamos el precio
		viaje.setPrecio(precio);

		// Mostramos el mensaje
		System.out.println("Precio modificado");
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
			System.out.println("Viaje " + (i + 1));
			System.out.println(viaje);
			System.out.println();
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

	/**
	 * Función que comprueba si hay un viaje igual y devuelve su indice
	 * 
	 * @param viaje Objeto viaje a comparar
	 * @return Devuelve si se ha encontrado un viaje igual, su indice
	 */
	private static int indexViajeIgual(Viaje viaje) {
		// Variable que almacena si se ha encontrado el viaje
		boolean encontrado = false;

		// Variable que almacena el indice donde se ha encontrado el viaje
		int pos = -1;

		// Entero donde se almacena el indice
		int indice = 0;

		// Hacemos un while mientras no se encuentre y no se salga de la longitud
		while (indice < listaViajes.size() && !encontrado) {
			if (viaje.equals(listaViajes.get(indice))) {
				pos = indice;
				encontrado = true;
			}
			indice++;
		}

		// Devuelve si se ha encontrado un viaje
		return pos;
	}

	/**
	 * Función que comprueba si hay un viaje igual
	 * 
	 * @param viaje Objeto viaje a comparar
	 * @return Devuelve si se ha encontrado un viaje igual
	 */
	private static List<Viaje> buscaViajeLugar(String lugar) {

		// CReamos una nueva lista para buscar los viajes
		List<Viaje> listaEncontrados = new ArrayList<>();

		// Creamos un bucle for each para que conferme encuentre un viaje lo añada
		for (Viaje viaje : listaViajes) {
			if (viaje.getLugar().equalsIgnoreCase(lugar)) {
				listaEncontrados.add(viaje);
			}
		}

		// Devuelve los viajes encontrados
		return listaEncontrados;
	}

	/**
	 * Función que elimina un viaje según su lugar
	 * 
	 * @param lugar Lugar del viaje a eliminar
	 * @return Devuelve si se ha eliminado el viaje
	 */
	public static boolean eliminaViaje(String lugar) {
		// Variable donde se almacena si se ha modificado el viaje
		boolean eliminado = false;

		// Lista donde se almacenará los viajes con ese destino
		List<Viaje> listaViajesEliminar = new ArrayList<>();

		// Variable donde se almacenará el viaje selecionado
		int opcion;

		// Llamamos a la función que busca los viajes por lugar
		listaViajesEliminar = buscaViajeLugar(lugar);

		// Comprobamos que la lista no esté vacía
		if (!listaViajesEliminar.isEmpty()) {
			// Mostramos cuantos viajes se han encontrado
			int numViajes = listaViajesEliminar.size();

			System.out.println("Hay " + numViajes + (numViajes > 0 ? " viajes" : " viaje") + " con ese lugar");

			// Llamamos a la funcion que muestra los viajes encontrados
			muestraViajes(listaViajesEliminar);

			do {
				opcion = Utiles.leeNumero("¿Qué viaje deseas eliminar? (0 para salir)");
			} while (opcion < 0 || opcion > numViajes);

			// Comprobamos que no quiera salir
			if (opcion != 0) {
				Viaje viaje = listaViajesEliminar.get(opcion - 1);

				int indice = indexViajeIgual(viaje);

				if (indice != -1) {
					listaViajes.remove(indice);
					eliminado = true;
				}

			} else {
				System.out.println("Saliendo...");
			}

		} else {
			System.out.println("No se ha encontrado nigún viaje con ese destino");
		}

		// Devolvemos si se ha modificado el viaje
		return eliminado;
	}

	/**
	 * Función que comprueba si hay cambios sin guardar
	 * 
	 * @return Devuelve si hay algún viaje sin guardar
	 */
	public static boolean cambiosGuardados() {
		// Obtenemos los viajes del archivo
		List<Viaje> viajesEscritos = LecturaArchivo.obetenerViajes();

		// Variable que almacena si ha encontrado un viaje sin guardar
		boolean viajeSinGuardar = false;
		
		// Variable que almacena si se han guardado los cambios
		boolean guardados = true;

		// Variable que almacena el indice
		int index = 0;

		// Comprobamos si la longitud coincide
		if (viajesEscritos.size() == listaViajes.size()) {

			// Obtenemos los viajes del archivo y de la lista
			Viaje viajeEscrito;
			Viaje viaje;

			// Comprobamos que todos sean iguales
			while (index < viajesEscritos.size() && !viajeSinGuardar) {

				// Obtenemos los viajes del archivo y de la lista
				viajeEscrito = viajesEscritos.get(index);
				viaje = listaViajes.get(index);

				// Comprobamos si son iguales
				if (!viaje.equals(viajeEscrito) || viaje.getPrecio() != viajeEscrito.getPrecio()) {
					viajeSinGuardar = true;
					guardados = false;
				}

				index++;
			}

		} else {
			guardados = false;
		}

		// Devolvemos si hay algún viaje sin guardar
		return guardados;
	}
}