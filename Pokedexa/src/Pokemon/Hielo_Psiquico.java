package Pokemon;

public class Hielo_Psiquico extends Pokemon implements IHielo , IPsiquico{


    
    /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Hielo_Psiquico (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoHielo + tipoPsiquico, rutaImagen);
    }
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public Hielo_Psiquico(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoHielo + tipoPsiquico, poke.getRutaImagen());
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
    public Hielo_Psiquico(int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoHielo + tipoPsiquico, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelHielo+nivelPsiquico+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
