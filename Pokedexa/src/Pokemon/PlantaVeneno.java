package Pokemon;

public class PlantaVeneno extends Pokemon implements IPlanta, IVeneno
{


    
    /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public PlantaVeneno (int id, String nombre, int vidas, String genero,String rutaImagen)
    {
    	super(id, nombre, vidas, genero, tipoPlanta + tipoVeneno, rutaImagen);
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
    public PlantaVeneno(int id, String nombre, String genero, String tipo,String rutaImagen)
    {
    	super(id, nombre,genero, tipoPlanta + tipoVeneno, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelPlanta+nivelVeneno+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
