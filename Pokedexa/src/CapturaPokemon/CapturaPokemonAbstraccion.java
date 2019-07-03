package CapturaPokemon;

import ManejadorExcepciones.ExcepcionGenerica;

public abstract class CapturaPokemonAbstraccion 
{
	/**
	 * @author Guilllermina Latorre
	 * se bajan las vidas del pokemon capturad, o captura pokemon, y suma un nivel al usuario .
	 * @throws ExcepcionGenerica 
	 */
	public abstract void resultadoPelea() throws ExcepcionGenerica;
}
