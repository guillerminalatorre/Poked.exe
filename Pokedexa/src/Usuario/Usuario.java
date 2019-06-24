package Usuario;
import Pokemon.*;
import java.io.*;
import ManejadorExcepciones.*;

import java.util.ArrayList;
import java.util.Iterator;
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
	 * retorna en un TreeMap los pokemons dañados (vidas != nivel)
	 * @return
	 * @throws ExcepcionGenerica
	 */
	public ArrayList<Pokemon> getPokemonsDañados()  throws ExcepcionGenerica
	{
		ArrayList < Pokemon> dañados = new ArrayList <Pokemon>();
		FileInputStream streamPokemons = null;	
		ObjectInputStream lectorPokemons = null;
		Pokemon copia;
		
		try
		{
			streamPokemons = new FileInputStream(nombreArchivoCapturados());
			lectorPokemons= new ObjectInputStream(streamPokemons);
			while((copia = (Pokemon)lectorPokemons.readObject()) != null)
			{
				if(copia.getNivel() != copia.getVidas()) dañados.add(copia);
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
		return dañados;
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
	 * Privado, Se realiza una copia del Archivo de capturados para poder sobreescribirlo con cambioc, cuando sea necesario.
	 * @return
	 * @throws ExcepcionGenerica
	 */
	private String copiarArchivoCapturados () throws ExcepcionGenerica
	{
        File destino = new File("\\Pokedexa\\src\\Usuario\\ArchivoCopias");
        ObjectInputStream lectura = null;
        try {
                lectura = new ObjectInputStream (new FileInputStream(archivoCapturados));
                ObjectOutputStream escritura = null;
                try
                {
                	escritura = new ObjectOutputStream (new FileOutputStream(destino));
                	Pokemon copia;
                	while((copia = (Pokemon)lectura.readObject()) != null)
                	{
                		escritura.writeObject(copia);
                	}
                }
                catch (FileNotFoundException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("Error abriendo archivo: " + destino.getPath());
        		} 
        		catch (IOException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("Error accediendo al archivo: " + destino.getPath());
        		}
                catch (ClassNotFoundException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("No se encuentra el objeto Pokemon en el archivo " + destino.getPath());
        		}
        		finally
        		{
        			
        			try {
        				if (escritura != null) {
        					escritura.close();
        				}
        			} catch (IOException exception) {
        				exception.printStackTrace();
        				throw new ExcepcionGenerica("No se puede cerrar el archivo " + destino.getPath());
        			}
        		}
        }
        catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturados.getPath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getPath());
		}
		finally
		{
			
			try {
				if (lectura != null) {
					lectura.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturados.getPath());
			}
		}
        return destino.getPath();     
	}
       
           
	public boolean restaurarVidas(ArrayList<Pokemon> curados)  throws ExcepcionGenerica
	{
		boolean rta = false;
		//Para el archivo de destino
		ObjectOutputStream escrituraPokemons = null;
		
		//Para el archivo copia
		String rutaCopia = copiarArchivoCapturados();
		
		
		//elimino el archivo y lo vuelvo a crear, asi esta vacío
		archivoCapturados.delete();
		archivoCapturados = new File ("\\Pokedexa\\src\\Usuario",getNombre());
		
		try
		{
			
			escrituraPokemons= new ObjectOutputStream(new FileOutputStream(nombreArchivoCapturados()));
			
			Iterator<Pokemon> iterador = curados.iterator();
			
			ObjectInputStream lecturaPokemonsCapturados = null;
			
			try
			{
				int pos=0;
				
				lecturaPokemonsCapturados = new ObjectInputStream( new FileInputStream ( rutaCopia ));
				
				Pokemon copiacopia = null;
				
				while(iterador.hasNext() || ( copiacopia = (Pokemon) lecturaPokemonsCapturados.readObject()) != null)
				{
					Pokemon posarray = curados.get(pos);
					
					//si el las ids del araay de los curados y del archivo que copie
					if( iterador.hasNext() && posarray.getId() == copiacopia.getId())
					{
						pos ++;
						
						escrituraPokemons.writeObject(iterador.next()); 
					}
					//si las ids no son iguales de copia el pokemon del archivo copia
					else
					{
						escrituraPokemons.writeObject(copiacopia); 
					}
				}
			}
			catch (FileNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error abriendo archivo: " + rutaCopia);
			} 
			catch (IOException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error accediendo al archivo: " + rutaCopia);
			}
			catch (ClassNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se encontro la clase Pokemon en el archivo: " + rutaCopia);
			}
			finally
			{
				try {
					if (lecturaPokemonsCapturados != null) {
						lecturaPokemonsCapturados.close();
					}
				} catch (IOException exception) {
					exception.printStackTrace();
					throw new ExcepcionGenerica("No se puede cerrar el archivo " + rutaCopia);
				}
				
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
		rta=true;
		return rta;
	}
	
	
	public void actualizarUnPokemon(Pokemon pokemonCambiado)  throws ExcepcionGenerica
	{
		//Para el archivo de destino
		ObjectOutputStream escrituraPokemons = null;
		
		//Para el archivo copia
		String rutaCopia = copiarArchivoCapturados();
		
		
		//elimino el archivo y lo vuelvo a crear, asi esta vacío
		archivoCapturados.delete();
		archivoCapturados = new File ("\\Pokedexa\\src\\Usuario",getNombre());
		
		try
		{
			
			escrituraPokemons= new ObjectOutputStream(new FileOutputStream(nombreArchivoCapturados()));
			
			ObjectInputStream lecturaPokemonsCapturados = null;
			
			try
			{
				lecturaPokemonsCapturados = new ObjectInputStream( new FileInputStream ( rutaCopia ));
				
				Pokemon copiacopia = null;
				
				while((copiacopia = (Pokemon) lecturaPokemonsCapturados.readObject()) != null)
				{
					
					//si la id del pokemonCambiado es igual a la del archivo de copia, copio la del pokemoCambiado
					if( pokemonCambiado.getId() == copiacopia.getId())
					{
						escrituraPokemons.writeObject(pokemonCambiado); 
					}
					//si las ids no son iguales de copia el pokemon del archivo copia
					else
					{
						escrituraPokemons.writeObject(copiacopia); 
					}
				}
			}
			catch (FileNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error abriendo archivo: " + rutaCopia);
			} 
			catch (IOException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error accediendo al archivo: " + rutaCopia);
			}
			catch (ClassNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se encontro la clase Pokemon en el archivo: " + rutaCopia);
			}
			finally
			{
				try {
					if (lecturaPokemonsCapturados != null) {
						lecturaPokemonsCapturados.close();
					}
				} catch (IOException exception) {
					exception.printStackTrace();
					throw new ExcepcionGenerica("No se puede cerrar el archivo " + rutaCopia);
				}
				
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
	 * retorna el pokemon de la id enviada por parametro, si no lo encuentra retorna null.
	 * @param pokemonNuevo
	 * @return
	 * @throws ExcepcionGenerica
	 */
	public Pokemon encontrarPokemon (int id)  throws ExcepcionGenerica
	{
		FileInputStream streamPokemons = null;	
		ObjectInputStream lecturaPokemon = null;
		Pokemon copia = null;
		
		try
		{
			streamPokemons = new FileInputStream(nombreArchivoPokedexUsuario());
			
			lecturaPokemon= new ObjectInputStream(streamPokemons);
			
			int i = 0;
			while(  i==0  && (copia = (Pokemon)lecturaPokemon.readObject()) != null )
			{
				if(copia.getId() == id) i = 1;
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
			throw new ExcepcionGenerica("Error accediendo al archivo: " + nombreArchivoPokedexUsuario());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("No se ha encontrado la clase Pokemon en el archivo " + nombreArchivoPokedexUsuario());
		}
		finally
		{
			try {
				if (lecturaPokemon != null) {
					lecturaPokemon.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + nombreArchivoPokedexUsuario());
			}
			
		}
		
		return copia;
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
