package GestorUsuarios;

import Pokemon.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ManejadorExcepciones.ExcepcionGenerica;
import Usuario.Usuario;

public class GestorUsuarios 
{
	private File archivoUsuarios=new File("src\\GestorUsuarios","Usuarios");
	private File archivoUsuariosCopia;
	
	
	/**
	 * Constructor de la clase;
	 */
	public GestorUsuarios()
	{
		archivoUsuariosCopia = null;
	}
	
	public String getRutaArchivoUsuarios()
	{
		return archivoUsuarios.getPath();
	}
	
	public String getRutaArchivoUsuariosCopia()
	{
		return archivoUsuariosCopia.getPath();
	}
	
	/**
	 * @param nombre
	 * @return null si no cargo el usuario porque ya existia; Usuario si lo cargo
	 * @throws ExcepcionGenerica 
	 */
	public Usuario cargarUnUsuario(String nombre) throws ExcepcionGenerica
	{
		Usuario nuevo = null;
		if( !ExisteNombre (nombre) )
		{
			nuevo = guardarNuevoUsuario( new Usuario (nombre));
		}
		return nuevo;
	}
	
	public Pokemon cargarPrimerPokemon ( Usuario usuario, int id ) throws ExcepcionGenerica
	{
		Pokemon primero = usuario.encontrarPokemon(id);
		
		usuario.cargarNuevoPokemonVisto(primero);
		
		usuario.cargarNuevoPokemonCapturado(primero);
		
		return primero;
	}
	
