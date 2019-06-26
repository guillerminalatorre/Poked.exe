package Ejecucion;

import ManejadorExcepciones.ExcepcionGenerica;
import interfazGrafica.*;

public class Main {

	public static void main(String[] args) {
		try {
			Ventana Juego= new Ventana();
		}
		catch(ExcepcionGenerica error) {
			System.out.println(error.MensajeError());
		}

	}

}
