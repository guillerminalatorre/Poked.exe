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
	    public Agua_Psiquico (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
	    {
	    	super(id, nombre,nivel, vidas, evolucion, tipoAgua + tipoPsiquico, rutaImagen);
	    }
		/**
	     * Constructor COPIA2
	     * @param Pokemon
	     */
	    public Agua_Psiquico(Pokemon poke)
	    {
	    	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoAgua + tipoPsiquico, poke.getRutaImagen());
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
	    public Agua_Psiquico(int id, String nombre, int evolucion, String tipo,String rutaImagen)
	    {
	    	super(id, nombre,evolucion, tipoAgua + tipoPsiquico, rutaImagen);
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

