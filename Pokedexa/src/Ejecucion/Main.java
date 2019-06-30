package Ejecucion;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeMap;

import CentroPokemon.CentroPokemon;
import GestorPokedex.GestorPokedex;
import GestorUsuarios.GestorUsuarios;
import Json.*; 
import Pokemon.*;
import Usuario.Usuario;
import ManejadorExcepciones.ExcepcionGenerica;


public class Main 
{

	public static void main(String[] args) throws ExcepcionGenerica 
	{
		ManejadorJSON manejador = new ManejadorJSON();
		
		//CARGA DE POKEMONS EN EL JSON (ya estan cargados)
		/*try {
			

			File imagenID1 = new File("src\\Imagenes\\1.jpg");
			manejador.CargarPokemon(new PlantaVeneno(1, "Bulbasur", 1, " ", imagenID1.getAbsolutePath()));

			File imagenID2 = new File("src\\Imagenes\\2.jpg");
			manejador.CargarPokemon(new PlantaVeneno(2, "Ivrsaur", 2, " ", imagenID2.getAbsolutePath()));

			File imagenID3 = new File("src\\Imagenes\\3.jpg");
			manejador.CargarPokemon(new PlantaVeneno(3, "Venusaur", 3, " ", imagenID3.getAbsolutePath()));

			File imagenID4 = new File("src\\Imagenes\\4.jpg");
			manejador.CargarPokemon(new PlantaVeneno(4, "Charmander", 1, " ", imagenID4.getAbsolutePath()));

			File imagenID5 = new File("src\\Imagenes\\5.jpg");
			manejador.CargarPokemon(new PlantaVeneno(5, "Charmaleon", 2, " ", imagenID5.getAbsolutePath()));

			File imagenID6 = new File("src\\Imagenes\\6.jpg");
			manejador.CargarPokemon(new PlantaVeneno(6, "Charizard", 3, " ", imagenID6.getAbsolutePath()));

			File imagenID7 = new File("src\\Imagenes\\7.jpg");
			manejador.CargarPokemon(new PlantaVeneno(7, "Squirtle", 1, " ", imagenID7.getAbsolutePath()));

			File imagenID8 = new File("src\\Imagenes\\8.jpg");
			manejador.CargarPokemon(new PlantaVeneno(8, "Wartortle", 2, " ", imagenID8.getAbsolutePath()));
			

			File imagenID9 = new File("src\\Imagenes\\9.jpg");
			manejador.CargarPokemon(new PlantaVeneno(9, "Blastoise", 3, " ", imagenID9.getAbsolutePath()));
			

			File imagenID10 = new File("src\\Imagenes\\10.jpg");
			manejador.CargarPokemon(new PlantaVeneno(10, "Cartepie", 1, " ", imagenID10.getAbsolutePath()));
			

			File imagenID11 = new File("src\\Imagenes\\11.jpg");
			manejador.CargarPokemon(new PlantaVeneno(11, "Metapod", 2, " ", imagenID11.getAbsolutePath()));
			

			File imagenID12 = new File("src\\Imagenes\\12.jpg");
			manejador.CargarPokemon(new PlantaVeneno(12, "Butterfree", 3, " ", imagenID12.getAbsolutePath()));
			

			File imagenID13 = new File("src\\Imagenes\\13.jpg");
			manejador.CargarPokemon(new PlantaVeneno(13, "Weedle", 1, " ", imagenID13.getAbsolutePath()));

			File imagenID14 = new File("src\\Imagenes\\14.jpg");
			manejador.CargarPokemon(new PlantaVeneno(14, "Kakuna",2, " ", imagenID14.getAbsolutePath()));
			

			File imagenID15 = new File("src\\Imagenes\\15.jpg");
			manejador.CargarPokemon(new PlantaVeneno(15, "Beedrill", 3, " ", imagenID15.getAbsolutePath()));
			

			File imagenID16 = new File("src\\Imagenes\\16.jpg");
			manejador.CargarPokemon(new PlantaVeneno(16, "Pidgey", 1, " ", imagenID16.getAbsolutePath()));
			

			File imagenID17 = new File("src\\Imagenes\\17.jpg");
			manejador.CargarPokemon(new PlantaVeneno(17, "Pidgeotto", 2, " ", imagenID17.getAbsolutePath()));
			

			File imagenID18 = new File("src\\Imagenes\\18.jpg");
			manejador.CargarPokemon(new PlantaVeneno(18, "Pidgeot", 3, " ", imagenID18.getAbsolutePath()));
			

			File imagenID19 = new File("src\\Imagenes\\19.jpg");
			manejador.CargarPokemon(new PlantaVeneno(19, "Rattata", 1, " ", imagenID19.getAbsolutePath()));
			manejador.cargarArchivoJSON();
			
		} catch (ExcepcionGenerica error) {
			System.out.println(error.MensajeError());
		}
		*/
		
		//CARGA DE USUARIOS ANDANDO
		GestorUsuarios usu= new GestorUsuarios();
		
		Usuario nuevo=new Usuario(usu.cargarUnUsuario("carlos"));
		System.out.println(nuevo.getNombre());
		
		Usuario nuevo1=new Usuario(usu.cargarUnUsuario("ariel"));
		System.out.println(nuevo1.getNombre());
		
		
		//CARGA DE PRIMER POKEMON
		
		//DATO: al cargar los pokemons, debo tener en cuenta de que la ID que debo pasar en una menos, por las posiciones de los arreglos.
		
		ManejadorJSON manejadorJSON = new ManejadorJSON();
		
		Pokemon squirtle = usu.cargarPrimerPokemon(nuevo, 0);
		System.out.println("pokemon Bulvasaur cargado a el usuario carlos");
		
		Pokemon charmander = usu.cargarPrimerPokemon(nuevo1, 3);
		System.out.println("pokemon Charmander cargado a el usuario ariel");
		
		
		
		//Imprimir Pokedex de cada usuario.
		
		System.out.println("POKEDEX DE CARLOS");
		
		ArrayList<Integer> nuevoPokedex = new ArrayList<Integer>();
		
		nuevoPokedex = nuevo.getArchivoPokedexUsuario();
		
		Iterator<Integer> iteradorNuevoPokedex = nuevoPokedex.iterator();
		
		while( iteradorNuevoPokedex.hasNext())
		{
			System.out.println(iteradorNuevoPokedex.next().toString());
		}
		
		System.out.println("POKEDEX DE ARIEL");
		
		ArrayList<Integer> nuevo1Pokedex = new ArrayList<Integer>();
		
		nuevo1Pokedex = nuevo1.getArchivoPokedexUsuario();
		
		Iterator<Integer> iteradorNuevo1Pokedex = nuevo1Pokedex.iterator();
		
		while( iteradorNuevo1Pokedex.hasNext())
		{
			System.out.println(iteradorNuevo1Pokedex.next().toString());
		}




		//Imprimir Capturados de cada usuario.


		System.out.println("CAPTURADOS DE CARLOS");

		//se rompe en el getArchivoCapturados();
		TreeMap<Integer, Pokemon> nuevoCapturados = nuevo.getArchivoCapturados();

		Collection<Pokemon> collectionNuevoCapturados = nuevoCapturados.values();

		Iterator<Pokemon> iteradorNuevoCapturados = collectionNuevoCapturados.iterator();

		while( iteradorNuevoCapturados.hasNext())
		{
			System.out.println(iteradorNuevoCapturados.next().toString());
		}
		
		
		
		
		

		System.out.println("CAPTURADOS DE ARIEL");

		TreeMap<Integer, Pokemon> nuevo1Capturados = new TreeMap<Integer, Pokemon>();

		nuevo1Capturados = nuevo.getArchivoCapturados();

		Collection<Pokemon> collectionNuevo1Capturados = nuevo1Capturados.values();

		Iterator<Pokemon> iteradorNuevo1Capturados = collectionNuevo1Capturados.iterator();

		while( iteradorNuevo1Capturados.hasNext())
		{
			System.out.println(iteradorNuevo1Capturados.next().toString());
		}



		/*CentroPokemon centro= new CentroPokemon(usu);
		gestorusu.cargarPrimerPokemon(usu, 3);
		centro.mostrarPokemonsDañados();
		centro.curarPokemonsDañados();
		 */

	}

}
