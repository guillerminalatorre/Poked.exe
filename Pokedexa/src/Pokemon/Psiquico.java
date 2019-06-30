package Pokemon;

public class Psiquico extends Pokemon implements  IPsiquico{
	
    /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Psiquico (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoPsiquico, rutaImagen);
    }
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public Psiquico(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoPsiquico, poke.getRutaImagen());
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
    public Psiquico(int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoPsiquico, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelPsiquico+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
