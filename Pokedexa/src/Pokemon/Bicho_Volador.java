package Pokemon;

public class Bicho_Volador extends Pokemon implements IBicho, IVolador {
	
	/**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Bicho_Volador (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, genero, tipoVolador + tipoBicho, rutaImagen);
    }
    
    /**
     * Constructor DEFECTO
     * @param id
     * @param nombre
     * @param genero
     * @param tipo
     * @param rutaImagen
     */
    public Bicho_Volador(int id, String nombre, String genero, String tipo,String rutaImagen)
    {
    	super(id, nombre,genero, tipoVolador + tipoBicho, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelVolador+nivelBicho+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}

