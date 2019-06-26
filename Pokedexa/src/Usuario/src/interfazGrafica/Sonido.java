package interfazGrafica;
import javazoom.jlme.util.*;
import java.io.*;
import ManejadorExcepciones.ExcepcionGenerica;


public class Sonido {
	private Player Reproductor;
	File Archivo;
	public Sonido(String ruta) throws ExcepcionGenerica{
		Archivo=new File(ruta);
		try {
			Reproductor= new Player(new FileInputStream(Archivo));
		}
		catch(FileNotFoundException error) {
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al abrir el archivo");
		}
		catch(Exception error) {
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al abrir el reproductor de sonidos");
		}
	}
	
	public void Reproducir() throws ExcepcionGenerica{
		try {
			Reproductor.play();
		}
		catch(Exception error) {
			error.printStackTrace();
		}
	}
	public void Parar() throws ExcepcionGenerica{
		try {
			Reproductor.stop();
		}
		catch(Exception error) {
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al parar sonido");
		
		}
	}
}
	
