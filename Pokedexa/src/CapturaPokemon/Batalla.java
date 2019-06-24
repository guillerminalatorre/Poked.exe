package CapturaPokemon;

import ManejadorExcepciones.ExcepcionGenerica;
import Pokemon.*;
import Usuario.*;

public class Batalla {
  
	private Usuario usuario;
	private Pokemon pokemonCapturado;
	private Pokemon pokemonSalvaje;
	private int Ganador;
	
	public Batalla(Pokemon pokemonCapturado ,Pokemon pokemonSalvaje, Usuario usuario)
	{
		this.pokemonCapturado = pokemonCapturado;
		this.pokemonSalvaje = pokemonSalvaje;
		setGanador(0);
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

	
	//METODOS
	
	/**
	 * 
	 * @return
	 */
	public int calcularGanador()
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
	public void definirGanador() 
	{
		
		int x = calcularGanador();
		
		if(x > 0) {
			setGanador(1);
		}
		else  if (x < 0 ) {
			setGanador(x);
		}
		else  {
			setGanador((Math.random()*3 + 1) + 2  );
		}	
		
	}
	
	/**
	 * se acreditan los premios o las prendas al pokemon capturado
	 */
	public void resultadoPelea()
	{
		if(getGanador() == 1 || getGanador() == 3)//si gana el capturado
		{
			pokemonCapturado.setNivel( pokemonCapturado.getNivel() +1);//se le suma uno nivel
			//capturarPokemon.
		}
		
		if(getGanador() < 0 )//si gana el salvaje 
		{
			if(( 0 - pokemonCapturado.getNivel()) > getGanador() )// si hay que restarle al pokemon capturado mas vidas de las que tiene 
			{
				pokemonCapturado.setVidas( 0 );
			}
			else
			{
				pokemonCapturado.setNivel( pokemonCapturado.getNivel() - getGanador() );
			} 
		} 
		
		if(getGanador() == 4)//si gana el salvaje por random
		{
			pokemonCapturado.setVidas( 0 );//golpe critico
		}
	}
	
	/**
	 * Si el pokemon no fue visto por el usuario lo carga.
	 * @param pokemon
	 * @throws ExcepcionGenerica
	 */
	public void agregarNuevoPokemonPokedex ( Pokemon pokemon ) throws ExcepcionGenerica
	{
		if(! usuario.ElPokemonFueVisto(pokemon))
		{
			usuario.cargarNuevoPokemonVisto(pokemon);
		}
	}
	
	
	/**
	 * método para averigüar a que subclase de Pokemon pertenece el pokemon Salvaje y guardarlo en el archivo binario capturados
	 * @param pokemon
	 * @return
	 */
	public void agregarNuevoPokemonCapturado ( Pokemon pokemon ) throws ExcepcionGenerica
	{

		if (pokemon instanceof Agua_Hielo) {
			usuario.cargarNuevoPokemonCapturado(new Agua_Hielo(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Agua_Lucha) {
			usuario.cargarNuevoPokemonCapturado(new Agua_Lucha(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Agua_Psiquico) {
			usuario.cargarNuevoPokemonCapturado(new Agua_Psiquico(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Agua_Veneno) {
			usuario.cargarNuevoPokemonCapturado(new Agua_Veneno(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Agua_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Agua_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Agua) {
			usuario.cargarNuevoPokemonCapturado(new Agua(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Bicho_Planta) {
			usuario.cargarNuevoPokemonCapturado(new Bicho_Veneno(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Bicho_Veneno) {
			usuario.cargarNuevoPokemonCapturado(new Bicho_Veneno(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Bicho_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Bicho_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Bicho ) {
			usuario.cargarNuevoPokemonCapturado(new Bicho(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Dragon_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Dragon_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Dragon) {
			usuario.cargarNuevoPokemonCapturado(new Dragon(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Electrico_Acero) {
			usuario.cargarNuevoPokemonCapturado(new Electrico_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Electrico_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Electrico(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Electrico) {
			usuario.cargarNuevoPokemonCapturado(new Electrico(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Fantasma_Veneno) {
			usuario.cargarNuevoPokemonCapturado(new Fantasma_Veneno(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Fuego_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Fuego_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		}
		if (pokemon instanceof Fuego) {
			usuario.cargarNuevoPokemonCapturado(new Fuego(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Hada) {
			usuario.cargarNuevoPokemonCapturado(new Hada(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Hielo_Psiquico) {
			usuario.cargarNuevoPokemonCapturado(new Hielo_Psiquico(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Hielo_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Hielo_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Lucha) {
			usuario.cargarNuevoPokemonCapturado(new Lucha(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Normal_Hada) {
			usuario.cargarNuevoPokemonCapturado(new Normal_Hada(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Normal_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Normal_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Normal) {
			usuario.cargarNuevoPokemonCapturado(new Normal(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Planta_Psiquico) {
			usuario.cargarNuevoPokemonCapturado(new Planta_Psiquico(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof PlantaVeneno) {
			usuario.cargarNuevoPokemonCapturado(new PlantaVeneno(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Psiquico_Hada) {
			usuario.cargarNuevoPokemonCapturado(new Psiquico_Hada(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Psiquico) {
			usuario.cargarNuevoPokemonCapturado(new Psiquico(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Roca_Agua) {
			usuario.cargarNuevoPokemonCapturado(new Roca_Agua(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Roca_Tierra) {
			usuario.cargarNuevoPokemonCapturado(new Roca_Tierra(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Roca_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Roca_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Tierra_Veneno) {
			usuario.cargarNuevoPokemonCapturado(new Tierra_Veneno(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Tierra) {
			usuario.cargarNuevoPokemonCapturado(new Tierra(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Veneno_Volador) {
			usuario.cargarNuevoPokemonCapturado(new Veneno_Volador(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
		if (pokemon instanceof Veneno) {
			usuario.cargarNuevoPokemonCapturado(new Veneno(pokemon.getId(), pokemon.getNombre(), pokemon.getEvolucion(), pokemon.getTipo(), pokemon.getRutaImagen()));
		} 
	}
}
