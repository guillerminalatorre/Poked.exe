package Pokemon;

public class Normal_Volador extends Pokemon implements INormal, IVolador {
	
	/**
    * Constructor COPIA
    * @param id
    * @param nombre
    * @param vidas
    * @param genero
    * @param rutaImagen
    */
   public Normal_Volador (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
   {
   	super(id, nombre,nivel, vidas, evolucion, tipoNormal + tipoVolador, rutaImagen);
   }
   
   /**
    * Constructor DEFECTO
    * @param id
    * @param nombre
    * @param genero
    * @param tipo
    * @param rutaImagen
    */
   public Normal_Volador(int id, String nombre, int evolucion, String tipo,String rutaImagen)
   {
   	super(id, nombre,evolucion, tipoNormal + tipoVolador, rutaImagen);
   	setVidasNivel(calcularNivel());
   }
   
 
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelNormal+nivelVolador+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
