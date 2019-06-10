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
	   public Lucha (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
	   {
	   	super(id, nombre,nivel, vidas, genero, tipoLucha, rutaImagen);
	   }
	   
	   /**
	    * Constructor DEFECTO
	    * @param id
	    * @param nombre
	    * @param genero
	    * @param tipo
	    * @param rutaImagen
	    */
	   public Lucha  (int id, String nombre, String genero, String tipo,String rutaImagen)
	   {
	   	super(id, nombre,genero, tipoLucha, rutaImagen);
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




