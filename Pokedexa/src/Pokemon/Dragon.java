package Pokemon;

public class Dragon extends Pokemon implements IDragon {
	
	/**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Dragon (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoDragon , rutaImagen);
    }
	/**
     * Constructor COPIA2
     * @param Pokemon
     */
    public Dragon(Pokemon poke)
    {
    	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoDragon, poke.getRutaImagen());
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
    public Dragon(int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoDragon , rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelDrago+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
