package icaro.aplicaciones.recursos.recursoUsuario;

import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.patronRecursoSimple.imp.ImplRecursoSimple;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClaseGeneradoraRecursoUsuario extends ImplRecursoSimple implements ItfRecursoUsuario{

	public ClaseGeneradoraRecursoUsuario(String idRecurso)
			throws RemoteException {
		super(idRecurso);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	public void crearUsuario(String nombre) {
		new Usuario(nombre);	
	}

	@Override
	public void crearUsuario(String nombre, String sexo, int edad) {
		new Usuario(nombre, sexo, edad);
		
	}
	
	@Override
	public boolean existeUsuario(String nombre) {
		// TODO buscar en fichero
		return false;
	}
	
	@Override
	public Usuario buscarUsuario(String nombre) {
		// TODO buscar en fichero
		return null;
	}

	@Override
	public boolean cambiarNombreUsuario(String nombreViejo, String nombreNuevo) {
		if(existeUsuario(nombreViejo)){
			buscarUsuario(nombreViejo).setNombre(nombreNuevo);
			return true;
		}
		return false;
	}

	@Override
	public void eliminarUsuario(String nombre) {
		// TODO Mirar a ver si esta clase será necesaria
		
	}

	@Override
	public boolean nuevaValoracion(String nombreUsuario, Valoracion valoracion) {
		if(existeUsuario(nombreUsuario)){
			buscarUsuario(nombreUsuario).nuevaValoracion(valoracion);
			return true;
		}
		return false;
	}
	
}
