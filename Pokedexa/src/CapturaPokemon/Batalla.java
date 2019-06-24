package CapturaPokemon;

import Pokemon.*;

public class Batalla {
  
	private Pokemon pokemonCapturado;
	private Pokemon pokemonSalvaje;
	private int Ganador;
	
	public Batalla(Pokemon pokemonCapturado ,Pokemon pokemonSalvaje )
	{
		this.pokemonCapturado = pokemonCapturado;
		this.pokemonSalvaje = pokemonSalvaje;
		setGanador(0);
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
			//pokemonCapturado.setNivel( pokemonCapturado.getNivel() + x);
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
	
	public Pokemon nuevoPokemonCapturado ( Pokemon pokemonSalvaje )
	{
		
	}
	
	
}	
	
