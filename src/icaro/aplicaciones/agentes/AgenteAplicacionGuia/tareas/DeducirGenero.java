package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.RecomendarPelicula;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;
import icaro.infraestructura.recursosOrganizacion.recursoTrazas.imp.componentes.InfoTraza;

public class DeducirGenero extends TareaSincrona {

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
			
			// TODO Mirar en el objeto estático usuario a ver si
			// las 3 ultimas pelis son del mismo género
			// o
			// las 5 ultimas suman 3 del mismo género
			// deducimos que quiere ver ese género
			boolean found = false;
			
			if(found){
				
			}else{
				// TODO si no le preguntas
				String identDeEstaTarea = this.getIdentTarea();
				String identAgenteOrdenante = this.getIdentAgente();
				try {
					// // Se busca la interfaz del recurso en el repositorio de
					// interfaces
					ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
					if (recComunicacionChat != null) {
						recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
						// Preguntar el género que le apetece ver
						String mensajeAenviar = "De que genero te apetece ver la peli?";
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					} else {
						identAgenteOrdenante = this.getAgente().getIdentAgente();
						this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-AlObtener:Interfaz:" + VocabularioGestionCitas.IdentRecursoComunicacionChat, CausaTerminacionTarea.ERROR);
					}
				} catch (Exception e) {
					this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea, identAgenteOrdenante, "Error-Acceso:Interfaz:" + VocabularioGestionCitas.IdentRecursoComunicacionChat, CausaTerminacionTarea.ERROR);
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			trazas.aceptaNuevaTraza(new InfoTraza(this.getIdentAgente(),
					"Error al ejecutar la tarea : " + this.getIdentTarea() + e,
					InfoTraza.NivelTraza.error));
		}
	}

}
