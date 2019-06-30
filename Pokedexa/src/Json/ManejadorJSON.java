package Json;
import Pokemon.*;
import jdk.nashorn.internal.parser.JSONParser;
import sun.font.FileFont;
import java.io.*;
import java.nio.file.*;
import org.json.*;
import ManejadorExcepciones.*;

public class ManejadorJSON {
	private JSONArray pokemones;
	private Pokemon pokemon=null;
	private FileWriter escritor;
	private File json= new File("src\\Json\\Pokedex.json");
	private int cantPokemones=0;

	
	
	//Pokemon pokemonObj;
	
	public ManejadorJSON() 
	{
		pokemones = new JSONArray();
	}
	
	public void CargarPokemon(Pokemon pokemonObj) throws ExcepcionGenerica
	{	
		 JSONObject pokemonJSON;
		pokemonJSON= new JSONObject ();
		try{
			pokemonJSON= new JSONObject ();
			pokemonJSON.put("Id", pokemonObj.getId());
			pokemonJSON.put("Nombre", pokemonObj.getNombre());
			pokemonJSON.put("Nivel", pokemonObj.getNivel());
			pokemonJSON.put("Vidas", pokemonObj.getVidas());
			pokemonJSON.put("Tipo", pokemonObj.getTipo());
			pokemonJSON.put("RutaImagen", pokemonObj.getRutaImagen());
			pokemonJSON.put("Evolucion", pokemonObj.getEvolucion());
			pokemones.put(cantPokemones,pokemonJSON);
			cantPokemones++;
		}
		catch(JSONException ErrorJSON) 
		{
			ErrorJSON.printStackTrace();
			throw new ExcepcionGenerica("Error al cargar pokemon"+pokemonObj.getNombre()+" en archivo JSON");
		}
	}
	
	public void cargarArchivoJSON() throws ExcepcionGenerica {
		try {
			escritor= new FileWriter(json);
			escritor.write(pokemones.toString());
		}
		catch(IOException error) 
		{
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al crear el lector JSON");
		}
		finally {
			try {
				if(escritor!=null) {
					escritor.close();
				}
			}
			catch(IOException error) 
			{
				error.printStackTrace();
				throw new ExcepcionGenerica("Error al cerrar el lector JSON");
			}
		}
	}
	public Pokemon leerPokemonJSON(int id) throws ExcepcionGenerica
	{
		Pokemon pokemon=new Pokemon();
		JSONObject pokemonJSON= new JSONObject ();
		try 
		{
			pokemones=new JSONArray(leer());
			pokemonJSON=pokemones.getJSONObject(id);
			pokemon.setNombre(pokemonJSON.getString("Nombre"));
			pokemon.setRutaImagen(pokemonJSON.getString("RutaImagen"));
			pokemon.setEvolucion(pokemonJSON.getInt("Evolucion"));
			pokemon.setId(pokemonJSON.getInt("Id"));
			pokemon.setNivel(pokemonJSON.getInt("Nivel"));
			pokemon.setVidas(pokemonJSON.getInt("Vidas"));
			pokemon.setTipo(pokemonJSON.getString("Tipo"));

		}
		catch(JSONException ErrorJSON) 
		{
			ErrorJSON.printStackTrace();
			throw new ExcepcionGenerica("Error al abrir el archivo JSON");
		}
		return pokemon;
	}

	public String leer() 
	{
		String contenido = "";
		try 
		{
			contenido = new String(Files.readAllBytes(Paths.get("src\\Json\\Pokedex.json")));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return contenido;
	}
}
