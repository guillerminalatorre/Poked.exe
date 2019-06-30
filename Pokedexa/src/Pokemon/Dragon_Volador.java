package Pokemon;

public class Dragon_Volador extends Pokemon implements IDragon , IVolador{
	
	/**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Dragon_Volador (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoDragon + tipoVolador, rutaImagen);
    }
	/**
     * Constructor COPIA2
     * @param Pokemon
     */
    public Dragon_Volador(Pokemon poke)
    {
    	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoDragon + tipoVolador, poke.getRutaImagen());
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
    public Dragon_Volador(int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoDragon + tipoVolador, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelDrago+nivelVolador+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}
}
