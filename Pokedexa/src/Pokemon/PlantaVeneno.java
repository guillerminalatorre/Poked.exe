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
    public PlantaVeneno (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoPlanta + tipoVeneno, rutaImagen);
    }
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public PlantaVeneno(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoPlanta + tipoVeneno, poke.getRutaImagen());
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
    public PlantaVeneno(int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoPlanta + tipoVeneno, rutaImagen);
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
