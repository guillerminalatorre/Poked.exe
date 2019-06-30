package Pokemon;

public class Electrico_Acero extends Pokemon implements IElectrico , IAcero{
	
	/**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public Electrico_Acero  (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, evolucion, tipoElectrico + tipoAcero, rutaImagen);
    }
	/**
     * Constructor COPIA2
     * @param Pokemon
     */
    public Electrico_Acero(Pokemon poke)
    {
    	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoElectrico + tipoAcero, poke.getRutaImagen());
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
    public Electrico_Acero (int id, String nombre, int evolucion, String tipo,String rutaImagen)
    {
    	super(id, nombre,evolucion, tipoElectrico + tipoAcero, rutaImagen);
    	setVidasNivel(calcularNivel());
    }
    
  
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelElectrico+nivelAcero+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
