package GestorPokedex;
import Json.*;
import ManejadorExcepciones.ExcepcionGenerica;
import Pokemon.Pokemon;

public class GestorPokedex
{	
	/**
	 * Arroja un pokemon, dependiendo de la cantidad de batallas que tenga el usuario.
	 * @author Guillermina Latorre 
	 * @param cantidadDeBatallas
	 * @return Pokemon levantado del archivo JSON
	 * @throws ExcepcionGenerica
	 * @see {@link ManejadorJSON#leerPokemonJSON(int)}
	 */
	public Pokemon pokemonRandomXnivel (int cantidadDeBatallas) throws ExcepcionGenerica 
	{	
		ManejadorJSON manejador = new ManejadorJSON();
		
		int id = 0;
		
		Pokemon pokemonRandom = null;
		
		id = (int) (Math.random() * 18) + 1;
		
		if(cantidadDeBatallas < 18)
		{
			while((pokemonRandom = manejador.leerPokemonJSON(id)).getEvolucion() != 1)
			{
				id = (int) (Math.random() * 18) + 1;
			}
		}
		
		if(cantidadDeBatallas > 15 && cantidadDeBatallas < 30)
		{
			while((pokemonRandom = manejador.leerPokemonJSON(id)).getEvolucion() == 3)
			{
				id = (int) (Math.random() * 18) + 1;
			}
		}
		
		else
		{
			id = (int) (Math.random() * 18) + 1;
		}
		
		return pokemonRandom;
	}
	
	
	
	
}
