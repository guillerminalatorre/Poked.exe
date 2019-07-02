package CapturaPokemon;
import GestorUsuarios.*;
import ManejadorExcepciones.ExcepcionGenerica;
import Pokemon.*;
import Usuario.*;

public class Batalla {
  
	private Usuario usuario;
	private Pokemon pokemonCapturado;
	private Pokemon pokemonSalvaje;
	private int Ganador;
	
	//CONSTRUCTOR
	
	public Batalla(Pokemon pokemonCapturado ,Pokemon pokemonSalvaje, Usuario usuario)
	{
		this.pokemonCapturado = pokemonCapturado;
		this.pokemonSalvaje = pokemonSalvaje;
		definirGanador();
		this.usuario = usuario;
	}

	
	

	// GETTERS Y SETTERS
	
	public int getGanador() 
	{
		return Ganador;
	}

	public void setGanador(double d)
	{
		Ganador = (int) d;
	}


	public Pokemon getPokemonSalvaje() 
	{
		return pokemonSalvaje;
	}


	public void setPokemonSalvaje(Pokemon pokemonSalvaje)
	{
		this.pokemonSalvaje = pokemonSalvaje;
	}


	public Pokemon getPokemonCapturado() 
	{
		return pokemonCapturado;
	}

	
	//METODOS PARA CONCER EL GANADOR
	
	/**
	 * 
	 * @return
	 */
	private int calcularGanador()
	{
		
		int nivel_ganador = 0;
		
		if(pokemonCapturado instanceof Pokemon && 	pokemonSalvaje instanceof Pokemon ) 
		{
				nivel_ganador = pokemonCapturado.getNivel() - pokemonSalvaje.getNivel();
		}
	
		return nivel_ganador;		
	}
	
	
	/**
	 * si el resultado de calcularGanador() es mayor a 0 gana el capturado, si es menos a 0 gana el salvaje, si es igual a cero es random.
	 */
	private void definirGanador() 
	{
		
		int x = calcularGanador();
		
		if(x > 0) {
			setGanador(1);
		}
		else  if (x < 0 ) {
			setGanador(x);
		}
		else  {
			setGanador((Math.random()*2 + 1) + 2  );
		}	
		
	}
	
	
	//METODO PARA RESOLVER LA PELEA
	
	
	/**
	 * se acreditan los premios o las prendas al pokemon capturado, premio al usuario, y captura pokemon.
	 * @throws ExcepcionGenerica 
	 */
	public void resultadoPelea() throws ExcepcionGenerica
	{
		usuario.cargarNuevoPokemonVisto(pokemonSalvaje);
		
		if(getGanador() == 1 || getGanador() == 3)//si gana el capturado
		{
			pokemonCapturado.setNivel( pokemonCapturado.getNivel() +1);//se le suma uno nivel
			//capturarPokemon.
			usuario.actualizarUnPokemon(pokemonCapturado);
			
			//cambios en el usuario
			usuario.sumarBatalla();
			
			
			//se sobreescribe el usuario en el archivo para guardar los cambios.
			GestorUsuarios gestor = new GestorUsuarios();
			
			gestor.sobreescribirUsuario(usuario);
			
			//le resto vidas al pokemon salvaje y lo capturo
			pokemonSalvaje.setVidas( pokemonSalvaje.getVidas() / 4);
			
			usuario.cargarNuevoPokemonCapturado(pokemonSalvaje);
			
			setGanador(1);
		}
		
		if(getGanador() < 0 )//si gana el salvaje 
		{
			
			if(( 0 - pokemonCapturado.getVidas()) > getGanador() )// si hay que restarle al pokemon capturado mas vidas de las que tiene 
			{
				pokemonCapturado.setVidas( 0 );
				usuario.actualizarUnPokemon(pokemonCapturado);
				
				//cambios en el usuario
				usuario.sumarBatalla();
				
				//se sobreescribe el usuario en el archivo para guardar los cambios.
				GestorUsuarios gestor = new GestorUsuarios();
				
				gestor.sobreescribirUsuario(usuario);
				
			}
			else
			{
				pokemonCapturado.setVidas( pokemonCapturado.getVidas() - getGanador() );
				usuario.actualizarUnPokemon(pokemonCapturado);
				
				//cambios en el usuario
				usuario.sumarBatalla();
				
				//se sobreescribe el usuario en el archivo para guardar los cambios.
				GestorUsuarios gestor = new GestorUsuarios();
				
				gestor.sobreescribirUsuario(usuario);
			} 
			
			setGanador(2);
		} 
		
		if(getGanador() == 4)//si gana el salvaje por random
		{
			pokemonCapturado.setVidas( 0 );//golpe critico
			usuario.actualizarUnPokemon(pokemonCapturado);
			
			//se sobreescribe el usuario en el archivo para guardar los cambios.
			GestorUsuarios gestor = new GestorUsuarios();
			
			usuario.sumarBatalla();
			gestor.sobreescribirUsuario(usuario);
			
			setGanador(3);
		}
	}

	
}
