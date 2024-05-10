package principal;

import java.util.Scanner;

/**
 * 
 */

/**
 * 
 */
public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int opcion = 0;
		do {
			opcion = imprimirMenu(sc);
			switch (opcion) {
			case 1: {
				imprimirViajes();
				break;
			}
			case 2: {
				nuevoViejae(sc);
				break;
			}
			case 3: {
				modificarViaje(sc);
				break;
			}
			case 4: {
				eliminarViajes(sc);
				break;
			}
			case 5: {
				System.out.println("Ha salido del programa");
				break;
			}

			default:
				throw new IllegalArgumentException("Unexpected value: " + opcion);

			}

		} while (opcion != 5);
	}

	/**
	 * @param sc
	 */
	public static void eliminarViajes(Scanner sc) {
		System.out.println("Introduzca el lugar del viaje  para eliminarlo:");
		String lugar = sc.next();
		System.out.println("el lugar es " + lugar);
	}

	private static void imprimirViajes() {
		System.out.println("Por implementar");

	}

	/**
	 * @param sc
	 */
	public static void modificarViaje(Scanner sc) {
		System.out.println("Introduzca el lugar del viaje :");
		String lugar = sc.next();
		System.out.println("Nuevo el precio :");
		double precio = sc.nextDouble();
		System.out.println("Nueva fecha :");
		System.out.println("Introduzca el dia :");
		int dia = sc.nextInt();
		System.out.println("Introduzca el mes :");
		int mes = sc.nextInt();
		System.out.println("Introduzca el año :");
		int año = sc.nextInt();
		String fecha = dia + "/" + mes + "/" + año;
		System.out.println(
				"El lugar es :" + lugar + " los datos son :" + fecha + " el precio es :" + precio + "$" + "\n");
	}

	/**
	 * @param sc
	 * @return
	 */
	public static int imprimirMenu(Scanner sc) {
		int opcion;
		System.out.println("1.Listado de todos los viajes disponibles");
		System.out.println("2.Añadir un nuevo viaje");
		System.out.println("3.Modificar el precio o la fecha de un viaje existente");
		System.out.println("4.Eliminar un viaje existente");
		System.out.println("5.salida");
		opcion = sc.nextInt();
		return opcion;
	}

	/**
	 * 
	 * @param sc
	 */
	private static void nuevoViejae(Scanner sc) {
		double precio = 0;
		int dia = 0;
		int mes = 0;
		int año = 0;
		do {
			System.out.println("Introduzca el precio :");
			precio = sc.nextDouble();
		} while (precio < 0);
		do {
			System.out.println("Introduzca el dia :");
			dia = sc.nextInt();
		} while (dia < 0);
		do {
			System.out.println("Introduzca el mes :");
			mes = sc.nextInt();
		} while (mes < 0);

		do {
			System.out.println("Introduzca el año :");
			año = sc.nextInt();
		} while (año < 0);

		String fecha = dia + "/" + mes + "/" + año;
		System.out.println("los datos son: " + fecha + " el precio es :" + precio + "$" + "\n");
	}

}
