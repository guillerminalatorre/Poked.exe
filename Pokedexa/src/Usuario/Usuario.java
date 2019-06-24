package Usuario;
import Pokemon.Pokemon;
import java.io.*;
import ManejadorExcepciones.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class Usuario {
  	private String Nombre; //NOMBRE DE USUARIO CON EL QUE SE VA A LOGUEAR
	private String Genero;
	private int Edad;
	private int CantidadDeBatallas; // "NIVEL" DEL JUGADOR
	private File archivoPokedexUsuario;
	private File archivoCapturados;
	
	// CONSTRUCTORES

	public Usuario(String nombre, String genero, int edad, int cantidadDeBatallas) {
		super();
		Nombre = nombre;
		Genero = genero;
		Edad = edad;
		CantidadDeBatallas = cantidadDeBatallas;
		archivoPokedexUsuario = new File ("\\Pokedexa\\src\\Usuario", nombre);
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
	 * @return retorna las ids de la pokedex de el usuario en un ArrayList.
	 * @throws FileNotFoundException 
	 */
	public ArrayList<Integer> getArchivoPokedexUsuario() throws ExcepcionGenerica
	{
		ArrayList<Integer> idsPokedex = new ArrayList<Integer>();
		
		FileInputStream lecturaIds = null;
		
		int idCopia;
		
		try
		{
			lecturaIds = new FileInputStream(nombreArchivoPokedexUsuario());
			while((idCopia = lecturaIds.read()) != -1)
			{
				idsPokedex.add(idCopia);
			}
			
	
		} 
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + nombreArchivoPokedexUsuario());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo archivo: " + nombreArchivoPokedexUsuario());
		}
		finally
		{
			try {
				if (null != lecturaIds) {
					lecturaIds.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo: " + nombreArchivoPokedexUsuario());
			}
		}
		return idsPokedex;
	}

	
	/**
	 * 
	 * @return retorna un TreeMap<Integer, Pokemon> correspondiente a los pokemons capturados 
	 */
	public TreeMap<Integer, Pokemon> getArchivoCapturados()  throws ExcepcionGenerica
	{
		TreeMap <Integer, Pokemon> capturados = new TreeMap<Integer, Pokemon>();
		FileInputStream streamPokemons = null;	
		ObjectInputStream lectorPokemons = null;
		Pokemon copia;
		
		try
		{
			streamPokemons = new FileInputStream(nombreArchivoCapturados());
			lectorPokemons= new ObjectInputStream(streamPokemons);
			while((copia = (Pokemon)lectorPokemons.readObject()) != null)
			{
				capturados.put(copia.getId(), copia);
			}
			
		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + nombreArchivoCapturados());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + nombreArchivoCapturados());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Clase no encontrada para leer del archivo ");
		}
		finally
		{
			
			try {
				if (lectorPokemons != null) {
					lectorPokemons.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + nombreArchivoCapturados());
			}
			/*finally {
				try {
					if (streamPokemons != null) {
						streamPokemons.close();
					}
				} catch (IOException exception) {
					exception.printStackTrace();
					throw new ExcepcionGenerica("No se puede cerrar el archivo " + nombreArchivoCapturados());
				}
			}*/
		}
		return capturados;
	}
	

	
	// SETTERS
	
	public void setCantidadDeBatallas(int cantidadDeBatallas) {
		CantidadDeBatallas = cantidadDeBatallas;
	}
	
	//METODOS 
	
	public String nombreArchivoPokedexUsuario()
	{
		return archivoPokedexUsuario.getPath();
	}
	
	public String nombreArchivoCapturados()
	{
		return archivoCapturados.getPath();
	}
	
	public void sumarBatalla ()
	{
		setCantidadDeBatallas( getCantidadDeBatallas()+1 );
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
