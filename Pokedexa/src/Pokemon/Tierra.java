package Pokemon;

public class Tierra extends Pokemon implements ITierra{


	/**
	 * Constructor COPIA
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param genero
	 * @param rutaImagen
	 */
	public Tierra (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
	{
		super(id, nombre,nivel, vidas, evolucion,  tipoTierra, rutaImagen);
	}
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public Tierra(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoTierra, poke.getRutaImagen());
	   	setVidasNivel(calcularNivel());
	   }
	/**
	 * Constructor DEFECTO
	 * @param id
	 * @param nombre
	 * @param genero
	 * @param tipo
	 * @param rutaImagen
	 */
	public Tierra(int id, String nombre, int evolucion, String tipo,String rutaImagen)
	{
		super(id, nombre,evolucion,  tipoTierra, rutaImagen);
		setVidasNivel(calcularNivel());
	}
	
	
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelTierra+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}
	
}
