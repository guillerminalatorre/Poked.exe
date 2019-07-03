package GestorUsuarios;

import Pokemon.*;
import GenericidadTreeMap.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.TreeMap;

import Json.ManejadorJSON;
import ManejadorExcepciones.ExcepcionGenerica;
import Usuario.Usuario;

public class GestorUsuarios implements Serializable
{
	private File archivoUsuarios=new File("src\\GestorUsuarios\\Usuarios.dat");
	@SuppressWarnings("unused")
	private TreeMap<String,Usuario> usuarios= new TreeMap<String,Usuario>();
	
	
	//CONSTRUCTOR
	/**
	 * Constructor de la clase
	 * @author Ivan Lopez
	 */
	public GestorUsuarios()
	{

	}
	
	//GETTERS 
	
	public String getRutaArchivoUsuarios()
	{
		return archivoUsuarios.getAbsolutePath();
	}
	
	
	//METODOS DE CARGA
	
	
	/**
	 * @author Ivan Lopez
	 * @param nombre
	 * @return null si no cargo el usuario porque ya existia; Usuario si lo cargo
	 * @throws ExcepcionGenerica 
	 */
	public Usuario cargarUnUsuario(String nombre) throws ExcepcionGenerica
	{
		Usuario nuevo = null;
		try {
			if(archivoUsuarios.exists()==false) {//SI EL ARCHIVO NO EXISTE LO CREA
				archivoUsuarios.createNewFile();
				nuevo = guardarNuevoUsuario( new Usuario (nombre));	
			}
			else
			{
				if( ExisteNombre (nombre) == false )
				{
					nuevo = guardarNuevoUsuario( new Usuario (nombre));	
				}
			}
			}
		catch(IOException error){
			error.printStackTrace();
		}
		return nuevo;
	}
	
	/**
	 * Carga el pokemon en la pokedex y capturados del usuario, extrayendolo del archivo JSON mediante la id pasada por parametro.
	 * @author Ivan Lopez
	 * @param usuario
	 * @param id
	 * @return Pokemon 
	 * @throws ExcepcionGenerica
	 */
	public Pokemon cargarPrimerPokemon ( Usuario usuario, int id ) throws ExcepcionGenerica
	{	
		ManejadorJSON manejadorJSON = new ManejadorJSON();
		
		Pokemon primero = manejadorJSON.leerPokemonJSON(id);
		
		usuario.cargarNuevoPokemonVisto(primero);
		
		usuario.cargarNuevoPokemonCapturado(primero);
		
		return primero;
	}
	
	
	
	//METODOS DE GUARDADO


	
	/**
	 *Privado, solo accesible desde  Agrega un nuevo usuario a final del archivo de usuarios.
	 *@author Ivan Lopez
	 * @param usuarioNuevo
	 * @throws ExcepcionGenerica
	 * @return el usuario que fue guardado.
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
			if(archivoUsuarios.length()>0) { //SI EL ARCHIVO NO ESTA VACIO EL TREEMAP SE CARGA CON EL QUE YA ESTA ADENTRO DEL ARCHIVO
				lector = new FileInputStream(archivoUsuarios);
				lectorUsuarios= new ObjectInputStream(lector);
				usuarios=new TreeMap<String,Usuario>((TreeMap<String,Usuario>)lectorUsuarios.readObject());
			}
			else { //SI EL ARCHIVO ESTA VACIO CREA ELTREEMAP DESDE 0
				usuarios= new TreeMap<String,Usuario>();
			}
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
	
	
	
	
	//METODOS DE OBTENCION DE USUARIOS 
	

	/**
	 * Aplica método generico
	 * @author Guillermina Latorre
	 * @return TreeMap<String, Usuario>
	 * @see {@link GenericidadTreeMap#sacarMapa(File)}
	 */
	private TreeMap<String,Usuario> sacarMapa()
	{
		GenericidadTreeMap<String,Usuario> capturados = new GenericidadTreeMap<String,Usuario>();
 		return capturados.sacarMapa(archivoUsuarios);
	}
	
	/**
	 * @author Ivan Lopez
	 * @param nombre
	 * @return El usuario sacado del archivoUsuarios
	 * @see {@link GestorUsuarios#ExisteNombre(String)}
	 */
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
	
	
	
	
	//METODOS DE CONTROL
	
	
	

	/**
	 * verifica si el nuevo nombre de usuario ya existe en el archivo de usuarios
	 * @author Ivan Lopez
	 * @param nombre
	 * @return booleano: true (si existe) false (si no existe)
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
	 * Pisa el usuario enviado por parametro con el que esta en el archivo de usuarios
	 * @author Ivan Lopez
	 * @param un Usuario
	 * @throws ExcepcionGenerica
	 */
	public void sobreescribirUsuario(Usuario usu)  throws ExcepcionGenerica
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
	
	/**
	 * @author Guillermina Latorre
	 * @return booleano: true (si existe) false (si no existe)
	 */
	public boolean elArchivoUsuariosEstaCreado()
	{
		return archivoUsuarios.exists();
	}
	
	
}
