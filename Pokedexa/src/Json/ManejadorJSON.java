package Json;
import org.json.*;
import Pokemon.*;
import ManejadorExcepciones.*;

public class ManejadorJSON {
	JSONArray pokemones;
	JSONObject pokemonJSON;
	Pokemon pokemon=null;
	//Pokemon pokemonObj;
	
	public ManejadorJSON() throws ExcepcionGenerica {
		try {
			pokemones=new JSONArray("pokemones");
		}
		catch(JSONException ErrorJson) {
			ErrorJson.printStackTrace();
			throw new ExcepcionGenerica("Error al realizar al crear el JSONArray");
		}
	}
	public void CargarPokemon(Pokemon pokemonObj) throws ExcepcionGenerica{
		try {
			pokemonJSON.put("Id", pokemonObj.getId());
			pokemonJSON.put("Nombre", pokemonObj.getNombre());
			pokemonJSON.put("Nivel", pokemonObj.getNivel());
			pokemonJSON.put("Vidas", pokemonObj.getVidas());
			pokemonJSON.put("Tipo", pokemonObj.getTipo());
			pokemonJSON.put("RutaImagen", pokemonObj.getRutaImagen());
			pokemones.put(pokemonJSON);
		}
		catch(JSONException ErrorJSON) {
			ErrorJSON.printStackTrace();
			throw new ExcepcionGenerica("Error al cargar pokemon"+pokemonObj.getNombre()+" en archivo JSON");
		}
	}
	
	@SuppressWarnings("finally")
	public Pokemon SacarPokemonJSON(int id) throws ExcepcionGenerica{

		try {
			pokemonJSON=pokemones.getJSONObject(id);
			pokemon.setId(pokemonJSON.getInt("Id"));
			pokemon.setNombre(pokemonJSON.getString("Nombre"));
			pokemon.setNivel(pokemonJSON.getInt("Nivel"));
			pokemon.setVidas(pokemonJSON.getInt("Vidas"));
			pokemon.setTipo(pokemonJSON.getString("Tipo"));
			pokemon.setRutaImagen(pokemonJSON.getString("RutaImagen"));
		}
			catch(JSONException ErrorJSON) {
				ErrorJSON.printStackTrace();
				throw new ExcepcionGenerica("Error al abrir el archivo JSON");
			}
		finally {
			return pokemon;
		}
	}
	
}
