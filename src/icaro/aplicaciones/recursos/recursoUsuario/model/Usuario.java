package icaro.aplicaciones.recursos.recursoUsuario.model;

import java.util.ArrayList;

public class Usuario {

	private String nombre;
	private String sexo;
	private int edad;
	private ArrayList<Valoracion> valoraciones;
	private ArrayList<String> generosPreferidos;
	private ArrayList<String> actoresPreferidos;
	private ArrayList<String> actoresOdiados;
	
	
	/**
	 * Nuevo usuario sin sexo (sexo=null) ni edad (edad=-1)
	 * @param nombre Nombre del usuario
	 */
	public Usuario(String nombre){
		this.nombre = nombre;
		this.sexo=null;
		this.edad = -1;
		this.valoraciones = new ArrayList<Valoracion>();
		this.generosPreferidos = new ArrayList<String>();
		this.actoresPreferidos = new ArrayList<String>();
		this.actoresOdiados = new ArrayList<String>();
		// TODO meter el usuario en un fichero
	}


	/**
	 * Nuevo usuario con nombre, sexo y edad
	 * @param nombre Nombre del uauario
	 * @param sexo Sexo del usuario
	 * @param edad Edad del usuario
	 */
	public Usuario(String nombre, String sexo, int edad){
		this.nombre = nombre;
		this.sexo = sexo;
		this.edad = edad;
		this.valoraciones = new ArrayList<Valoracion>();
		this.generosPreferidos = new ArrayList<String>();
		this.actoresPreferidos = new ArrayList<String>();
		this.actoresOdiados = new ArrayList<String>();
		// TODO meter el usuario en un fichero
	}	
	
	
	public void nuevaValoracion(Valoracion valoracion){
		this.valoraciones.add(valoracion);
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public ArrayList<Valoracion> getValoraciones() {
		return valoraciones;
	}

	public void setValoraciones(ArrayList<Valoracion> valoraciones) {
		this.valoraciones = valoraciones;
	}

	public ArrayList<String> getGenerosPreferidos() {
		return generosPreferidos;
	}

	public void setGenerosPreferidos(ArrayList<String> generosPreferidos) {
		this.generosPreferidos = generosPreferidos;
	}

	public ArrayList<String> getActoresPreferidos() {
		return actoresPreferidos;
	}

	public void setActoresPreferidos(ArrayList<String> actoresPreferidos) {
		this.actoresPreferidos = actoresPreferidos;
	}

	public ArrayList<String> getActoresOdiados() {
		return actoresOdiados;
	}

	public void setActoresOdiados(ArrayList<String> actoresOdiados) {
		this.actoresOdiados = actoresOdiados;
	}
	
}
