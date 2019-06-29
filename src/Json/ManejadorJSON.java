package Json;
import Pokemon.*;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ManejadorExcepciones.*;

public class ManejadorJSON {
	private JSONArray pokemones;
	private JSONObject pokemonJSON;
	private Pokemon pokemon=null;
	FileWriter archivoJson;


	
	
	//Pokemon pokemonObj;
	
	public ManejadorJSON() 
	{
		try {
			archivoJson= new FileWriter("Pokemones.json");
			pokemonJSON= new JSONObject ();
			pokemones = new JSONArray();
		}
		catch(IOException error) {
			error.printStackTrace();
		}
	}
	
	public void CargarPokemon(Pokemon pokemonObj) throws ExcepcionGenerica
	{
		try {
			pokemonJSON.put("Id", pokemonObj.getId());
			pokemonJSON.put("Nombre", pokemonObj.getNombre());
			pokemonJSON.put("Nivel", pokemonObj.getNivel());
			pokemonJSON.put("Vidas", pokemonObj.getVidas());
			pokemonJSON.put("Tipo", pokemonObj.getTipo());
			pokemonJSON.put("RutaImagen", pokemonObj.getRutaImagen());
			pokemones.put(pokemonJSON);
		}
		catch(JSONException ErrorJSON) 
		{
			ErrorJSON.printStackTrace();
			throw new ExcepcionGenerica("Error al cargar pokemon"+pokemonObj.getNombre()+" en archivo JSON");
		}
	}
	
	@SuppressWarnings("finally")
	public Pokemon SacarPokemonJSON(int id) throws ExcepcionGenerica
	{

		try 
		{
			pokemonJSON=pokemones.getJSONObject(id);
			pokemon.setId(pokemonJSON.getInt("Id"));
			pokemon.setNombre(pokemonJSON.getString("Nombre"));
			pokemon.setNivel(pokemonJSON.getInt("Nivel"));
			pokemon.setVidas(pokemonJSON.getInt("Vidas"));
			pokemon.setTipo(pokemonJSON.getString("Tipo"));
			pokemon.setRutaImagen(pokemonJSON.getString("RutaImagen"));
		}
		catch(JSONException ErrorJSON) 
		{
			ErrorJSON.printStackTrace();
			throw new ExcepcionGenerica("Error al abrir el archivo JSON");
		}
		finally 
		{
			return pokemon;
		}
	}
	
}
