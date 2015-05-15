package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.recursoUsuario.ItfUsoRecursoUsuario;
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
		Usuario usuario = VocabularioGestionCitas.usuario;
		try {
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoUsuario);
			Objetivo objAntiguo = (Objetivo) params[1];
			if (recComunicacionChat != null && itfUsoRecursoUsuario != null
					&& usuario.getPeliculaActual() != null) {
				if (notifica.equals(VocabularioGestionCitas.NombreTipoNotificacionNegacion)) {
					// TODO hay que quitar el objeto usuario estatico y utilizar solo el recurso
					// para evitar fallos
					// itfUsoRecursoUsuario.nuevaPeliculaOdiada(usuario.getNombre(),
					// usuario.getPeliculaActual().getIdPelicula());
					itfUsoRecursoUsuario.nuevaPeliculaOdiada(usuario.getNombre(), usuario
							.getPeliculaActual().getIdPelicula());
					usuario.addPeliculaOdiada(usuario.getPeliculaActual().getIdPelicula());
					usuario.setPeliculaActual(null);

					objAntiguo.setPending();
					this.getEnvioHechos().actualizarHecho(objAntiguo);
				} else if (notifica
						.equals(VocabularioGestionCitas.NombreTipoNotificacionAfirmacion)) {
					usuario.addValoracion(new Valoracion(usuario.getPeliculaActual()
							.getIdPelicula(), null));
					usuario.setPeliculaActual(null);
					VocabularioGestionCitas.busqueda.reset();
					int numDespedida = (int) ((100 * Math.random()) % VocabularioGestionCitas.Despedida.length);
					int numDisfruta = (int) ((100 * Math.random()) % VocabularioGestionCitas.Disfruta.length);
					String mensajeAenviar = VocabularioGestionCitas.Disfruta[numDisfruta] + "  "
							+ VocabularioGestionCitas.Despedida[numDespedida];
					recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					// objAntiguo.setSolved();
					// this.getEnvioHechos().actualizarHecho(objAntiguo);
				}
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