	/**
	 * verifica si el nuevo nombre de usuario ya existe en el archivo de usuarios
	 * @param nombre
	 * @return
	 * @throws ExcepcionGenerica
	 */
	public boolean ExisteNombre(String nombre) throws ExcepcionGenerica
	{
		FileInputStream lector = null;
		ObjectInputStream lectorObjeto = null;
		Usuario usu;
		try {
			lector= new FileInputStream(archivoUsuarios);
			lectorObjeto= new ObjectInputStream(lector);
		}
		catch(FileNotFoundException error) {
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al abrir el archivo");
		}
		catch(IOException error) {
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al crear el lector de objetos");
		}
		finally {
			try {
				if(lectorObjeto!=null) {
					lectorObjeto.close();
				}
			}
			catch(IOException error) {
				error.printStackTrace();
				throw new ExcepcionGenerica("Error al crear el lector de objetos");
			}
		}
		try {
			while( (usu= (Usuario)lectorObjeto.readObject())!= null) {
				if(usu.getNombre().equalsIgnoreCase(nombre)==true) return true;
			}
		}
		catch(IOException error) {
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al leer del archivo");
		}
		catch(ClassNotFoundException error) {
			error.printStackTrace();
			throw new ExcepcionGenerica("Error al leer del archivo");
		}
		return false;
	}
	
	
	/**
	 *Privado, solo accesible desde  Agrega un nuevo usuario a final del archivo de usuarios.
	 * @param usuarioNuevo
	 * @throws ExcepcionGenerica
	 */
	private Usuario guardarNuevoUsuario (Usuario usuarioNuevo)  throws ExcepcionGenerica
	{
		FileOutputStream streamUsuarios = null;	
		ObjectOutputStream escrituraUsuarios = null;
		
		try
		{
			streamUsuarios = new FileOutputStream(getRutaArchivoUsuarios());
			
			escrituraUsuarios= new ObjectOutputStream(streamUsuarios);
			
			escrituraUsuarios.writeObject(usuarioNuevo);
			
		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + getRutaArchivoUsuarios());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + getRutaArchivoUsuarios());
		}
		finally
		{
			try {
				if (escrituraUsuarios != null) {
					escrituraUsuarios.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + getRutaArchivoUsuarios());
			}
			
		}
		
		return usuarioNuevo;
	}
	
	/**
	 * Privado, Se realiza una copia del Archivo de usuarios para poder sobreescribirlo con cambioc, cuando sea necesario.
	 * @return
	 * @throws ExcepcionGenerica
	 */
	private String copiarArchivoUsuarios () throws ExcepcionGenerica
	{
		//hago un new del archivoUsuariosCopia,
		
		archivoUsuariosCopia = new File (getRutaArchivoUsuariosCopia());
		
        ObjectInputStream lectura = null;
        try {
                lectura = new ObjectInputStream (new FileInputStream(archivoUsuarios));
                ObjectOutputStream escritura = null;
                try
                {
                	escritura = new ObjectOutputStream (new FileOutputStream(archivoUsuariosCopia));
                	Usuario copia;
                	while((copia = (Usuario)lectura.readObject()) != null)
                	{
                		escritura.writeObject(copia);
                	}
                }
                catch (FileNotFoundException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoUsuariosCopia.getPath());
        		} 
        		catch (IOException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoUsuariosCopia.getPath());
        		}
                catch (ClassNotFoundException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("No se encuentra el objeto Usuario en el archivo " + archivoUsuariosCopia.getPath());
        		}
        		finally
        		{
        			
        			try {
        				if (escritura != null) {
        					escritura.close();
        				}
        			} catch (IOException exception) {
        				exception.printStackTrace();
        				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoUsuariosCopia.getPath());
        			}
        		}
        }
        catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoUsuarios.getPath());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoUsuarios.getPath());
		}
		finally
		{
			
			try {
				if (lectura != null) {
					lectura.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + archivoUsuarios.getPath());
			}
		}
        return archivoUsuariosCopia.getPath();     
	}
       
    /**
     * sobreescribe un asuario en el archivo
     * @param usuarioAsobreescribir
     * @return
     * @throws ExcepcionGenerica
     */
	public boolean sobreescribirUsuario(Usuario usuarioAsobreescribir)  throws ExcepcionGenerica
	{
		boolean rta = false;
		//Para el archivo de destino
		ObjectOutputStream escrituraUsuarios = null;
		
		//Para el archivo copia
		String rutaCopia = copiarArchivoUsuarios();
		
		
		//elimino el archivo usuarios y lo vuelvo a crear, así esta vacío
		archivoUsuarios.delete();
		archivoUsuarios = new File ("src\\GestorUsuarios","Usuarios");
		
		try
		{
			
			escrituraUsuarios= new ObjectOutputStream(new FileOutputStream(getRutaArchivoUsuarios()));
			
			//para abrir el archivo donde tengo la copia
			ObjectInputStream lecturaUsuarios = null;
			
			try
			{
				
				
				//abro el archivo donde tengo la copia
				lecturaUsuarios = new ObjectInputStream( new FileInputStream ( rutaCopia ));
				
				Usuario copiacopia = null;
				
				int sobreescrito = 0;
				
				//mientras que no haya sobreescrito el usario O no tenga mas usuarios el archivo copia
				while(sobreescrito == 0 || ( copiacopia = (Usuario) lecturaUsuarios.readObject()) != null)
				{
					
					//si elnombre del usuario es igual al siguiente en el archivo de copia, se guarda el pasado por parametro
					if( sobreescrito ==0 && usuarioAsobreescribir.getNombre() == copiacopia.getNombre())
					{
						sobreescrito = 1;
						
						escrituraUsuarios.writeObject( usuarioAsobreescribir ); 
					}
					
					//si los nombres no son iguales se sigue guardando los del archivo de copia
					else
					{
						escrituraUsuarios.writeObject(copiacopia); 
					}
				}
			}
			catch (FileNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error abriendo archivo: " + rutaCopia);
			} 
			catch (IOException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error accediendo al archivo: " + rutaCopia);
			}
			catch (ClassNotFoundException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se encontro la clase Usuario en el archivo: " + rutaCopia);
			}
			finally
			{
				
				try {
					if (lecturaUsuarios != null) {
						lecturaUsuarios.close();
					}
					
					//elimino el archivoCopia Para que no exista en el caso de una nuevacopia;
					archivoUsuariosCopia.delete();
					
				} catch (IOException exception) {
					exception.printStackTrace();
					throw new ExcepcionGenerica("No se puede cerrar el archivo " + rutaCopia);
				}
			
			}
		}
		catch (FileNotFoundException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error abriendo archivo: " + getRutaArchivoUsuarios());
		} 
		catch (IOException exception) 
		{
			exception.printStackTrace();
			throw new ExcepcionGenerica("Error accediendo al archivo: " + getRutaArchivoUsuarios());
		}
		finally
		{
			try {
				if (escrituraUsuarios != null) {
					escrituraUsuarios.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
				throw new ExcepcionGenerica("No se puede cerrar el archivo " + getRutaArchivoUsuarios());
			}
			
		}
		rta=true;
		return rta;
	}
	

}
