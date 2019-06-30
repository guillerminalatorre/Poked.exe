package Pokemon;

public class Veneno_Volador extends Pokemon implements IVeneno, IVolador {

	/**
	 * Constructor COPIA
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param genero
	 * @param rutaImagen
	 */
	public Veneno_Volador (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
	{
		super(id, nombre,nivel, vidas, evolucion,  tipoVeneno + tipoVolador, rutaImagen);
	}
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public Veneno_Volador(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(),tipoVeneno + tipoVolador, poke.getRutaImagen());
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
	public Veneno_Volador (int id, String nombre, int evolucion, String tipo,String rutaImagen)
	{
		super(id, nombre,evolucion,  tipoVeneno + tipoVolador, rutaImagen);
		setVidasNivel(calcularNivel());
	}
	
	
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelVeneno+nivelVolador+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}
	
}

