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
    public Electrico_Acero  (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, genero, tipoElectrico + tipoAcero, rutaImagen);
    }
    
    /**
     * Constructor DEFECTO
     * @param id
     * @param nombre
     * @param genero
     * @param tipo
     * @param rutaImagen
     */
    public Electrico_Acero (int id, String nombre, String genero, String tipo,String rutaImagen)
    {
    	super(id, nombre,genero, tipoElectrico + tipoAcero, rutaImagen);
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
