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
	 * @return retorna las ids de la pokedex de el usuario en un ArrayList.
	 * @throws FileNotFoundException 
	 */
	public ArrayList<Integer> getArchivoPokedex() throws FileNotFoundException
	{
		ArrayList<Integer> idsPokedex = new ArrayList<Integer>();
		
		FileInputStream ids = null;
		
		int idCopia;
		
		try
		{
			ids = new FileInputStream(nombreArchivoPokedex());
			while((idCopia = ids.read()) != -1)
			{
				idsPokedex.add(idCopia);
			}
			
	
		} 
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
		}
		finally
		{
			try {
				if (null != ids) {
					ids.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return idsPokedex;
	}

	
	/**
	 * 
	 * @return retorna un TreeMap<Integer, Pokemon> correspondiente a los pokemons capturados 
	 */
	public TreeMap<Integer, Pokemon> getArchivoCapturados()  throws FileNotFoundException
	{
		TreeMap <Integer, Pokemon> capturados = new TreeMap<Integer, Pokemon>();
		
		FileInputStream pokemons;
		
		int copia;
		
		try
		{
			pokemons = new FileInputStream(nombreArchivoCapturados()));
			while((copia = pokemons.read()) != -1)
			{
				capturados.put(copia.getId(), copia);
			}
			
		}
		catch (FileNotFoundException exception) 
		{
			System.err.println("Error abriendo archivo: " + nombreArchivoCapturados());
		} 
		catch (IOException exception) 
		{
			System.err.println("Error accediendo al archivo: " + nombreArchivoCapturados());
		}
		finally
		{
			try {
				if (null != pokemons) {
					pokemons.close();
				}
			} catch (IOException ioe) {
				System.out.println("No se puede cerrar el archivo " + nombreArchivoPokedex());
			}
		}
		return capturados;
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
