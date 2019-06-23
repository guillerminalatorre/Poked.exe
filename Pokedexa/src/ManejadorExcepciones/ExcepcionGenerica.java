package ManejadorExcepciones;

/*
 * Clase generica para el manejo de excpeciones 
 */
@SuppressWarnings("serial")
public class ExcepcionGenerica extends Exception{
	String informacion;
	public ExcepcionGenerica (String info) {
		super(info);
		informacion=info;
	}
	public String MensajeError() {
		return informacion;
	}
}
