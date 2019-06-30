package Pokemon;

public class Veneno extends Pokemon implements  IVeneno {

	/**
	 * Constructor COPIA
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param genero
	 * @param rutaImagen
	 */
	public Veneno (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
	{
		super(id, nombre,nivel, vidas, evolucion,  tipoVeneno, rutaImagen);
	}
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public Veneno(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(),tipoVeneno, poke.getRutaImagen());
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
	public Veneno (int id, String nombre, int evolucion, String tipo,String rutaImagen)
	{
		super(id, nombre,evolucion,  tipoVeneno, rutaImagen);
		setVidasNivel(calcularNivel());
	}
	
	
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelVeneno+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}
	
}
