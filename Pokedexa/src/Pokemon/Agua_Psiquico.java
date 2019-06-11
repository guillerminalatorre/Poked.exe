package Pokemon;

public class Agua_Psiquico extends Pokemon implements IAgua , IPsiquico {

	    
	    /**
	     * Constructor COPIA
	     * @param id
	     * @param nombre
	     * @param vidas
	     * @param genero
	     * @param rutaImagen
	     */
	    public Agua_Psiquico (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
	    {
	    	super(id, nombre,nivel, vidas, genero, tipoAgua + tipoPsiquico, rutaImagen);
	    }
	    
	    /**
	     * Constructor DEFECTO
	     * @param id
	     * @param nombre
	     * @param genero
	     * @param tipo
	     * @param rutaImagen
	     */
	    public Agua_Psiquico(int id, String nombre, String genero, String tipo,String rutaImagen)
	    {
	    	super(id, nombre,genero, tipoAgua + tipoPsiquico, rutaImagen);
	    	setVidasNivel(calcularNivel());
	    }
	    
	  
		
		/**
		 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
		 */
		protected int calcularNivel() 
		{
			return nivelAgua+nivelPsiquico+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
		}

}

