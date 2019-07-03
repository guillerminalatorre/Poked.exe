package CentroPokemon;


import java.util.ArrayList;

import ManejadorExcepciones.ExcepcionGenerica;
import Pokemon.*;
import Usuario.Usuario;

public class CentroPokemon 
{
	Usuario usuario;
	boolean compuertaAbierta;
	
	public boolean getCompuertaAbierta()
	{
		return compuertaAbierta;
	}
	
	/**
	 * @author Guillermina Latorre
	 * Constructor de la clase
	 * @param usuario
	 */
	public CentroPokemon(Usuario usuario)
	{
		this.usuario =usuario;
		this.compuertaAbierta = true;
	}

	/**
	 * @author Guillermina Latorre
	 * @throws ExcepcionGenerica
	 * Si estan todos lo pokemon debilitados el atributo compuertaAbierta es true.
	 * @see {@link Usuario#estanTodosDebilitados()}
	 */
	public void manejadorDeCompuerta() throws ExcepcionGenerica
	{
		if(compuertaAbierta == false)
		{
			try 
			{
				if(usuario.estanTodosDebilitados())
				{
					compuertaAbierta = true;
				}
			}
			catch(ExcepcionGenerica excepcion)
			{
				System.err.println("El metodo estanTodosDebilitados() no funciona correctamente");
			}
		}
	}
	
	/**
	 * @author Guillermina Latorre
	 * @return String con la cedena de pokemons capturados danados.
	 * @throws ExcepcionGenerica
	 * @see {@link Usuario#listarPokemonsDanados()}
	 */
	public String listarPokemonsDanados() throws ExcepcionGenerica
	{
		return usuario.listarPokemonsDanados();
	}
	
	/**
	 * @author Guillermina Latorre
	 * @throws ExcepcionGenerica
	 * Restaura las vidas de los pokemons danados
	 * @see {@link Usuario#restaurarVidas()}
	 */
	public void curarPokemonsDañados() throws ExcepcionGenerica
	{
		usuario.restaurarVidas();
		compuertaAbierta = false;
	}
	
	
	
	
}
