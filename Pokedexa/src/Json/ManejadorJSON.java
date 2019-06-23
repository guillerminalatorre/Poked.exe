import org.json.*;

public class ManejadorJSON {
	JSONArray pokemones;
	JSONObject pokemon;
	Pokemon pokemonObj;
	
	public ManejadorJSON() throws ErrorGenerico {
		try {
			pokemones=new JSONArray("pokemones");
		}
		catch(JSONException ErrorJson) {
			ErrorJson.printStackTrace();
			throw new ErrorGenerico("Error al realizar al crear el JSONArray");
		}
	}
	public void CargarPokemon(int id, String nombre , int vidas , int nivel , String genero , String tipo , String rutaImagen) {
		
	}
}
