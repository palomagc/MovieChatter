package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.RecomendarPelicula;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class ProponerPelicula extends TareaSincrona {

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
		try {
			
			// TODO Encontrar la peli que quieres recomendar en el objeto estático usuario
			String movieTitle = "NULL";
			
			
			String identDeEstaTarea = this.getIdentTarea();
			String identAgenteOrdenante = this.getIdentAgente();
			try {
				// // Se busca la interfaz del recurso en el repositorio de
				// interfaces
				ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
				if (recComunicacionChat != null) {
					recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
					
					// TODO Cambiar mensaje
					String mensajeAenviar = "Te apetece ver " + movieTitle + "?";
					
					recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
				} else {
					identAgenteOrdenante = this.getAgente().getIdentAgente();
					this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz:" + VocabularioGestionCitas.IdentRecursoComunicacionChat, CausaTerminacionTarea.ERROR);
				}
			} catch (Exception e) {
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-Acceso:Interfaz:" + VocabularioGestionCitas.IdentRecursoComunicacionChat, CausaTerminacionTarea.ERROR);
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}

}
