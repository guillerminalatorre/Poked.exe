package Pokemon;

import java.io.Serializable;

public class Pokemon implements Serializable 
{
	private int id;
	private String nombre;
	private int vidas;
	private int nivel;
	private int evolucion;
	private String tipo;
	private String rutaImagen;

	//CONSTRUCTORES

	/**
	 * Constructor para COPIA, aplicado en la captura pokemon.
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param nivel
	 * @param evolucion
	 */
	public Pokemon (int id, String nombre,int Nivel,  int Vidas, int evolucion, String tipo,String rutaImagen)
	{
		this.id = id;
		this.nombre = nombre;
		setVidas(vidas);
		setNivel(nivel);
		this.evolucion = evolucion;
		this.tipo = tipo;
		this.rutaImagen = rutaImagen;
	}
	/**
	 * Constructor COPIA2
	 * @param Pokemon
	 */
	public Pokemon(Pokemon poke)
	{
		id=poke.getId();
		nombre=poke.getNombre();
		setVidas(poke.getVidas());
		setNivel(poke.getNivel());
		tipo=poke.getTipo();
		evolucion= poke.getEvolucion();
		rutaImagen=poke.getRutaImagen();
	}
	
	/**
	 * Constructor por DEFECTO, para pokemons nuevos.
	 * @param id
	 * @param nombre
	 * @param nivel
	 * @param evolucion
	 */
	public Pokemon (int id, String nombre, int evolucion, String tipo,String rutaImagen)
	{
		this.id = id;
		this.nombre = nombre;
		setVidas(0);
		setNivel(0);
		this.tipo=tipo;
		this.evolucion = evolucion;
		this.rutaImagen = rutaImagen;
	}
	
	/**
	 * Constructor por DEFECTO2, para pokemons nuevos.
	 * @param id
	 * @param nombre
	 * @param nivel
	 * @param evolucion
	 */
	public Pokemon ()
	{
		this.id = 0;
		this.nombre = " ";
		setVidas(0);
		setNivel(0);
		this.tipo=" ";
		this.evolucion =0;
		this.rutaImagen = " ";
	}

	//SETTERS

	/**
	 * las vidas varian en cada pokemon depende la actividad que se aplique sobre Ã©l, por eso pueden ser sobreescritas
	 * @param vidas
	 */
	public void setVidas (int vidas)
	{
		this.vidas= vidas;
	}
	
	/**
	 * el nivel varia en cada pokemon depende la actividad que se aplique sobre Ã©l, por eso puede ser sobreescrito
	 * @param nivel
	 */
	public void setNivel (int nivel)
	{
		this.nivel = nivel;
	}
	
	public void setVidasNivel (int n)
	{
		setNivel(n);
		setVidas(n);
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setEvolucion(int evolucion) {
		this.evolucion = evolucion;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	
	
	//GETTERS 

	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}

	public int getVidas() {
		return vidas;
	}

	public int getNivel() {
		return nivel;
	}

	public int getEvolucion() {
		return evolucion;
	}

	public String getTipo() {
		return tipo;
	}
	public String getRutaImagen(){
		return rutaImagen;
	}

	protected int calcularNivel()
	{
		return nivel+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

	
	
	//CALCULADORES DE NIVEL
	
	
	
	protected int nivelPrimeraEvolucion()
	{	
		int nivel = 0;
		if (getEvolucion() == 1 ) nivel =1;
		return nivel;
	}

	protected int nivelSegundaEvolucion()
	{
		int nivel = 0;
		if (getEvolucion() == 2 ) nivel =10;
		return nivel;
	}

	protected int nivelTerceraEvolucion ()
	{
		int nivel = 0;
		if (getEvolucion() == 1 ) nivel =20;
		return nivel;
	}

	
	//METODOS OVERRIDE
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + evolucion;
		result = prime * result + id;
		result = prime * result + nivel;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + vidas;
		return result;
	}

	@Override
	/**
	 * compara si dos pokemons son iguales desde el objeto, la clase, la id, y el nombre.
	 */

	public boolean equals(Object obj) 
	{
		boolean rta = false;
		if (this != obj) {
			if (obj != null) {
				if (obj instanceof Pokemon) {
					Pokemon other = (Pokemon) obj;

					if (id == other.getId()) {

						if (nombre == null) {
							if (other.getNombre() == null)
								rta = true;
						} else if (nombre.equalsIgnoreCase(other.getNombre()))
							rta = true;
					}
				}
			}
		}
		return rta;
	}

	@Override
	public String toString() {
		return "\n -ID: " + id + "\n -Nombre:" + nombre + "\n -Vidas: " + vidas + "\n Nivel: " + nivel + "\n -Evolucion: "
				+ evolucion + "\n -Tipo: "+ tipo;
	}

}
