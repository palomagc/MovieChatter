package icaro.aplicaciones.recursos.recursoUsuario;

import java.util.ArrayList;

import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfRecursoUsuario extends ItfUsoRecursoSimple{

	/**
	 * Crea un nuevo usuario solo con nombre, sexo y edad (los demás campos vacíos)
	 * @param nombre
	 * @param sexo
	 * @param edad
	 * @return usuario creado
	 */
	public Usuario crearUsuario(String nombre, String sexo, int edad);
	
	/**
	 * Crea un usuario con todos sus campos
	 * @param nombre
	 * @param sexo
	 * @param edad
	 * @param valoraciones
	 * @param generosPreferidos
	 * @param actoresPreferidos
	 * @param actoresOdiados
	 * @return usuario creado
	 */
	public Usuario crearUsuario(String nombre, String sexo, int edad, ArrayList<Valoracion> valoraciones, ArrayList<String> generosPreferidos, ArrayList<String> actoresPreferidos, ArrayList<String> actoresOdiados);
	
	/**
	 * @param nombre
	 * @return true si existe el fichero para ese usuario, false en caso contrario
	 */
	public boolean existeUsuario(String nombre);
	
	/**
	 * Busca un usuario en la base de datos
	 * @param nombre
	 * @return objeto Usuario si lo encuentra, null si no lo encuentra
	 */
	public Usuario buscarUsuario (String nombre);
	
	/**
	 * Modifica un usuario y lo actualiza en la base de datos
	 * @param nombre
	 * @param usuarioNuevo
	 * @return true si lo ha conseguido actualizar, false en caso contrario
	 */
	public boolean modificarUsuario(String nombre, Usuario usuarioNuevo);
	
	/**
	 * Elimina un usuario de la base de datos
	 * @param nombre
	 * @return true si lo ha conseguido eliminar, false en caso contrario
	 */
	public boolean eliminarUsuario(String nombre);
	
	/**
	 * Añade una nueva valoración a un usuario dado
	 * @param nombreUsuario
	 * @param valorcion
	 * @return true si la ha añadido, false en caso contrario
	 */
	public boolean nuevaValoracion(String nombreUsuario, Valoracion valorcion);
	
	/**
	 * Añade un nuevo género preferido a un usuario dado
	 * @param nombreUsuario
	 * @param genero
	 * @return true si lo ha añadido, false en caso contrario
	 */
	public boolean nuevoGeneroPreferido(String nombreUsuario, String genero);
	
	/**
	 * Añade un nuevo actor preferido a un usuario dado
	 * @param nombreUsuario
	 * @param actor
	 * @return true si lo ha añadido, false en caso contrario
	 */
	public boolean nuevoActorPreferido(String nombreUsuario, String actor);
	
	/**
	 * Añade un nuevo actor odiado a un usuario dado
	 * @param nombreUsuario
	 * @param actor
	 * @return true si lo ha añadido, false en caso contrario
	 */
	public boolean nuevoActorOdiado(String nombreUsuario, String actor);
	
}
