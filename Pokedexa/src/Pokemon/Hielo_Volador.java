package Pokemon;

public class Hielo_Volador extends Pokemon implements IHielo , IVolador{


    
    /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Hielo_Volador (int id, String nombre,int nivel, int vidas, int evolucion ,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion , tipoHielo + tipoVolador, rutaImagen);
    }
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public Hielo_Volador(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoHielo + tipoVolador, poke.getRutaImagen());
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
    public Hielo_Volador (int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion , tipoHielo + tipoVolador, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelHielo+nivelVolador+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
