package Main;

import ManejadorExcepciones.ExcepcionGenerica;
import interfazGrafica.*;


public class Main {

	public static void main(String[] args) {
		try {
			VentanaSignIn f= new VentanaSignIn("f");
		}
		catch(ExcepcionGenerica error) {
			System.out.println(error.MensajeError());
		}
	}

}
