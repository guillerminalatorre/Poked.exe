package Usuario;
import Pokemon.*;
import java.io.*;
import ManejadorExcepciones.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;
import GenericidadTreeMap.*;

public class Usuario implements Serializable 
{
  	private String Nombre=" "; //NOMBRE DE USUARIO CON EL QUE SE VA A LOGUEAR
	private int CantidadDeBatallas; // "NIVEL" DEL JUGADOR
	private File archivoPokedexUsuario;
	private File archivoCapturados;
	private FileWriter cargadorArchivos;
	
	
	
	
	// CONSTRUCTORES
	
	
	
	//vacio
	
	public Usuario() 
	{
		super();
		Nombre = "null";
		archivoPokedexUsuario= new File ("src\\Usuario\\Pokedex Usuarios\\"+ getNombre()+"Pokedex.dat");
		archivoCapturados=new File ("src\\Usuario\\Capturados Usuarios\\"+getNombre()+"Capturados.dat");
		CantidadDeBatallas =0;
	}
	
	
	//defecto
	
	public Usuario(String nombre) 
	{
		super();
		Nombre = nombre;
		archivoPokedexUsuario= new File ("src\\Usuario\\Pokedex Usuarios\\"+ getNombre()+"Pokedex.dat");
		archivoCapturados=new File ("src\\Usuario\\Capturados Usuarios\\"+getNombre()+"Capturados.dat");
		CantidadDeBatallas = 0;
		try {
			if(archivoPokedexUsuario.exists()== false) {
				archivoPokedexUsuario.createNewFile();
			}
			if(archivoCapturados.exists()== false) {
				archivoCapturados.createNewFile();
			}
		}
		catch(IOException error) {
			error.printStackTrace();
		}
	}
	
	//copia
	
	public Usuario(Usuario usu) 
	{
		super();

		this.Nombre = usu.getNombre();
		this.CantidadDeBatallas = usu.getCantidadDeBatallas();
		this.archivoPokedexUsuario = new File (usu.getRutaArchivoPokedexUsuario());
		this.archivoCapturados = new File (usu.getRutaArchivoCapturados());

	}
	
	
	
	
	
 
	// GETTERS
	
	public int getCantidadDeBatallas() {
		return CantidadDeBatallas;
	}

