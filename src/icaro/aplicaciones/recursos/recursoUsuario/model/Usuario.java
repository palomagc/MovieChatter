package icaro.aplicaciones.recursos.recursoUsuario.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import constantes.Constantes;

public class Usuario {

	private String nombre;
	private String sexo;
	private String edad;
	private ArrayList<Valoracion> valoraciones;
	private ArrayList<String> generosPreferidos;
	private ArrayList<String> actoresPreferidos;
	private ArrayList<String> actoresOdiados;
	
	/**
	 * Nuevo usuario vacío (nombre=null, sexo=null, edad=-1)
	 */
	public Usuario(){
		this.nombre = null;
		this.sexo = null;
		this.edad = null;
		this.valoraciones = new ArrayList<Valoracion>();
		this.generosPreferidos = new ArrayList<String>();
		this.actoresPreferidos = new ArrayList<String>();
		this.actoresOdiados = new ArrayList<String>();
	}
	
	/**
	 * Nuevo usuario con nombre, sexo y edad
	 * Si se necesita un usuario con solo el nombre, se pone el sexo a null y la edad a -1
	 * @param nombre Nombre del uauario
	 * @param sexo Sexo del usuario
	 * @param edad Edad del usuario
	 */
	public Usuario(String nombre, String sexo, String edad){
		this.nombre = nombre;
		this.sexo = sexo;
		this.edad = edad;
		this.valoraciones = new ArrayList<Valoracion>();
		this.generosPreferidos = new ArrayList<String>();
		this.actoresPreferidos = new ArrayList<String>();
		this.actoresOdiados = new ArrayList<String>();
	}


	/**
	 * Nuevo usuario con todos los campos
	 * @param nombre
	 * @param sexo
	 * @param edad
	 * @param valoraciones
	 * @param generosPreferidos
	 * @param actoresPreferidos
	 * @param actoresOdiados
	 */
	public Usuario(String nombre, String sexo, String edad, ArrayList<Valoracion> valoraciones, ArrayList<String> generosPreferidos, ArrayList<String> actoresPreferidos, ArrayList<String> actoresOdiados){
		this.nombre = nombre;
		this.sexo = sexo;
		this.edad = edad;
		this.valoraciones = valoraciones;
		this.generosPreferidos = generosPreferidos;
		this.actoresPreferidos = actoresPreferidos;
		this.actoresOdiados = actoresOdiados;
		
	}
	
	/**
	 * Crea un objeto JSON a partir del objeto usuario
	 * @return objeto JSON con el contenido del usuario
	 */
	private JSONObject creaJSONUsuario(){
		
		JSONObject obj = new JSONObject();
		
		obj.put("nombre", this.nombre);
		obj.put("sexo", this.sexo);
		obj.put("edad", this.edad);
		JSONArray listV = new JSONArray();	
		Iterator<Valoracion> it1 = this.valoraciones.iterator();
		while(it1.hasNext()){
			Valoracion v = it1.next();
			JSONObject objV = new JSONObject();
			objV.put("idPelicula", v.getIdPelicula());
			objV.put("nota", v.getNota());
			listV.add(objV);
		} 
		obj.put("valoraciones", listV);
		JSONArray listGP = new JSONArray();
		Iterator<String> it2 = this.generosPreferidos.iterator();
		while(it2.hasNext()){
			String gp = it2.next();
			listGP.add(gp);
		} 
		obj.put("generosPreferidos", listGP);
		JSONArray listAP = new JSONArray();	
		it2 = this.actoresPreferidos.iterator();
		while(it2.hasNext()){
			String ap = it2.next();
			listAP.add(ap);
		} 
		obj.put("actoresPreferidos", listAP);
		JSONArray listAO = new JSONArray();	
		it2 = this.actoresOdiados.iterator();
		while(it2.hasNext()){
			String ao = it2.next();
			listAO.add(ao);
		} 
		obj.put("actoresOdiados", listAO);
		
		return obj;
		
	}
	
	/**
	 * Añade el usuario a la base de datos
	 */
	public void addUsuarioBD(){
		JSONObject obj = this.creaJSONUsuario();
		try {
			 
			FileWriter file = new FileWriter(Constantes.DB_PATH + this.nombre + ".json");
			file.write(obj.toJSONString());
			file.flush();
			file.close();
	 
		} catch (IOException e) {
			e.printStackTrace();
		}
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


	public String getEdad() {
		return edad;
	}


	public void setEdad(String edad) {
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
