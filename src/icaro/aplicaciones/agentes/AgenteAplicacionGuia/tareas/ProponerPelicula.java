package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import constantes.Busqueda;
import icaro.aplicaciones.informacion.gestionCitas.VocabularioGestionCitas;
import icaro.aplicaciones.recursos.comunicacionChat.ItfUsoComunicacionChat;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.aplicaciones.recursos.recursoUsuario.model.Usuario;
import icaro.aplicaciones.recursos.recursoUsuario.model.Valoracion;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

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

		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		Busqueda busqueda = VocabularioGestionCitas.busqueda;
		Usuario usuario = VocabularioGestionCitas.usuario;
		try {
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(VocabularioGestionCitas.IdentRecursoComunicacionChat);
			//Objetivo objAntiguo = (Objetivo) params[0];
			if (recComunicacionChat != null) {
				recComunicacionChat.comenzar(VocabularioGestionCitas.IdentAgenteAplicacionGuia);
				String mensajeAenviar = "";
				Movie movie = null;
				if (busqueda.getResult().size() > 0) {
					int numRecomienda = (int) ((100 * Math.random()) % VocabularioGestionCitas.Recomienda.length);
					mensajeAenviar = VocabularioGestionCitas.Recomienda[numRecomienda] + "  ";
					for (Movie m : busqueda.getResult()) {
						if (movie == null
								&& !usuario.getIdValoraciones().contains(
										Integer.toString(m.getId()))
								&& !usuario.getPeliculasOdiadas().contains(
										Integer.toString(m.getId())))
							movie = m;
					}
					int numParams = (int) ((100 * Math.random()) % VocabularioGestionCitas.Params.length);
					// TODO parece que cuando le dices muchhas veces que no quieres ver la peli salta null pointer exception en la linea de aquí abajo.
					mensajeAenviar += (movie.getTitle() + ". " + VocabularioGestionCitas.Params[numParams]);
					usuario.setPeliculaActual(new Valoracion(Integer.toString(movie.getId()), null));
				} else {
					// TODO mejorar la frase
					mensajeAenviar = "La consulta con los parï¿½metros dados no ha obtenido ningï¿½n resultado."
							+ " Se limpian los parametros de busqueda.";
					busqueda.reset();
					//objAntiguo.setPending();
					//this.getEnvioHechos().actualizarHecho(objAntiguo);
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
