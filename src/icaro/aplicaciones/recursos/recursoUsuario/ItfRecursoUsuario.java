package icaro.aplicaciones.recursos.recursoUsuario;

import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.patronRecursoSimple.ItfUsoRecursoSimple;

public interface ItfRecursoUsuario extends ItfUsoRecursoSimple{

	public void crearUsuario(String nombre);
	public void crearUsuario(String nombre, String sexo, int edad);
	public boolean existeUsuario(String nombre);
	public Usuario buscarUsuario (String nombre);
	public boolean cambiarNombreUsuario(String nombreViejo, String nombreNuevo);
	public boolean nuevaValoracion(String nombreUsuario, Valoracion valorcion);
	/**
	 * No está claro que esta clase vaya a ser necesaria
	 * @param nombre
	 */
	public void eliminarUsuario(String nombre);
	
}
