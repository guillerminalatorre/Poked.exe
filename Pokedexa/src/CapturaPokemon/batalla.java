package CapturaPokemon;

import Pokemon.Pokemon;

public class batalla {
  
	private Pokemon Pokemon_Capturado;
	private Pokemon Pokemon_Salvaje;
	private int Ganador;
	
	public batalla(Pokemon Pokemon_Capturado ,Pokemon Pokemon_Salvaje ){
		
		this.Pokemon_Capturado = Pokemon_Capturado;
		this.Pokemon_Salvaje = Pokemon_Salvaje;
		setGanador(0);
		
		}


	public int getGanador() {
		return Ganador;
	}

	public void setGanador(double d) {
		Ganador = (int) d;
	}


	public Pokemon getPokemon_Salvaje() {
		return Pokemon_Salvaje;
	}


	public void setPokemon_Salvaje(Pokemon pokemon_Salvaje) {
		Pokemon_Salvaje = pokemon_Salvaje;
	}


	public Pokemon getPokemon_Capturado() {
		return Pokemon_Capturado;
	}

	
	public int calcularGanador(){
		
		int nivel_ganador = 0;
		
		if(Pokemon_Capturado instanceof Pokemon ) {
		}	
			else if(Pokemon_Salvaje instanceof Pokemon ) {
				
				nivel_ganador = Pokemon_Capturado.getNivel() - Pokemon_Salvaje.getNivel();
			}
	
		return nivel_ganador;		
	}
	
	public void definirGanador() {
		
		int x = calcularGanador();
		
		if(x > 0) {
			setGanador(1);
		}
		else  if (x < 0 ) {
			setGanador(x);
		}
		else  {
			setGanador(Math.random()*3 + 1);
		}	
		
	}
	
}	
	
