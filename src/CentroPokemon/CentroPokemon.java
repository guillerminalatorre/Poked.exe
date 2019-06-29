package CentroPokemon;


import java.util.ArrayList;

import ManejadorExcepciones.ExcepcionGenerica;
import Pokemon.*;
import Usuario.Usuario;

public class CentroPokemon 
{
	Usuario usuario;
	boolean compuerta;
	
	
	public CentroPokemon(Usuario usuario)
	{
		this.usuario =usuario;
		this.compuerta = true;
	}
	

	public String mostrarPokemonsDañados() throws ExcepcionGenerica
	{
		ArrayList <Pokemon> pokemonsDañados = usuario.getPokemonsDañados();
		
		String dañados = "";
		
		for(Pokemon dañado : pokemonsDañados)
		{
			dañados = dañado + "\n" +  dañado.toString();
		}
		
		return dañados;
	}
	
	// devuelve false si ya se curaron los pokemon, asi la compuerta se cierra, true si no se curaron, asi la compuerta sigue abierta.
	public boolean curarPokemonsDañados() throws ExcepcionGenerica
	{
		boolean rta=false;
		
		if(usuario.restaurarVidas(usuario.getPokemonsDañados()));
		
		else rta=true;
		
		return rta;
	}
	
	
	
	
}
