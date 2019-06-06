package Pokemon;

public class PlantaVeneno extends Pokemon implements IPlanta, IVeneno
{
	private static final String tipoPlanta = "Veneno";
	private static final int nivelPlanta = 3;
    private static final String tipoVeneno = "Veneno";
    private static final int nivelVeneno = 5;
    
    /**
     * Constructor COPIA
     * @param id
     * @param nombre
     * @param vidas
     * @param genero
     * @param rutaImagen
     */
    public PlantaVeneno (int id, String nombre, int vidas, String genero,String rutaImagen)
    {
    	super(id, nombre, vidas, calcularNivel(), genero, getTipoPlanta()+getTipoVeneno(), rutaImagen);
    }
    
    /**
     * Constructor DEFECTO
     * @param id
     * @param nombre
     * @param genero
     * @param tipo
     * @param rutaImagen
     */
    public PlantaVeneno(int id, String nombre, String genero, String tipo,String rutaImagen)
    {
    	super(id, nombre,calcularNivel(),calcularNivel(), genero, getTipoPlanta()+getTipoVeneno(), rutaImagen);
    }
    
    public static String getTipoPlanta() {
		return tipoPlanta;
	}

	public static int getNivelPlanta() {
		return nivelPlanta;
	}

	public static String getTipoVeneno() {
		return tipoVeneno;
	}

	public static int getNivelVeneno() {
		return nivelVeneno;
	}
	
	/**
	 * Metodo abstracto entre las clases solocambia el "tipo" las evoluciones quedan igual.
	 */
	protected int calcularNivel() 
	{
		return getNivelPlanta()+getNivelVeneno()+nivelPrimeraEvolucion()+nivelSegundaEvolucion()+nivelTerceraEvolucion();
	}

}
