package Pokemon;

public class Electrico extends Pokemon implements IElectrico {
	
	/**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param evolcion
     * @param rutaImagen
     */
    public Electrico (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoElectrico, rutaImagen);
    }
	/**
     * Constructor COPIA2
     * @param Pokemon
     */
    public Electrico(Pokemon poke)
    {
    	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoElectrico , poke.getRutaImagen());
    	setVidasNivel(calcularNivel());
    }
    /**
     * Constructor DEFECTO
     * @param id
     * @param nombre
     * @param evolucion
     * @param tipo
     * @param rutaImagen
     */
    public Electrico (int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoElectrico, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelElectrico+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
