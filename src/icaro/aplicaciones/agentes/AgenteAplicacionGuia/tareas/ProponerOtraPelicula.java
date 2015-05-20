package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.Vocabulario;
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
		Usuario usuario = Vocabulario.usuario;
		try {
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionChat);
			ItfUsoRecursoUsuario itfUsoRecursoUsuario = (ItfUsoRecursoUsuario) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoUsuario);
			Objetivo objAntiguo = (Objetivo) params[1];
			if (recComunicacionChat != null && itfUsoRecursoUsuario != null
					&& usuario.getPeliculaActual() != null) {
				if (notifica.equals(Vocabulario.NombreTipoNotificacionNegacion)) {
					// TODO hay que quitar el objeto usuario estatico y utilizar solo el recurso
					// para evitar fallos
					// itfUsoRecursoUsuario.nuevaPeliculaOdiada(usuario.getNombre(),
					// usuario.getPeliculaActual().getIdPelicula());
					itfUsoRecursoUsuario.nuevaPeliculaOdiada(usuario.getNombre(), usuario
							.getPeliculaActual().getIdPelicula());
					usuario.addPeliculaOdiada(usuario.getPeliculaActual().getIdPelicula());
					usuario.setPeliculaActual(null);

					objAntiguo.setPending();	// RecomendarPelicula
					this.getEnvioHechos().actualizarHecho(objAntiguo);
				} else if (notifica
						.equals(Vocabulario.NombreTipoNotificacionAfirmacion)) {
					itfUsoRecursoUsuario.nuevaValoracion(Vocabulario.usuario.getNombre(), new Valoracion(usuario.getPeliculaActual().getIdPelicula(), null));
					usuario.setPeliculaActual(null);
					Vocabulario.busqueda.reset();
					int numDespedida = (int) ((100 * Math.random()) % Vocabulario.Despedida.length);
					int numDisfruta = (int) ((100 * Math.random()) % Vocabulario.Disfruta.length);
					String mensajeAenviar = Vocabulario.Disfruta[numDisfruta] + "  "
							+ Vocabulario.Despedida[numDespedida];
					recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					objAntiguo.setSolved();	// RecomendarPelicula
					this.getEnvioHechos().actualizarHecho(objAntiguo);
				}
			} else {
				identAgenteOrdenante = this.getAgente().getIdentAgente();
				this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
						identAgenteOrdenante, "Error-AlObtener:Interfaz:"
								+ Vocabulario.IdentRecursoComunicacionTMDB,
						CausaTerminacionTarea.ERROR);
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, "Error-Acceso:Interfaz:"
							+ Vocabulario.IdentRecursoComunicacionTMDB,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
