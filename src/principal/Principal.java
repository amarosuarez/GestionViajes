package principal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import metodos.ArrayViajes;
import metodos.LecturaArchivo;
import metodos.Utiles;
import objetos.Viaje;

/**
 * Classe principal donde se almacena el main se recojen datos y se llama a las
 * funciones de cada clase
 */
public class Principal {
	/**
	 * metdodo main encargado de llamar a las funciones
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		char seguir = 's';
		ArrayViajes.listaViajes = LecturaArchivo.obetenerViajes();
		do {
			opcion = imprimirMenu(sc);
			switch (opcion) {
			case 1: {
				imprimirViajes();
				break;
			}
			case 2: {
				nuevoViaje(sc);
				break;
			}
			case 3: {
				modificarViaje(sc);
				break;
			}
			case 4: {
				eliminarViaje(sc);
				break;
			}
			case 5: {
				guardarCambios(sc);
				break;
			}
			case 6: {
				if (!ArrayViajes.cambiosGuardados()) {
					System.out.println("Hay cambios sin guardar, ¿desea salir? (S para salir, otra para volver)");
					
					seguir = sc.next().toLowerCase().charAt(0);
					
				}
				break;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);

			}

		} while (opcion != 6 || seguir != 's');
		
		System.out.println("Ha salido del programa");
	}

	public static void eliminarViaje(Scanner sc) {
		sc.nextLine();
		System.out.println("Indica el lugar del viaje");
		String lugar = sc.nextLine();

		if (ArrayViajes.eliminaViaje(lugar)) {
			System.out.println("Viaje eliminado");
		} else {
			System.out.println("No se ha eliminado el viaje");
		}
	}

	public static void modificarViaje(Scanner sc) {
		sc.nextLine();
		System.out.println("Indica el lugar del viaje");
		String lugar = sc.nextLine();

		if (ArrayViajes.modificaViaje(lugar)) {
			System.out.println("Viaje modificado");
		} else {
			System.out.println("No se ha modificado el viaje");
		}
	}

	/**
	 * funcion encargada de imprimir el menu y registar una oppcion escogida por el
	 * usuario
	 * 
	 * @param sc
	 * @return
	 */
	public static int imprimirMenu(Scanner sc) {
		int opcion;
		do {
			System.out.println("╔═════════════════════════════════════╗");
			System.out.println("║          Menú de Opciones           ║");
			System.out.println("╠═════════════════════════════════════╣");
			System.out.println("║ 1. Listado de todos los viajes      ║");
			System.out.println("║ 2. Añadir un nuevo viaje            ║");
			System.out.println("║ 3. Modificar precio o fecha         ║");
			System.out.println("║    de un viaje existente            ║");
			System.out.println("║ 4. Eliminar un viaje existente      ║");
			System.out.println("║ 5. Guardar cambios                  ║");
			System.out.println("║ 6. Salir                            ║");
			System.out.println("╚═════════════════════════════════════╝");

			opcion = sc.nextInt();
		} while (opcion < 0 && opcion > 5);
		return opcion;
	}

	/**
	 * Esta funcion imprimi la listade viajes
	 */
	private static void imprimirViajes() {
		ArrayViajes.mostrarViajes();

	}

	/**
	 * se encarga de llamar a constructor de viajes crear un nuevo objeto y llamar a
	 * una funcion para almacenarlo
	 * 
	 * @param sc
	 */
	private static void nuevoViaje(Scanner sc) {
		double precio;
		int dia, mes, año;
		String lugar = "";

		LocalDateTime hoy = LocalDateTime.now();
		int actualDay = hoy.getDayOfMonth();
		int actualMonth = hoy.getMonthValue();
		int actualYear = hoy.getYear();

		sc.nextLine();

		System.out.println("Ingrese el lugar del viaje:");
		lugar = sc.nextLine();

		do {
			System.out.println("Precio del viaje (Debe ser mayor que 0)");
			precio = sc.nextDouble();
		} while (precio <= 0);

		do {
			System.out.println("Año del viaje (Debe ser mayor o igual que " + actualYear + ")");
			año = sc.nextInt();
		} while (año < actualYear);

		do {
			System.out.println("Mes del viaje (Entre 1 y 12)");
			mes = sc.nextInt();
		} while (!Utiles.mesDentroRango(mes, actualMonth, año, actualYear));

		do {
			System.out.println("Día del viaje");
			dia = sc.nextInt();
		} while (dia < 1 || dia >= Utiles.diaMaxMes(mes, año)
				|| (año == actualYear && mes == actualMonth && dia < actualDay));

		String diaS = (dia < 10 ? "0" : "") + dia;
		String mesS = (mes < 10 ? "0" : "") + mes;

		String fecha = diaS + "/" + mesS + "/" + año;

		Viaje viaje = new Viaje(lugar, fecha, precio);
		if (ArrayViajes.nuevoViaje(viaje)) {
			System.out.println("Se ha añadido el viaje. ");
		} else {
			System.out.println("No se ha añadido el viaje. ");
		}
	}

	private static void guardarCambios(Scanner sc) {
		if (LecturaArchivo.escrituraViaje((List<Viaje>) ArrayViajes.listaViajes)) {
			System.out.println("Cambios guardados");
		} else {
			System.out.println("No se han podido guardar los cambios");
		}
	}

}
