package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.PreguntarDatosInicialesUsuario;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.RecomendarPelicula;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ConfigInfoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class BuscarUsuario extends TareaSincrona {

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
		 * Ya tenemos el nombre del usuario, ahora a buscarlo !
		 */
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identInterlocutor = ConfigInfoComunicacionChat.identInterlocutorPruebas;

		// identInterlocutor contiene el nombre del usuario, iniciar la base de
		// datos y buscar al usuario
		boolean exists = false;

		// Si no está, lo creas nuevo y se le responderá como nuevo usuario. Se
		// le intentará preguntar por sus datos básicos, mediante el cuestionario inicial.
		if (!exists) {
			this.getEnvioHechos().insertarHecho(new PreguntarDatosInicialesUsuario());
		}
		// Si está, coges todos sus datos y le dices que ya le conoces. Le
		// preguntas por las ultimas pelis que le pusiste y por datos básicos
		// que falten
		else {
			// AQUI PUEDE QUE TE DES CUENTA DE QUE VIO UNA PELI Y QUERRAS
			// PREGUNTARLE SI QUIERE VALORARLA (HAY QUE LANZAR EL HECHO Y METER
			// LA REGLA EN EL AGENTE GUIA)
			this.getEnvioHechos().insertarHecho(new RecomendarPelicula());
		}

		try {
			// // Se busca la interfaz del recurso en el repositorio de
			// interfaces
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
			if (recComunicacionChat != null) {
				recComunicacionChat
						.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
				// int numDespedida = (int) ((100 * Math.random()) %
				// VocabularioGestionCitas.Despedida.length);
				String mensajeAenviar = "NULL";
				if (!exists) {
					mensajeAenviar = "Hola " + identInterlocutor
							+ ", no nos conocemos.";
				} else {
					mensajeAenviar = "Hola, " + identInterlocutor
							+ " sabia que volverias!";
				}

				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(
						identDeEstaTarea,
						contextoEjecucionTarea,
						identAgenteOrdenante,
						"Error-AlObtener:Interfaz:"
								+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(
					identDeEstaTarea,
					contextoEjecucionTarea,
					identAgenteOrdenante,
					"Error-Acceso:Interfaz:"
							+ VocabularioGestionCitas.IdentRecursoComunicacionChat,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}

}
