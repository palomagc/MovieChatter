package icaro.aplicaciones.recursos.recursoUsuario;

import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import constantes.Constantes;

public class ClaseGeneradoraRecursoUsuario extends ImplRecursoSimple implements ItfUsoRecursoUsuario{

	public ClaseGeneradoraRecursoUsuario(String idRecurso) throws RemoteException {
		super(idRecurso);
		new File(Constantes.DB_PATH).mkdirs(); // Crea las carpetas necesarias del PATH de la Base de Datos.
	}


	@Override
	public Usuario crearUsuario(String nombre, String sexo, String edad) {
		Usuario usuario = new Usuario(nombre, sexo, edad);
		usuario.addUsuarioBD();
		return usuario;
	}

	@Override
	public Usuario crearUsuario(String nombre, String sexo, String edad, ArrayList<Valoracion> valoraciones, ArrayList<String> generosPreferidos, ArrayList<String> actoresPreferidos, ArrayList<String> actoresOdiados, Valoracion peliculaActual) {
		Usuario usuario = new Usuario(nombre, sexo, edad, valoraciones, generosPreferidos, actoresPreferidos, actoresOdiados, peliculaActual);
		usuario.addUsuarioBD();
		return usuario;
	}

	@Override
	public boolean existeUsuario(String nombre) {
		if(new File(Constantes.DB_PATH + nombre + ".json").isFile())
			return true;
		else
			return false;
	}

	@Override
	public Usuario buscarUsuario(String nombre) throws Exception{
		
		Usuario usuario = null;
		JSONParser parser = new JSONParser();
		 
        try {
        	if(existeUsuario(nombre)){
        		
        		usuario = new Usuario();
        		
        		Object obj = parser.parse(new FileReader(Constantes.DB_PATH + nombre + ".json"));
	 
        		JSONObject jsonObject = (JSONObject) obj;
	            
        		usuario.setNombre((String) jsonObject.get("nombre"));
        		usuario.setSexo((String) jsonObject.get("sexo"));
        		usuario.setEdad((String) jsonObject.get("edad"));
        		
        		JSONArray valoraciones = (JSONArray) jsonObject.get("valoraciones");
        		Iterator<JSONObject> it = valoraciones.iterator();
        		while(it.hasNext()){
        			JSONObject o = it.next();
        			usuario.getValoraciones().add(new Valoracion((String) o.get("idPelicula"), (String) o.get("nota")));
        		}
        		JSONArray generosPreferidos = (JSONArray) jsonObject.get("generosPreferidos");
        		Iterator<String> iterator = generosPreferidos.iterator();
                while (iterator.hasNext()) {
                    usuario.getGenerosPreferidos().add(iterator.next());
                }
                JSONArray actoresPreferidos = (JSONArray) jsonObject.get("actoresPreferidos");
        		iterator = actoresPreferidos.iterator();
                while (iterator.hasNext()) {
                    usuario.getActoresPreferidos().add(iterator.next());
                }
                JSONArray actoresOdiados = (JSONArray) jsonObject.get("actoresOdiados");
        		iterator = actoresOdiados.iterator();
                while (iterator.hasNext()) {
                    usuario.getActoresOdiados().add(iterator.next());
                }
                /*JSONObject pActual = (JSONObject) jsonObject.get("peliculaActual");
                usuario.setPeliculaActual(new Valoracion((String) pActual.get("idPelicula"), (String) pActual.get("nota"))); */
        		
        	}

        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		return usuario;
	}

	@Override
	public boolean modificarUsuario(String nombre, Usuario usuarioNuevo){
		if(existeUsuario(nombre)){
			// Si sigue teniendo el mismo nombre basta con actualizar el fichero json
			if(usuarioNuevo.getNombre().equals(nombre)){
				usuarioNuevo.addUsuarioBD();
			}
			// Si no habrá que borrar el json antiguo y crear uno nuevo
			else{
				if(!eliminarUsuario(nombre)){
					return false;
				}
				usuarioNuevo.addUsuarioBD();
			}
			return true;
		}
		System.err.println("No se pudo modificr el usuario, el usuario especificado no existe");
		return false;
	}

	@Override
	public boolean eliminarUsuario(String nombre) {
		
		File file = new File(Constantes.DB_PATH + nombre + ".json");
		 
		if(!file.delete()){
			System.err.println("No se pudo eliminar el usuario");
			return false;
		}
		return true;
	}

	@Override
	public boolean nuevaValoracion(String nombreUsuario, Valoracion valoracion) {
		if(existeUsuario(nombreUsuario)){
			Usuario usuario = null;
			try {
				usuario = buscarUsuario(nombreUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
			usuario.getValoraciones().add(valoracion);
			modificarUsuario(nombreUsuario, usuario);
			return true;
		}
		return false;
	}



	@Override
	public boolean nuevoGeneroPreferido(String nombreUsuario, String genero) {
		if(existeUsuario(nombreUsuario)){
			Usuario usuario = null;
			try {
				usuario = buscarUsuario(nombreUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
			usuario.getGenerosPreferidos().add(genero);
			modificarUsuario(nombreUsuario, usuario);
			return true;
		}
		return false;
	}



	@Override
	public boolean nuevoActorPreferido(String nombreUsuario, String actor) {
		if(existeUsuario(nombreUsuario)){
			Usuario usuario = null;
			try {
				usuario = buscarUsuario(nombreUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
			usuario.getActoresPreferidos().add(actor);
			modificarUsuario(nombreUsuario, usuario);
			return true;
		}
		return false;
	}



	@Override
	public boolean nuevoActorOdiado(String nombreUsuario, String actor) {
		if(existeUsuario(nombreUsuario)){
			Usuario usuario = null;
			try {
				usuario = buscarUsuario(nombreUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			}
			usuario.getActoresOdiados().add(actor);
			modificarUsuario(nombreUsuario, usuario);
			return true;
		}
		return false;
	}

}
