package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ConfigInfoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

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
		/**
		 * Ya tenemos el nombre del usuario, ahora a buscarlo !
		 */
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		String identInterlocutor = ConfigInfoComunicacionChat.identInterlocutorPruebas;

		// TODO Mirar en las �ltimas peliculas que ha visto y si las N ultimas
		// son de un mismo g�nero, contando que puede haber entremedias alguna
		// diferente, suponemos que quiere ver una de ese g�nero. O podr�amos
		// contar el g�nero que m�s ha visto.
		boolean foundPreferredGenre = false;
		String preferredGenre = "NULL";

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
				if (foundPreferredGenre) {
					// TODO Si hemos encontrado un genero preferido, se lo
					// decimos.
					// Pasamos a intentar recomendarle una peli de ese g�nero o
					// lanzamos un objetivo que averigue un actor preferido.
					// Tambi�n podr�amos preguntar si has acertado o le parece
					// bien.
					// ADEMAS, DEBERIAMOS BORRAR LAS ANOTACIONES QUE TENDAMOS DE
					// SI O NO, YA QUE PODRIAMOS ESTAR GUARDANDO ALGUNA Y NOS
					// FASTIDIE LA PREGUNTA
					mensajeAenviar = "Creo que te podr�a gustar el genero"
							+ preferredGenre + ", si?";
					// TODO this.getEnvioHechos().insertarHecho(new SujeririPeliculaConLosDatosActuales());
					
				} else {
					// TODO Si no tenemos mucha idea de lo que le podr�a gustar
					// le preguntamos cu�l le apetece.
					mensajeAenviar = "Dime el genero de pelis que te apetece ver.";
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
