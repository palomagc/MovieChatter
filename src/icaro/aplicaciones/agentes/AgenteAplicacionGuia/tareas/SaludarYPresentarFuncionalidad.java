package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

/**
 * <p>Title: Agenda de citas vocal</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Telef�nica  I+D</p>
 * @author Jorge Gonz�lez
 * @version 1.0
 */

import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class SaludarYPresentarFuncionalidad extends TareaSincrona {

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
		/**
		 * Produce un saludo inicial y una presentacion de funcionalidad inicial al entrar en el
		 * sistema
		 */
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identInterlocutor = (String) params[0];
		try {
			// // Se busca la interfaz del recurso en el repositorio de interfaces
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
			if (recComunicacionChat != null) {
				recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
				int numSaludo = (int) ((100 * Math.random()) % VocabularioGestionCitas.SaludoInicial2.length);
				int numPeticion = (int) ((100 * Math.random()) % VocabularioGestionCitas.PeticionInfoGeneral.length);
				String mensajeAenviar = VocabularioGestionCitas.SaludoInicial2[numSaludo] + "  "
						+ identInterlocutor + ",  "
						+ VocabularioGestionCitas.InfoFuncionalidad.toLowerCase() + ".  "
						+ VocabularioGestionCitas.PeticionInfoGeneral[numPeticion];
				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, "Error-AlObtener:Interfaz:"
								+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, "Error-Acceso:Interfaz:"
							+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
