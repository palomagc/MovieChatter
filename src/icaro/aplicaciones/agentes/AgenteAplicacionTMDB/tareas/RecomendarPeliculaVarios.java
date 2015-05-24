package icaro.aplicaciones.agentes.AgenteAplicacionTMDB.tareas;

import java.util.ArrayList;
import java.util.List;

import icaro.aplicaciones.informacion.Busqueda;
import icaro.aplicaciones.informacion.Notificacion;
import icaro.aplicaciones.informacion.Vocabulario;
import icaro.aplicaciones.recursos.comunicacionTMDB.ItfUsoComunicacionTMDB;
import icaro.aplicaciones.recursos.comunicacionTMDB.model.Movie;
import icaro.infraestructura.entidadesBasicas.NombresPredefinidos;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.CausaTerminacionTarea;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.Objetivo;
import icaro.infraestructura.entidadesBasicas.procesadorCognitivo.TareaSincrona;

public class RecomendarPeliculaVarios extends TareaSincrona {

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
		 * Recomienda una película de un género
		 */
		String identDeEstaTarea = this.getIdentTarea();
		String identAgenteOrdenante = this.getIdentAgente();
		// String notifica = (String) params[0];
		// String param = (String) params[1];
		String year = null;
		List<Integer> genres = new ArrayList<>();
		List<Integer> people = new ArrayList<>();
		Busqueda busqueda = Vocabulario.busqueda;

		try {
			ItfUsoComunicacionTMDB itfUsoComunicacionTMDB = (ItfUsoComunicacionTMDB) NombresPredefinidos.REPOSITORIO_INTERFACES_OBJ
					.obtenerInterfazUso(Vocabulario.IdentRecursoComunicacionTMDB);

			List<Movie> movies = new ArrayList<Movie>();
			if (itfUsoComunicacionTMDB != null) {
				if (busqueda.getYear() != null) {
					year = busqueda.getYear();
				}
				if (busqueda.getPeople() != null && busqueda.getPeople().size() > 0) {
					people = busqueda.getPeople();
				}
				if (busqueda.getGenres() != null && busqueda.getGenres().size() > 0) {
					genres = busqueda.getGenres();
				}
				/*
				 * Date after, Date before, String sort, Float minAverage, Integer minCount,
				 * List<Integer> genres, List<Integer> people, String year, String language, int
				 * page
				 */
				movies = itfUsoComunicacionTMDB.discoverMovies(null, null, busqueda.getSort(),
						null, null, genres, people, year, busqueda.getLanguage(),
						busqueda.getPage());
				if (movies != null) {
					busqueda.setResult(movies);
					// this.getEnvioHechos().insertarHecho(new ObtenerPelicula());
					// TODO poner notificacion porque es para otro agente
					Notificacion infoAenviar = new Notificacion();
					infoAenviar
							.setTipoNotificacion(Vocabulario.NombreTipoNotificacionComprobarDatosBusqueda);
					getComunicator().enviarInfoAotroAgente(infoAenviar,
							Vocabulario.IdentAgenteAplicacionGuia);
				}
			}
		} catch (Exception e) {
			this.generarInformeConCausaTerminacion(identDeEstaTarea, contextoEjecucionTarea,
					identAgenteOrdenante, Vocabulario.ErrorAccesoInterfaz
							+ Vocabulario.IdentRecursoComunicacionTMDB,
					CausaTerminacionTarea.ERROR);
			e.printStackTrace();
		}
	}
}
