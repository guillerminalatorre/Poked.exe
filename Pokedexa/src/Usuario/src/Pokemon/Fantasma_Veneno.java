
package Pokemon;

public class Fantasma_Veneno extends Pokemon implements IFantasma , IVeneno{
	
	/**
    * Constructor COPIA
    * @param id
    * @param nombre
    * @param vidas
    * @param genero
    * @param rutaImagen
    */
   public Fantasma_Veneno (int id, String nombre,int nivel, int vidas, int evolucion,String rutaImagen)
   {
   	super(id, nombre,nivel, vidas, evolucion, tipoFantasma + tipoVeneno, rutaImagen);
   }
   
   /**
    * Constructor DEFECTO
    * @param id
    * @param nombre
    * @param genero
    * @param tipo
    * @param rutaImagen
    */
   public Fantasma_Veneno (int id, String nombre, int evolucion, String tipo,String rutaImagen)
   {
   	super(id, nombre,evolucion, tipoFantasma + tipoVeneno, rutaImagen);
   	setVidasNivel(calcularNivel());
   }
   
 
	
	/**
	 * Metodo abstracto entre las clases solo cambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return nivelFantasma+nivelVeneno+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
