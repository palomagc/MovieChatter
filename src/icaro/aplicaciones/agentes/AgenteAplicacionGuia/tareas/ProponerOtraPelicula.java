package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import constantes.Busqueda;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class ProponerOtraPelicula extends TareaSincrona {

	// TODO regla que acepte notificacion ComprobarDatosBusqueda y lance esta tarea

	/**
	 * Constructor
	 *
	 * @param Description
	 *            of the Parameter
	 * @param Description
	 *            of the Parameter
	 */
	private Objetivo contextoEjecucionTarea = null;

	@Override
	public void ejecutar(Object... params) {
		
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String notifica = (String) params[0];
		Busqueda busqueda = VocabularioGestionCitas.busqueda;
		Usuario usuario = VocabularioGestionCitas.usuario;
		try {
			if (notifica.equals(VocabularioGestionCitas.NombreTipoNotificacionYaVista) &&
					VocabularioGestionCitas.usuario.getPeliculaActual() != null)
				usuario.addValoracion(usuario.getPeliculaActual());
			else if (notifica.equals(VocabularioGestionCitas.NombreTipoNotificacionNegacion) &&
					VocabularioGestionCitas.usuario.getPeliculaActual() != null)
				usuario.addPeliculaOdiada(usuario.getPeliculaActual().getIdPelicula());
			VocabularioGestionCitas.usuario.setPeliculaActual(null);
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
			if (recComunicacionChat != null) {
				recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
				String mensajeAenviar = "";
				Movie movie = null;
				if (busqueda.getResult().size() > 0) {
					int numRecomienda = (int) ((100 * Math.random()) % VocabularioGestionCitas.Recomienda.length);
					mensajeAenviar = VocabularioGestionCitas.Recomienda[numRecomienda] + "  ";
					for (Movie m : busqueda.getResult()) {
							if (movie==null && 
									!VocabularioGestionCitas.usuario.getIdValoraciones().contains(m.getId()))
								movie = m;
					}
					mensajeAenviar += movie.getTitle() + ".";
					VocabularioGestionCitas.usuario.setPeliculaActual(new Valoracion(Integer.toString(movie.getId()), null));
				} else {
					mensajeAenviar = "La consulta con los par�metros dados no ha obtenido ning�n resultado.";
				}
				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
				
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, "Error-AlObtener:Interfaz:"
								+ VocabularioGestionCitas.IdentRecursoComunicacionTMDB,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, "Error-Acceso:Interfaz:"
							+ VocabularioGestionCitas.IdentRecursoComunicacionTMDB,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}

}
