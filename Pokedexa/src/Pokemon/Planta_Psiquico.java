package Pokemon;

public class Planta_Psiquico extends Pokemon implements IPlanta , IPsiquico{
	
    /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Planta_Psiquico (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoPlanta + tipoPsiquico, rutaImagen);
    }
	/**
	   * Constructor COPIA2
	   * @param Pokemon
	   */
	   public Planta_Psiquico(Pokemon poke)
	   {
	   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoPlanta + tipoPsiquico, poke.getRutaImagen());
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
    public Planta_Psiquico(int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoPlanta + tipoPsiquico, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelPlanta+nivelPsiquico+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
