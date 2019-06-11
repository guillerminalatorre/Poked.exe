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
    public Psiquico (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
    {
    	super(id, nombre,nivel, vidas, genero, tipoPsiquico, rutaImagen);
    }
    
    /**
     * Constructor DEFECTO
     * @param id
     * @param nombre
     * @param genero
     * @param tipo
     * @param rutaImagen
     */
    public Psiquico(int id, String nombre, String genero, String tipo,String rutaImagen)
    {
    	super(id, nombre,genero, tipoPsiquico, rutaImagen);
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
