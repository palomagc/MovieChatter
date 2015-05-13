package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import constantes.Busqueda;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class MostrarPelicula extends TareaSincrona {

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
		//String identInterlocutor = (String) params[0];
		Busqueda busqueda = VocabularioGestionCitas.busqueda;
		try {
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
				} else {
					mensajeAenviar = "La consulta con los parámetros dados no ha obtenido ningún resultado.";
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
