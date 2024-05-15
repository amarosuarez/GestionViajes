package principal;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.Scanner;

import metodos.ArrayViajes;
import metodos.LecturaArchivo;
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
				// modificarViajes(sc);
				break;
			}
			case 4: {
				// eliminarViajes(sc);
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
			System.out.println("║ 5. Salir                            ║");
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
		} while (!mesDentroRango(mes, actualMonth, año, actualYear));
		
		do {
			System.out.println("Día del viaje");
			dia = sc.nextInt();
		} while (dia < 1 || dia >= diaMaxMes(mes, año) || (año == actualYear && mes == actualMonth && dia < actualDay) );

		
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
	
	public static int diaMaxMes(int mes, int anio) {
		int diaMax = 31;
		if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
			diaMax = 30;
		} else if (mes == 2) {
			if (Year.isLeap(anio)) {
				diaMax = 29;
			} else {
				diaMax = 28;
			}
		}
		
		return diaMax;
	}
	
	public static boolean mesDentroRango(int mes, int actualMonth, int anio, int actualYear) {
		boolean correcto = false;
		
		if (anio == actualYear && mes >= actualMonth) {
			correcto = true;
		} else if (anio != actualYear && mes >= 1 && mes <= 12) {
			correcto = true;
		}
		
		return correcto;
	}

	/**
	 * modifica un viaje (llam a la funcion resoectiva en la clase y ppon los
	 * parametros de entrada )
	 * 
	 * @param sc
	 *//*
		 * public static void modificarViajes(Scanner sc) { int dia = 0; int mes = 0;
		 * int año = 0; double precio = 0;
		 * System.out.println("Ingrese el lugar del viaje:"); String lugar = sc.next();
		 * 
		 * do { System.out.println("Ingrese el nuevo precio:"); precio =
		 * sc.nextDouble(); if (precio <= 0)
		 * System.out.println("El precio debe ser mayor que cero."); } while (precio <=
		 * 0);
		 * 
		 * do { System.out.println("Ingrese el nuevo día:"); dia = sc.nextInt(); if (dia
		 * < 1 || dia > 31) System.out.println("Ingrese un día válido (entre 1 y 31).");
		 * } while (dia < 1 || dia > 31);
		 * 
		 * do { System.out.println("Ingrese el nuevo mes:"); mes = sc.nextInt(); if (mes
		 * < 1 || mes > 12) System.out.println("Ingrese un mes válido (entre 1 y 12).");
		 * } while (mes < 1 || mes > 12);
		 * 
		 * do { System.out.println("Ingrese el nuevo año:"); año = sc.nextInt(); if (año
		 * < 1900 || año > 2999)
		 * System.out.println("Ingrese un año válido (entre 1900 y 2999)."); } while
		 * (año < 1900 || año > 2999);
		 * 
		 * String fecha = dia + "/" + mes + "/" + año; Viaje viaje = new Viaje(lugar,
		 * fecha, precio); System.out.println("Se ha modificado viaje ? " +
		 * ArrayViajes.modificarViaje(viaje));
		 * 
		 * }
		 */
	/**
	 * Elimina un viaje llama al array o list donde se almacena los viajes
	 * 
	 * @param sc
	 */
	/*
	 * public static void eliminarViajes(Scanner sc) {
	 * System.out.println("Introduzca el lugar del viaje  para eliminarlo:"); String
	 * lugar = sc.next(); System.out.println("el lugar es :" + lugar); Viaje viaje =
	 * new Viaje(lugar); System.out.println("Se ha eliminado un viaje ? " +
	 * ArrayViajes.eliminarViaje(viaje)); }
	 */
}
