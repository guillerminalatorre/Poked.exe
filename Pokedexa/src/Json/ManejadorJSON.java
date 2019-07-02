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
	
	public void cargarPokedexBDD ()
	{
		try {


			File imagenID1 = new File("src\\Imagenes\\1.jpg");
			CargarPokemon(new PlantaVeneno(1, "Bulbasur", 1, " ", imagenID1.getAbsolutePath()));

			File imagenID2 = new File("src\\Imagenes\\2.jpg");
			CargarPokemon(new PlantaVeneno(2, "Ivisaur", 2, " ", imagenID2.getAbsolutePath()));

			File imagenID3 = new File("src\\Imagenes\\3.jpg");
			CargarPokemon(new PlantaVeneno(3, "Venusaur", 3, " ", imagenID3.getAbsolutePath()));

			File imagenID4 = new File("src\\Imagenes\\4.jpg");
			CargarPokemon(new Fuego(4, "Charmander", 1, " ", imagenID4.getAbsolutePath()));

			File imagenID5 = new File("src\\Imagenes\\5.jpg");
			CargarPokemon(new Fuego(5, "Charmaleon", 2, " ", imagenID5.getAbsolutePath()));

			File imagenID6 = new File("src\\Imagenes\\6.jpg");
			CargarPokemon(new Fuego_Volador(6, "Charizard", 3, " ", imagenID6.getAbsolutePath()));

			File imagenID7 = new File("src\\Imagenes\\7.jpg");
			CargarPokemon(new Agua(7, "Squirtle", 1, " ", imagenID7.getAbsolutePath()));

			File imagenID8 = new File("src\\Imagenes\\8.jpg");
			CargarPokemon(new Agua(8, "Wartortle", 2, " ", imagenID8.getAbsolutePath()));

			File imagenID9 = new File("src\\Imagenes\\9.jpg");
			CargarPokemon(new Agua(9, "Blastoise", 3, " ", imagenID9.getAbsolutePath()));


			File imagenID10 = new File("src\\Imagenes\\10.jpg");
			CargarPokemon(new Bicho(10, "Cartepie", 1, " ", imagenID10.getAbsolutePath()));


			File imagenID11 = new File("src\\Imagenes\\11.jpg");
			CargarPokemon(new Bicho(11, "Metapod", 2, " ", imagenID11.getAbsolutePath()));


			File imagenID12 = new File("src\\Imagenes\\12.jpg");
			CargarPokemon(new Bicho_Volador(12, "Butterfree", 3, " ", imagenID12.getAbsolutePath()));


			File imagenID13 = new File("src\\Imagenes\\13.jpg");
			CargarPokemon(new Bicho_Veneno(13, "Weedle", 1, " ", imagenID13.getAbsolutePath()));

			File imagenID14 = new File("src\\Imagenes\\14.jpg");
			CargarPokemon(new Bicho_Veneno(14, "Kakuna",2, " ", imagenID14.getAbsolutePath()));


			File imagenID15 = new File("src\\Imagenes\\15.jpg");
			CargarPokemon(new Bicho_Veneno(15, "Beedrill", 3, " ", imagenID15.getAbsolutePath()));


			File imagenID16 = new File("src\\Imagenes\\16.jpg");
			CargarPokemon(new Normal_Volador(16, "Pidgey", 1, " ", imagenID16.getAbsolutePath()));


			File imagenID17 = new File("src\\Imagenes\\17.jpg");
			CargarPokemon(new Normal_Volador(17, "Pidgeotto", 2, " ", imagenID17.getAbsolutePath()));


			File imagenID18 = new File("src\\Imagenes\\18.jpg");
			CargarPokemon(new Normal_Volador(18, "Pidgeot", 3, " ", imagenID18.getAbsolutePath()));


			File imagenID19 = new File("src\\Imagenes\\19.jpg");
			CargarPokemon(new Normal(19, "Rattata", 1, " ", imagenID19.getAbsolutePath()));

			cargarArchivoJSON();

		} catch (ExcepcionGenerica error) {
			System.out.println(error.MensajeError());
		}

	}
}
