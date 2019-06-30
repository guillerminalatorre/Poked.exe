package GestorUsuarios;

import Pokemon.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import ManejadorExcepciones.ExcepcionGenerica;
import Usuario.Usuario;

public class GestorUsuarios implements Serializable
{
	private File archivoUsuarios=new File("src\\GestorUsuarios\\Usuarios.dat");
	private File archivoUsuariosCopia;
	@SuppressWarnings("unused")
	private TreeMap<String,Usuario> usuarios= new TreeMap<String,Usuario>();
	
	/**
	 * Constructor de la clase;
	 */
	public GestorUsuarios()
	{
		archivoUsuariosCopia = null;
	}
	
	public String getRutaArchivoUsuarios()
	{
		return archivoUsuarios.getAbsolutePath();
	}
	
	public String getRutaArchivoUsuariosCopia()
	{
		return archivoUsuariosCopia.getAbsolutePath();
	}
	
	/**
	 * @param nombre
	 * @return null si no cargo el usuario porque ya existia; Usuario si lo cargo
	 * @throws ExcepcionGenerica 
	 */
	public Usuario cargarUnUsuario(String nombre) throws ExcepcionGenerica
	{
		Usuario nuevo = null;
		if( ExisteNombre (nombre) == false )
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
	@SuppressWarnings("unchecked")
	public boolean ExisteNombre(String nombre) throws ExcepcionGenerica
	{
		boolean flag=false;
		FileInputStream lector = null;
		ObjectInputStream lectorObjeto = null;
		TreeMap<String,Usuario> copia=new TreeMap<String,Usuario> ();
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

		try {
			copia.putAll((TreeMap<String,Usuario>)lectorObjeto.readObject());
			if(copia.get(nombre)!=null) {
				flag=true;
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
		return flag;
	
	}

	
	/**
	 *Privado, solo accesible desde  Agrega un nuevo usuario a final del archivo de usuarios.
	 * @param usuarioNuevo
	 * @throws ExcepcionGenerica
	 */
	@SuppressWarnings("unchecked")
	private Usuario guardarNuevoUsuario (Usuario usuarioNuevo)  throws ExcepcionGenerica
	{
		FileOutputStream streamUsuarios = null;	
		ObjectOutputStream escrituraUsuarios = null;
		FileInputStream lector = null;	
		ObjectInputStream lectorUsuarios = null;
		TreeMap<String,Usuario> usuarios;
		
		try
		{
			lector = new FileInputStream(archivoUsuarios);
			lectorUsuarios= new ObjectInputStream(lector);
			usuarios=new TreeMap<String,Usuario>((TreeMap<String,Usuario>)lectorUsuarios.readObject());
			usuarios.put(usuarioNuevo.getNombre(), usuarioNuevo);
			streamUsuarios = new FileOutputStream(archivoUsuarios);
			escrituraUsuarios= new ObjectOutputStream(streamUsuarios);
			escrituraUsuarios.writeObject(usuarios);
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
		catch (ClassNotFoundException exception) 
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
				if (lectorUsuarios != null) {
					lectorUsuarios.close();
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
        			throw new ExcepcionGenerica("Error abriendo archivo: " + archivoUsuariosCopia.getAbsolutePath());
        		} 
        		catch (IOException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("Error accediendo al archivo: " + archivoUsuariosCopia.getAbsolutePath());
        		}
                catch (ClassNotFoundException exception) 
        		{
        			exception.printStackTrace();
        			throw new ExcepcionGenerica("No se encuentra el objeto Usuario en el archivo " + archivoUsuariosCopia.getAbsolutePath());
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
	@SuppressWarnings("unchecked")
	private TreeMap<String,Usuario> sacarMapa(){
		FileInputStream lector = null;	
		ObjectInputStream lectorUsuarios = null;
		TreeMap<String,Usuario> usuarios=null;
		try
		{
			lector = new FileInputStream(archivoUsuarios);
			lectorUsuarios= new ObjectInputStream(lector);
			usuarios= new TreeMap <String,Usuario>((TreeMap<String,Usuario>)lectorUsuarios.readObject());
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
				if (lectorUsuarios != null) {
					lectorUsuarios.close();
				}
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
		return usuarios;
	}
	private void sobreescribirUsuario(Usuario usu)  throws ExcepcionGenerica
	{
		FileOutputStream escribir=null;
		ObjectOutputStream escrituraUsuarios = null;
		TreeMap<String,Usuario> usuarios= new TreeMap<String,Usuario>(sacarMapa());
		
		try
		{
			usuarios.remove(usu.getNombre());
			usuarios.put(usu.getNombre(), usu);
			escribir = new FileOutputStream(archivoUsuarios);
			escrituraUsuarios= new ObjectOutputStream(escribir);
			escrituraUsuarios.writeObject(usuarios);
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
		finally{
			try {
				if (escrituraUsuarios != null) {
					escrituraUsuarios.close();
				}
			}
			catch (IOException exception) 
			{
				exception.printStackTrace();
				throw new ExcepcionGenerica("Error accediendo al archivo: " + getRutaArchivoUsuarios());
			}
		}
	}
	public Usuario sacarUsuario(String nombre) {
		TreeMap<String,Usuario> usuarios=null;
		try {		
			if(ExisteNombre(nombre)==true) {
				usuarios= new TreeMap<String,Usuario>(sacarMapa());
			}
		}
		catch(ExcepcionGenerica error) {
			error.printStackTrace();
		}
		return usuarios.get(nombre);
	}
	
}
