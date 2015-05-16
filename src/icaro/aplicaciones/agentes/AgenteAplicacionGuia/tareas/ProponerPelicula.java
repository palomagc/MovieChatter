package icaro.aplicaciones.agentes.AgenteAplicacionGuia.tareas;

import icaro.aplicaciones.agentes.AgenteAplicacionGuia.objetivos.RecomendarPelicula;
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
						usuario.setPeliculaActual(new Valoracion(Integer.toString(movie.getId()),
								null));
						// this.getEnvioHechos().insertarHecho(new ObtenerPelicula());
					} else {
						busqueda.setPage(busqueda.getPage()+1);
						this.getEnvioHechos().insertarHecho(new RecomendarPelicula());
					}
				} else {
					// TODO mejorar la frase
					mensajeAenviar = "La consulta con los parï¿½metros dados no ha obtenido ningï¿½n resultado."
							+ " Se limpian los parametros de busqueda.";
					busqueda.reset();
					this.getEnvioHechos().insertarHecho(new RecomendarPelicula());
				}
				recComunicacionChat.enviarMensagePrivado(mensajeAenviar);

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
