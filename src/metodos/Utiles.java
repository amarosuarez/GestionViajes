package metodos;

import java.time.Year;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase que almacena los métodos útiles a usar en otras clases
 * 
 * @author Amaro
 */
public class Utiles {
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
	 * Función que muestra un mensaje y lee un número
	 * 
	 * @param mensaje Mensaje a mostrar al usuario
	 * @return Devuelve el número leído
	 */
	public static int leeNumero(String mensaje) {
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
	 * Función que muestra un mensaje y lee un número
	 * 
	 * @param mensaje Mensaje a mostrar al usuario
	 * @return Devuelve el número leído
	 */
	public static double leeDoble(String mensaje) {
		// Variable donde se almacena el número a leer
		double num = -1;
		
		boolean valorCorrecto = false;
		
		// Creamos un Scanner 
		Scanner sc = new Scanner(System.in);
		
		do {
			// Mostramos el mensaje
			try {
				System.out.println(mensaje);
				num = sc.nextDouble();
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

}
