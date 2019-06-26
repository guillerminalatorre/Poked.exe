package GestorPokedex;
import Json.*;
import ManejadorExcepciones.ExcepcionGenerica;
import Pokemon.Pokemon;

public class GestorPokedex
{	
	//este método debe ser llamado 3 veces cada vez que el usuario entra en zona de pokemons salvajes 
	/**
	 * Arroja un pokemon, dependiendo de la cantidad de batallas que tenga el usuario.
	 * @param cantidadDeBatallas
	 * @return
	 * @throws ExcepcionGenerica
	 */
	public Pokemon pokemonRandomXnivel (int cantidadDeBatallas) throws ExcepcionGenerica 
	{	
		ManejadorJSON manejador = new ManejadorJSON();
		
		int id = 0;
		
		Pokemon pokemonRandom = null;
		
		id = (int) (Math.random() * 150) + 1;
		
		if(cantidadDeBatallas < 15)
		{
			while((pokemonRandom = manejador.SacarPokemonJSON(id)).getEvolucion() != 1)
			{
				id = (int) (Math.random() * 150) + 1;
			}
		}
		
		if(cantidadDeBatallas > 15 && cantidadDeBatallas < 30)
		{
			while((pokemonRandom = manejador.SacarPokemonJSON(id)).getEvolucion() == 3)
			{
				id = (int) (Math.random() * 150) + 1;
			}
		}
		
		else
		{
			id = (int) (Math.random() * 150) + 1;
		}
		
		return pokemonRandom;
	}
	
	
	
	
}
