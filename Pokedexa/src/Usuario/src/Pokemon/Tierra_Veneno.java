package Pokemon;

public class Tierra_Veneno extends Pokemon implements ITierra , IVeneno {

	/**
	 * Constructor COPIA
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param genero
	 * @param rutaImagen
	 */
	public Tierra_Veneno (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
	{
		super(id, nombre,nivel, vidas, evolucion,  tipoTierra + tipoVeneno, rutaImagen);
	}
	
	/**
	 * Constructor DEFECTO
	 * @param id
	 * @param nombre
	 * @param genero
	 * @param tipo
	 * @param rutaImagen
	 */
	public Tierra_Veneno (int id, String nombre, int evolucion, String tipo,String rutaImagen)
	{
		super(id, nombre,evolucion,  tipoTierra + tipoVeneno, rutaImagen);
		setVidasNivel(calcularNivel());
	}
	
	
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelTierra+nivelVeneno+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}
	
}
