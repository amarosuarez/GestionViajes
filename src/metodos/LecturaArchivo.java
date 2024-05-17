package metodos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import objetos.Viaje;

public class LecturaArchivo {

	public static List<Viaje> obetenerViajes() {

		// Lista donde vamos a guardar los viajes
		List<Viaje> listaViajes = new LinkedList<>();

		// Creamos un objeto tipo viaje sin parámetros
		Viaje viaje;

		// Variable donde guardaremos el destino del viaje
		String destino = "";

		// Variable donde guardaremos la fecha del viaje
		String fecha = "";

		// Variable donde guardaremos el precio del viaje
		double precio = 0;

		// Variable para ir leyendo las líneas
		String linea = "";

		// Objeto bufferedReader
		BufferedReader reader = null;

		try {

			// Hacemos que el objeto pueda coger los datos de nuestro archivo txt
			reader = new BufferedReader(new FileReader("src\\archivos\\datosTurismo.txt"));

			// Leemos la primera línea
			linea = reader.readLine();

			// Bucle para ir metiendo en el array datos los datos de los viajes
			while (linea != null) {

				// Array donde introduciremos los datos del viaje
				String[] datos = linea.split("::");

				// El destino será el primer dato
				destino = datos[0];

				// La fecha será el segundo dato
				fecha = datos[1];

				// El precio será el tercer dato
				precio = Double.parseDouble(datos[2]);

				// Establecemos el objeto viajes con los datos introducidos
				viaje = new Viaje(destino, fecha, precio);

				// los añadimos a la lista de viajes
				listaViajes.add(viaje);

				// Leemos la siguiente línea
				linea = reader.readLine();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Devolvemos la lista de viajes
		return listaViajes;

	} // Cierre del método

	public static void escrituraViaje(List<Viaje> listaViaje) {

		BufferedWriter in = null;

		try {
			in = new BufferedWriter(new FileWriter("src\\archivos\\datosTurismo.txt"));
			for (Viaje viaje : listaViaje) {
				String lugar = viaje.getLugar();
				String fecha = viaje.getFecha();
				double precio = viaje.getPrecio();
				in.newLine();
				in.write(lugar + "::" + fecha + "::" + precio);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				in.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
} // Cierre de la clase
