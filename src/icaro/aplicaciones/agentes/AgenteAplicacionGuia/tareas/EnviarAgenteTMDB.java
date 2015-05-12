package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import constantes.Busqueda;

public class EnviarAgenteTMDB extends TareaSincrona {
	// TODO regla que acepte notificaciones de TMDB y envie al agente guia

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
		String param = (String) params[1];
		if (Busqueda.getYear()==null && Busqueda.getGenres().size()==0 && Busqueda.getPeople().size()==0) {
			//Enviar a agenteTMDB
			//Notificacion notif = new Notificacion();
	 		//notif.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionComprobarDatosBusqueda);
		} else {
			//Mirar los parametro que hay en Busqueda
			//Nueva tarea en TMDB con todos los parámetros
		}
	}
}
