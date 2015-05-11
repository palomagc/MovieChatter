package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.PreguntarDatosInicialesUsuario;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.PreguntarEdad;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.PreguntarSexo;
import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.RecomendarPelicula;
import icaro.aplicaciones.informacion.gestionCitas.Notificacion;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ConfigInfoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

import java.util.ArrayList;

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
		ItfUsoRecursoUsuario itfUsoRecursoUsuario = null;
		try {
			itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		try {
			VocabularioGestionCitas.usuario = itfUsoRecursoUsuario
					.buscarUsuario(identInterlocutor);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (VocabularioGestionCitas.usuario != null) {
			exists = true;
		}

		// Si no est�, lo creas nuevo y se le responder� como nuevo usuario. Se
		// le intentar� preguntar por sus datos b�sicos, mediante el
		// cuestionario inicial.
		if (!exists) {
			this.getEnvioHechos().insertarHecho(
					new PreguntarDatosInicialesUsuario());
			// Creamos un nuevo usuario con sexo a null y edad a -1
			try {
				VocabularioGestionCitas.usuario = itfUsoRecursoUsuario.crearUsuario(
						identInterlocutor, null, -1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Si está, coges todos sus datos. Le preguntas por las ultimas
		// pelis que le pusiste y por datos básicos que falten
		else {
			// AQUI PUEDE QUE TE DES CUENTA DE QUE VIO UNA PELI Y QUERRAS
			// PREGUNTARLE SI QUIERE VALORARLA (HAY QUE LANZAR EL HECHO Y METER
			// LA REGLA EN EL AGENTE GUIA)
			ArrayList<Valoracion> valoraciones = VocabularioGestionCitas.usuario.getValoraciones();
			if(VocabularioGestionCitas.usuario.getSexo() == null){
				this.getEnvioHechos().insertarHecho(
						new PreguntarSexo());
			}
			else if(VocabularioGestionCitas.usuario.getEdad() == -1){
				this.getEnvioHechos().insertarHecho(
						new PreguntarEdad());
			}
			// TODO esto se podría mejorar. Si la última película sí está valorada, podemos buscar
			// la anterior película que está sin valorar. En caso de que no la quiera valorar, le podemos
			// asignar nota=-2 para saber que no hay que volver a preguntarle por esta peli
			else if(valoraciones.get(valoraciones.size()-1).getNota() == -1){
				Notificacion notif = new Notificacion();
		 		notif.setTipoNotificacion(VocabularioGestionCitas.NombreTipoNotificacionValorarUltimaPelicula);
			}
			else{
				this.getEnvioHechos().insertarHecho(new RecomendarPelicula());
			}
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
