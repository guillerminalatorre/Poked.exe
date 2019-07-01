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
		/*
		//CARGA DE POKEMONS EN EL JSON (ya estan cargados)
		try {
			

			File imagenID1 = new File("src\\Imagenes\\1.jpg");
			manejador.CargarPokemon(new PlantaVeneno(1, "Bulbasur", 1, " ", imagenID1.getAbsolutePath()));

			File imagenID2 = new File("src\\Imagenes\\2.jpg");
			manejador.CargarPokemon(new PlantaVeneno(2, "Ivisaur", 2, " ", imagenID2.getAbsolutePath()));

			File imagenID3 = new File("src\\Imagenes\\3.jpg");
			manejador.CargarPokemon(new PlantaVeneno(3, "Venusaur", 3, " ", imagenID3.getAbsolutePath()));

			File imagenID4 = new File("src\\Imagenes\\4.jpg");
			manejador.CargarPokemon(new Fuego(4, "Charmander", 1, " ", imagenID4.getAbsolutePath()));

			File imagenID5 = new File("src\\Imagenes\\5.jpg");
			manejador.CargarPokemon(new Fuego(5, "Charmaleon", 2, " ", imagenID5.getAbsolutePath()));

			File imagenID6 = new File("src\\Imagenes\\6.jpg");
			manejador.CargarPokemon(new Fuego_Volador(6, "Charizard", 3, " ", imagenID6.getAbsolutePath()));

			File imagenID7 = new File("src\\Imagenes\\7.jpg");
			manejador.CargarPokemon(new Agua(7, "Squirtle", 1, " ", imagenID7.getAbsolutePath()));

			File imagenID8 = new File("src\\Imagenes\\8.jpg");
			manejador.CargarPokemon(new Agua(8, "Wartortle", 2, " ", imagenID8.getAbsolutePath()));
			
			File imagenID9 = new File("src\\Imagenes\\9.jpg");
			manejador.CargarPokemon(new Agua(9, "Blastoise", 3, " ", imagenID9.getAbsolutePath()));
			

			File imagenID10 = new File("src\\Imagenes\\10.jpg");
			manejador.CargarPokemon(new Bicho(10, "Cartepie", 1, " ", imagenID10.getAbsolutePath()));
			

			File imagenID11 = new File("src\\Imagenes\\11.jpg");
			manejador.CargarPokemon(new Bicho(11, "Metapod", 2, " ", imagenID11.getAbsolutePath()));
			

			File imagenID12 = new File("src\\Imagenes\\12.jpg");
			manejador.CargarPokemon(new Bicho_Volador(12, "Butterfree", 3, " ", imagenID12.getAbsolutePath()));
			

			File imagenID13 = new File("src\\Imagenes\\13.jpg");
			manejador.CargarPokemon(new Bicho_Veneno(13, "Weedle", 1, " ", imagenID13.getAbsolutePath()));

			File imagenID14 = new File("src\\Imagenes\\14.jpg");
			manejador.CargarPokemon(new Bicho_Veneno(14, "Kakuna",2, " ", imagenID14.getAbsolutePath()));
			

			File imagenID15 = new File("src\\Imagenes\\15.jpg");
			manejador.CargarPokemon(new Bicho_Veneno(15, "Beedrill", 3, " ", imagenID15.getAbsolutePath()));
			

			File imagenID16 = new File("src\\Imagenes\\16.jpg");
			manejador.CargarPokemon(new Normal_Volador(16, "Pidgey", 1, " ", imagenID16.getAbsolutePath()));
			

			File imagenID17 = new File("src\\Imagenes\\17.jpg");
			manejador.CargarPokemon(new Normal_Volador(17, "Pidgeotto", 2, " ", imagenID17.getAbsolutePath()));
			

			File imagenID18 = new File("src\\Imagenes\\18.jpg");
			manejador.CargarPokemon(new Normal_Volador(18, "Pidgeot", 3, " ", imagenID18.getAbsolutePath()));
			

			File imagenID19 = new File("src\\Imagenes\\19.jpg");
			manejador.CargarPokemon(new Normal(19, "Rattata", 1, " ", imagenID19.getAbsolutePath()));
			manejador.cargarArchivoJSON();
			
		} catch (ExcepcionGenerica error) {
			System.out.println(error.MensajeError());
		}
		*/
		
	
		//NO VA A ANDAR SI EXISTEN LOS .DAT
		//CARGA DE USUARIOS ANDANDO
		GestorUsuarios usu= new GestorUsuarios();
		
		Usuario carlos=new Usuario(usu.cargarUnUsuario("carlos"));
		System.out.println(carlos.getNombre());
		
		Usuario ariel=new Usuario(usu.cargarUnUsuario("ariel"));
		System.out.println(ariel.getNombre());
		
		
		//CARGA DE PRIMER POKEMON
		
		//DATO: al cargar los pokemons, debo tener en cuenta de que la ID que debo pasar en una menos, por las posiciones de los arreglos.
		
		ManejadorJSON manejadorJSON = new ManejadorJSON();
		
		Pokemon squirtle = usu.cargarPrimerPokemon(carlos, 0);
		System.out.println("pokemon Bulvasaur cargado a el usuario carlos");
		
		Pokemon charmander = usu.cargarPrimerPokemon(ariel, 3);
		System.out.println("pokemon Charmander cargado a el usuario ariel");
	
		
		
		
		/*
		//TRABAJAR CON LOS ARCHIVOS YA CARGADOS Y CREADOS

		GestorUsuarios usu = new GestorUsuarios();
		
		Usuario carlos = usu.sacarUsuario("carlos");
		
		Usuario ariel = usu.sacarUsuario("ariel");	
				*/
			
				
		//MPRIMIR POKEDEX DE CADA USUARIO
		System.out.println("POKEDEX DE CARLOS");
		
		System.out.println(carlos.listarPokemonsPokedex());
		
		System.out.println("POKEDEX DE ARIEL");
		
		System.out.println(ariel.listarPokemonsPokedex());
	



		//IMPRIMIR CAPTURADOS DE CADA USUARIO

		System.out.println("CAPTURADOS DE CARLOS");
		
		System.out.println(carlos.listarPokemonsCapturados());
		
		

		System.out.println("CAPTURADOS DE ARIEL");

		System.out.println(ariel.listarPokemonsCapturados());
		
		
		

		//CARGAR OTRO POKEMON A LA POKEDEX
		
		System.out.println("CARGANDO POKEMON NUEVO A CADA USUARIO\n");
		//carlos.cargarNuevoPokemonVisto(manejador.leerPokemonJSON(3));
		
		
		System.out.println(carlos.listarPokemonsPokedex());
		
		System.out.println("\n");
		//ariel.cargarNuevoPokemonVisto(manejador.leerPokemonJSON(6));
		
		System.out.println("POKEDEX DE ARIEL");
		
		System.out.println(ariel.listarPokemonsPokedex());
		
		
		//CARGAR UN POKEMON QUE YA EXISTE DENTRO DE LA POKEDEX
		
		System.out.println("CARGANDO UN POKEMON QUE YA EXISTE, EN LA POKEDEX (NO LO DEBERIA CARGAR)\n Cargarndo id 1 en carlos...");
		
		carlos.cargarNuevoPokemonVisto(manejador.leerPokemonJSON(3));
		System.out.println(carlos.listarPokemonsPokedex());
		
		System.out.println("\nCargando id 4 en ariel...");
		
		ariel.cargarNuevoPokemonVisto(manejador.leerPokemonJSON(6));
		System.out.println(ariel.listarPokemonsPokedex());
		
		
		//CARGAR UN POKEMON EN CAPTURADOS DE CADA USUARIO
		
		System.out.println("\nCARGANDO UN NUEVO POKEMON CAPTURADO PARA CADA USUARIO\n");
		
		carlos.cargarNuevoPokemonCapturado(manejador.leerPokemonJSON(3));
		System.out.println(carlos.listarPokemonsCapturados());
		
		ariel.cargarNuevoPokemonCapturado(manejador.leerPokemonJSON(0));
		System.out.println(ariel.listarPokemonsCapturados());

		
		//ELIMINAR UN POKEMON CAPTURADO
		
		System.out.println("Eliminando el pokemon Bulvasaur de carlos");
		carlos.eliminarUnPokemonCapturado(1);
		System.out.println(carlos.listarPokemonsCapturados());
		
		
		//¿HAY POKEMON
		
		
		/*CentroPokemon centro= new CentroPokemon(usu);
		gestorusu.cargarPrimerPokemon(usu, 3);
		centro.mostrarPokemonsDañados();
		centro.curarPokemonsDañados();
		 */

	}

}
