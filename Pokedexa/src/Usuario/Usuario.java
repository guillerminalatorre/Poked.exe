package Usuario;
import Pokemon.*;
import java.io.*;
import ManejadorExcepciones.*;

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
	
	/**
	 * retorna True si el pokemon fue visto, false si no lo fue.
	 * @param pokemonNuevo
	 * @return
	 * @throws ExcepcionGenerica
	 */
	public boolean ElPokemonFueVisto(Pokemon pokemonNuevo) throws ExcepcionGenerica
	{
		boolean visto = false;
		
		FileInputStream lecturaIds = null;
		
		int idVisto;
		
		try
		{
			lecturaIds = new FileInputStream(nombreArchivoPokedexUsuario());
			while((idVisto = lecturaIds.read()) != -1  && visto==false)
			{
				if(pokemonNuevo.getId() == idVisto) visto = true;
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
		return visto;
	}

	
	
	/**
	 * método publico para guardar el nuevo pokemon en el archivo binario
	 * @param pokemonNuevo
	 * @throws ExcepcionGenerica
	 */
	public void cargarNuevoPokemonCapturado(Pokemon pokemonNuevo)  throws ExcepcionGenerica
	{
		FileOutputStream streamPokemons = null;	
		ObjectOutputStream escrituraPokemons = null;
		
		try
		{
			streamPokemons = new FileOutputStream(nombreArchivoCapturados());
			
			escrituraPokemons= new ObjectOutputStream(streamPokemons);
			
			escrituraPokemons.writeObject(pokemonNuevo);
			
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
		finally
		{
			try {
				if (escrituraPokemons != null) {
					escrituraPokemons.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + nombreArchivoCapturados());
			}
			
		}
	}
	
	/**
	 * método para guarda una id en el archivo binario de pokemons vistos (pokedex)
	 * @param pokemon
	 * @throws ExcepcionGenerica
	 */
	public void cargarNuevoPokemonVisto(Pokemon pokemon) throws ExcepcionGenerica
	{
		FileOutputStream escrituraIds = null;
		
		try
		{
			escrituraIds = new FileOutputStream(nombreArchivoCapturados());
			
			escrituraIds.write(pokemon.getId());
			
	
		} 
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + nombreArchivoCapturados());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo archivo: " + nombreArchivoCapturados());
		}
		finally
		{
			try {
				if (null != escrituraIds) {
					escrituraIds.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo: " + nombreArchivoCapturados());
			}
		}
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
