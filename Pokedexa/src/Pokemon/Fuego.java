package Pokemon;

public class Fuego extends Pokemon implements IFuego {


    
    /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Fuego (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoFuego, rutaImagen);
    }
	/**
	    * Constructor COPIA2
	    * @param Pokemon
	    */
	   public Fuego(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(),  tipoFuego , poke.getRutaImagen());
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
    public Fuego(int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoFuego, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelFuego+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
