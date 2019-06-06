package Usuario;

public class Usuario {
  	private String Nombre; //NOMBRE DE USUARIO CON EL QUE SE VA A LOGUEAR
	private String Genero;
	private int Edad;
	private int CantidadDeBatallas; // "NIVEL" DEL JUGADOR
	
	// CONSTRUCTORES
	
	public Usuario(){
		super();
		Nombre="";
		Genero="";
		Edad=0;
		CantidadDeBatallas=0;
	}

	public Usuario(String nombre, String genero, int edad, int cantidadDeBatallas) {
		super();
		Nombre = nombre;
		Genero = genero;
		Edad = edad;
		CantidadDeBatallas = cantidadDeBatallas;
	}
	
	// GETTERS
	
	public int getCantidadDeBatallas() {
		return CantidadDeBatallas;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getGenero() {
		return Genero;
	}

	public int getEdad() {
		return Edad;
	}
	
	// SETTERS
	
	public void setCantidadDeBatallas(int cantidadDeBatallas) {
		CantidadDeBatallas = cantidadDeBatallas;
	}


	
	//METODOS OVERRIDE
	
	@Override
	public String toString() {
		return "\n-Nombre="+ Nombre +"\n-Genero=" + Genero + "\n-Edad=" + Edad + "\n-CantidadDeBatallas="
				+ CantidadDeBatallas;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(obj instanceof Usuario) {
			Usuario aux= (Usuario)obj;
			if (this.getNombre().equalsIgnoreCase(aux.getNombre())) 
				return true;
			else 
				return false;
		}
		else 
			return false;
	}
	
}
