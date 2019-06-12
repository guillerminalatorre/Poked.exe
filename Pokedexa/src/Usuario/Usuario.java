package Usuario;
import Pokemon.Pokemon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Usuario {
  	private String Nombre; //NOMBRE DE USUARIO CON EL QUE SE VA A LOGUEAR
	private String Genero;
	private int Edad;
	private int CantidadDeBatallas; // "NIVEL" DEL JUGADOR
	private File archivoPokedex;
	private File archivoCapturados;
	
	// CONSTRUCTORES

	public Usuario(String nombre, String genero, int edad, int cantidadDeBatallas) {
		super();
		Nombre = nombre;
		Genero = genero;
		Edad = edad;
		CantidadDeBatallas = cantidadDeBatallas;
		archivoPokedex = new File ("\\Pokedexa\\src\\Usuario", nombre);
		archivoCapturados = new File ("\\Pokedexa\\src\\Usuario",nombre);
	}

	// GETTERS
	
	public int getCantidadDeBatallas() {
		return CantidadDeBatallas;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getGenero() {
		return Genero;
	}

	public int getEdad() {
		return Edad;
	}
	
	/**
	 * 
	 * @return  
	 */
	public getArchivoPokedex() 
	{
		
	}
	
	/**
	 * 
	 * @return 
	 */
	public getArchivoCapturados()  
	{
		
	}
	
	// SETTERS
	
	public void setCantidadDeBatallas(int cantidadDeBatallas) {
		CantidadDeBatallas = cantidadDeBatallas;
	}
	
	//METODOS 
	
	public String nombreArchivoPokedex()
	{
		return archivoPokedex.getPath();
	}
	
	public String nombreArchivoCapturados()
	{
		return archivoCapturados.getPath();
	}
	//METODOS OVERRIDE
	
	@Override
	public String toString() {
		return "\n-Nombre="+ Nombre +"\n-Genero=" + Genero + "\n-Edad=" + Edad + "\n-CantidadDeBatallas="
				+ CantidadDeBatallas;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(obj instanceof Usuario) {
			Usuario aux= (Usuario)obj;
			if (this.getNombre().equalsIgnoreCase(aux.getNombre())) 
				return true;
			else 
				return false;
		}
		else 
			return false;
	}
	
}
