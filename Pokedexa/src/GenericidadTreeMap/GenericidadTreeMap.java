package GenericidadTreeMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TreeMap;

public class GenericidadTreeMap <U, T>
{
	/**
	 * @author Guillermina Latorre
	 * Constructor de la clase.
	 */
	public GenericidadTreeMap()
	{
		
	}
	
	/**
	 * @author Guillermina Latorre
	 * @param archivo que tenga datos guardados en forma de TreeMap 
	 * @return TreeMap con los datos del archivo pasado por parametro.
	 * @see {@link Usuario#leerMapaCapturados()}
	 * {@link GestorUsuarios#sacarMapa()}
	 */
	public TreeMap<U,T> sacarMapa(File archivo)
	{
		FileInputStream lector = null;	
		ObjectInputStream lectorTipos = null;
		TreeMap<U,T> tipos=null;
		try
		{
			lector = new FileInputStream(archivo);
			lectorTipos= new ObjectInputStream(lector);
			tipos= new TreeMap <U,T>((TreeMap<U,T>)lectorTipos.readObject());
		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
		}
		catch (ClassNotFoundException exception) 
		{
			exception.printStackTrace();
		}
		finally
		{
			try {
				if (lectorTipos != null) {
					lectorTipos.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		return tipos;
	}
	
}
