package CapturaPokemon;

public class batalla {
  
	private String Pokemon_Capturado;
	private String Pokemon_Salvaje;
	private int Ganador;
	
	public batalla(String Pokemon_Capturado,String Pokemon_Salvaje){
		
		this.Pokemon_Capturado = Pokemon_Capturado;
		this.Pokemon_Salvaje = Pokemon_Capturado;
		setGanador(0);
		
		
	}

	public String getPokemon_Salvaje() {
		return Pokemon_Salvaje;
	}

	public void setPokemon_Salvaje(String pokemon_Salvaje) {
		Pokemon_Salvaje = pokemon_Salvaje;
	}

	public int getGanador() {
		return Ganador;
	}

	public void setGanador(int ganador) {
		Ganador = ganador;
	}

	public String getPokemon_Capturado() {
		return Pokemon_Capturado;
	}
	
	private int calcularGanador(){

	}

}