	public String getNombre() {
		return Nombre;
	}

	
	public String getRutaArchivoPokedexUsuario()
	{
		return archivoPokedexUsuario.getAbsolutePath();
	}
	public String getRutaArchivoCapturados()
	{
		return archivoCapturados.getAbsolutePath();
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




	
	
	//METODOS DE LISTADO
	
	public String listarPokemonsPokedex() throws ExcepcionGenerica
	{
		StringBuilder listado = new StringBuilder ("Pokemons Pokedex de " + getNombre()+ ": \n");
		
		ArrayList<Integer> nuevoPokedex = new ArrayList<Integer>();
		try {
			nuevoPokedex = getArchivoPokedexUsuario();
		}
		catch(ExcepcionGenerica exception)
		{
			
		}
		Iterator<Integer> iteradorNuevoPokedex = nuevoPokedex.iterator();
		
		while( iteradorNuevoPokedex.hasNext())
		{
			listado.append(iteradorNuevoPokedex.next()+", ");
		}
		
		return listado.toString();
		
	}
	
	
	public String listarPokemonsCapturados()
	{
		StringBuilder listado = new StringBuilder("Pokemons Capturados de " + getNombre()+ ": \n");
		
		TreeMap<Integer, Pokemon> nuevoCapturados = leerMapaCapturados();

		Collection<Pokemon> collectionNuevoCapturados = nuevoCapturados.values();

		Iterator<Pokemon> iteradorNuevoCapturados = collectionNuevoCapturados.iterator();

		while( iteradorNuevoCapturados.hasNext())
		{
			listado.append(iteradorNuevoCapturados.next().toString() +"\n");
		}
		
		return listado.toString();
	}
	
	
	public String listarPokemonsDanados() throws ExcepcionGenerica
	{
		StringBuilder listado = new StringBuilder("Pokemons Danados de " + getNombre()+ ": \n");
		
		TreeMap<Integer, Pokemon> nuevoCapturados = getPokemonsDanados();

		Collection<Pokemon> collectionNuevoCapturados = nuevoCapturados.values();

		Iterator<Pokemon> iteradorNuevoCapturados = collectionNuevoCapturados.iterator();

		while( iteradorNuevoCapturados.hasNext())
		{
			listado.append(iteradorNuevoCapturados.next().toString() +"\n");
		}
		
		return listado.toString();
	}
	
	
	
	
	
	
	//METODOS DE OBTENCION DE ARCHIVOS
	
	
	/**
	 * 
	 * @return retorna las ids de la pokedex de el usuario en un ArrayList.
	 * @throws  
	 * @throws FileNotFoundException 
	 */
	private ArrayList<Integer> getArchivoPokedexUsuario() throws ExcepcionGenerica
	{
		ArrayList<Integer> idsPokedex = new ArrayList<Integer>();
		
		ObjectInputStream lecturaids= null;
		
		try
		{
			lecturaids = new ObjectInputStream (new FileInputStream(archivoPokedexUsuario));
			idsPokedex= new ArrayList<Integer>((ArrayList<Integer>)lecturaids.readObject());		
	
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
		catch(ClassNotFoundException exception)
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error encontrando la clase en el archivo : " + nombreArchivoPokedexUsuario());
		}
		finally
		{
			try {
				if (null != lecturaids) {
					lecturaids.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo: " + nombreArchivoPokedexUsuario());
			}
		}
		return idsPokedex;
	}
	
	
	private TreeMap<Integer,Pokemon> leerMapaCapturados()
	{
		GenericidadTreeMap<Integer,Pokemon> capturados = new GenericidadTreeMap<Integer,Pokemon>();
 		return capturados.sacarMapa(archivoCapturados);
	}
	
	private TreeMap<Integer, Pokemon> getPokemonsDanados()  throws ExcepcionGenerica
	{
		FileInputStream lector = null;	
		ObjectInputStream lectorCapturados = null;
		TreeMap<Integer,Pokemon> danados= new TreeMap<Integer,Pokemon>();
		try
		{
			lector = new FileInputStream(archivoCapturados);
			lectorCapturados= new ObjectInputStream(lector);
			TreeMap <Integer,Pokemon> capturadosDelArchivo = new TreeMap <Integer,Pokemon>((TreeMap<Integer,Pokemon>)lectorCapturados.readObject());
			
			Collection<Pokemon> coleccion = capturadosDelArchivo.values();
			
			Iterator<Pokemon> iterados = coleccion.iterator();
			
			while(iterados.hasNext())
			{
				Pokemon pokemon = iterados.next();
				
				if(pokemon.getNivel() > pokemon.getVidas())
				{
					danados.put(pokemon.getId(), pokemon);
				}
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
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
		}
		finally
		{
			try {
				if (lectorCapturados != null) {
					lectorCapturados.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		return danados;
	}
	
	
	

	
	
	
	//CARGA Y ELEMINACION DE POKEMON CAPTURADO 
	
	
	
	/**
	 * método publico para guardar el nuevo pokemon en el archivo binario
	 * @param pokemonNuevo
	 * @throws ExcepcionGenerica
	 */
	@SuppressWarnings("unchecked")
	public void cargarNuevoPokemonCapturado(Pokemon pokemonNuevo)  throws ExcepcionGenerica
	{
		FileOutputStream streamCapturados = null;	
		ObjectOutputStream escrituraCapturados = null;
		FileInputStream lector = null;	
		ObjectInputStream lectorCapturados = null;
		TreeMap<Integer,Pokemon> capturados;
		
		try
		{
			if(archivoCapturados.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
				lector = new FileInputStream(archivoCapturados);
				lectorCapturados= new ObjectInputStream(lector);
				capturados=new TreeMap<Integer, Pokemon>((TreeMap<Integer,Pokemon>)lectorCapturados.readObject());
			}
			else { //SI EL ARCHIVO ESTA VACIO CREA ELTREEMAP DESDE 0
				capturados= new TreeMap<Integer, Pokemon>();
			}
			capturados.put(pokemonNuevo.getId(), pokemonNuevo);
			streamCapturados = new FileOutputStream(archivoCapturados);
			escrituraCapturados= new ObjectOutputStream(streamCapturados);
			escrituraCapturados.writeObject(capturados);
		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturados.getAbsolutePath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		finally
		{
			try {
				if (escrituraCapturados != null) {
					escrituraCapturados.close();
				}
				if (lectorCapturados!= null) {
					lectorCapturados.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturados.getAbsolutePath());
			}
			
		}
	}
	
	
       
  
	/**
	 * elimina el pokemon que sea igual a la id pasada por parametro;
	 * @param id
	 * @throws ExcepcionGenerica 
	 */
	public boolean eliminarUnPokemonCapturado(int idAeliminar) throws ExcepcionGenerica
	{

		boolean rta = false;
		
		FileOutputStream streamCapturados = null;	
		ObjectOutputStream escrituraCapturados = null;
		FileInputStream lector = null;	
		ObjectInputStream lectorCapturados = null;
		TreeMap<Integer,Pokemon> capturados;
		
		try
		{
			if(archivoCapturados.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
				lector = new FileInputStream(archivoCapturados);
				lectorCapturados= new ObjectInputStream(lector);
				capturados=new TreeMap<Integer, Pokemon>((TreeMap<Integer,Pokemon>)lectorCapturados.readObject());
				
				Pokemon eliminado = capturados.remove(idAeliminar);
				if(eliminado.getId() == idAeliminar)
				{
					rta = true;
				}
				
				
				streamCapturados = new FileOutputStream(archivoCapturados);
				escrituraCapturados= new ObjectOutputStream(streamCapturados);
				escrituraCapturados.writeObject(capturados);
			}

		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturados.getAbsolutePath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		finally
		{
			try {
				if (escrituraCapturados != null) {
					escrituraCapturados.close();
				}
				if (lectorCapturados!= null) {
					lectorCapturados.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturados.getAbsolutePath());
			}
			
		}
		
		return rta;
	}
	
	
	
	
	
	
	
	
	
	
	//CARGA DE POKEMON VISTO
	
	
	
	/**
	 * método para guarda una id en el archivo binario de pokemons vistos (pokedex)
	 * @param pokemon
	 * @throws ExcepcionGenerica
	 */
	public void cargarNuevoPokemonVisto(Pokemon pokemon) throws ExcepcionGenerica
	{
		if(!ElPokemonFueVisto(pokemon))
		{
			FileOutputStream streamPokedex = null;	
			ObjectOutputStream escrituraPokedex = null;
			FileInputStream lector = null;	
			ObjectInputStream lectorPokedex = null;
			ArrayList<Integer> pokedex;

			try
			{
				if(archivoPokedexUsuario.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
					lector = new FileInputStream(archivoPokedexUsuario);
					lectorPokedex= new ObjectInputStream(lector);
					pokedex = new ArrayList<Integer> ((ArrayList<Integer> )lectorPokedex.readObject());
				}
				else { //SI EL ARCHIVO ESTA VACIO CREA ELTREEMAP DESDE 0
					pokedex = new ArrayList<Integer>();
				}
				pokedex.add(pokemon.getId());
				streamPokedex = new FileOutputStream(archivoPokedexUsuario);
				escrituraPokedex= new ObjectOutputStream(streamPokedex);
				escrituraPokedex.writeObject(pokedex);	
				escrituraPokedex.flush();
			}
			catch (FileNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error abriendo archivo: " + archivoPokedexUsuario.getAbsolutePath());
			} 
			catch (IOException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error accediendo archivo: " + archivoPokedexUsuario.getAbsolutePath());
			}
			catch(ClassNotFoundException exception)
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se ha encontrado la clase en  " + archivoPokedexUsuario.getAbsolutePath());
			}
			finally
			{
				try {
					if (null != escrituraPokedex) {
						escrituraPokedex.close();
					}
				} catch (IOException exception) {
					exception.printStackTrace();
					throw new ExcepcionGenerica("No se puede cerrar el archivo: " + archivoPokedexUsuario.getAbsolutePath());
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	//METODOS VARIOS
	
	
		/**
		 * retorna True si el pokemon fue visto, false si no lo fue.
		 * @param pokemonNuevo
		 * @return
		 * @throws ExcepcionGenerica
		 */
		@SuppressWarnings("unchecked")
		public boolean ElPokemonFueVisto(Pokemon pokemonNuevo) throws ExcepcionGenerica
		{
			boolean visto = false;
			
			if(archivoPokedexUsuario.length()>0)
			{
		
			FileInputStream lector = null;	
			ObjectInputStream lectorPokedex = null;
			ArrayList<Integer> pokedex;
			
			try
			{
				
					lector = new FileInputStream(archivoPokedexUsuario);
					lectorPokedex= new ObjectInputStream(lector);
					pokedex = new ArrayList<Integer> ((ArrayList<Integer>)lectorPokedex.readObject());
				
					Iterator<Integer> iterador = pokedex.iterator();
					
					while(iterador.hasNext() && visto==false)
					{
						if(pokemonNuevo.getId() == iterador.next())
						{
							visto = true;
						}
					}
					
			}
			catch (FileNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error abriendo archivo: " + archivoPokedexUsuario.getAbsolutePath());
			} 
			catch (IOException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error accediendo archivo: " + archivoPokedexUsuario.getAbsolutePath());
			}
			catch(ClassNotFoundException exception)
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se ha encontrado la clase en  " + archivoPokedexUsuario.getAbsolutePath());
			}
			finally
			{
				try {
					if (null != lectorPokedex) {
						lectorPokedex.close();
					}
				} catch (IOException exception) {
					exception.printStackTrace();
					throw new ExcepcionGenerica("No se puede cerrar el archivo: " + archivoPokedexUsuario.getAbsolutePath());
				}
			}
			}

			return visto;
		}
		
	
	

	
	 /**
     * sobreescribe los pokemon ya curados, que son pasados por parametro en el arrayList,  en el archivo.       
     * @param curados
     * @return
     * @throws ExcepcionGenerica
     */
	public void restaurarVidas()  throws ExcepcionGenerica
	{
		FileOutputStream streamCapturados = null;	
		ObjectOutputStream escrituraCapturados = null;
		FileInputStream lector = null;	
		ObjectInputStream lectorCapturados = null;
		TreeMap<Integer,Pokemon> capturados;
		
		try
		{
			if(archivoCapturados.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
				lector = new FileInputStream(archivoCapturados);
				lectorCapturados= new ObjectInputStream(lector);
				capturados=new TreeMap<Integer, Pokemon>((TreeMap<Integer,Pokemon>)lectorCapturados.readObject());
				
				Collection<Pokemon> coleccion = capturados.values();
				
				Iterator<Pokemon> iterador = coleccion.iterator();
				
				while( iterador.hasNext() )
				{
					Pokemon pokemon = iterador.next();
					if(pokemon.getVidas() != pokemon.getNivel())
					{
						pokemon.setVidas(pokemon.getNivel());
					}
				}
					
				streamCapturados = new FileOutputStream(archivoCapturados);
				escrituraCapturados= new ObjectOutputStream(streamCapturados);
				escrituraCapturados.writeObject(capturados);
			}

		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturados.getAbsolutePath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		finally
		{
			try {
				if (escrituraCapturados != null) {
					escrituraCapturados.close();
				}
				if (lectorCapturados!= null) {
					lectorCapturados.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturados.getAbsolutePath());
			}
			
		}

	}
	
	/**
	 * retorna el pokemon de la id enviada por parametro, si no lo encuentra retorna null.
	 * @param pokemonNuevo
	 * @return
	 * @throws ExcepcionGenerica
	 */
	public Pokemon leerPokemonCapturado (int id)  throws ExcepcionGenerica
	{
		FileOutputStream streamCapturados = null;	
		ObjectOutputStream escrituraCapturados = null;
		FileInputStream lector = null;	
		ObjectInputStream lectorCapturados = null;
		TreeMap<Integer,Pokemon> capturados;
		
		Pokemon retorno = null;
		
		try
		{
			if(archivoCapturados.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
				lector = new FileInputStream(archivoCapturados);
				lectorCapturados= new ObjectInputStream(lector);
				capturados=new TreeMap<Integer, Pokemon>((TreeMap<Integer,Pokemon>)lectorCapturados.readObject());
				
				retorno = capturados.get(id);
					
				streamCapturados = new FileOutputStream(archivoCapturados);
				escrituraCapturados= new ObjectOutputStream(streamCapturados);
				escrituraCapturados.writeObject(capturados);
			}

		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturados.getAbsolutePath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		finally
		{
			try {
				if (escrituraCapturados != null) {
					escrituraCapturados.close();
				}
				if (lectorCapturados!= null) {
					lectorCapturados.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturados.getAbsolutePath());
			}
			
		}
		
		return retorno;
	}
	
	
	public void actualizarUnPokemon (Pokemon pokemon)  throws ExcepcionGenerica
	{
		FileOutputStream streamCapturados = null;	
		ObjectOutputStream escrituraCapturados = null;
		FileInputStream lector = null;	
		ObjectInputStream lectorCapturados = null;
		TreeMap<Integer,Pokemon> capturados;
		
		
		try
		{
			if(archivoCapturados.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
				lector = new FileInputStream(archivoCapturados);
				lectorCapturados= new ObjectInputStream(lector);
				capturados=new TreeMap<Integer, Pokemon>((TreeMap<Integer,Pokemon>)lectorCapturados.readObject());
				
				//primero elimina el pokemon
				capturados.remove(pokemon.getId());
				
				//luego lo carga nuevamente
				capturados.put(pokemon.getId(), pokemon);
					
				streamCapturados = new FileOutputStream(archivoCapturados);
				escrituraCapturados= new ObjectOutputStream(streamCapturados);
				
				//guarda el TreeMap actualizado en el archivo
				
				escrituraCapturados.writeObject(capturados);
			}

		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturados.getAbsolutePath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		finally
		{
			try {
				if (escrituraCapturados != null) {
					escrituraCapturados.close();
				}
				if (lectorCapturados!= null) {
					lectorCapturados.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturados.getAbsolutePath());
			}
			
		}
		
	}
	
	public boolean estanTodosDebilitados() throws ExcepcionGenerica
	{
		FileOutputStream streamCapturados = null;	
		ObjectOutputStream escrituraCapturados = null;
		FileInputStream lector = null;	
		ObjectInputStream lectorCapturados = null;
		TreeMap<Integer,Pokemon> capturados;
		
		boolean rta = false;
		
		try
		{
			if(archivoCapturados.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
				lector = new FileInputStream(archivoCapturados);
				lectorCapturados= new ObjectInputStream(lector);
				capturados=new TreeMap<Integer, Pokemon>((TreeMap<Integer,Pokemon>)lectorCapturados.readObject());
				
				Collection<Pokemon> colecion = capturados.values();
				
				Iterator<Pokemon> iterados = colecion.iterator();
				
				int suma = 0 ;
				
				while(iterados.hasNext())
				{
					suma = suma + iterados.next().getVidas();
				}
				
				if(suma == 0) rta = true;
				
				streamCapturados = new FileOutputStream(archivoCapturados);
				escrituraCapturados= new ObjectOutputStream(streamCapturados);
				escrituraCapturados.writeObject(capturados);
			}

		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoCapturados.getAbsolutePath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoCapturados.getAbsolutePath());
		}
		finally
		{
			try {
				if (escrituraCapturados != null) {
					escrituraCapturados.close();
				}
				if (lectorCapturados!= null) {
					lectorCapturados.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoCapturados.getAbsolutePath());
			}
			
		}
		
		return rta;
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
