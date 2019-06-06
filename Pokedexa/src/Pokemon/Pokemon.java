package Pokemon;

import java.util.ArrayList;
import java.util.Vector;

public abstract class Pokemon 
{
	private int id;
	private String nombre;
	private int vidas;
	private int nivel;
	private String genero;
	private String tipo;
	
	/**
	 * Constructor para COPIA, aplicado en la captura pokemon.
	 * @param id
	 * @param nombre
	 * @param vidas
	 * @param nivel
	 * @param genero
	 */
	public Pokemon (int id, String nombre, int vidas, int nivel, String genero, String tipo)
	{
		this.id = id;
		this.nombre = nombre;
		setVidas(vidas);
		setNivel(nivel);
		this.genero = genero;
		this.tipo = tipo;
	}

	/**
	 * Constructor por DEFECTO, para pokemons nuevos.
	 * @param id
	 * @param nombre
	 * @param nivel
	 * @param genero
	 */
	public Pokemon (int id, String nombre, int nivel, String genero, String tipo)
	{
		this.id = id;
		this.nombre = nombre;
		setVidas(nivel);
		setNivel(nivel);
		this.genero = genero;
		this.tipo = tipo;
	}
	
	/**
	 * las vidas varían en cada pokemon depende la actividad que se aplique sobre él, por eso pueden ser sobreescritas
	 * @param vidas
	 */
	protected void setVidas (int vidas)
	{
		this.vidas= vidas;
	}
	
	/**
	 * el nivel varía en cada pokemon depende la actividad que se aplique sobre él, por eso puede ser sobreescrito
	 * @param nivel
	 */
	protected void setNivel (int nivel)
	{
		this.nivel = nivel;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getVidas() {
		return vidas;
	}

	public int getNivel() {
		return nivel;
	}

	public String getGenero() {
		return genero;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	protected abstract int calcularNivel();
	
	protected int nivelPrimeraEvolucion()
	{
		return 0;
	}
	
	protected int nivelSegundaEvolucion()
	{
		int nivel = 10;
		//Vector a = new Vector();
		ArrayList segundaEvolucion = new ArrayList<int>();
		segundaEvolucion = segundaEvolucion.add(002,004);
		/*if (this.id != 002)
		{
			if (this.id != 005)
			{
				if (this.id != 008)
				{
					if (this.id != 011)
					{
						if (this.id != 014)
						{
							if (this.id != 020)
							{
								if (this.id != 022)
								{
									if (this.id != 024)
									{
										if (this.id != 014)
										{
											if (this.id != 026)
											{
												if (this.id != 028)
												{
													if (this.id != 030)
													{
														if (this.id != 033)
														{
															if (this.id != 036)
															{
																if (this.id != 038)
																{
																	if (this.id != 040)
																	{
																		if (this.id != 042)
																		{
																			if (this.id != 044)
																			{
																				if (this.id != 014)
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		}
		*/
		return nivel;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genero == null) ? 0 : genero.hashCode());
		result = prime * result + id;
		result = prime * result + nivel;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + vidas;
		return result;
	}

	/**
	 * compara si dos pokemons son iguales desde el objeto, la clase, la id, y el nombre.
	 */
	@Override
	public boolean equals(Object obj) 
	{
		boolean rta = false;
		if (this != obj) {
			if (obj != null) {
				if (getClass() == obj.getClass()) {
					Pokemon other = (Pokemon) obj;

					if (id == other.id) {

						if (nombre == null) {
							if (other.nombre == null)
								rta = true;
						} else if (nombre.equals(other.nombre))
							rta = true;
					}
				}
			}
		}
		return rta;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", nombre=" + nombre + ", vidas=" + vidas + ", nivel=" + nivel + ", genero="
				+ genero + "]";
	}
	
}
