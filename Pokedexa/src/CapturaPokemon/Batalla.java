package CapturaPokemon;
import GestorUsuarios.*;
import ManejadorExcepciones.ExcepcionGenerica;
import Pokemon.*;
import Usuario.*;

public class Batalla  extends CapturaPokemonAbstraccion {
  
	private Usuario usuario;
	private Pokemon pokemonCapturado;
	private Pokemon pokemonSalvaje;
	private int Ganador;
	
	//CONSTRUCTOR
	
	/**
	 * Constructor de la clase Batalla
	 * @author Matias Araneta
	 * @param pokemonCapturado
	 * @param pokemonSalvaje
	 * @param usuario
	 */
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
	 * @author Guillermina Latorre
	 * @return diferencia entre los niveles de los dos pokemons
	 * @see {@link Batalla#definirGanador()}
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
	 * @author Matias Araneta
	 * si el resultado de calcularGanador() es mayor a 0 gana el capturado, si es menos a 0 gana el salvaje, si es igual a cero es random.
	 * @see {@link Batalla#resultadoPelea()}
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
	 * @author Guilllermina Latorre
	 * se bajan las vidas del pokemon capturad, o captura pokemon, y suma un nivel al usuario .
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
