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
	public Tierra (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
	{
		super(id, nombre,nivel, vidas, genero,  tipoTierra, rutaImagen);
	}
	
	/**
	 * Constructor DEFECTO
	 * @param id
	 * @param nombre
	 * @param genero
	 * @param tipo
	 * @param rutaImagen
	 */
	public Tierra(int id, String nombre, String genero, String tipo,String rutaImagen)
	{
		super(id, nombre,genero,  tipoTierra, rutaImagen);
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
