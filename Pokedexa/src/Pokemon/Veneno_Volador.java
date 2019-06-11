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
	public Veneno_Volador (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
	{
		super(id, nombre,nivel, vidas, genero,  tipoVeneno + tipoVolador, rutaImagen);
	}
	
	/**
	 * Constructor DEFECTO
	 * @param id
	 * @param nombre
	 * @param genero
	 * @param tipo
	 * @param rutaImagen
	 */
	public Veneno_Volador (int id, String nombre, String genero, String tipo,String rutaImagen)
	{
		super(id, nombre,genero,  tipoVeneno + tipoVolador, rutaImagen);
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

