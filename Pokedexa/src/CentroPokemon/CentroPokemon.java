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
	
	public CentroPokemon(Usuario usuario)
	{
		this.usuario =usuario;
		this.compuertaAbierta = true;
	}
	

	//Este método se debe ajecutar cada  vez que el usuario vuelva al menú pokemon.
	
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


	public String listarPokemonsDanados() throws ExcepcionGenerica
	{
		return usuario.listarPokemonsDanados();
	}
	
	// devuelve false si ya se curaron los pokemon, asi la compuerta se cierra, true si no se curaron, asi la compuerta sigue abierta.
	
	public void curarPokemonsDañados() throws ExcepcionGenerica
	{
		usuario.restaurarVidas();
		compuertaAbierta = false;
	}
	
	
	
	
}
