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
   public Fantasma_Veneno (int id, String nombre,int nivel, int vidas, String genero,String rutaImagen)
   {
   	super(id, nombre,nivel, vidas, genero, tipoFantasma + tipoVeneno, rutaImagen);
   }
   
   /**
    * Constructor DEFECTO
    * @param id
    * @param nombre
    * @param genero
    * @param tipo
    * @param rutaImagen
    */
   public Fantasma_Veneno (int id, String nombre, String genero, String tipo,String rutaImagen)
   {
   	super(id, nombre,genero, tipoFantasma + tipoVeneno, rutaImagen);
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
