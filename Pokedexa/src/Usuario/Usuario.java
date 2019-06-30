package Usuario;
import Pokemon.*;
import java.io.*;
import ManejadorExcepciones.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class Usuario implements Serializable {
  	private String Nombre=" "; //NOMBRE DE USUARIO CON EL QUE SE VA A LOGUEAR
	private int CantidadDeBatallas; // "NIVEL" DEL JUGADOR
	private File archivoPokedexUsuario= new File ("Pokedexa\\src\\Usuario", getNombre()+"Pokedex.dat");
	private File archivoCapturados=new File ("Pokedexa\\src\\Usuario",getNombre()+"Capturados.dat");
	private File archivoCapturadosCopia=new File ("Pokedexa\\src\\Usuario",getNombre()+"CapturadosCopia.dat");
	private FileWriter cargadorArchivos;
	
	// CONSTRUCTORES
	//vacio
	public Usuario() 
	{
		super();
		Nombre = "null";
		try {
				if(archivoPokedexUsuario.exists()== false) 
				{
					archivoPokedexUsuario.createNewFile();
				}
				if(archivoCapturados.exists()== false) 
				{
					archivoCapturados.createNewFile();
				}
		}	
		catch(IOException error) 	
		{
			error.printStackTrace();
		}
		CantidadDeBatallas =0;
	}
	
	//defecto
	public Usuario(String nombre) 
	{
		super();
		Nombre = nombre;
		//archivoPokedexUsuario= new File ("src\\Usuario", getNombre()+"Pokedex.dat");
		//archivoCapturados=new File ("src\\Usuario",getNombre()+"Capturados.dat");
		//archivoCapturadosCopia=new File ("src\\Usuario",getNombre()+"CapturadosCopia.dat");;
		CantidadDeBatallas = 0;
		
		try {
				if(archivoPokedexUsuario.exists()== false) 
				{
					archivoPokedexUsuario.createNewFile();
				}
				if(archivoCapturados.exists()== false) 
				{
					archivoCapturados.createNewFile();
				}
		}	
		catch(IOException error) 	
		{
			error.printStackTrace();
		}
	}
	
	//copia
	public Usuario(Usuario usu) {
		super();

		this.Nombre = usu.getNombre();
		this.CantidadDeBatallas = usu.getCantidadDeBatallas();
		this.archivoPokedexUsuario = new File (usu.getRutaArchivoPokedexUsuario());
		this.archivoCapturados = new File (usu.getRutaArchivoCapturados());
		this.archivoCapturadosCopia = new File (usu.getRutaArchivoCapturadosCopia());
	}

	// GETTERS
	
	public int getCantidadDeBatallas() {
		return CantidadDeBatallas;
	}

	public String getNombre() {
		return Nombre;
	}

	
	public String getRutaArchivoCapturadosCopia()
	{
		return archivoCapturadosCopia.getAbsolutePath();
	}
	public String getRutaArchivoPokedexUsuario()
	{
		return archivoPokedexUsuario.getAbsolutePath();
	}
	public String getRutaArchivoCapturados()
	{
		return archivoCapturados.getAbsolutePath();
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
	public boolean HayDebilitados() throws ExcepcionGenerica{
		Pokemon nuevo;
		FileInputStream lector;
		ObjectInputStream lectorObjeto=null;
		boolean flag=false;
		try {
			lector= new FileInputStream(archivoCapturados);
			lectorObjeto= new ObjectInputStream(lector);
			while((nuevo = (Pokemon)lectorObjeto.readObject()) != null) {
				if(nuevo.getVidas()==0) {
					flag=true;
				}
				else flag=false;
			}
		}
		catch(IOException error) {
			error.printStackTrace();
			throw new ExcepcionGenerica(error.getMessage());
		}
		catch(ClassNotFoundException error) {
			error.printStackTrace();
			throw new ExcepcionGenerica(error.getMessage());
		}
		

			try {
				lectorObjeto.close();
			}
			catch(IOException error) {
				error.printStackTrace();
				throw new ExcepcionGenerica(error.getMessage());
			}
			return flag;
	}
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
		//hago un new del archivoCapturadosCopia,
		
		archivoCapturadosCopia = new File (getRutaArchivoCapturadosCopia());
		
        ObjectInputStream lectura = null;
        try {
                lectura = new ObjectInputStream (new FileInputStream(archivoCapturados));
                ObjectOutputStream escritura = null;
                try
                {
                	escritura = new ObjectOutputStream (new FileOutputStream(archivoCapturadosCopia));
                	Pokemon copia;
                	while((copia = (Pokemon)lectura.readObject()) != null)
                	{
                		escritura.writeObject(copia);
                	}
                }
                catch (FileNotFoundException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturadosCopia.getPath());
        		} 
        		catch (IOException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturadosCopia.getPath());
        		}
                catch (ClassNotFoundException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("No se encuentra el objeto Pokemon en el archivo " + archivoCapturadosCopia.getPath());
        		}
        		finally
        		{
        			
        			try {
        				if (escritura != null) {
        					escritura.close();
        				}
        			} catch (IOException exception) {
        				exception.printStackTrace();
        				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturadosCopia.getPath());
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
        return archivoCapturadosCopia.getPath();     
	}
       
    /**
     * sobreescribe los pokemon ya curados, que son pasados por parametro en el arrayList,  en el archivo.       
     * @param curados
     * @return
     * @throws ExcepcionGenerica
     */
	public boolean restaurarVidas(ArrayList<Pokemon> curados)  throws ExcepcionGenerica
	{
		boolean rta = false;
		//Para el archivo de destino
		ObjectOutputStream escrituraPokemons = null;
		
		//Para el archivo copia
		String rutaCopia = copiarArchivoCapturados();
		
		
		//elimino el archivo capturados y lo vuelvo a crear, así esta vacío
		archivoCapturados.delete();
		archivoCapturados = new File ("\\Pokedexa\\src\\Usuario",getNombre());
		
		try
		{
			
			escrituraPokemons= new ObjectOutputStream(new FileOutputStream(nombreArchivoCapturados()));
			
			Iterator<Pokemon> iterador = curados.iterator();
			
			//para abrir el archivo donde tengo la copia
			ObjectInputStream lecturaPokemonsCapturados = null;
			
			try
			{
				int pos=0;
				
				//abro el archivo donde tengo la copia
				lecturaPokemonsCapturados = new ObjectInputStream( new FileInputStream ( rutaCopia ));
				
				Pokemon copiacopia = null;
				
				//mientras que el iterador tenga alguno o todavía tenga pokemons en el archivo de copia
				while(iterador.hasNext() || ( copiacopia = (Pokemon) lecturaPokemonsCapturados.readObject()) != null)
				{
					Pokemon posarray = curados.get(pos);
					
					//si el las ids del araay de los curados y del archivo que copie el del array, porque son los que quiero pisar
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
					
					//elimino el archivoCopiaParaque no exista en el caso de una nuevacopia;
					archivoCapturadosCopia.delete();
					
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
	
	/**
	 * elimina el pokemon que sea igual a la id pasada por parametro;
	 * @param id
	 * @throws ExcepcionGenerica 
	 */
	public void eliminarUnPokemonCapturado(int idAeliminar) throws ExcepcionGenerica
	{
		//Para el archivo de destino
				ObjectOutputStream escrituraPokemons = null;
				
				//Para el archivo copia
				String rutaCopia = copiarArchivoCapturados();
				
				
				//elimino el archivo capturados y lo vuelvo a crear, así esta vacío
				archivoCapturados.delete();
				archivoCapturados = new File ("\\Pokedexa\\src\\Usuario",getNombre());
				
				try
				{
					
					escrituraPokemons= new ObjectOutputStream(new FileOutputStream(nombreArchivoCapturados()));
					
					//para abrir el archivo donde tengo la copia
					ObjectInputStream lecturaPokemonsCapturados = null;
					
					try
					{
						
						//abro el archivo donde tengo la copia
						lecturaPokemonsCapturados = new ObjectInputStream( new FileInputStream ( rutaCopia ));
						
						Pokemon copiacopia = null;
						
						int eliminado=0;
						
						//mientras que eliminado sea igual a 0 y el archivo copia siga teniendo pokemons pra copiar
						while(eliminado == 0 || ( copiacopia = (Pokemon) lecturaPokemonsCapturados.readObject()) != null)
						{
							
							//si la id pasada por parametro es igual a la del archivo que copia, creo un pokemon y copio el pokemon del archivoCopia sin pasarlo al archivoCapturados original 
							if( eliminado == 0 &&idAeliminar  == copiacopia.getId())
							{
								eliminado = 1;
								//no hago nada porque el lector de pokemons paso al otro pokemon que figuraba en el archivo.
								
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
							
							//elimino el archivoCopiaParaque no exista en el caso de una nuevacopia;
							archivoCapturadosCopia.delete();
							
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
		return archivoPokedexUsuario.getAbsolutePath();
	}
	
	public String nombreArchivoCapturados()
	{
		return archivoCapturados.getAbsolutePath();
	}
	
	public void sumarBatalla ()
	{
		setCantidadDeBatallas( getCantidadDeBatallas()+1 );
	}
	//METODOS OVERRIDE
	
	@Override
	public String toString() {
		return "\n-Nombre="+ Nombre + "\n-CantidadDeBatallas="
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
