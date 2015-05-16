package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.informacion.Busqueda;
import icaro.aplicaciones.informacion.Vocabulario;
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
		Busqueda busqueda = Vocabulario.busqueda;
		Usuario usuario = Vocabulario.usuario;
		try {
			ItfUsoComunicacionChat recComunicacionChat = (ItfUsoComunicacionChat) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionChat);
			Objetivo obj = (Objetivo) params[0];
			Objetivo objAntiguo = (Objetivo) params[1];
			if (recComunicacionChat != null) {
				recComunicacionChat.comenzar(Vocabulario.IdentAgenteAplicacionGuia);
				String mensajeAenviar = "";
				Movie movie = null;
				if (busqueda.getResult().size() > 0) {
					int numRecomienda = (int) ((100 * Math.random()) % Vocabulario.Recomienda.length);
					mensajeAenviar = Vocabulario.Recomienda[numRecomienda] + "  ";
					for (Movie m : busqueda.getResult()) {
						if (movie == null
								&& !usuario.getIdValoraciones().contains(
										Integer.toString(m.getId()))
								&& !usuario.getPeliculasOdiadas().contains(
										Integer.toString(m.getId())))
							movie = m;
					}
					if (movie != null) {
						int numParams = (int) ((100 * Math.random()) % Vocabulario.Params.length);
						// TODO parece que cuando le dices muchhas veces que no quieres ver la peli
						// salta null pointer exception en la linea de aquí abajo.
						mensajeAenviar += (movie.getTitle() + ". " + Vocabulario.Params[numParams]);
						recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
						usuario.setPeliculaActual(new Valoracion(Integer.toString(movie.getId()),
								null));
						// Si encuentra una peli pone ObtenerPelicula a Solving
						obj.setSolving();	// ObtenerPelicula
						this.getEnvioHechos().actualizarHecho(obj);
					} else {
						busqueda.setPage(busqueda.getPage()+1);
						// Si no la encuentra tiene que ejecutar la búsqueda con página siguiente
						// Pone todo a Pending
						obj.setPending();	// ObtenerPelicula
						this.getEnvioHechos().actualizarHecho(obj);
						objAntiguo.setPending();	// RecomendarPelicula
						this.getEnvioHechos().actualizarHecho(objAntiguo);
					}
				} else {
					mensajeAenviar = Vocabulario.LimpiarBusqueda;
					recComunicacionChat.enviarMensagePrivado(mensajeAenviar);
					// Si la busqueda tiene tamaño 0, tendrá que borrar borrar parametros
					// de la busqueda y poner a Pending todo
					busqueda.reset();
					obj.setPending();	// ObtenerPelicula
					this.getEnvioHechos().actualizarHecho(obj);
					objAntiguo.setPending();	// RecomendarPelicula
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
