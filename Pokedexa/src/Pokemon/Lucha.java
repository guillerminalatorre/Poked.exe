package Pokemon;

public class Lucha extends Pokemon implements ILucha {
	
		/**
	    * Constructor COPIA
	    * @param id
	    * @param nombre
	    * @param vidas
	    * @param genero
	    * @param rutaImagen
	    */
	   public Lucha (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
	   {
	   	super(id, nombre,nivel, vidas, evolucion, tipoLucha, rutaImagen);
	   }
		/**
		   * Constructor COPIA2
		   * @param Pokemon
		   */
		   public Lucha(Pokemon poke)
		   {
		   	super(poke.getId(), poke.getNombre(),poke.getEvolucion(), tipoLucha, poke.getRutaImagen());
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
	   public Lucha  (int id, String nombre, int evolucion, String tipo,String rutaImagen)
	   {
	   	super(id, nombre,evolucion, tipoLucha, rutaImagen);
	   	setVidasNivel(calcularNivel());
	   }
	   
	 
		
		/**
		 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
		 */
		protected int calcularNivel() 
		{
			return nivelLucha+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
		}

}




