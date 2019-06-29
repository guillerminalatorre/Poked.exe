package Pokemon;

public class Roca_Agua extends Pokemon implements IRoca , IAgua{

	/**
	 * Constructor COPIA
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param genero
	 * @param rutaImagen
	 */
	public Roca_Agua (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
	{
		super(id, nombre,nivel, vidas, evolucion, tipoRoca + tipoAgua, rutaImagen);
	}
	
	/**
	 * Constructor DEFECTO
	 * @param id
	 * @param nombre
	 * @param genero
	 * @param tipo
	 * @param rutaImagen
	 */
	public Roca_Agua(int id, String nombre, int evolucion, String tipo,String rutaImagen)
	{
		super(id, nombre,evolucion, tipoRoca + tipoAgua, rutaImagen);
		setVidasNivel(calcularNivel());
	}
	
	
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelRoca+nivelAgua+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}
	
}
