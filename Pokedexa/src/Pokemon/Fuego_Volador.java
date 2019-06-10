package Pokemon;

public class Fuego_Volador extends Pokemon implements IFuego, IVolador {
	
		/**
	    * Constructor COPIA
	    * @param id
	    * @param nombre
	    * @param vidas
	    * @param genero
	    * @param rutaImagen
	    */
	   public Fuego_Volador  (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
	   {
	   	super(id, nombre,nivel, vidas, genero, tipoFuego + tipoVolador, rutaImagen);
	   }
	   
	   /**
	    * Constructor DEFECTO
	    * @param id
	    * @param nombre
	    * @param genero
	    * @param tipo
	    * @param rutaImagen
	    */
	   public Fuego_Volador  (int id, String nombre, String genero, String tipo,String rutaImagen)
	   {
	   	super(id, nombre,genero, tipoFuego + tipoVolador, rutaImagen);
	   	setVidasNivel(calcularNivel());
	   }
	   
	 
		
		/**
		 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
		 */
		protected int calcularNivel() 
		{
			return nivelFuego+nivelVolador+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
		}

}

