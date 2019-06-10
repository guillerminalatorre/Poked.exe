package Pokemon;

public class Bicho extends Pokemon implements IBicho {
    
     /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Bicho (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, genero, tipoBicho, rutaImagen);
    }
    
    /**
     * Constructor DEFECTO
     * @param id
     * @param nombre
     * @param genero
     * @param tipo
     * @param rutaImagen
     */
    public Bicho(int id, String nombre, String genero, String tipo,String rutaImagen)
    {
    	super(id, nombre,genero,tipoBicho, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelBicho+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
