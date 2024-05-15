package metodos;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que contendra el listado de viajes disponibles con sus respectivos datos.
 */
public class ArrayViajes {
	
static List<Viaje> listaViajes = new ArrayList<>();

public static void mostrarViajes() {
	for ( Viaje viaje : listaViajes) {
		System.out.println(viaje);
	}
}

}