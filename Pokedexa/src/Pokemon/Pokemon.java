package Pokemon;

public abstract class Pokemon 
{
	private int id;
	private String nombre;
	private int vidas;
	private int nivel;
	private String genero;
	private String tipo;
	private String rutaImagen;
	
	/**
	 * Constructor para COPIA, aplicado en la captura pokemon.
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param nivel
	 * @param genero
	 */
	public Pokemon (int id, String nombre,int Nivel,  int Vidas, String genero, String tipo,String rutaImagen)
	{
		this.id = id;
		this.nombre = nombre;
		setVidas(vidas);
		setNivel(nivel);
		this.genero = genero;
		this.tipo = tipo;
		this.rutaImagen = rutaImagen;
	}

	/**
	 * Constructor por DEFECTO, para pokemons nuevos.
	 * @param id
	 * @param nombre
	 * @param nivel
	 * @param genero
	 */
	public Pokemon (int id, String nombre, String genero, String tipo,String rutaImagen)
	{
		this.id = id;
		this.nombre = nombre;
		setVidas(0);
		setNivel(0);
		this.genero = genero;
		this.rutaImagen = rutaImagen;
	}
	
	/**
	 * las vidas varian en cada pokemon depende la actividad que se aplique sobre Ã©l, por eso pueden ser sobreescritas
	 * @param vidas
	 */
	protected void setVidas (int vidas)
	{
		this.vidas= vidas;
	}
	
	/**
	 * el nivel varia en cada pokemon depende la actividad que se aplique sobre Ã©l, por eso puede ser sobreescrito
	 * @param nivel
	 */
	protected void setNivel (int nivel)
	{
		this.nivel = nivel;
	}

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

	public String getGenero() {
		return genero;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setVidasNivel(int n)
	{
		setVidas(n);
		setNivel(n);
	}
	
	protected abstract int calcularNivel();
	
	protected int nivelPrimeraEvolucion()
	{	
		int nivel = 0;
		int[] primeraEvolucion = {1,4,7,10,13,16,19,21,23,25,27,29,32,35,37,39,41,43,46,48,50,52,54,56,58,60,63,66,69,72,74,77,79,81,83,84,86,88,90,92,95,96,98,100,102,104,106,108,109,111,113,114,115,116,118,120,122,123,124,125,126,127,128,129,131,132,133,140};
		int recorre=0;
		while(nivel == 0)
		{
			if(primeraEvolucion[recorre] == getId()) nivel=1;
			recorre++;
		}
		return nivel;
	}
	
	protected int nivelSegundaEvolucion()
	{
		int nivel = 0;
		int[] segundaEvolucion = {2,5,8,11,14,17,20,22,24,26,28,30,33,36,38,40,42,44,47,49,51,53,55,57,59,61,64,67,70,73,75,78,80,82,85,87,89,91,93,97,99,101,103,105,107,110,112,117,119,121,134,135,146,137,139,141};
		int recorre=0;
		while(nivel == 0)
		{
			if(segundaEvolucion[recorre] == getId()) nivel=10;
			recorre++;
		}
		return nivel;
	}
	
	protected int nivelTerceraEvolucion ()
	{
		int nivel = 0;
		int[] terceraEvolucion = {3,6,9,12,15,18,31,34,45,62,65,68,71,76,94,130,136,138,142,143,144,145,147,148,149,150};
		int recorre=0;
		while(nivel == 0)
		{
			if(terceraEvolucion[recorre] == getId()) nivel=20;
			recorre++;
		}
		return nivel;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + id;
		result = prime * result + nivel;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + vidas;
		return result;
	}

	/**
	 * compara si dos pokemons son iguales desde el objeto, la clase, la id, y el nombre.
	 */
	@Override
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
		return "Pokemon [id=" + id + ", nombre=" + nombre + ", vidas=" + vidas + ", nivel=" + nivel + ", genero="
				+ genero + "]";
	}
	
}
